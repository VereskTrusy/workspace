package kr.or.ddit.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 롬복 로그 생성
@Controller
@RequiredArgsConstructor
@RequestMapping("/member/memberList.do")
public class MemberListControllerServlet{
	
	@Autowired
	private final MemberService service; // 서비스
	
	@GetMapping
	public String doGet(
			Model model
	) {
		List<MemberVO> memberList = service.retriveMemberList();
		
		model.addAttribute("memberList", memberList);
		
		return "member/memberList_bak";
	}
}
