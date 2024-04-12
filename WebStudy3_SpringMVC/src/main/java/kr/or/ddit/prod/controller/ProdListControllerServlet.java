package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@WebServlet("/prod/prodList.do")
public class ProdListControllerServlet extends HttpServlet{

	@Autowired
	private final ProdService service;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		List<ProdVO> prodList = service.retriveProdList();
		// scope
		req.setAttribute("prodList", prodList);
		// view
		String viewName = "prod/prodList";
		// view controll
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 검색용?
	}
}
