package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

public class OnlineDataDAO extends SqlDAO implements ServletRequestAware{
	
	private HttpServletRequest request;
	
	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public HttpServletRequest getHttpServletRequest(){
		return this.request;
	}
	
	public List<OnlineField> getList() throws Exception{
		List<OnlineField> returnList = new ArrayList<OnlineField>();
		String sql = "SELECT objectid,objectnumber,price,objectname,detail FROM object";

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
			of.setPrice(rs.getInt("price"));
			of.setObjectname(rs.getString("objectname"));
			of.setDetail(rs.getString("detail"));

			returnList.add(of);
		}
		return returnList;
	}

	public long getCartlist(String s) throws Exception{
		
		OnlineField o = new OnlineField();
		String sql = "select SUM(price) from object where objectnumber in (" +  s + "'123-938-37')";

		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��1
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();
		
		long sum = 0;
		while(rs.next()){ 
			o.setPrice(rs.getInt("SUM(price)"));
			sum = o.getPrice();
		}
		return sum;
	}
	
	public static OnlineField findListid(String inId) throws Exception{
		OnlineField o = new OnlineField();
		String sql = "select * from object where objectnumber in (" + inId + ")";

		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();

		// ���ʂ̍s�����t�F�b�`���s���A�擾���ʂ�of�֊i�[����
		while(rs.next()){
			// �ڑ�1���R�[�h�̓��e���擾����
			o.setObjectid(rs.getInt("objectid"));
			o.setObjectnumber(rs.getString("objectnumber"));
			o.setPrice(rs.getInt("price"));
			o.setObjectname(rs.getString("objectname"));
			o.setDetail(rs.getString("detail"));
		}
		return o;

	}
		
}