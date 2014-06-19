package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

public class OnlineDataDAO extends SqlDAO implements ServletRequestAware{
	
	private HttpServletRequest request;
	
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public HttpServletRequest getHttpServletRequest(){
		return this.request;
	}
	
	public int getAddobject(OnlineField of) throws Exception{
		String sql = "INSERT INTO object(objectnumber,objectprice,objectname,objectdetail) VALUES(?,?,?,?)";
		
		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);
		int result = 0;
		
		try{
			// 自動コミットをOFFにする
			connection.setAutoCommit(false);
		
			// プリペアーステートメントを取得し、実行SQLを渡す。
			statement.setString(1,of.getNewnumber());
			statement.setInt(2,of.getNewprice());
			statement.setString(3,of.getNewname());
			statement.setString(4,of.getNewdetail());
			
			result = statement.executeUpdate();
			
			// コミットを行う
			connection.commit();			
		}catch(Exception e){
			// ロールバックを行い、スローした例外はDAOから脱出する
			connection.rollback();
			throw e;
		}		
		return result;
	}
	
	// ユーザー側の新規登録用メソッド
	public int getAdd(OnlineField of) throws Exception{
		String sql = "INSERT INTO user_member(customer_id,customer_pass,customer_join) VALUES(?,?,now())";
		
		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);
		int result = 0;
		
		try{
			// 自動コミットをOFFにする
			connection.setAutoCommit(false);
		
			// プリペアーステートメントを取得し、実行SQLを渡す。
			statement.setString(1,of.getCustomerid());
			statement.setString(2,of.getCustomerpass());
			
			result = statement.executeUpdate();
			
			// コミットを行う
			connection.commit();			
		}catch(Exception e){
			// ロールバックを行い、スローした例外はDAOから脱出する
			connection.rollback();
			throw e;
		}		
		return result;
	}
	
	public List<OnlineField> getList() throws Exception{
		List<OnlineField> returnList = new ArrayList<OnlineField>();
		String sql = "SELECT objectid,objectnumber,objectprice,objectname,objectdetail FROM object";

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 結果の行数分フェッチを行い、取得結果をofへ格納する
		while(rs.next()){
			// 接続1レコードの内容を取得する
			OnlineField of = new OnlineField();
			
			of.setObjectid(rs.getInt("objectid"));
			of.setObjectnumber(rs.getString("objectnumber"));
			of.setObjectprice(rs.getInt("objectprice"));
			of.setObjectname(rs.getString("objectname"));
			of.setObjectdetail(rs.getString("objectdetail"));

			returnList.add(of);
		}
		return returnList;
	}
	
	public List<OnlineField> cartList(Object o) throws Exception{
		List<OnlineField> returnList = new ArrayList<OnlineField>();
		String sql = "SELECT * FROM cart where cus_id in('" + o + "')";

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 結果の行数分フェッチを行い、取得結果をofへ格納する
		while(rs.next()){
			// 接続1レコードの内容を取得する
			OnlineField of = new OnlineField();
			
			//of.setCusid(rs.getString("cus_id"));
			of.setCartnum(rs.getString("cart_num"));
			of.setCartprice(rs.getInt("cart_price"));
			of.setCartname(rs.getString("cart_name"));
			of.setCartdetail(rs.getString("cart_detail"));
			of.setOrderday(rs.getTimestamp("order_day"));
			
			returnList.add(of);
		}
		return returnList;
	}

	public long getCartlist(String s) throws Exception{
		
		OnlineField o = new OnlineField();
		String sql = "select SUM(objectprice) from object where objectnumber in (" +  s + "'123-456-789')";

		// プリペアステートメントを取得し、実行SQLを渡す1
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();
		
		long sum = 0;
		while(rs.next()){ 
			o.setObjectprice(rs.getInt("SUM(objectprice)"));
			sum = o.getObjectprice();
		}
		return sum;
	}
	
	public static OnlineField findListid(String inId) throws Exception{
		OnlineField o = new OnlineField();
		String sql = "select * from object where objectnumber in ('" + inId + "')";

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 結果の行数分フェッチを行い、取得結果をofへ格納する
		while(rs.next()){
			// 接続1レコードの内容を取得する
			o.setObjectid(rs.getInt("objectid"));
			o.setObjectnumber(rs.getString("objectnumber"));
			o.setObjectprice(rs.getInt("objectprice"));
			o.setObjectname(rs.getString("objectname"));
			o.setObjectdetail(rs.getString("objectdetail"));
		}
		return o;

	}
	
	// ログイン時に、データベースに登録したユーザー情報引っ張ってくるメソッド
	public String checkPass(String s) throws Exception {
		// idで、引数とデータベースの情報が一致しているか検索を掛けて、該当したパスワードを拾ってくる。
		String sql = "select customer_pass from user_member where customer_id in('" + s + "')";
		
		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);
		
		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();
		
		// 結果の行数分フェッチを行い、取得結果をofへ格納する
		String str = "";
		while(rs.next()){
			// 接続1レコードの内容を取得する
			OnlineField of = new OnlineField();
			of.setCustomerpass(rs.getString("customer_pass"));
			str = of.getCustomerpass();
		}
		return str;
	}
	
	// ログイン時に、データベースに登録した管理者情報引っ張ってくるメソッド
	public String checkManagerpass(String m) throws Exception {
		// idで、引数とデータベースの情報が一致しているか検索を掛けて、該当したパスワードを拾ってくる。
		String sql = "select manager_pass from manager where manager_id in('" + m + "')";
			
		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);
		
		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();
			
		// 結果の行数分フェッチを行い、取得結果をofへ格納する
		String str = "";
		while(rs.next()){
			// 接続1レコードの内容を取得する
			OnlineField of = new OnlineField();
			of.setAdminpass(rs.getString("manager_pass"));
			str = of.getAdminpass();
		}
		return str;
	}
	
	// 管理者側の新規登録用メソッド
	public int getManagerAdd(OnlineField of) throws Exception{
		String sql = "INSERT INTO manager(manager_id,manager_pass) VALUES(?,?)";
		
		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);
		int result = 0;
		
		try{
			// 自動コミットをOFFにする
			connection.setAutoCommit(false);
		
			// プリペアーステートメントを取得し、実行SQLを渡す。
			statement.setString(1,of.getAddadminid());
			statement.setString(2,of.getAddadminpass());
			
			result = statement.executeUpdate();
			
			// コミットを行う
			connection.commit();			
		}catch(Exception e){
			// ロールバックを行い、スローした例外はDAOから脱出する
			connection.rollback();
			throw e;
		}		
		return result;
	}
	
	// cartテーブルにインサートする為のメソッド
	public int getAddcart(OnlineField of,Object userid) throws Exception {
		String sql = "INSERT INTO cart(cus_id,cart_num,cart_price,cart_name,cart_detail,order_day) VALUES('" + userid + "',?,?,?,?,now())";
		
		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);
		int result = 0;

		try{
			// 自動コミットをOFFにする
			connection.setAutoCommit(false);

			// プリペアーステートメントを取得し、実行SQLを渡す。
			statement.setString(1,of.getObjectnumber());
			statement.setInt(2,of.getObjectprice());
			statement.setString(3,of.getObjectname());
			statement.setString(4,of.getObjectdetail());
			
			result = statement.executeUpdate();
			
			// コミットを行う
			connection.commit();			
		}catch(Exception e){
			// ロールバックを行い、スローした例外はDAOから脱出する
			connection.rollback();
			throw e;
		}		
		
		return result;
	}
	
	// チェックボックスに入れられた数の分、引っ張って格納するメソッド
	public List<OnlineField> getCartadd(String objid,Object userid) throws Exception{
		List<OnlineField> list = new ArrayList<OnlineField>();
		String sql = "select objectnumber,objectprice,objectname,objectdetail from object where objectnumber in (" + objid  + "'123-456-789')";

		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		// 結果の行数分フェッチを行い、取得結果をoへ格納する
		while(rs.next()){
			// 接続1レコードの内容を取得する
			OnlineField o = new OnlineField();
			o.setObjectnumber(rs.getString("objectnumber"));
			o.setObjectprice(rs.getInt("objectprice"));
			o.setObjectname(rs.getString("objectname"));
			o.setObjectdetail(rs.getString("objectdetail"));
			
			list.add(o);
			// 取得した一列分の値をインサートのメソッドに渡す。
			getAddcart(o,userid);
		}
		return list;
	}

}