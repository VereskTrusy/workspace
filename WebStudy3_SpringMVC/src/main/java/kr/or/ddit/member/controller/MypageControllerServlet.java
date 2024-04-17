package kr.or.ddit.member.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageControllerServlet {

	@Autowired
	private final MemberService service;
	
	@RequestMapping("/mypage")
	protected String mypage(Principal principal, Model model) { // 핸들러어댑터는 리퀘스트에서 확보할 수 있는 모든 것들은 가져오는게 가능하다.
		
		MemberVO member = service.retriveMember(principal.getName());
		
		model.addAttribute("member", member);
		
		return "member/mypage";
	}
}
