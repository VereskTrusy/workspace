package kr.or.ddit.case07;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.case07.vo.AddressbookVO;

@Controller
@RequestMapping("/case07/handler1")
public class FormDataBindingController {

	/**
	 * form:form 형태의 커스텀태그 사용
	 * 1. form의 modelAttribute 속성으로 사용할 모델 전달
	 * 2. form:input 커스텀태그 path 속성이 command object의 프로퍼티로 반영됨.
	 * @return
	 */
	@GetMapping
	public String formHandler(Model model) {
		model.addAttribute("adrs", new AddressbookVO());
		
		
		return "case07/formView2";
	}
	
	/**
	 * post요청을 통해서 전송된 여러개의 파라미터를 하나의 객체로 바인딩해서 받기위한 객체 : command Object ==> model attribute로 반영됨
	 * @ModelAttribute 를 이용해서 command object 의 모델명을 변경한다.
	 * 1. @Valid / @Validated 를 커맨드 오브젝트 앞에 사용한다.
	 *    (그룹 힌트 적용 가능)
	 * 2. 검증 대상이 되는 커맨드 오브젝트 바로 다음에 Errors / BindingResult 파라미터를 통해 받는다.
	 * @param adrs
	 * errors
	 * @return
	 */
	@PostMapping
	public String process(@Validated @ModelAttribute("adrs") AddressbookVO adrs, BindingResult errors
			, @RequestHeader String accept
			, RedirectAttributes redirectAttributes
	) {
		// 검증된 결과를 클라이언트에서 확인 할 수 있는 이름
		// BindingResult.MODEL_KEY_PREFIX+"adrs" // errors가 request에 담길때 이름이다.
		
		
		if(!errors.hasErrors()) {
			return null;
		} else {
			redirectAttributes.addFlashAttribute("adrs", adrs);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX+"adrs", errors);
			return "redirect:/case07/handler1";
		}
	}
}
