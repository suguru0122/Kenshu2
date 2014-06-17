package action;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import DAO.OnlineDataDAO;
import DAO.OnlineField;


@Results({ 
	@Result(name="addtocart",location="List.jsp"),
	@Result(name="checkout",location="Checkout.jsp")
})
public class AddToCartAction extends HttpServlet implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	
	private List<OnlineField> blist;
	
	private HttpServletRequest request;
	private String[] selecteditems;
	
	OnlineDataDAO dao = new OnlineDataDAO();
	
	public void setBlist(List<OnlineField> blist){
		this.blist = blist;
	}
	public List<OnlineField> getBlist(){
		return this.blist;
	}
	
	public void setSelecteditems(String[] selecteditems){
		this.selecteditems = selecteditems;
	}
	public String[] getSelecteditems(){
		return this.selecteditems;
	}
	
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public HttpServletRequest getHttpServletRequest(){
		return this.request;
	}
	
	@Action("/AddToCart")
	public String addTocart() throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("CART");
		
		if(selecteditems != null && selecteditems.length != 0){
			List<String> cart = Arrays.asList(selecteditems);
			session.setAttribute("CART",cart);
		}
		
		try{
			dao.getConnection();
			blist = dao.getList();
			return ("addtocart");
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			dao.closeConnection();
		}
	}
	
	public String checkout() throws Exception{
		
		HttpSession session = request.getSession();
		List<String> cart = (List<String>)session.getAttribute("CART");
		String[] c =  (String[])cart.toArray(new String[0]);
		String sqlcart = "";
		for(String ISBN : c){
			sqlcart += "'" + ISBN + "'" + ",";
		}
		
		try{
			dao.getConnection();
			Long total = (Long)dao.getCartlist(sqlcart);
			request.setAttribute("TOTAL",total);
			return ("checkout");
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			dao.closeConnection();
		}
	}
}