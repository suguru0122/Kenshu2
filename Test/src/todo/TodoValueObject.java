package todo;

import java.sql.Timestamp;

public class TodoValueObject {

	Integer id;
	String title;
	String task;
	Timestamp limitdate;
	Timestamp lastupdate;
	String userid;
	String label;
	Integer status;
	String inputlimitdate;
	private String filename;

	// セッタ
	public void setId(Integer id){
		this.id = id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setTask(String task){
		this.task = task;
	}
	public void setLimitdate(Timestamp limitdate){
		this.limitdate = limitdate;
	}
	public void setLastupdate(Timestamp lastupdate){
		this.lastupdate = lastupdate;
	}
	public void setUserid(String userid){
		this.userid = userid;
	}
	public void setLabel(String label){
		this.label = label;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public void setInputLimitdate(String inputlimitdate){
		this.inputlimitdate = inputlimitdate;
	}
	public void setFilename(String filename){
		this.filename = filename;
	}
	

	// ゲッタ
	public Integer getId(){
		return this.id;
	}
	public String getTitle(){
		return this.title;
	}
	public String getTask(){
		return this.task;
	}
	public Timestamp getLimitdate(){
		return this.limitdate;
	}
	public Timestamp getLastupdate(){
		return this.lastupdate;
	}
	public String getUserid(){
		return this.userid;
	}
	public String getLabel(){
		return this.label;
	}
	public Integer getStatus(){
		return this.status;
	}
	public String getInputLimit(){
		return this.inputlimitdate;
	}
	public String getFilename(){
		return this.filename;
	}

}