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
		
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);
		int result = 0;
		
		try{
			// �����R�~�b�g��OFF�ɂ���
			connection.setAutoCommit(false);
		
			// �v���y�A�[�X�e�[�g�����g���擾���A���sSQL��n���B
			statement.setString(1,of.getNewnumber());
			statement.setInt(2,of.getNewprice());
			statement.setString(3,of.getNewname());
			statement.setString(4,of.getNewdetail());
			
			result = statement.executeUpdate();
			
			// �R�~�b�g���s��
			connection.commit();			
		}catch(Exception e){
			// ���[���o�b�N���s���A�X���[������O��DAO����E�o����
			connection.rollback();
			throw e;
		}		
		return result;
	}
	
	// ���[�U�[���̐V�K�o�^�p���\�b�h
	public int getAdd(OnlineField of) throws Exception{
		String sql = "INSERT INTO user_member(customer_id,customer_pass,customer_join) VALUES(?,?,now())";
		
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);
		int result = 0;
		
		try{
			// �����R�~�b�g��OFF�ɂ���
			connection.setAutoCommit(false);
		
			// �v���y�A�[�X�e�[�g�����g���擾���A���sSQL��n���B
			statement.setString(1,of.getCustomerid());
			statement.setString(2,of.getCustomerpass());
			
			result = statement.executeUpdate();
			
			// �R�~�b�g���s��
			connection.commit();			
		}catch(Exception e){
			// ���[���o�b�N���s���A�X���[������O��DAO����E�o����
			connection.rollback();
			throw e;
		}		
		return result;
	}
	
	public List<OnlineField> getList() throws Exception{
		List<OnlineField> returnList = new ArrayList<OnlineField>();
		String sql = "SELECT objectid,objectnumber,objectprice,objectname,objectdetail FROM object";

		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();

		// ���ʂ̍s�����t�F�b�`���s���A�擾���ʂ�of�֊i�[����
		while(rs.next()){
			// �ڑ�1���R�[�h�̓��e���擾����
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

		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();

		// ���ʂ̍s�����t�F�b�`���s���A�擾���ʂ�of�֊i�[����
		while(rs.next()){
			// �ڑ�1���R�[�h�̓��e���擾����
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

		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��1
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQL�����s���Ă��̌��ʂ��擾����
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

		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();

		// ���ʂ̍s�����t�F�b�`���s���A�擾���ʂ�of�֊i�[����
		while(rs.next()){
			// �ڑ�1���R�[�h�̓��e���擾����
			o.setObjectid(rs.getInt("objectid"));
			o.setObjectnumber(rs.getString("objectnumber"));
			o.setObjectprice(rs.getInt("objectprice"));
			o.setObjectname(rs.getString("objectname"));
			o.setObjectdetail(rs.getString("objectdetail"));
		}
		return o;

	}
	
	// ���O�C�����ɁA�f�[�^�x�[�X�ɓo�^�������[�U�[�����������Ă��郁�\�b�h
	public String checkPass(String s) throws Exception {
		// id�ŁA�����ƃf�[�^�x�[�X�̏�񂪈�v���Ă��邩�������|���āA�Y�������p�X���[�h���E���Ă���B
		String sql = "select customer_pass from user_member where customer_id in('" + s + "')";
		
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);
		
		// SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();
		
		// ���ʂ̍s�����t�F�b�`���s���A�擾���ʂ�of�֊i�[����
		String str = "";
		while(rs.next()){
			// �ڑ�1���R�[�h�̓��e���擾����
			OnlineField of = new OnlineField();
			of.setCustomerpass(rs.getString("customer_pass"));
			str = of.getCustomerpass();
		}
		return str;
	}
	
	// ���O�C�����ɁA�f�[�^�x�[�X�ɓo�^�����Ǘ��ҏ����������Ă��郁�\�b�h
	public String checkManagerpass(String m) throws Exception {
		// id�ŁA�����ƃf�[�^�x�[�X�̏�񂪈�v���Ă��邩�������|���āA�Y�������p�X���[�h���E���Ă���B
		String sql = "select manager_pass from manager where manager_id in('" + m + "')";
			
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);
		
		// SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();
			
		// ���ʂ̍s�����t�F�b�`���s���A�擾���ʂ�of�֊i�[����
		String str = "";
		while(rs.next()){
			// �ڑ�1���R�[�h�̓��e���擾����
			OnlineField of = new OnlineField();
			of.setAdminpass(rs.getString("manager_pass"));
			str = of.getAdminpass();
		}
		return str;
	}
	
	// �Ǘ��ґ��̐V�K�o�^�p���\�b�h
	public int getManagerAdd(OnlineField of) throws Exception{
		String sql = "INSERT INTO manager(manager_id,manager_pass) VALUES(?,?)";
		
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);
		int result = 0;
		
		try{
			// �����R�~�b�g��OFF�ɂ���
			connection.setAutoCommit(false);
		
			// �v���y�A�[�X�e�[�g�����g���擾���A���sSQL��n���B
			statement.setString(1,of.getAddadminid());
			statement.setString(2,of.getAddadminpass());
			
			result = statement.executeUpdate();
			
			// �R�~�b�g���s��
			connection.commit();			
		}catch(Exception e){
			// ���[���o�b�N���s���A�X���[������O��DAO����E�o����
			connection.rollback();
			throw e;
		}		
		return result;
	}
	
	// cart�e�[�u���ɃC���T�[�g����ׂ̃��\�b�h
	public int getAddcart(OnlineField of,Object userid) throws Exception {
		String sql = "INSERT INTO cart(cus_id,cart_num,cart_price,cart_name,cart_detail,order_day) VALUES('" + userid + "',?,?,?,?,now())";
		
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);
		int result = 0;

		try{
			// �����R�~�b�g��OFF�ɂ���
			connection.setAutoCommit(false);

			// �v���y�A�[�X�e�[�g�����g���擾���A���sSQL��n���B
			statement.setString(1,of.getObjectnumber());
			statement.setInt(2,of.getObjectprice());
			statement.setString(3,of.getObjectname());
			statement.setString(4,of.getObjectdetail());
			
			result = statement.executeUpdate();
			
			// �R�~�b�g���s��
			connection.commit();			
		}catch(Exception e){
			// ���[���o�b�N���s���A�X���[������O��DAO����E�o����
			connection.rollback();
			throw e;
		}		
		
		return result;
	}
	
	// �`�F�b�N�{�b�N�X�ɓ����ꂽ���̕��A���������Ċi�[���郁�\�b�h
	public List<OnlineField> getCartadd(String objid,Object userid) throws Exception{
		List<OnlineField> list = new ArrayList<OnlineField>();
		String sql = "select objectnumber,objectprice,objectname,objectdetail from object where objectnumber in (" + objid  + "'123-456-789')";

		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();

		// ���ʂ̍s�����t�F�b�`���s���A�擾���ʂ�o�֊i�[����
		while(rs.next()){
			// �ڑ�1���R�[�h�̓��e���擾����
			OnlineField o = new OnlineField();
			o.setObjectnumber(rs.getString("objectnumber"));
			o.setObjectprice(rs.getInt("objectprice"));
			o.setObjectname(rs.getString("objectname"));
			o.setObjectdetail(rs.getString("objectdetail"));
			
			list.add(o);
			// �擾������񕪂̒l���C���T�[�g�̃��\�b�h�ɓn���B
			getAddcart(o,userid);
		}
		return list;
	}

}