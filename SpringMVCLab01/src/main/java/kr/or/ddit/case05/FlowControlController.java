package kr.or.ddit.case05;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * 일반적으로 controller 에서 사용하는 이동방식
 * forward : controller > view 로 이동하는 경우(logical view name 으로 이동)
 *    controller > controller 로 이동하는 경우 
 * redirect :
 * 
 * redirectAttribute 플래시맵메니저
 */
@Slf4j
@Controller
@RequestMapping("/case05")
public class FlowControlController {
	
	@RequestMapping("start1")
	public String handler1(Model model) {
		model.addAttribute("");
		log.info("start1 요청 접수");
		return "forward:/case05/dest1";
	}
	
	@RequestMapping("dest1")
	public void handler2() {
		log.info("dest1 요청 접수");
	}
	
	@RequestMapping("start2")
	public String handler3(Model model) {
		model.addAttribute("");
		log.info("start1 요청 접수");
		return "redirect:/case05/dest1";
	}
	
	@RequestMapping("dest2")
	public void handler4(Model model) {
		log.info("dest1 요청 접수");
	}
	
	@RequestMapping("start3")
	public String handler5(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("info", "start 3 에서 만든 요청");
		log.info("start3 요청 접수");
		return "redirect:/case05/dest3";
	}
	
	@RequestMapping("dest3")
	public void handler6(Model model) {
		log.info("dest3 요청 접수");
		model.asMap().forEach((k,v)->{log.info("{} : {}", k, v);});
	}
}
