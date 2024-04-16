package kr.or.ddit.prod.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.ViewResolverComposite;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lprod.controller.OthersControllerAdvice;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController extends HttpServlet{
	
	public static final String MODELNAME = "newProd";
	@Autowired
	private final ProdService service;
	
	@GetMapping
	protected String formHandler(Model model) {
		model.addAttribute(MODELNAME, new ProdVO());
		return "prod/prodForm";
	}
	
	@PostMapping
	protected String insertProcess(
			@Validated(InsertGroup.class) @ModelAttribute(MODELNAME) ProdVO prod
			, BindingResult errors
			, Model model
	) {
		
		String viewName = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case FAIL:
				model.addAttribute("message", "서버오류");
				viewName = "member/memberForm";
				break;
			default: 
				viewName = "redirect:/prod/prodDetail.do?what=" + prod.getProdId();
				break;
			}
		} else {
			viewName = "prod/prodForm";
		}
		
		return viewName;
	}
}
