package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@WebServlet("/mypage")
public class MypageControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
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
