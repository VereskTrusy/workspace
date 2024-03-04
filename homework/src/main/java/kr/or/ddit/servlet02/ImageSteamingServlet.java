package kr.or.ddit.servlet02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image.do")
public class ImageSteamingServlet extends HttpServlet {
	
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException { // lifecycle callback !
		super.init(config);
		application = getServletContext();
	}
	
	// 미션 : 이거 바꾸기 http://localhost/WebStudy01/image.do?name=cute3.jpg 받고, name요청이 없으면 400에러 보내기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		if(name == null || name.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "이미지 파일명이 없습니다.");
			return;
		}
		File imageFolder = new File("F:/00.medias/images");
		File imageFile = new File(imageFolder, name);
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, String.format("%s 파일은 없습니다.", name));
			return;
		}
		String mime = application.getMimeType(imageFile.getName());
		if (mime == null || !mime.startsWith("image")) { // Servers > 톰켓 > web.xml > MIME정보에 HWP가 없으니까 null 체크만 해주면 된다.
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, String.format("%s 은(는) 이미지가 아닙니다.", name));
			return;
		}
		resp.setContentType(mime); // 지금은 image/png
		resp.setContentLengthLong(imageFile.length());
		// 미션 : try with resource문 사용, 2차 스트림 사용, byte[size] 미사용 
		try(InputStream is = new FileInputStream(imageFile);
			BufferedInputStream bis = new BufferedInputStream(is);
			OutputStream os = resp.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os); 
			){
				int readByte = -1;
				while((readByte = bis.read()) != -1) { // 어디까지 읽어들였는지 읽어들인 길이를 가지고 있음, -1 : EOF문자(파일의 끝을말한다.) 
					bos.write(readByte);
			}
		}
	}
}
