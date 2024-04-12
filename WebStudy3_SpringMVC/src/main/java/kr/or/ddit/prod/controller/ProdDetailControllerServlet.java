package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@WebServlet("/prod/prodDetail.do")
public class ProdDetailControllerServlet extends HttpServlet{
	
	@Autowired
	private final ProdService service;
	
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
		String viewName = "prod/prodDetail";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
