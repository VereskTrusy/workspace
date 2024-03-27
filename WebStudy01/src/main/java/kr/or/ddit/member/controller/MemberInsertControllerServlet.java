package kr.or.ddit.member.controller;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * 1. 요청 접수, 분석 2. 검증 3. 로직 사용(model 확보) 4. scope를 이용하여 model 공유 5. view 결정 6.
 * view 로 이동(flow Control)
 */
@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl();

	// 회원 가입 폼으로 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/WEB-INF/views/member/memberForm.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}

	// 회원 가입 진행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 접수, 분석
		req.setCharacterEncoding("UTF-8");
		MemberVO member = new MemberVO(); // command Object
		req.setAttribute("member", member);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(member, parameterMap); // 내부적으로 리플렉션 시킴
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException();
		}
		//System.out.println(member);
		
		// 2. 검증
		Map<String, String> errors = new LinkedHashMap<>();
		boolean valid = validate(member, errors);
		req.setAttribute("errors", errors);
		
		// 3. 로직 사용(model 확보)
		String viewName = null;
		if (errors.isEmpty()) { // 에러가 없다 == 검증이 끝났다.
			// 4. scope를 이용하여 model 공유
			// 서비스 실행
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED: // 아이디 중복
				req.setAttribute("message", "아이디중복");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;
			case FAIL: // 서버오류
				req.setAttribute("message", "서버오류");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;
			default: // 정상처리
				req.getSession().setAttribute("lastCreated", member);
				viewName = "redirect:/member/memberList.do";
				break;
			}
		} else { // 밸리데이션 검증 실패
			req.setAttribute("member", member);
			req.setAttribute("errors", errors);
			viewName = "/WEB-INF/views/member/memberForm.jsp"; // 리퀘스트에
		}
		
		// 5. view 결정
		// 6. view 로 이동(flow Control)
		if (viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}

	// 밸리데이션 검증
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;

		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
			errors.put("memId", "회원번호 누락");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "암호 누락");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			valid = false;
			errors.put("memName", "회원명 누락");
		}
		if (StringUtils.isBlank(member.getMemZip())) {
			valid = false;
			errors.put("memZip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			valid = false;
			errors.put("memAdd1", "기본주소 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			valid = false;
			errors.put("memAdd2", "상세주소 누락");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "메일주소 누락");
		}

		return valid;
	}
}
