package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;

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

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
  조건 : 의사코드 작성
  MemberInsertControllerServlet 를 보고 어떤 코드가 중복되는지 확인 할것
  
  doGet 메서드로 요청이 들어 옴
  request의 캐릭터 셋을 UTF-8로 맞춘다.
  파라미터로 회원아이디를 받는다
  파라미터의 회원아이디가 null인지 검사한다.
  파라미터가 null이 아니라면 현재 등록되어있는지 조회한다.
  		서비스를 실행하여 단일 회원 조회 후 있다면 로직을 계속 수행한다
  			memberVo에 파라미터로 받은 정보를 받는다. 
  		단일 회원이 존재 하지 않으면 잘못된 요청에 대한 처리를 진행한다.
  리퀘스트에 회원정보를 저장한다.
  view 수정폼으로 포워드 이동한다
  
  
  doPost 메서드로 요청이 들어 옴
  파라미터로 회원정보를 받는다
  파라미터의 각 필수정보가 null인지 검사한다.
  파라미터가 null이 아니라면 현재 등록되어있는지 조회한다.
  파라미터 중 아이디,패스워드 로 서비스 조회(단일회원 조회) 
  		일치 -> memberVo에 파라미터로 받은 정보를 받는다.
  		불일치 -> 단일 회원이 존재 하지 않으면 잘못된 요청에 대한 처리를 진행한다.
  파라미터 가 담긴 memberVo를 update 서비스를 사용하여 처리한다.
  		업데이트 결과
  			성공 시 성공메시지를 세션에 저장한다.
  			실패 시 실패 메시지를 리퀘스트에 담는다

**/
@Slf4j
@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateControllerServlet {
	
	@Autowired
	private MemberService service;

	// 회원 가입 폼으로 이동
	@GetMapping
	protected String doGet(Model model, Principal principal) throws ServletException, IOException {
		if(!model.containsAttribute("member")) {
			String memId = principal.getName();
			MemberVO member = service.retriveMember(memId);
			model.addAttribute("member", member);			
		}
		return "member/memberForm";
	}

	// 회원 가입 진행
	@PostMapping
	protected String doPost(@Validated(UpdateGroup.class) @ModelAttribute("Member") MemberVO member
			, BindingResult errors
			, RedirectAttributes redirectAttributes
			, Model model
	) {
		String viewName = null;
		if (!errors.hasErrors()) { // 에러가 없다 == 검증이 끝났다.
			// 4. scope를 이용하여 model 공유
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				redirectAttributes.addFlashAttribute("message", "비밀번호가 잘못됨");
				viewName = "redirect:/member/memberUpdate.do";
				break;
			case FAIL: // 현재는 fail상황 없음. 그보다 먼저 SQLException 발생, 현티어구조때문임. 
				model.addAttribute("message", "서버오류");
				viewName = "member/memberForm";
				break;
			default: 
//				req.getSession().setAttribute("lastCreated", member);
				viewName = "redirect:/mypage";
				break;
			}
		} else {
			redirectAttributes.addFlashAttribute("member", member);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX+"member", errors);
			viewName = "redirect:/member/memberUpdate.do"; // 리퀘스트에
		}
		
		// 5. view 결정
		// 6. view 로 이동(flow Control)
		// Front Controller Pattern 을 통해서 하위의 모든 컨트롤러에서 중복되는 코드를 해결할 예정.
		return viewName;
	}
}
