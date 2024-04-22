package kr.or.ddit.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.paging.DefaultPaginationRenderer;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.paging.PaginationRenderer;
import kr.or.ddit.paging.SimpleCondition;
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
			, @ModelAttribute("paging") PaginationInfo paging
	) {		
		List<MemberVO> memberList = service.retriveMemberList(paging);
		model.addAttribute("memberList", memberList);
		
		PaginationRenderer render = new DefaultPaginationRenderer();
		model.addAttribute("pagingFunction", "paging");
		String pagingHTML = render.renderPagination(paging, "paging");
		model.addAttribute("pagingHTML", pagingHTML);
		
		return "member/memberList_bak";
	}
}
