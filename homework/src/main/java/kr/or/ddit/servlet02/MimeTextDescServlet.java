package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * MIME text 란?
 * 
 * 웹이란 네트웍사이에 동떨어진 
 * peer 와 peer 사이에 전송되는 content 의 다양한 형태가 존재하고,
 * content 전송시에는 반드시 해당 컨텐츠의 형태나 종류(형식)을 표현할 수 있는 메타데이터(header)를 전송함.
 * ex) Content-type : 컨텐츠 종류 표현 방식
 * 			MIME text 방식으로 표현
 * 			main_type/sub_type;charset=UTF-8 // html 5 버전 이후엔 UTF-8 이 기본으로 통일되고 있다.
 * 			ex) 전송 content : html -> text/html;
 * 			ex) 전송 content : text -> text/plain;
 * 			ex) 전송 content : json -> text/json; -> application/json;charset=UTF-8 으로 요즘엔 이렇게 사용한다.
 * 			ex) 전송 content : xml -> application/json;charset=UTF-8 // charset 이 필요한 경우는 2바이트 이상일 때 필요하다. 
 * 			ex) 전송 content : image -> image/jpeg[gif];
 * 			ex) 전송 content : mp3 -> image/mpeg;
 * 
 * // name = "mimeTextDescServlet" : servlet name 의 기본값은 서블릿 명이다.
 */
//@WebServlet(name = "mimeTextDesc", urlPatterns = "/mine", loadOnStartup = 1
//	, initParams = {@WebInitParam(name="p1", value="VALUE")}
//		) 
public class MimeTextDescServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException { // ServletConfig 은 서블릿과 1대1관계로, 서블릿당 하나씩 생성된다.
		super.init(config); // init() 메서드에서는 상위 클래스에서 사용하는 일정한 작업들이 필요해서 init()메서드를 없애면 안된다.
		System.out.printf("%s \n", config.getServletName()); // formating 메서드
		System.out.printf("p1 : %s\n", config.getInitParameter("p1"));
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.printf("service 메소드 실행, request method : %s\n", req.getMethod());
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 메소드 실행");
		
		String mime = "text/plain;charset=UTF-8"; // alt+Enter : 우리가 사용하는 캐릭터 셋이 지금 UTF-8 버전이기 때문, 
		resp.setContentType(mime);
		resp.setContentLength(6);
		
		String data = "데이터";
		StringBuffer content = new StringBuffer();
		content.append("<html>");
		content.append("<body>");
		content.append(String.format("<h4>%s</h4>", data));
		content.append("</body>");
		content.append("</html>");
		
		// String content = "<html>";
		// content += "</html>"; // + : 문자열 + 문자열 일때 concat연산자로 작동, String 타입은 쓰면 쓸수록 퍼포먼스가 떨어진다.
		
		PrintWriter out = resp.getWriter(); // 캐릭터 스트림
		out.print(content);
		out.close(); // 이때 응답 데이터가 나간다.
		
		//resp.getOutputStream(); // 바이트 스트림
	}
}
