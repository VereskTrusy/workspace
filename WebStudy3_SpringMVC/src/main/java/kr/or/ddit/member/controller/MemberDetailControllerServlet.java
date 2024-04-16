package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/memberDetail.do")
public class MemberDetailControllerServlet {
	
	@Autowired
	private final MemberService service;
	
	@GetMapping
	protected String doGet(
			HttpServletRequest req
			, @Valid @RequestParam("who") String memId
			, Model model
	) {
		MemberVO member = service.retriveMember(memId);
		model.addAttribute("member", member);
		return "jsonView"; 
	}
}
