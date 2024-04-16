package kr.or.ddit.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.ViewResolverComposite;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;

/**
 * Controller에서 프로세스 흐름
 * 1. 요청 접수, 분석 
 * 2. 검증 
 * 3. 로직 사용(model 확보) 
 * 4. scope를 이용하여 model 공유 
 * 5. view 결정 
 * 6. view 로 이동(flow Control)
 */
@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController {
	
	@Autowired
	private MemberService service;
	
	// 핸들러 어댑터는 리퀘스트에 member가 있으면 자동으로 추가, 없으면 스킵한다.
	@ModelAttribute("member")
	private MemberVO member() {
		return new MemberVO();
	}

	// 회원 가입 폼으로 이동
	// 
	@GetMapping
	protected String formHandler() { 
		return "member/memberForm";
	}

	// 회원 가입 진행
	@PostMapping
	protected String insertProcess(
			@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
			, BindingResult errors
			, Model model
			, RedirectAttributes redirectAttributes
	) {
		String viewName = null;
		if (errors.hasErrors()) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				redirectAttributes.addFlashAttribute("member", member);
				redirectAttributes.addFlashAttribute("message", "아이디 중복, 바꾸셈.");
				viewName = "redirect:/member/memberInsert.do";
				break;
			case FAIL: 
				model.addAttribute("message", "서버 오류, 잠시 뒤 다시 가입하세요.");
				viewName = "member/memberForm";
				break;
			default: 
				viewName = "redirect:/index.do";
				break;
			}
		} else {
			redirectAttributes.addFlashAttribute("member", member);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX+"member", errors);
			viewName = "redirect:/member/memberInsert.do";
		}
		return viewName;
	}
}
