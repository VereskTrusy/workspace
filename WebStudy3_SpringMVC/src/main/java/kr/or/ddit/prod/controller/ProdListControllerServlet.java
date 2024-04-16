package kr.or.ddit.prod.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProdListControllerServlet extends HttpServlet {

	@Autowired
	private final ProdService service;

	@RequestMapping("/prod/prodList.do")
	protected String doGet(Model model) {
		List<ProdVO> prodList = service.retriveProdList();
		model.addAttribute("prodList", prodList);
		return "prod/prodList";
	}
}
