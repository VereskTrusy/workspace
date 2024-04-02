package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodDetail.do")
public class ProdDetailControllerServlet extends HttpServlet{
	ProdService service = new ProdServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 받기
		String prodId = req.getParameter("what");
		// 검증
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400, "잘못된 파라미터");
			return;
		}
		
		// 비즈니스로직 태우기
		ProdVO prod = service.retriveProd(prodId);
		
		// scope 설정
		req.setAttribute("prod", prod);
		
		// view
		String viewName = "/WEB-INF/views/prod/prodDetail.jsp";
		
		// view controll
		if (viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else if(viewName.startsWith("forward:")) {
			String path = viewName.substring("forward:".length());
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
