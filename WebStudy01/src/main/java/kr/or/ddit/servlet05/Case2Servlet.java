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

@WebServlet("/06/case2Req.do")
public class Case2Servlet extends HttpServlet{
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 출력해보기 // name : p1 , value : 23
		req.setCharacterEncoding("UTF-8");
		
		String ptrn = "name : %s , value: %s\n";
		
//		Map<String, String[]> paramMap = req.getParameterMap();
//		
//		for(Entry<String, String[]>entry : paramMap.entrySet()) {
//			String paramName = entry.getKey();
//			String[] paramValues = entry.getValue();
//			System.out.println(String.format(ptrn, paramName, Arrays.toString(paramValues)));
//		}
		
		// suger syntax
		req.getParameterMap().forEach((k,v)->{
			System.out.printf(ptrn, k, Arrays.toString(v));
		});
		
		String accept = req.getHeader("Accept");
		String contentType = null;
		String content = null;
		
		if(accept.contains("application/json")) {
			contentType = "application/json;charset=UTF-8";
			content = "{\"result\":\"처리성공\"}";
			
		} else if(accept.contains("text/html")) {
			contentType = "text/html;charset=UTF-8";
			content = "<html><body><h4>처리성공</h4></body></html>";
		}
		
		resp.setContentType(contentType);
		
		try(PrintWriter out = resp.getWriter()){
			out.print(content);
		}
		
	}
}















