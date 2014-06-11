package todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * �o�^�������s��
 */
@WebServlet("/todo/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * post���\�b�h�̂ݎ󂯕t����
     */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ���N�G�X�g�p�����[�^���󂯎��A�X�VVO�Ɋi�[���鏀��������
		
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String task = request.getParameter("task");
		String inputLimitdate = request.getParameter("limitdate");
		String userid = request.getParameter("userid");
		int status = Integer.parseInt(request.getParameter("status"));
		
		// VO�֊i�[����B�o�^�����ValueObject�̊���(limit)��vo�ł�inputLimit�ɂȂ�B
		TodoValueObject vo = new TodoValueObject();
		vo.setId(id);
		vo.setTitle(title);
		vo.setTask(task);
		vo.setInputLimitdate(inputLimitdate);
		vo.setUserid(userid);
		vo.setStatus(status);
		
		// DAO�̎擾
		TodoDAO dao = new TodoDAO();
		String message = "";
		
		try{
			dao.getConnection();
			// �X�V�A�܂��͓o�^�������s��
			// id��0�̎��͐V�K�o�^�Aid >= 1�̎��͍X�V
			if(id == 0){
				dao.registerInsert(vo);
				setMessage(request,"�^�X�N�̐V�K�o�^�������������܂����B");
				setMessage(request,message);
			}else{
				dao.registerUpdate(vo);
				setMessage(request,"�^�X�N[ " + id + " ]�̍X�V�������������܂����B");
				setMessage(request,message);
			}
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			// DAO�̏���������������ڑ������
			dao.closeConnection();
		}
		
		String toAddr = "suguru.kyogoku@gmail.com";
		String fromAddr = "suguru.kyogoku@gmail.com";
		String personName = "Test";
		String subject = "TODO�Ǘ��A�v���P�[�V��������̕񍐂ł�";
		String mailText = "�e�X�g�ł��B";
		
		// �������Ƀ��[���𑗐M����
		SimpleMailSender mail = new SimpleMailSender(); 
		try{
			mail.sendMessage(toAddr, fromAddr, personName, subject, mailText);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// ������ʂ�\������
		RequestDispatcher rd = request.getRequestDispatcher("/complete.jsp");
		rd.forward(request, response);
	}
	
	// JSP�ŕ\�����郁�b�Z�[�W���擾����
	protected void setMessage(HttpServletRequest request,String message){
		request.setAttribute("message", message);
		
	}
}
