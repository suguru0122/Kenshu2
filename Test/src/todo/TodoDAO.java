package todo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO extends CommonMySQLDAO{
	
	public List<TodoValueObject> todoList() throws Exception{
		List<TodoValueObject> returnList = new ArrayList<TodoValueObject>();
		String sql = "SELECT id,title,task,limitdate,lastupdate,userid,label,td.status filename FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status";

		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);

		// SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();

		// ���ʂ̍s�����t�F�b�`���s���A�擾���ʂ�ValueObject�֊i�[����
		while(rs.next()){
			// �ڑ�1���R�[�h�̓��e���擾����
			TodoValueObject vo = new TodoValueObject();
			
			vo.setId(rs.getInt("id"));
			vo.setTitle(rs.getString("title"));
			vo.setTask(rs.getString("task"));
			vo.setLimitdate(rs.getTimestamp("limitdate"));
			vo.setLastupdate(rs.getTimestamp("lastupdate"));
			vo.setUserid(rs.getString("userid"));
			vo.setLabel(rs.getString("label"));
			vo.setFilename(rs.getString("filename"));

			returnList.add(vo);
		}
		return returnList;
	}
	
	public TodoValueObject detail(int id) throws Exception{
		TodoValueObject vo = new TodoValueObject();
		String sql = "SELECT id,title,task,limitdate,lastupdate,userid,label,filename,td.status FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status where id = ?";

		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);

		// SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();

		// ���ʂ̍s�����t�F�b�`���s���A�擾���ʂ�ValueObject�֊i�[����
		while(rs.next()){
			// �N�G�����ʂ�VO�֊i�[(���炩���߃N�G���[���ʂ�VO�̕ϐ����͈�v�����Ă���)
			vo.setId(rs.getInt("id"));
			vo.setTitle(rs.getString("title"));
			vo.setTask(rs.getString("task"));
			vo.setLimitdate(rs.getTimestamp("limitdate"));
			vo.setLastupdate(rs.getTimestamp("lastupdate"));
			vo.setUserid(rs.getString("userid"));
			vo.setLabel(rs.getString("label"));
			vo.setStatus(rs.getInt("status"));
			vo.setFilename(rs.getString("filename"));
		}
		return vo;
	}
	
	public int registerInsert(TodoValueObject vo) throws Exception{
		String sql = "INSERT INTO todo_list(title,task,limitdate,lastupdate,userid,status) VALUES (?,?,?,now(),?,0)";
		int result = 0;
		
		try{
			// �����R�~�b�g��OFF�ɂ���
			connection.setAutoCommit(false);
		
			// �v���y�A�[�X�e�[�g�����g���擾���A���sSQL��n���B
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,vo.getTitle());
			statement.setString(2,vo.getTask());
			statement.setString(3,vo.getInputLimit());
			statement.setString(4,vo.getUserid());
			
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
	
	public int registerUpdate(TodoValueObject vo) throws Exception{
		String sql = "UPDATE todo_list SET title = ?,task = ?,limitdate = ?,lastupdate = now(),userid = ?,status = ? WHERE id = ?";
		
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		int result = 0;
		try{
			// �����R�~�b�g��OFF�ɂ���
			connection.setAutoCommit(false);
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,vo.getTitle());
			statement.setString(2,vo.getTask());
			statement.setString(3,vo.getInputLimit());
			statement.setString(4,vo.getUserid());
			statement.setInt(5,vo.getStatus());
			statement.setInt(6,vo.getId());
			
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
	
	public int delete(int id) throws Exception{
		String sql = "DELETE FROM todo_list WHERE id = ?";
		
		// SQL�����s���Ă��̌��ʂ��擾����
		int result = 0;
		try{
			// �����R�~�b�g��OFF�ɂ���
			connection.setAutoCommit(false);
			
			// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1,id);
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
	
	public int updateUploadInfo(TodoValueObject vo) throws Exception{
		String sql = "UPDATE todo_list SET filename = ? WHERE id = ?";
		int result = 0;
		
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n���B
		try{
			// �����R�~�b�g��OFF�ɂ���
			connection.setAutoCommit(false);
						
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,vo.getFilename());
			statement.setInt(2,vo.getId());
			
			// �o�^���s��
			result = statement.executeUpdate();
			
			// �R�~�b�g���s��
			connection.commit();			
		}catch(Exception e){
			connection.rollback();
			throw e;
		}	
		return result;		
	}
	
}