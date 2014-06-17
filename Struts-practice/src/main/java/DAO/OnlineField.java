package DAO;

public class OnlineField {
	// 商品(object)テーブルへの各フィールド
		Integer objectid;	// 列番号
		String objectnumber;// 商品番号
		Integer price;		// 商品価格
		String objectname;	// 商品名
		String detail;		// 商品詳細
		
		
		// 会員情報(member)テーブルへの各フィールド
		Integer memberid;	// 会員番号
		String userid;		// ユーザーID
		String userpass;	// ユーザーのパスワード
		String username;	// ユーザーの名前

		// カート情報(cart)テーブルへの各フィールド
		Integer cartid;		// カートに入っている商品番号
		Integer cartprice;	// カートに入っている商品価格
		String cartname;	// カートに入っている商品名称
		String cartdetail;	// カートに入っている商品の詳細
		
		// 商品(object)テーブルのセッタ
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
		// 会員(member)テーブルのセッタ	
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
		
		//	商品(object)テーブルのゲッタ
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
		// 会員(member)テーブルのゲッタ	
		 
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
		
		// ログイン時に、データベースに登録した情報と一致しているかを判断するメソッド
		/*String result = "";
		public String checkLogin(String id,String pass){
			if(id.equals(id) && pass.equals(pass) ){
				result = "true";
			}else{
				result = "false";
			}
			return result;
		}*/
	
		// カート(cart)テーブルのセッタ
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
		
		// カート(cart)テーブルのゲッタ
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