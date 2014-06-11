package todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �폜�������s��
 */
@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// DAO�̎擾
		TodoDAO dao = new TodoDAO();

		// ���N�G�X�g�p�����[�^����I�������^�X�Nid���擾����
		String paramId = request.getParameter("id");
		
		try{
			dao.getConnection();
			
			// int�֕ϊ���NumberFormatException����������\������B�`�F�b�N�Ώ�
			int id = Integer.parseInt(paramId);
			
			// String����int�֕ϊ����Adao�ŏ������s���B�Ώۂ̃^�X�N��1���폜���A���������1���Ԃ����B
			int result = dao.delete(id);
					
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			// DAO�̏���������������ڑ������
			dao.closeConnection();
		}

		setMessage(request,"�^�X�N[ " + paramId + " ]�̍폜�������������܂����B");
		
		// �ꗗ��ʂփt�H���[�h����
		RequestDispatcher rd = request.getRequestDispatcher("/todo/search");
		rd.forward(request, response);

	}

	protected void setMessage(HttpServletRequest request,String message){
		request.setAttribute("message", message);
	}
}