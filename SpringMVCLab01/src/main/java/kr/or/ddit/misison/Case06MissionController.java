package kr.or.ddit.misison;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mission/case06")
public class Case06MissionController {
	
	@GetMapping
	public String formHandler() {
		return "case06/missionForm";
	}
	
	@PostMapping
	public String process(
			@RequestParam(name = "leftOp", required = true) double leftOp,
			@RequestParam(name = "rightOp", required = true) double rightOp,
			RedirectAttributes redirectAttributes) {
		
		double sum = 0;
		sum = leftOp + rightOp;
		
		redirectAttributes.addFlashAttribute("leftOp", leftOp);
		redirectAttributes.addFlashAttribute("rightOp", rightOp);
		redirectAttributes.addFlashAttribute("result", sum);
		
		return "redirect:/mission/case06";
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public void process2(
			@RequestParam(name = "leftOp", required = true) double leftOp,
			@RequestParam(name = "rightOp", required = true) double rightOp,
			Model model) {
		
		double sum = 0;
		sum = leftOp + rightOp;
		
		model.addAttribute("leftOp", leftOp);
		model.addAttribute("rightOp", rightOp);
		model.addAttribute("result", sum);
	}

}
