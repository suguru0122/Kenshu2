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
	// �t�B���^�̎�����
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws IOException,ServletException{
		HttpServletRequest request = (HttpServletRequest)req;
		// HTTP GET�v���ł���΁A�g�[�N����V�K�ɐ���
		if(request.getMethod().equals("GET")){
			createToken(request);
		}else{
			// HTTP POST�v���ł���΃g�[�N���̈�v���`�F�b�N
			if(!checkToken(request)){
				throw new ServletException("�s���ȃA�N�Z�X���s���܂����B");
			}
		}
		chain.doFilter(req,res);
	}
	
	public void init(FilterConfig config) throws ServletException{}
	public void destroy(){}

	// �g�[�N���𐶐�����ׂ̃��\�b�h
	private void createToken(HttpServletRequest request){
		MessageDigest md = null;
		HttpSession session = request.getSession();
		try{
			// �Z�b�V����id�����md5�n�b�V���𐶐�
			md = MessageDigest.getInstance("MD5");
			md.update(session.getId().getBytes());
			// �o�C�g�f�[�^��16�i��������ɕϊ����Z�b�V��������token�ɐݒ�
			session.setAttribute("token",toHex(md.digest()));
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
	}
	
	// ���M���ꂽ�g�[�N���ƃA�v���P�[�V�������̃g�[�N������v���Ă��邩���`�F�b�N
	private boolean checkToken(HttpServletRequest request){
		HttpSession session = request.getSession();
		String s_token = (String)session.getAttribute("token");
		String r_token = request.getParameter("token");
		// ���������g�[�N������̏ꍇ�́A�s���ȃg�[�N���ƌ��Ȃ�
		if(s_token == null || r_token == null || r_token.isEmpty()){
			return false;
		}
		// �g�[�N������v���Ă��邩��Ԃ�
		return s_token.equals(r_token);
	}
	
	// �o�C�g�f�[�^��16�i���̕�����ɕϊ�
	private String toHex(byte[] digest){
		StringBuffer buff = new StringBuffer();
		for(int i = 0; i < digest.length; i++){
			buff.append(Integer.toHexString((digest[i] >> 4) & 0x0F));
			buff.append(Integer.toHexString(digest[i] & 0x0F));
		}
		return buff.toString();
	}
}
