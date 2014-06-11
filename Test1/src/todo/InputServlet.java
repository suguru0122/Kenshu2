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
 * �V�K�o�^�̉�ʂ�\������
 */
@WebServlet("/todo/input")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		request.setCharacterEncoding("UTF-8");
   		// vo�̍쐬
   		TodoValueObject vo = new TodoValueObject();

   		// �V�K�o�^�ł��邱�Ƃ𔻖������id=0�Ƃ��Ă���B
   		vo.setId(0);

   		// �^�X�N1����vo�����N�G�X�g�����փo�C���h
   		request.setAttribute("vo", vo);

   		// �ڍ׉�ʂ�\������
   		RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
   		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST���M����GET�Ɠ����������s��
		doGet(request,response);
	}

}