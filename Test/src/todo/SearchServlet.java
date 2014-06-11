package todo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 検索機能。タスク一覧を取得し、一覧結果へフォワードする。
 */
@WebServlet("/todo/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	// 認証情報の取得処理を追加↓
    	String userid = request.getRemoteUser();
    	request.setAttribute("LoginUserId", userid);
    			
    	// adminロールを所有するユーザーであるかを判断する
   		boolean isAdmin = request.isUserInRole("admin_gui");
   		request.setAttribute("isAdmin",isAdmin);
    			
    	// DAOの取得
    	TodoDAO dao = new TodoDAO();
    	try{
    		dao.getConnection();
    		// タスクのリスト一覧で取得し、リクエスト属性へ格納する
    		List<TodoValueObject> list = dao.todoList();
    		request.setAttribute("todoList", list);
    		
    	}catch(Exception e){
    		throw new ServletException(e);
    	}finally{
    		// DAOの処理が完了したら接続を閉じる
    		dao.closeConnection();
    	}
    	// 検索一覧を表示する
    	RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
    	rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST送信時もGETと同じ処理を行う
		doGet(request,response);
	}

}