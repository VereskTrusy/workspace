package kr.or.ddit.servlet10;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.servlet10.service.PropertyService;
import kr.or.ddit.servlet10.service.PropertyServiceImpl;

@WebServlet("/15/jdbcDesc.do")
public class JdbcDescServlet extends HttpServlet{
	
	private ServletContext application;
	private PropertyService service = new PropertyServiceImpl();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, Object> paramMap = new HashMap<>();
		service.readProperties(paramMap);
		
		for(Entry<String,Object> entry : paramMap.entrySet()) {
			req.setAttribute(entry.getKey(), entry.getValue());
		}
		
		String accept = req.getHeader("accept");
		// 이동할 View 지정
		String viewName = null;
		if(accept.contains("json")) {
			viewName = "/jsonView.do";
		} else {
			// View 로 이동
			viewName = "/WEB-INF/views/15/jdbcDesc.jsp";
		}
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
