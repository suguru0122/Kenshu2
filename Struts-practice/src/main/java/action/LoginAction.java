package action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import DAO.OnlineDataDAO;
import DAO.OnlineField;

@Results({ 
	@Result(name="success",location="List.jsp"),
	@Result(name="error",location="Login_error.jsp"),
	@Result(name="errorManager",location="ErrorManager.jsp")
})
public class LoginAction extends HttpServlet implements ServletRequestAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private List<OnlineField> blist;
	
	private String userid;
	private String userpass;
	private String check;
	
	private String managerid;
	private String managerpass;
	private String managercheck;
	
	public String md5(String pass) {
		String rtn = "";
		try {
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(pass.getBytes());
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
	
	@Action("/LoginCheck")
	public String checkLogin() throws Exception {
		OnlineDataDAO dao = new OnlineDataDAO();
		
		try{
			// 認証に対する処理
			dao.getConnection();
			check = dao.checkPass(userid);
			String s = md5(userpass);
			
			if(check.equals(s)){
				blist = dao.getList();
				HttpSession session = request.getSession();
				session.setAttribute("USER",userid);
				return "success";
			}else{
				return "error";
			}
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			dao.closeConnection();
		}
	}
	
	public String managerLogin() throws Exception{
		OnlineDataDAO dao = new OnlineDataDAO();
		
		try{
			// 認証に対する処理
			dao.getConnection();
			managercheck = dao.checkManagerpass(managerid);
			String m = md5(managerpass);
			
			if(managercheck.equals(m)){
				blist = dao.getList();
				HttpSession session = request.getSession();
				session.setAttribute("MANAGER",managerid);
				return "success";
			}else{
				return "errorManager";
			}
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			dao.closeConnection();
		}
	}
	
	public void setCheck(String check){
		this.check = check;
	}
	public String getCheckUser(){
		return this.check;
	}
	public void setManagercheck(String managercheck){
		this.managercheck = managercheck;
	}
	public String getManagercheck(){
		return this.managercheck;
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
	public void setBlist(List<OnlineField> blist){
		this.blist = blist;
	}
	public List<OnlineField> getBlist(){
		return this.blist;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setManagerid(String managerid){
		this.managerid = managerid;
	}
	public String getManagerid(){
		return this.managerid;
	}
	public void setManagerpass(String managerpass){
		this.managerpass = managerpass;
	}
	public String getManagerpass(){
		return this.managerpass;
	}
}