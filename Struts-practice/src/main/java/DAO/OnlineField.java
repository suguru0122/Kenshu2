package DAO;

import java.sql.Timestamp;

public class OnlineField {
	// ���i(object)�e�[�u���ւ̊e�t�B�[���h
	Integer objectid;		// ��ԍ�
	String objectnumber;	// ���i�ԍ�
	Integer objectprice;	// ���i���i
	String objectname;		// ���i��
	String objectdetail;	// ���i�ڍ�
		
	// ������(member)�e�[�u���ւ̊e�t�B�[���h
	String customer_id;				// ���[�U�[ID
	String customer_pass;			// ���[�U�[�̃p�X���[�h
	Timestamp customer_orderday;	// ���[�U�[�̏��i������

	// �Ǘ���(manager)�e�[�u���ւ̊e�t�B�[���h
	String admin_id;
	String admin_pass;
	
	// �Ǘ��ғo�^�p�̃t�B�[���h
	String add_admin_id;
	String add_admin_pass;
	
	// �J�[�g���(cart)�e�[�u���ւ̊e�t�B�[���h
	String cusid;		// �J�[�g�ɓ��ꂽ���[�U�[��ID
	String cartnum;		// �J�[�g�ɓ����Ă��鏤�i�ԍ�
	Integer cartprice;	// �J�[�g�ɓ����Ă��鏤�i�̉��i
	String cartname;	// �J�[�g�ɓ����Ă��鏤�i�̖���
	String cartdetail;	// �J�[�g�ɓ����Ă��鏤�i�̏ڍ�
	Timestamp orderday;	// �J�[�g�ɓ����Ă��鏤�i�𒍕�������
	
	// �V�K���i�����i�[���邽�߂̃t�B�[���h
	String newnumber;
	Integer newprice;
	String newname;
	String newdetail;
	
	// ���i(object)�e�[�u���̃Z�b�^
	public void setObjectid(Integer objectid){
		this.objectid = objectid;
	}
	public void setObjectnumber(String objectnumber){
		this.objectnumber = objectnumber;
	}
	public void setObjectprice(Integer objectprice){
		this.objectprice = objectprice;
	}
	public void setObjectname(String objectname){
		this.objectname = objectname;
	}
	public void setObjectdetail(String objectdetail){
		this.objectdetail = objectdetail;
	}
		
	// ���(member)�e�[�u���̃Z�b�^	
	public void setCustomerid(String customer_id){
		this.customer_id = customer_id;
	}
	public void setCustomerpass(String customer_pass){
		this.customer_pass = customer_pass;
	}
	
	// �Ǘ��ғo�^�p�̃Z�b�^�[
	public void setAddadminid(String add_admin_id){
		this.add_admin_id = add_admin_id;
	}
	public void setAddadminpass(String add_admin_pass){
		this.add_admin_pass = add_admin_pass;
	}
	
	// �Ǘ���(manager)�e�[�u���̃Z�b�^
	public void setAdminid(String admin_id){
		this.admin_id = admin_id;
	}
	public void setAdminpass(String admin_pass){
		this.admin_pass = admin_pass;
	}
	
	// �V�K���i�����i�[���邽�߂̃Z�b�^
	public void setNewnu(String newnumber){
		this.newnumber = newnumber;
	}
	public void setNewpr(Integer newprice){
		this.newprice = newprice;
	}
	public void setNewna(String newname){
		this.newname = newname;
	}
	public void setNewde(String newdetail){
		this.newdetail = newdetail;
	}
	
	//	���i(object)�e�[�u���̃Q�b�^
	public Integer getObjectid(){
		return this.objectid;
	}
	public String getObjectnumber(){
		return this.objectnumber;
	}
	public Integer getObjectprice(){
		return this.objectprice;
	}
	public String getObjectname(){
		return this.objectname;
	}
	public String getObjectdetail(){
		return this.objectdetail;
	}
		
	// ���(member)�e�[�u���̃Q�b�^	
	public String getCustomerid(){
		return this.customer_id;
	}
	public String getCustomerpass(){
		return this.customer_pass;
	}
	
	// �Ǘ���(manager)�e�[�u���̃Q�b�^
	public String getAdminid(){
		return this.admin_id;
	}
	public String getAdminpass(){
		return this.admin_pass;
	}
	
	// �Ǘ��ғo�^�p�̃Q�b�^�[
	public String getAddadminid(){
		return this.add_admin_id;
	}
	public String getAddadminpass(){
		return this.add_admin_pass;
	}
	
	// �J�[�g(cart)�e�[�u���̃Z�b�^
	public void setCusid(String cusid){
		this.cusid = cusid;
	}
	public void setCartnum(String cartnum){
		this.cartnum = cartnum;
	}
	public void setCartprice(Integer cartprice){
		this.cartprice = cartprice;
	}
	public void setCartname(String cartname){
		this.cartname = cartname;
	}
	public void setCartdetail(String cartdetail){
		this.cartdetail = cartdetail;
	}
	public void setOrderday(Timestamp orderday){
		this.orderday = orderday;
	}
		
	// �J�[�g(cart)�e�[�u���̃Q�b�^
	public String getCusid(){
		return this.cusid;
	}
	public String getCartnum(){
		return this.cartnum;
	}
	public Integer getCartprice(){
		return this.cartprice;
	}
	public String getCartname(){
		return this.cartname;
	}
	public String getCartdetail(){
		return this.cartdetail;
	}
	public Timestamp getOrderday(){
		return this.orderday;
	}
	
	// �V�K���i�����i�[���邽�߂̃Q�b�^
	public String getNewnumber(){
		return this.newnumber;
	}
	public Integer getNewprice(){
		return this.newprice;
	}
	public String getNewname(){
		return this.newname;
	}
	public String getNewdetail(){
		return this.newdetail;
	}
}