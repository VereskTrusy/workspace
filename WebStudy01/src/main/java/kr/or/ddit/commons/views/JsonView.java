package kr.or.ddit.commons.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.servlet09.FileWrapper;

@WebServlet("/jsonView.do")
public class JsonView extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		marshalling(req, resp);
	}
	
	private void marshalling(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 응답데이터의 타입 지정
		resp.setContentType("application/json;charset=utf-8");
		
		// 요청의 모든 속성명 가져오기
		Enumeration<String> attrNames = req.getAttributeNames(); // Enumeration 는 이터레이터블하지 못하니깐 hasMoreElements() 로 뺑뺑이 
		Map<String, Object> targetMap = new HashMap<>();
		
		// 리퀘스트의 모든 속성에 대한 키/값을 Map에 담기
		while (attrNames.hasMoreElements()) {
			String name = (String) attrNames.nextElement();
			Object value = req.getAttribute(name);
			targetMap.put(name, value);
		}
		
		ObjectMapper mapper = new ObjectMapper(); // ObjectMapper 를 통한 직렬화 및 전송
		try(
			PrintWriter out = resp.getWriter(); // text를 전송 할 수 있는 stream 을 response 로 부터 받기
		){
			mapper.writeValue(out, targetMap);
		}
	}
	
//	private void marshalling(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		// 컨텐츠 타입 설정
//		resp.setContentType("application/json;charset=UTF-8");
//		
//		// 마샬링
//		List<FileWrapper> wrapperList = (List<FileWrapper>) req.getAttribute("wrapperList");
//		ObjectMapper mapper = new ObjectMapper();
//		//String json = mapper.writeValueAsString(wrapperList);
//		
//		// 직렬화
//		try(
//			PrintWriter out = resp.getWriter();
//		) {
//			//out.write(json);
//			mapper.writeValue(out, wrapperList);
//		}
//	}
}
