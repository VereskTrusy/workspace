package kr.or.ddit.prod.controller;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lprod.controller.OthersControllerAdvice;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateControllerServlet extends HttpServlet{

	public static final String MODELNAME = "prod";
	
	@Autowired
	private ProdService service;
	
	@GetMapping
	protected String formHandler(
			@Valid @RequestParam(name = "what") String prodId
			, Model model
	) {
		ProdVO prod = service.retriveProd(prodId);
		model.addAttribute(MODELNAME, prod);
		return "prod/prodEdit";
	}
	
	@PostMapping
	protected String UpdateProcess(
			@Validated(UpdateGroup.class) @ModelAttribute(MODELNAME) ProdVO prod
			, BindingResult errors
			, Model model
	) {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		if (valid) {
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case FAIL: 
				model.addAttribute("message", "서버오류");
				viewName = "prod/prodForm";
			default: 
				viewName = "redirect:/prod/prodDetail.do?what=" + prod.getProdId();
			}
		} else {
			viewName = "prod/prodForm"; // 리퀘스트에
		}
		
		return viewName;
	}
}
