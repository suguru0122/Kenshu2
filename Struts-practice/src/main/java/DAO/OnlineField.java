package DAO;

public class OnlineField {
	// ���i(object)�e�[�u���ւ̊e�t�B�[���h
		Integer objectid;	// ��ԍ�
		String objectnumber;// ���i�ԍ�
		Integer price;		// ���i���i
		String objectname;	// ���i��
		String detail;		// ���i�ڍ�
		
		
		// ������(member)�e�[�u���ւ̊e�t�B�[���h
		Integer memberid;	// ����ԍ�
		String userid;		// ���[�U�[ID
		String userpass;	// ���[�U�[�̃p�X���[�h
		String username;	// ���[�U�[�̖��O

		// �J�[�g���(cart)�e�[�u���ւ̊e�t�B�[���h
		Integer cartid;		// �J�[�g�ɓ����Ă��鏤�i�ԍ�
		Integer cartprice;	// �J�[�g�ɓ����Ă��鏤�i���i
		String cartname;	// �J�[�g�ɓ����Ă��鏤�i����
		String cartdetail;	// �J�[�g�ɓ����Ă��鏤�i�̏ڍ�
		
		// ���i(object)�e�[�u���̃Z�b�^
		public void setObjectid(Integer objectid){
			this.objectid = objectid;
		}
		public void setObjectnumber(String objectnumber){
			this.objectnumber = objectnumber;
		}
		public void setPrice(Integer price){
			this.price = price;
		}
		public void setObjectname(String objectname){
			this.objectname = objectname;
		}
		public void setDetail(String detail){
			this.detail = detail;
		}
		
		/*
		// ���(member)�e�[�u���̃Z�b�^	
		public void setMemberId(Integer memberid){
			this.memberid = memberid;
		}
		public void setUserId(String userid){
			this.userid = userid;
		}
		public void setUserPass(String userpass){
			this.userpass = userpass;
		}
		public void setUserName(String username){
			this.username = username;
		}*/
		
		//	���i(object)�e�[�u���̃Q�b�^
		public Integer getObjectid(){
			return this.objectid;
		}
		public String getObjectnumber(){
			return this.objectnumber;
		}
		public Integer getPrice(){
			return this.price;
		}
		public String getObjectname(){
			return this.objectname;
		}
		public String getDetail(){
			return this.detail;
		}
		/*
		// ���(member)�e�[�u���̃Q�b�^	
		 
		public Integer getMemberId(){
			return this.memberid;
		}
		public String getUserId(){
			return this.userid;
		}
		public String getUserPass(){
			return this.userpass;
		}
		public String setUserName(){
			return this.username;
		}
		
		// ���O�C�����ɁA�f�[�^�x�[�X�ɓo�^�������ƈ�v���Ă��邩�𔻒f���郁�\�b�h
		/*String result = "";
		public String checkLogin(String id,String pass){
			if(id.equals(id) && pass.equals(pass) ){
				result = "true";
			}else{
				result = "false";
			}
			return result;
		}*/
	
		// �J�[�g(cart)�e�[�u���̃Z�b�^
		public void setCartid(Integer cartid){
			this.cartid = cartid;
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
		
		// �J�[�g(cart)�e�[�u���̃Q�b�^
		public Integer getCartid(){
			return this.cartid;
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
		
}