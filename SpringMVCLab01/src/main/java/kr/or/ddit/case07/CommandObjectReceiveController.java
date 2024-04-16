package kr.or.ddit.case07;

import javax.validation.groups.Default;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.case07.vo.AddressbookVO;
import kr.or.ddit.case07.vo.BankInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case07")
public class CommandObjectReceiveController {
	
	@GetMapping("commandObject1")
	public String formHandler1(@ModelAttribute("bank") BankInfoVO bank) {
		return "case07/formView1";
	}
	
	@PostMapping("commandObject1")
	public String processHandler1(@Validated(Default.class) @ModelAttribute("bank") BankInfoVO bank, Errors errors) {
		log.info("bank : {}", bank);
		if(errors.hasErrors()) {
			// 검증실패
			return "case07/formView1";
		} else {
			// 검증통과
			return "case07/formResult1";
		}
	}
	
	@GetMapping("commandObject2")
	public String formHandler2(@ModelAttribute("addressbook") AddressbookVO addressbook) {
		return "case07/formView2";
	}
	
	@PostMapping("commandObject2")
	public String processHandler2(@Validated(Default.class) @ModelAttribute("addressbook") AddressbookVO addressbook, Errors errors) {
		if(!errors.hasErrors()) {
			// 검증통과
			return "case07/formResult2";
		} else {
			// 검증실패
			return "case07/formView2";
		}
		
	}
	
	@GetMapping("commandObject3")
	public String formHandler3(Model model) {
		if(!model.containsAttribute("addressbook")) {
			model.addAttribute("addressbook", new AddressbookVO());
		} else {
			log.info("검증실패후 리다이렉션 : model : {}, errors : {}", 
					model.getAttribute("addressbook"), 
					model.getAttribute(BindingResult.MODEL_KEY_PREFIX+"addressbook"));
		}
		return "case07/formView2";
	}
	
	@PostMapping("commandObject3")
	public String processHandler3(@Validated(Default.class) @ModelAttribute("addressbook") AddressbookVO addressbook, 
			Errors errors,
			RedirectAttributes redirectAttributes) {
		if(!errors.hasErrors()) {
			// 검증통과
			return "case07/formResult2";
		} else {
			// 검증실패
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX+"addressbook", errors);
			redirectAttributes.addFlashAttribute("addressbook", addressbook);
			return "redirect:/case07/commandObject3";
		}
	}
}
 