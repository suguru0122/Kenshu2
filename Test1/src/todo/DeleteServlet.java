package todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 削除処理を行う
 */
@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// DAOの取得
		TodoDAO dao = new TodoDAO();

		// リクエストパラメータから選択したタスクidを取得する
		String paramId = request.getParameter("id");
		
		try{
			dao.getConnection();
			
			// intへ変換※NumberFormatExceptionが発生する可能性あり。チェック対象
			int id = Integer.parseInt(paramId);
			
			// Stringからintへ変換し、daoで処理を行う。対象のタスクを1件削除し、成功すると1が返される。
			int result = dao.delete(id);
					
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			// DAOの処理が完了したら接続を閉じる
			dao.closeConnection();
		}

		setMessage(request,"タスク[ " + paramId + " ]の削除処理が完了しました。");
		
		// 一覧画面へフォワードする
		RequestDispatcher rd = request.getRequestDispatcher("/todo/search");
		rd.forward(request, response);

	}

	protected void setMessage(HttpServletRequest request,String message){
		request.setAttribute("message", message);
	}
}