package todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �t�@�C�����i�[�����t�H���_
		String root = "/WEB-INF";
		ServletContext application = this.getServletContext();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		boolean flag = false;
		// �z���C�g���X�g�Ƀ}�b�`���邩�̃t���O
		File fileRoot = new File(application.getRealPath("root"));
		// WEB-INF/doc�t�H���_�z���̃t�@�C�������Ɏ擾
		for(File f : fileRoot.listFiles()){
			// �N�G�����path�Ɠ������t�@�C�������݂���ꍇ�̂݁A�t���O��true��
			if(f.isFile() && f.getName().equals(request.getParameter("path"))){
				flag = true;
				break;
			}
		}
		
		// �t���O��false�ł���ꍇ�͕s���ȗv���Ɣ��f
		if(!flag){throw new ServletException("�s���ȗv���ł��B");}
		
		// �N�G�����path�Ŏw�肳�ꂽ�t�@�C�����I�[�v��
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(application.getRealPath(root + request.getParameter("path"))),"UTF-8"));
		
		// �t�@�C���̓��e��ǂݍ��݁A���̂܂܏����o��
		while(reader.ready()){
			out.println(reader.readLine() + "<br />");
		}
		reader.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}