package todo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommonMySQLDAO {
	protected Connection connection = null;

	// �f�[�^�x�[�X�Ƃ̐ڑ����J�n����B�����擾���Ă����ꍇ�ɂ́A�����̐ڑ��𗘗p���A�擾���Ă��Ȃ��ꍇ�ɂ͐V���ɃR���e�i����擾����B
	public Connection getConnection() throws Exception {
		// NamingException,SQLException���X���[�����B
		try{
			if(connection == null || connection.isClosed()){
				// JNDI�R���e�L�X�g���擾
				InitialContext initCtx = new InitialContext();
				// Tomcat�ŊǗ����ꂽ�f�[�^�x�[�X�ڑ���JNDI�o�R�Ŏ擾
				// java:comp/env/��K���t����
				DataSource ds =(DataSource)initCtx.lookup("java:comp/env/jdbc/dbtest");

				// �f�[�^�x�[�X�ڑ����擾����
				connection = ds.getConnection();

				// �����R�~�b�g���s�킸�A�X�V�n�����ł͏�Ƀg�����U�N�V�����Ǘ����s���l�ɐݒ肷��
				connection.setAutoCommit(false);
			}
		}catch(Exception e){
			// �����ڑ��擾�ŗ�O���o���ꍇ��connection = null�ɂ��A����������O�͂��̂܂ܑ��o����
			e.printStackTrace();
			connection = null;
			throw e;
		}
		return connection;
	}

	// �ڑ������B�m���ɐڑ���������邽��finally��connection = null���s��
	public void closeConnection(){
		try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		} finally{
			connection = null;
		}
	}
}
