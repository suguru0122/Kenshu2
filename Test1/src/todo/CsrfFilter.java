package todo;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/*")
public class CsrfFilter implements Filter{
	// フィルタの実処理
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws IOException,ServletException{
		HttpServletRequest request = (HttpServletRequest)req;
		// HTTP GET要求であれば、トークンを新規に生成
		if(request.getMethod().equals("GET")){
			createToken(request);
		}else{
			// HTTP POST要求であればトークンの一致をチェック
			if(!checkToken(request)){
				throw new ServletException("不正なアクセスが行われました。");
			}
		}
		chain.doFilter(req,res);
	}
	
	public void init(FilterConfig config) throws ServletException{}
	public void destroy(){}

	// トークンを生成する為のメソッド
	private void createToken(HttpServletRequest request){
		MessageDigest md = null;
		HttpSession session = request.getSession();
		try{
			// セッションidを基にmd5ハッシュを生成
			md = MessageDigest.getInstance("MD5");
			md.update(session.getId().getBytes());
			// バイトデータを16進数文字列に変換＆セッション属性tokenに設定
			session.setAttribute("token",toHex(md.digest()));
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
	}
	
	// 送信されたトークンとアプリケーション側のトークンが一致しているかをチェック
	private boolean checkToken(HttpServletRequest request){
		HttpSession session = request.getSession();
		String s_token = (String)session.getAttribute("token");
		String r_token = request.getParameter("token");
		// そもそもトークンが空の場合は、不正なトークンと見なす
		if(s_token == null || r_token == null || r_token.isEmpty()){
			return false;
		}
		// トークンが一致しているかを返す
		return s_token.equals(r_token);
	}
	
	// バイトデータを16進数の文字列に変換
	private String toHex(byte[] digest){
		StringBuffer buff = new StringBuffer();
		for(int i = 0; i < digest.length; i++){
			buff.append(Integer.toHexString((digest[i] >> 4) & 0x0F));
			buff.append(Integer.toHexString(digest[i] & 0x0F));
		}
		return buff.toString();
	}
}
