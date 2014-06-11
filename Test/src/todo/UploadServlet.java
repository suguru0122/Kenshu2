package todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(urlPatterns={"/todo/upload"})
@MultipartConfig(location="C:/upload/")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		// <INPUT type="file" name="uploadfile">����Multipart�`���̃A�b�v���[�h�R���e���c�̓��e���擾
		Part part = request.getPart("uploadfile");
		
		// �A�b�v���[�h���ꂽ�R���e���c(part)����t�@�C���e����������������͂��A�擾����B
		String filename = null;
		for(String cd : part.getHeader("Content-Disposition").split(";")){
			cd = cd.trim();
			
			if(cd.startsWith("filename")){
				// �t�@�C������=�̉E�ȍ~�̕�����
				// ���������p���ɂ���Ă̓_�u���N�E�H�[�e�[�V�������܂܂�Ă���̂ŁA��菜���K�v������
				filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"","");
				break;
			}
		}
		
		// ���N�G�X�g�p�����[�^��id���擾����B
		String idStr = request.getParameter("id");
		log("idStr:" + idStr);
		int id = Integer.parseInt(idStr);
		
		// �A�b�v���[�h�����t�@�C���������o��
		String message = null;
		if(filename != null){
			
			// �A�b�v���[�h���ꂽ�t�@�C�����́AOS�ˑ��̃t�@�C���p�X�����܂�ł���̂Œu������
			// ����\�ɒu�����A���̌�̃t�@�C�����̂ݒ��o����B
			filename = filename.replace("\\","/");
			
			int pos = filename.lastIndexOf("/");
			if(pos >= 0){
				filename = filename.substring(pos+1);
			}
			part.write(filename);
			
			// �A�b�v���[�h������������̓f�[�^�x�[�X�ɓo�^����
			// �ۑ�����̂̓t�@�C�����̂݁B���S�p�X�͊܂܂Ȃ��B
			TodoValueObject vo = new TodoValueObject();
			vo.setId(id);
			vo.setFilename(filename);
			
			TodoDAO dao = new TodoDAO();
			
			try{
				dao.getConnection();
				int result = dao.updateUploadInfo(vo);
				vo = dao.detail(result);
				// �^�X�N1����vo�����N�G�X�g�����փo�C���h
				request.setAttribute("vo", vo);
			}catch(Exception e){
				throw new ServletException(e);
			}finally{
				// DAO�̏���������������ڑ������
				dao.closeConnection();
			}
			message = "[ " + filename + " ]�̃A�b�v���[�h���������܂����B";
			
		}else{
			message = "�A�b�v���[�h�����s���܂����B";
		}
		
		// ���N�G�X�g�����֊i�[����
		request.setAttribute("message", message);
		
		// �ڍ׉�ʂ�\������
		request.getRequestDispatcher("/todo/detail?id=" + id).forward(request, response);
	}

}