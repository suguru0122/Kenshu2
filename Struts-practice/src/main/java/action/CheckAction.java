package action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

@Results({ @Result(name="result",location="result.jsp") })
public class CheckAction implements ServletRequestAware{
	private String name;
	private String age;
	private String sex;
	
	private HttpServletRequest request;
	
	@Action("/Check")
	public String checkPersonalInfo(){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String new_name;
		
		if("male".equals(sex) == true){
			new_name = "Mr." + name;
		}else{
			new_name = "Ms." + name;
		}
		
		// �V����(Mr./Ms.������)���O��Atribute�Œǉ�
		request.setAttribute("new_name",new_name);
		
		// HttpSession�̍쐬
		HttpSession session = request.getSession(true);
		session.setAttribute("prev_name",name);
		
		return "result";
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getAge(){
		return age;
	}
	
	public void setAge(String age){
		this.age = age;
	}
	
	public String getSex(){
		return sex;
	}
	
	public void setSex(String sex){
		this.sex = sex;
	}
	
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
}