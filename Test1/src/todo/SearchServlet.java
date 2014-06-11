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
 * �����@�\�B�^�X�N�ꗗ���擾���A�ꗗ���ʂփt�H���[�h����B
 */
@WebServlet("/todo/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	// �F�؏��̎擾������ǉ���
    	String userid = request.getRemoteUser();
    	request.setAttribute("LoginUserId", userid);
    			
    	// admin���[�������L���郆�[�U�[�ł��邩�𔻒f����
   		boolean isAdmin = request.isUserInRole("admin_gui");
   		request.setAttribute("isAdmin",isAdmin);
    			
    	// DAO�̎擾
    	TodoDAO dao = new TodoDAO();
    	try{
    		dao.getConnection();
    		// �^�X�N�̃��X�g�ꗗ�Ŏ擾���A���N�G�X�g�����֊i�[����
    		List<TodoValueObject> list = dao.todoList();
    		request.setAttribute("todoList", list);
    		
    	}catch(Exception e){
    		throw new ServletException(e);
    	}finally{
    		// DAO�̏���������������ڑ������
    		dao.closeConnection();
    	}
    	// �����ꗗ��\������
    	RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
    	rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST���M����GET�Ɠ����������s��
		doGet(request,response);
	}

}