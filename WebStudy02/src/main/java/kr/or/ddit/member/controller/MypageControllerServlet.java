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
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage")
public class MypageControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(400);
			return;
		}
		
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		MemberVO member = null;
		String viewName = null;
		if(authMember == null) {
			viewName = "redirect:/login/loginForm.jsp";
		}else {
			try {
				member = service.retriveMember(authMember.getMemId());
			}catch(PkNotFoundException e) {
				resp.sendError(e.getStatus(), e.getMessage());
				return;
			}
			req.setAttribute("member", member);
			
			viewName = "/WEB-INF/views/member/mypage.jsp";
		}
		
		if (viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
		
		// req 챠셋하기
		// ㅅㅔ션에서 authMember 꺼내기
		// 멤버 조회하기
		// rq에 담기
		// 또 뷰네임
		// 포워드로 날라가기
	}
}
