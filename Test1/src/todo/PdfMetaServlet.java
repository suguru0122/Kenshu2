package todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/PdfBasicServlet.pdf")
public class PdfMetaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			// �h�L�������g�{�̂𐶐�(�y�[�W�T�C�Y�ƃ}�[�W���̐ݒ�)
			Document doc = new Document(PageSize.A4,50,20,170,20);
			// �h�L�������g�̏o�͐���`
			PdfWriter writer = PdfWriter.getInstance(doc,response.getOutputStream());
			
			// PDF�����̃��^����ݒ�
			doc.addTitle("iText�T���v��");
			doc.addSubject("iText�̃T���v���ł��I");
			doc.addAuthor("YAMADA,YOSHIHIRO");
			doc.addCreator("iText");
			doc.addKeywords("iText,JSP,�T�[�u���b�g");
			
			// �h�L�������g���I�[�v��
			doc.open();
			// �t�H���g�̒�`
			Font fnt = new Font(BaseFont.createFont("HeiseiKakuGo-W5","UniJIS-UCS2-H",BaseFont.NOT_EMBEDDED),18,Font.BOLD);
			// ������̏o��
			doc.add(new Paragraph("����ɂ��́AiText!",fnt));
			// �h�L�������g���N���[�Y
			doc.close();
		}catch(DocumentException e){
			throw new ServletException(e);
		}
		
	}
}
