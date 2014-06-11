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
		
		// <INPUT type="file" name="uploadfile">からMultipart形式のアップロードコンテンツの内容を取得
		Part part = request.getPart("uploadfile");
		
		// アップロードされたコンテンツ(part)からファイル各部分示す部分を解析し、取得する。
		String filename = null;
		for(String cd : part.getHeader("Content-Disposition").split(";")){
			cd = cd.trim();
			
			if(cd.startsWith("filename")){
				// ファイル名は=の右以降の文字列
				// ただし利用環境によってはダブルクウォーテーションが含まれているので、取り除く必要がある
				filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"","");
				break;
			}
		}
		
		// リクエストパラメータのidを取得する。
		String idStr = request.getParameter("id");
		log("idStr:" + idStr);
		int id = Integer.parseInt(idStr);
		
		// アップロードしたファイルを書き出す
		String message = null;
		if(filename != null){
			
			// アップロードされたファイル名は、OS依存のファイルパス等を含んでいるので置換する
			// ￥は\に置換し、その後のファイル名のみ抽出する。
			filename = filename.replace("\\","/");
			
			int pos = filename.lastIndexOf("/");
			if(pos >= 0){
				filename = filename.substring(pos+1);
			}
			part.write(filename);
			
			// アップロードが完了した後はデータベースに登録する
			// 保存するのはファイル名のみ。完全パスは含まない。
			TodoValueObject vo = new TodoValueObject();
			vo.setId(id);
			vo.setFilename(filename);
			
			TodoDAO dao = new TodoDAO();
			
			try{
				dao.getConnection();
				int result = dao.updateUploadInfo(vo);
				vo = dao.detail(result);
				// タスク1件のvoをリクエスト属性へバインド
				request.setAttribute("vo", vo);
			}catch(Exception e){
				throw new ServletException(e);
			}finally{
				// DAOの処理が完了したら接続を閉じる
				dao.closeConnection();
			}
			message = "[ " + filename + " ]のアップロードが完了しました。";
			
		}else{
			message = "アップロードが失敗しました。";
		}
		
		// リクエスト属性へ格納する
		request.setAttribute("message", message);
		
		// 詳細画面を表示する
		request.getRequestDispatcher("/todo/detail?id=" + id).forward(request, response);
	}

}