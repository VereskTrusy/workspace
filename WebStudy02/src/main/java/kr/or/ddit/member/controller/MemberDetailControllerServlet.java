package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDetail.do")
public class MemberDetailControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// QueryString의 파라메터 받기
		String memId = req.getParameter("who");
		
		// 파라메터 null 검증
		if(StringUtils.isBlank(memId)) {
			resp.sendError(400);
			return;
		}
		
		// 서비스 실행
		MemberVO member = null;
		try {
			member = service.retriveMember(memId);
		}catch(PkNotFoundException e) {
			resp.sendError(e.getStatus(), e.getMessage());
			return;
		}
		
		// Attribute 태우기 : scope 결정 -> request 왜? application,session단위에서 데이터가 남아 있을 필요가 없어서.
		req.setAttribute("member", member);
		req.setAttribute("who", member.getMemId());
		
		// Content 협상 : Accept 헤더를 통해 판단.
		String accept = req.getHeader("accept");
		String viewName = null;
		if(accept.contains("json")) {
			viewName = "/jsonView.do"; // 하는일 : request to serialize, response wriete.
		}
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
