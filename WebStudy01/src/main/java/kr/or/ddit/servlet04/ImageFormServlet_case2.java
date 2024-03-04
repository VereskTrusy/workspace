package kr.or.ddit.servlet04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
 * case 1 : model1 + servlet spec\
 * case 2 : template 구조 활용
 * case 3 : jsp 스펙 활용
 * case 4 : servlet + jsp 스펙 동시 사용 --> model 2 방식이다
 * case 5 : 비동기 처리
 *
 */
//@WebServlet("/case2/imageForm.do")
@WebServlet("/04/imageForm.tmpl") // 정적자원을 서비스 할때의 주소를 ㅂ다아서 쓰면 이제 동적ㅇ자원을 서비스하는 유알엘ㄹ이된다
public class ImageFormServlet_case2 extends HttpServlet{
	
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	// 리퀘스트 콜백 메서드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
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
		
		// 1. 템플릿(파일)을 읽는다.(읽는디 톰켓 경로에따라 진짜주소가 달라지니까 ServletContext에서 찾아야한다.)
		// /04/imageForm.tmpl
		String realPath = application.getRealPath("/04/imageForm.tmpl"); // 논리주소를 통해서 실제 물리주소를 받아온다
		File tempFile = new File(realPath);
		try(
			FileReader fr = new FileReader(tempFile);
			BufferedReader br = new BufferedReader(fr);
			PrintWriter out = resp.getWriter();
		){
			String line = null;
			while((line = br.readLine()) != null) {
				html.append(String.format("%s\n", line));
			}
			
			// 2. 뚤려있는곳에 식별자 규칙대로 [%cPath%], [%options%] 여기에 데이터를 치환한다.
			String content = html.toString().replace("[%cPath%]", req.getContextPath())
						   .replace("[%options%]", options);
			
			// 3. 치환된 후의 완전한 컨텐츠를 전송한다.
			out.println(content);
		}
		
		
		
	}
}
/*
 * 1. 화면 전환 x
 * 2. 버튼 클릭 이벤트
 * 
 * 
 * 템플릿 사용
1. 읽고
2. 식별자 채우고
3. 이 결과로 UI전송

여기서 실행시키면 정적자원으로 서비스된다

http://localhost/WebStudy01/04/imageForm.tmpl
 * */
