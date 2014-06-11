package todo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String user = request.getParameter("j_username");
		String passwd = request.getParameter("j_password");
		try{
			// 認証に対する処理
			request.login(user, passwd);
			out.println("こんにちは、" + request.getRemoteUser() + "さん！");
		}catch(Exception e){
			e.printStackTrace();
			out.println("認証済みであるか、ユーザ名/パスワードが間違っています。");
		}
	}
}