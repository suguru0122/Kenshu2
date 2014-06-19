package action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import DAO.OnlineDataDAO;
import DAO.OnlineField;

@Results({ 
	@Result(name="success",location="Complete.jsp"),
})
public class InsertMemberAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String userid;
	private String userpass;

	public String md5(String userpass) {
		String rtn = "";
		try {
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(userpass.getBytes());
		    byte[] digest = md.digest();

		    //ダイジェストを文字列に変換します。
		    for (int i = 0; i < digest.length; i++) {
		        rtn += String.format("%02x", digest[i]);
		    }
		} catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();
		}
		return rtn;
	}
	
	// ユーザーの登録処理
	@Action("/Insert")
	public String returnAdd() throws Exception{
		
		// VOへ格納する。
		OnlineField o = new OnlineField();
		o.setCustomerid(userid);
		String s = md5(userpass);
		o.setCustomerpass(s);
		
		// DAOの取得
		OnlineDataDAO dao = new OnlineDataDAO();
		try{
			// 更新処理を行う
			dao.getConnection();
			dao.getAdd(o);
			return "success";
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			// DAOの処理が完了したら接続を閉じる
			dao.closeConnection();
		}
	}
	
	public void setHttpServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public HttpServletRequest getHttpServletRequest(){
		return this.request;
	}
	public void setHttpServletResponse(HttpServletResponse response){
		this.response = response;
	}
	public HttpServletResponse getHttpServletResponse(){
		return this.response;
	}
	public void setUserid(String userid){
		this.userid = userid;
	}
	public String getUserid(){
		return this.userid;
	}
	public void setUserpass(String userpass){
		this.userpass = userpass;
	}
	public String getUserpass(){
		return this.userpass;
	}

}
