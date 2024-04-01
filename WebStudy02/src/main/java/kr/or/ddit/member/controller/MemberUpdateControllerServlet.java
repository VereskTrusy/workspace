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
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
  조건 : 의사코드 작성
  MemberInsertControllerServlet 를 보고 어떤 코드가 중복되는지 확인 할것
  
  doGet 메서드로 요청이 들어 옴
  request의 캐릭터 셋을 UTF-8로 맞춘다.
  파라미터로 회원아이디를 받는다
  파라미터의 회원아이디가 null인지 검사한다.
  파라미터가 null이 아니라면 현재 등록되어있는지 조회한다.
  		서비스를 실행하여 단일 회원 조회 후 있다면 로직을 계속 수행한다
  			memberVo에 파라미터로 받은 정보를 받는다. 
  		단일 회원이 존재 하지 않으면 잘못된 요청에 대한 처리를 진행한다.
  리퀘스트에 회원정보를 저장한다.
  view 수정폼으로 포워드 이동한다
  
  
  doPost 메서드로 요청이 들어 옴
  파라미터로 회원정보를 받는다
  파라미터의 각 필수정보가 null인지 검사한다.
  파라미터가 null이 아니라면 현재 등록되어있는지 조회한다.
  파라미터 중 아이디,패스워드 로 서비스 조회(단일회원 조회) 
  		일치 -> memberVo에 파라미터로 받은 정보를 받는다.
  		불일치 -> 단일 회원이 존재 하지 않으면 잘못된 요청에 대한 처리를 진행한다.
  파라미터 가 담긴 memberVo를 update 서비스를 사용하여 처리한다.
  		업데이트 결과
  			성공 시 성공메시지를 세션에 저장한다.
  			실패 시 실패 메시지를 리퀘스트에 담는다

**/
@Slf4j
@WebServlet("/member/memberUpdate.do")
public class MemberUpdateControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();

	// 회원 가입 폼으로 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(400);
			return;
		}
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		
		
		MemberVO member = service.retriveMember(authMember.getMemId());
		
		req.setAttribute("member", member);
		
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
		boolean valid = validate(member, errors); // errors -> call by referense 
		req.setAttribute("errors", errors);
		
		// 3. 로직 사용(model 확보)
		// 4) 밸리데이션 검증 실패
		String viewName = null;
		if (errors.isEmpty()) { // 에러가 없다 == 검증이 끝났다.
			// 4. scope를 이용하여 model 공유
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				req.setAttribute("message", "비밀번호가 잘못됨");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;
			case FAIL: // 현재는 fail상황 없음. 그보다 먼저 SQLException 발생, 현티어구조때문임. 
				req.setAttribute("message", "서버오류");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;
			default: 
//				req.getSession().setAttribute("lastCreated", member);
				viewName = "redirect:/mypage";
				break;
			}
		} else {
			req.setAttribute("member", member);
			req.setAttribute("errors", errors);
			viewName = "/WEB-INF/views/member/memberForm.jsp"; // 리퀘스트에
		}
		
		// 5. view 결정
		// 6. view 로 이동(flow Control)
		// Front Controller Pattern 을 통해서 하위의 모든 컨트롤러에서 중복되는 코드를 해결할 예정.
		if (viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
	
	
	// 회원 수정 폼으로 이동
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// QueryString의 파라메터 받기
//		String memId = req.getParameter("who");
//		// 파라메터 null 검증
//		if(StringUtils.isBlank(memId)) {
//			resp.sendError(400, "잘못됐어");
//			return;
//		}
//		
//		// 단일 회원 조회 서비스 실행
//		MemberVO member = null;
//		member = service.retriveMember(memId);
//		
//		// Attribute 태우기 : scope 결정 -> request 왜? application,session단위에서 데이터가 남아 있을 필요가 없어서.
//		req.setAttribute("member", member);
//		req.setAttribute("who", member.getMemId());
//		
//		// Content 협상 : Accept 헤더를 통해 판단.
//		String accept = req.getHeader("accept");
//		String viewName = null;
//		if(accept.contains("json")) {
//			viewName = "/jsonView.do"; // 하는일 : request to serialize, response wriete.
//		}else {
//			viewName = "/WEB-INF/views/member/memberForm.jsp";
//		}
//		req.getRequestDispatcher(viewName).forward(req, resp);
//	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// 1. 요청 접수, 분석
//		req.setCharacterEncoding("UTF-8");
//		
//		MemberVO member = new MemberVO(); // command Object
//		req.setAttribute("member", member);
//		
//		Map<String, String[]> parameterMap = req.getParameterMap();
//		try {
//			BeanUtils.populate(member, parameterMap); // 내부적으로 리플렉션 시킴
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new RuntimeException();
//		}
//		
//		// 2. 검증
//		Map<String, String> errors = new LinkedHashMap<>();
//		boolean valid = validate(member, errors); // errors -> call by referense 
//		req.setAttribute("errors", errors);
//		
//		String viewName = null;
//		if (errors.isEmpty()) {
//			ServiceResult result = service.modifyMember(member);
//			switch (result) {
//			case FAIL: 
//				req.setAttribute("message", "일치하는 회원의 정보가 없습니다.");
//				viewName = "/WEB-INF/views/member/memberForm.jsp";
//				break;
//			case INVALIDPASSWORD: 
//				req.setAttribute("message", "비밀번호를 다시 확인하여주십시오.");
//				viewName = "/WEB-INF/views/member/memberForm.jsp";
//				break;
//			default: 
//				req.getSession().setAttribute("lastCreated", member);
//				viewName = "redirect:/member/memberList.do";
//				break;
//			}
//		} else {
//			req.setAttribute("member", member);
//			req.setAttribute("errors", errors);
//			viewName = "/WEB-INF/views/member/memberForm.jsp"; // 리퀘스트에
//		}
//	}
	
	
	
	// 밸리데이션 검증
	// code cost 많이 발생 > 프레임웍사용으로 Controller의 부하 감소
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
