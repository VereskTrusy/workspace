package kr.or.ddit.servlet04;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * case 1 ~ 4 : 동기처리
 * case 1(model1 방식) : model1 + servlet spec\
 * case 2(model2 방식) : template 구조 활용
 * case 3(model1 방식) : jsp 스펙 활용
 * case 4(model2 방식) : servlet + jsp 스펙 동시 사용 --> model 2 방식이다
 * case 5 : 비동기 처리
 *
 */
@WebServlet("/case1/imageForm.do")
public class ImageFormServlet_case1 extends HttpServlet{
	
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	// 리퀘스트 콜백 메서드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		File folder = new File("F:/00.medias/images");
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
		
		StringBuffer html = new StringBuffer();
		html.append("<html>");
		html.append("\n    <body>");
		html.append(String.format("\n    <form action='%s/image.do' method='%s'>", req.getContextPath(), "GET"));
		html.append("\n        <select name='name'>");
		html.append(options);
		html.append("\n        </select>");
		html.append("\n	            <button type='submit'>이미지 랜더링</button>");
		html.append("\n             <img src=''></img>");
		html.append("\n	       </form>");
		html.append("\n        <script src='../resources/js/app/04/imageForm.js'></script>");
		html.append("\n    </body>");
		html.append("\n</html>");
		
		try(PrintWriter out = resp.getWriter(); 
		){
			out.println(html);
		}
	}
	
}
/*
 * 1. 화면 전환 x
 * 2. 버튼 클릭 이벤트
 * */
