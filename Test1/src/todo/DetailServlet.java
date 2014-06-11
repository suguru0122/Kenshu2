package todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DetailServlet_10_7
 */
@WebServlet("/todo/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// DAO�̎擾
		TodoDAO dao = new TodoDAO();

		// ���N�G�X�g�p�����[�^����I�������^�X�Nid���擾����
		String paramId = request.getParameter("id");

		// String����int�֕ϊ����Adao�ŏ������s���X�V�Ώۂ̃^�X�N��1���擾����
		TodoValueObject vo;
		try{
			dao.getConnection();
			
			// int�֕ϊ���NumberFormatException����������\������B�`�F�b�N�Ώ�
			int id = Integer.parseInt(paramId);

			// �^�X�N�ڍ׌��ʂ��擾
			vo = dao.detail(id);
			
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			// DAO�̏���������������ڑ������
			dao.closeConnection();
		}

		// �^�X�N1����vo�����N�G�X�g�����փo�C���h
		request.setAttribute("vo", vo);

		// ��ʂ�Ԃ��A�����ꗗ��\������
		RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST���M����GET�Ɠ����������s��
		doGet(request,response);
	}

}