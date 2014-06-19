package action;

import java.util.List;

import javax.servlet.ServletException;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import DAO.OnlineDataDAO;
import DAO.OnlineField;

@Results({ 
	@Result(name="AddObj",location="List.jsp"),
})
public class AddObjectAction {

	private String number;
	private int price;
	private String name;
	private String detail;
	private List<OnlineField> blist;
	
	// ���[�U�[�̓o�^����
	@Action("/AddObject")
	public String plusAddObject() throws Exception{
		
		// VO�֊i�[����B
		OnlineField o = new OnlineField();
		o.setNewnu(number);
		o.setNewpr(price);
		o.setNewna(name);
		o.setNewde(detail);
			
		// DAO�̎擾
		OnlineDataDAO dao = new OnlineDataDAO();
		try{
			// �X�V�������s��
			dao.getConnection();
			dao.getAddobject(o);
			blist = dao.getList();
			return "AddObj";
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			// DAO�̏���������������ڑ������
			dao.closeConnection();
		}
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	public String getNumber(){
		return this.number;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public int getPrice(){
		return this.price;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setDetail(String detail){
		this.detail = detail;
	}
	public String getDetail(){
		return this.detail;
	}
	public void setBlist(List<OnlineField> blist){
		this.blist = blist;
	}
	public List<OnlineField> getBlist(){
		return this.blist;
	}
}
