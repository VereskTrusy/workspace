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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lprod.controller.OthersControllerAdvice;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@WebServlet("/prod/prodUpdate.do")
public class ProdUpdateControllerServlet extends HttpServlet{


	@Autowired
	private final ProdService service;
	private OthersControllerAdvice advice = new OthersControllerAdvice();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodId = req.getParameter("what");
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400, "잘못된 파라미터");
			return;
		}
		
		ProdVO prod = service.retriveProd(prodId);
		
		advice.addBuyerList(req);
		advice.addLprodList(req);
		
		req.setAttribute("prod", prod);
		
		String viewName = "prod/prodForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		ProdVO prod = new ProdVO(); // command Object
		req.setAttribute("prod", prod);
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(prod, parameterMap);
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		boolean valid = ValidateUtils.validate(prod, errors, UpdateGroup.class); // errors -> call by referense 
		req.setAttribute("errors", errors);
		
		String viewName = null;
		if (errors.isEmpty()) {
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case FAIL: 
				req.setAttribute("message", "서버오류");
				viewName = "prod/prodForm";
				break;
			default: 
				viewName = "redirect:/prod/prodDetail.do?what=" + prod.getProdId();
				break;
			}
		} else {
			viewName = "prod/prodForm"; // 리퀘스트에
		}
		
		new ViewResolverComposite().resolveView(viewName, req, resp);
		
	}
}
