package todo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/todo/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// �_�E�����[�h����t�@�C���̏����擾����
    	int id = Integer.parseInt(request.getParameter("id"));
    	TodoDAO dao = new TodoDAO();
    	TodoValueObject vo = new TodoValueObject();
    	try{
    		dao.getConnection();
    		vo = dao.detail(id);
    	}catch(Exception e){
    		e.printStackTrace();
    		throw new ServletException();
    	}finally{
    		dao.closeConnection();
    	}
    	
    	// �t�@�C���������o��
    	String filename = vo.getFilename();
    	
    	// �����t�@�C�����o�^����Ă��Ȃ�(�擾�ł��Ȃ�����)�ꍇ�́A�ēx�ڍ׉�ʂ֖߂�B
    	if(filename == null || "".equals(filename)){
    		request.setAttribute("message","�t�@�C���͓Y�t����Ă��܂���");
    		request.getRequestDispatcher("/todo/search?id=" + id).forward(request, response);
    		// �����ŏ����I��
    		return;
    	}
    	
    	// �Y�t�t�@�C���̕ۑ��f�B���N�g�����A�b�v���[�h�f�B���N�g��
    	File downloadFile = new File("C:/upload/" + filename);
    	FileInputStream fis = new FileInputStream(downloadFile); 
    	BufferedInputStream buf = new BufferedInputStream(fis);
    	
    	// �S�p�t�@�C������ϊ����Ă���
    	filename = URLEncoder.encode(filename,"utf-8");
    	
    	response.setContentType("application/octet-stream; charset=\"utf-8\"");
    	response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
    	
    	// �T�[�u���b�g�̏o��(���X�|���X)���擾����
    	ServletOutputStream out = response.getOutputStream();
    	
    	int length = 0;
    	byte[] buffer = new byte[1024];
    	while((length = buf.read(buffer)) >= 0){
    		out.write(buffer,0,length);	
    	}
    	out.close();
    	out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}