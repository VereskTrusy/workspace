package kr.or.ddit.servlet01;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/now.html")
public class DynamicServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Date now = new Date(); // data
		String info = now.toString(); // information
		String content = "<html><body>" + info + "</body></html>";
		
		resp.getWriter().println(content); // 바이트스트림, 캐릭터스트림 중 현재는 캐릭터스트림 사용
		
	}

}
// 1. 웹 요청 받기
// 2. 웹 응답 하기
// 상속 이란 구조 이용

// 현재 서버의 접속시간을 응답데이터로 내보내기
// 데이터 -> 가공 -> 정보 -> 컨텐츠화(클라이언트에 맞춰서) -> 메시지(브라우저가 이해할 수 있는 메시지) 

// 웹자원
