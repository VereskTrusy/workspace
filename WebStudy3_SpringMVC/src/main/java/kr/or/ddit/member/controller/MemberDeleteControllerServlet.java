package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
public class MemberDeleteControllerServlet {
	
	@Autowired
	private MemberService service;

	@RequestMapping("/member/memberDelete.do")
	protected String doPost(Principal principal
			, @RequestParam String password
			, RedirectAttributes redirectAttributes
	) {
		String memId = principal.getName();
		String viewName = null;
		
//		3. 로직 사용
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(password);
		ServiceResult result = service.removeMember(inputData);
//		4. 로직으로부터 확보한 모델을 공유
		switch (result) {
		case INVALIDPASSWORD:
			redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
			viewName = "redirect:/mypage";
			break;
		case FAIL:
			redirectAttributes.addFlashAttribute("message", "서버 오류");
			viewName = "redirect:/mypage";
			break;

		default:
			viewName = "forward:/login/logout.do";
			break;
		}
		
		return viewName;
	}
}
