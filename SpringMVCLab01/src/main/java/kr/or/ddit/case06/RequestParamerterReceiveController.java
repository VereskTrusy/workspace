package kr.or.ddit.case06;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case6")
public class RequestParamerterReceiveController {
	
	@RequestMapping("param4")
	public String handler4(Optional<Integer> numParam) {
		int number = numParam.orElse(1);
		log.info("numParam : {}", number);
		return "jsonView";
	}
	
	@RequestMapping("param3")
	public String handler3(@RequestParam(defaultValue = "0") int numParam) {
		log.info("numParam : {}", numParam);
		return "jsonView";
	}
	
	@RequestMapping("param2")
	public void handler2(Model model, @RequestParam(required = false, defaultValue = "DEFAULT") String what) {
		log.info("what : {}", what);
		model.addAttribute("what", what);
	}

	@GetMapping("param1")
	public String handler(@RequestParam(required = true) String who, Model model) {
		
		log.info("who : {}", who);
		model.addAttribute("who", who);
		
		return "jsonView";
	}
	
	
}
