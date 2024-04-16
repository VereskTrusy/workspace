package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lprod.controller.OthersControllerAdvice;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.ProdVO;



@WebServlet("/prod/prodInsert.do")
public class ProdInsertController extends HttpServlet{
	ProdService service = new ProdServiceImpl();
	private OthersControllerAdvice advice = new OthersControllerAdvice();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		advice.addBuyerList(req);
		advice.addLprodList(req);
		
		String viewName = "prod/prodForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		advice.addBuyerList(req);
		advice.addLprodList(req);
		
		req.setCharacterEncoding("UTF-8");
		
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		PopulateUtils.populate(prod, parameterMap);
		
//		prod.setProdId("test");
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		boolean valid = ValidateUtils.validate(prod, errors, InsertGroup.class);
		req.setAttribute("errors", errors);
		String viewName = null;
		if (errors.isEmpty()) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case FAIL:
				req.setAttribute("message", "서버오류");
				viewName = "member/memberForm";
				break;
			default: 
				viewName = "redirect:/prod/prodDetail.do?what=" + prod.getProdId();
				break;
			}
		} else {
			viewName = "prod/prodForm";
		}
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
