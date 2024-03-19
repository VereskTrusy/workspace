package kr.or.ddit.servlet04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * case 1 ~ 4 : 동기처리
 * case 1 : model1 + servlet spec\
 * case 2 : template 구조 활용
 * case 3 : jsp 스펙 활용
 * case 4 : servlet + jsp 스펙 동시 사용 --> model 2 방식이다
 * case 5 : 비동기 처리
 *
 */
@WebServlet("/case4/imageForm.do")
public class ImageFormServlet_case4 extends HttpServlet{
	
	private ServletContext application;
	private String imageFolder;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		imageFolder = application.getInitParameter("imageFolder");
	}
	
	// 리퀘스트 콜백 메서드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Cookie[] cookies = req.getCookies();
		String findedName = null;
		if(cookies != null) {
			for(Cookie single : cookies) {
				if("imageCookie".equals(single.getName())) {
					 findedName = URLDecoder.decode(single.getValue(), "UTF-8");
					 break;
				}
			}
		}
		req.setAttribute("imageCookieValue", findedName);
		
		resp.setContentType("text/html;charset=UTF-8");
		
		File folder = new File(imageFolder);
		String[] filelist = folder.list(new FilenameFilter() { // 익명객체 : 특징 : 해당 객체를 여기서만 사용한다.
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("image/");
			}
		});
		StringBuffer options = new StringBuffer();
		String optPtrn = "\n<option>%s</option>";
		for(String item : filelist) {
			options.append( String.format(optPtrn, item) );
		}
		
		req.setAttribute("options", options);
		req.setAttribute("cPath", req.getContextPath());
		
		String viewName = "/WEB-INF/views/04/imageForm.jsp";
		// 톰켓의 정보를 가져오기 위해서 dispatcher라는 놈을 불러온거다.
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}

