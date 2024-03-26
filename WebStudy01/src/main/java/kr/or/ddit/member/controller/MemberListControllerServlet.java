package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * C : /member/memberInsert.do
 * 		Get
 * 		Post
 * R - 단건 : /member/memberDetail.do?who=a001
 * 		Get 
 * R - 다건 : /member/memberList.do
 * 		Get
 * U : /member/memberUpdate.do
 * 		Get
 * 		Post
 * D : /member/memberDelete.do
 * 		Post
 * 
 * 
 */
@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl(); // 서비스
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 멤버 조회를 위한 리스트의 폼으로 이동
		List<MemberVO> memberList = service.retriveMemberList();
		
		// scope 결정
		req.setAttribute("memberList", memberList);
		
		// view 결정
		// flow control
		String viewName = "/WEB-INF/views/member/memberList_bak.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
