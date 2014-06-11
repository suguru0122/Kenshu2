package todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadDisplayServlet
 */
@WebServlet("/todo/preUpload")
public class UploadDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// ���N�G�X�g�p�����[�^����^�X�N�ԍ����擾���āA���N�G�X�g�����֊i�[
		String idString = request.getParameter("id");
		
		// ���l�ɕϊ�����
		Integer id = Integer.parseInt(idString);
		
		// ���N�G�X�g�����֊i�[����
		request.setAttribute("id", id);
		
		request.getRequestDispatcher("/upload.jsp").forward(request, response);
	}

}