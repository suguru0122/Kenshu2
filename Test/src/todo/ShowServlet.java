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
		// ファイルを格納したフォルダ
		String root = "/WEB-INF";
		ServletContext application = this.getServletContext();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		boolean flag = false;
		// ホワイトリストにマッチするかのフラグ
		File fileRoot = new File(application.getRealPath("root"));
		// WEB-INF/docフォルダ配下のファイルを順に取得
		for(File f : fileRoot.listFiles()){
			// クエリ情報pathと等しいファイルが存在する場合のみ、フラグをtrueに
			if(f.isFile() && f.getName().equals(request.getParameter("path"))){
				flag = true;
				break;
			}
		}
		
		// フラグがfalseである場合は不正な要求と判断
		if(!flag){throw new ServletException("不正な要求です。");}
		
		// クエリ情報pathで指定されたファイルをオープン
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(application.getRealPath(root + request.getParameter("path"))),"UTF-8"));
		
		// ファイルの内容を読み込み、そのまま書き出し
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