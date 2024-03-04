package kr.or.ddit.servlet03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 사용자로부터 /eta 요청을 받고, 
 * 컨텐트 폴더에 있는 eta_utf8.txt 파일에 있는 가사를 컨텐츠로 서비스해라
 */
@WebServlet("/eta")
public class ETALylicsServlet extends HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		File folder = new File("F:/00.medias");
		File file = new File(folder, "ETA_UTF8.txt");
		
		
		String mime = getServletContext().getMimeType(file.getName());

		resp.setContentType(String.format("%s;charset=UTF-8", mime));
		
		try(FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			OutputStream os = resp.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			){
				int readByte = -1;
				while((readByte = bis.read()) != -1) {
					bos.write(readByte);
			}
			bos.flush(); // 간혹 버퍼로 읽은 것이 모두 나가지 않을때 있는데 그래서 flush로 모두 내보낸다.
		}
	}
}

















