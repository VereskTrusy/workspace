package kr.or.ddit.case04;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * MVC 패턴
 * M : infomation
 * V : UI
 * C : infomation + UI
 */
@Controller
@RequestMapping("/case04")
public class MVCPatternController {
	
	@RequestMapping("model3")
	public ModelAndView handler3(Model model) {
		String info = "가공된 인포메이션";
		ModelAndView mav = new ModelAndView();
		mav.addObject("info", info);
		mav.setViewName("case04/view1");
		return mav;
	}
	
	@RequestMapping("model2")
	public String handler2(Model model) {
		String info = "가공된 인포메이션";
		model.addAttribute("info", info);
		return "case04/view2";
	}
	
	@RequestMapping("model1")
	public String handler1(HttpServletRequest req) {
		String info = "가공된 인포메이션";
		req.setAttribute("info", info);
		return "case04/view1";
	}
	
}
