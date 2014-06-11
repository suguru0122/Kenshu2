package todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.TodoValueObject;
/**
 * 新規登録の画面を表示する
 */
@WebServlet("/todo/input")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		request.setCharacterEncoding("UTF-8");
   		// voの作成
   		TodoValueObject vo = new TodoValueObject();

   		// 新規登録であることを判明する為id=0としている。
   		vo.setId(0);

   		// タスク1件のvoをリクエスト属性へバインド
   		request.setAttribute("vo", vo);

   		// 詳細画面を表示する
   		RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
   		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST送信時もGETと同じ処理を行う
		doGet(request,response);
	}

}