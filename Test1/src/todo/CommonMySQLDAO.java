package todo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommonMySQLDAO {
	protected Connection connection = null;

	// データベースとの接続を開始する。もし取得していた場合には、既存の接続を利用し、取得していない場合には新たにコンテナから取得する。
	public Connection getConnection() throws Exception {
		// NamingException,SQLExceptionがスローされる。
		try{
			if(connection == null || connection.isClosed()){
				// JNDIコンテキストを取得
				InitialContext initCtx = new InitialContext();
				// Tomcatで管理されたデータベース接続をJNDI経由で取得
				// java:comp/env/を必ず付ける
				DataSource ds =(DataSource)initCtx.lookup("java:comp/env/jdbc/dbtest");

				// データベース接続を取得する
				connection = ds.getConnection();

				// 自動コミットを行わず、更新系処理では常にトランザクション管理を行う様に設定する
				connection.setAutoCommit(false);
			}
		}catch(Exception e){
			// もし接続取得で例外が出た場合はconnection = nullにし、発生した例外はそのまま送出する
			e.printStackTrace();
			connection = null;
			throw e;
		}
		return connection;
	}

	// 接続を閉じる。確実に接続を解放するためfinallyでconnection = nullを行う
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
