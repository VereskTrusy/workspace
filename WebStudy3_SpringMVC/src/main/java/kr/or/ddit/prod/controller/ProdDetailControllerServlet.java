package kr.or.ddit.prod.controller;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProdDetailControllerServlet extends HttpServlet{
	
	@Autowired
	private final ProdService service;
	
	@RequestMapping("/prod/prodDetail.do")
	protected String doGet(
			@Valid @RequestParam(name = "what", required = true) String prodId
			, Model model
	) {
		ProdVO prod = service.retriveProd(prodId);
		model.addAttribute("prod", prod);
		return "prod/prodDetail";
	}
}
// 공유기 설정 > nat > 외부아이피 설정 > 리셋 > 외부아이피 확인