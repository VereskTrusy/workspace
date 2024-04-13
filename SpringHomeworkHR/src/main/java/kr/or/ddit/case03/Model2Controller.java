package kr.or.ddit.case03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/case03")
public class Model2Controller {

	@GetMapping("view1")
	public String handler1() {
		return "case03/view1";
	}
	
	@RequestMapping("view2")
	public void handler2() {
		// logical view name : case03/view2
	}
	
	
	@RequestMapping("view-3")
	public ModelAndView handler3() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("case03/view3");		
		return mav;
	}
}
