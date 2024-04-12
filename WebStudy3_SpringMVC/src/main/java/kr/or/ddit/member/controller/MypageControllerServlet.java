package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@WebServlet("/mypage")
public class MypageControllerServlet extends HttpServlet{

	@Autowired
	private final MemberService service;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "";	
		MemberVOWrapper principal = (MemberVOWrapper) req.getUserPrincipal();
		MemberVO member = service.retriveMember(principal.getName());
		req.setAttribute("member", member);
		viewName = "member/mypage";
		
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
