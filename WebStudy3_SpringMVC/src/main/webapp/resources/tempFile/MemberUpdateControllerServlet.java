package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * 1. 요청 접수, 분석
 * 2. 검증
 * 3. 로직 사용(model 확보)
 * 4. scope를 이용해 model 공유
 * 5. view 결정
 * 6. view 이동(flow control)
 *
 */
@WebServlet("/member/memberUpdate.do")
public class MemberUpdateControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("who");
		MemberVO memberInfo = service.retrieveMember(memId);
		req.setAttribute("member", memberInfo);
		
		String viewName = "/WEB-INF/views/member/memberForm.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		* 1. 요청 접수, 분석
		req.setCharacterEncoding("UTF-8");
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(member, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		System.out.println(member);
//		* 2. 검증
		Map<String, String> errors = new LinkedHashMap<String, String>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		String viewName = null;
		if (errors.isEmpty()) {
//	        *    3. 로직 사용(model) 
//	          1) 먼저 의존관계 형성 (맨위 서비스 호출)
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "존재하지 않는 계정, 계정확인해라");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;
				
			case INVALIDPASSWORD:
				req.setAttribute("message", "비밀번호 불일치, 비번 까먹었음?ㅋ");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;

			case FAIL:
				req.setAttribute("message", "서버 오류, ㅈㅅㅈㅅ헤헷>.<");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;

			default:
				req.getSession().setAttribute("lastCreated", member);
				viewName = "redirect:/member/memberList.do";
				break;
			}
//			 * 4. scope를 이용해 model 공유

		} else {
			viewName = "/WEB-INF/views/member/memberForm.jsp";
		}
//		 * 5. view 결정
//		 * 6. view로 이동(flow control)
		if(viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}		
	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
			errors.put("memId", "회원번호가 누락됐잖니?");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "암호가 누락됐잖니?");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			valid = false;
			errors.put("memName", "회원명이 누락됐잖니?");
		}
		if (StringUtils.isBlank(member.getMemZip())) {
			valid = false;
			errors.put("memZip", "우편번호가 누락됐잖니?");
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			valid = false;
			errors.put("memAdd1", "기본주소가 누락됐잖니?");
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			valid = false;
			errors.put("memAdd2", "상세주소가 누락됐잖니?");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "메일주소가 누락됐잖니?");
		}
		return valid;
	}
}
