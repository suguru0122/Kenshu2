package DAO;

import java.sql.Timestamp;

public class OnlineField {
	// 商品(object)テーブルへの各フィールド
	Integer objectid;		// 列番号
	String objectnumber;	// 商品番号
	Integer objectprice;	// 商品価格
	String objectname;		// 商品名
	String objectdetail;	// 商品詳細
		
	// 会員情報(member)テーブルへの各フィールド
	String customer_id;				// ユーザーID
	String customer_pass;			// ユーザーのパスワード
	Timestamp customer_orderday;	// ユーザーの商品発注日

	// 管理者(manager)テーブルへの各フィールド
	String admin_id;
	String admin_pass;
	
	// 管理者登録用のフィールド
	String add_admin_id;
	String add_admin_pass;
	
	// カート情報(cart)テーブルへの各フィールド
	String cusid;		// カートに入れたユーザーのID
	String cartnum;		// カートに入っている商品番号
	Integer cartprice;	// カートに入っている商品の価格
	String cartname;	// カートに入っている商品の名称
	String cartdetail;	// カートに入っている商品の詳細
	Timestamp orderday;	// カートに入っている商品を注文した日
	
	// 新規商品情報を格納するためのフィールド
	String newnumber;
	Integer newprice;
	String newname;
	String newdetail;
	
	// 商品(object)テーブルのセッタ
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
		
	// 会員(member)テーブルのセッタ	
	public void setCustomerid(String customer_id){
		this.customer_id = customer_id;
	}
	public void setCustomerpass(String customer_pass){
		this.customer_pass = customer_pass;
	}
	
	// 管理者登録用のセッター
	public void setAddadminid(String add_admin_id){
		this.add_admin_id = add_admin_id;
	}
	public void setAddadminpass(String add_admin_pass){
		this.add_admin_pass = add_admin_pass;
	}
	
	// 管理者(manager)テーブルのセッタ
	public void setAdminid(String admin_id){
		this.admin_id = admin_id;
	}
	public void setAdminpass(String admin_pass){
		this.admin_pass = admin_pass;
	}
	
	// 新規商品情報を格納するためのセッタ
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
	
	//	商品(object)テーブルのゲッタ
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
		
	// 会員(member)テーブルのゲッタ	
	public String getCustomerid(){
		return this.customer_id;
	}
	public String getCustomerpass(){
		return this.customer_pass;
	}
	
	// 管理者(manager)テーブルのゲッタ
	public String getAdminid(){
		return this.admin_id;
	}
	public String getAdminpass(){
		return this.admin_pass;
	}
	
	// 管理者登録用のゲッター
	public String getAddadminid(){
		return this.add_admin_id;
	}
	public String getAddadminpass(){
		return this.add_admin_pass;
	}
	
	// カート(cart)テーブルのセッタ
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
		
	// カート(cart)テーブルのゲッタ
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
	
	// 新規商品情報を格納するためのゲッタ
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