package action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.struts2.convention.annotation.Action;

import DAO.OnlineDataDAO;
import DAO.OnlineField;

/*@Results({ 
	@Result(name="success",location="List.jsp")
})*/
public class OnlineAction extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private List<OnlineField> blist;
	private String userid;
	
	@Action("/List")
	public String returnList() throws Exception{
		
		OnlineDataDAO dao = new OnlineDataDAO(); 
		
		try{
			dao.getConnection();
			blist = dao.getList();
			return ("success");
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			dao.closeConnection();
		}
	}
	
	public void setBlist(List<OnlineField> blist){
		this.blist = blist;
	}
	public List<OnlineField> getBlist(){
		return this.blist;
	}
	public void setUserid(String userid){
		this.userid = userid;
	}
	public String getUserid(){
		return this.userid;
	}
}