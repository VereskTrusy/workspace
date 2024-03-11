package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

@WebServlet("/06/case1Req.do")
public class Case1Servlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 출력해보기 // name : p1 , value : 23
		
		String ptrn = "name : %s , value: %s\n";
		
//		Map<String, String[]> paramMap = req.getParameterMap();
//		
//		for(Entry<String, String[]>entry : paramMap.entrySet()) {
//			String paramName = entry.getKey();
//			String[] paramValues = entry.getValue();
//			System.out.println(String.format(ptrn, paramName, Arrays.toString(paramValues)));
//		}
		
		// get 방식으로 넘어온 모든 파라메터들을 Map 형식으로 받는다.
		// suger syntax
		req.getParameterMap().forEach((k,v)->{
			System.out.printf(ptrn, k, Arrays.toString(v));
		});
		
		// 클라이언트로 데이터를 전달하기 전에 클라이언트에서 요청한 헤더를 읽어서
		// 해당 하는 데이터 형식으로 전달 한다.
		// 데이터 형식에 해당하는 정보는 Accept에 들어 잇당.
		String accept = req.getHeader("Accept");
		String contentType = null;
		String content = null;
		
		if(accept.contains("application/json")) { // accept 검사 후 분기
			contentType = "application/json;charset=UTF-8";
			content = "{\"result\":\"처리성공\"}";
			
		} else if(accept.contains("text/html")) {
			contentType = "text/html;charset=UTF-8";
			content = "<html><body><h4>처리성공</h4></body></html>";
		}
		
		// 응답을 클라이언트에서 요청한 데이터 형식으로 세팅
		resp.setContentType(contentType);
		
		// 바이너리 -> stream
		// 텍스트 -> writer
		// 응답 객체로 데이터 출력
		try(PrintWriter out = resp.getWriter()){
			out.print(content);
		}
		
	}
}















