package kr.or.ddit.login;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.exception.ResponseStatusException;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@WebServlet("/login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet {
	
	@Autowired
	private final AuthenticateService service;
	
	private boolean authenticate(String id, String password) { // 로그인 성공여부 판단
		return id.equals(password);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 현재의 요청은 최초의 요청은 아니다.
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(400, "로그인을 하려면 로그인 폼이 먼저 최초의 요청으로 전송되어야함.");
			return;
		}
		
		// 1. body 영역의 디코딩에 사용할 charset 결정
		req.setCharacterEncoding("UTF-8");
		try {
			// 2. 필요 파라미터 확보
			// 3. 파라미터 검증
			// 	파라미터 검증 - 검증 통과 : 
			String memId = Optional.of(req.getParameter("memId"))
				.filter(id->!id.isEmpty())
				.orElseThrow(()->new ResponseStatusException(400, "아이디 누락"));
			
			String memPass = Optional.of(req.getParameter("memPass"))
					.filter(id->!id.isEmpty())
					.orElseThrow(()->new ResponseStatusException(400, "비밀번호 누락"));
			
			String viewName = null;
			
			// 4. 인증 여부 판단.
			try {
				MemberVO inputData = new MemberVO();
				inputData.setMemId(memId);
				inputData.setMemPass(memPass);
				MemberVO authMember = service.authenticate(inputData);
				
				// 4-1. 로그인 인증이 되었다. 인증된 상태정보가 저장되어야함
				session.setAttribute("authMember", authMember);
				// - 성공 : 웰컴페이지 이동 - redirect
				viewName = "redirect:/";
			}catch(AuthenticateException e) {
				// - 실패 : 로그인 페이지로 이동 - 1. 리퀘살려, 2. A는 응답책임없으 -> forward
				// Session 으로 메시지를 전달 한 이유 => 페이지를 redirect하기 때문에 request에 정보가 남아 있지 않기 때문이다.
				session.setAttribute("message", e.getMessage());
				viewName = "redirect:/login/loginForm.jsp";
			}
			new ViewResolverComposite().resolveView(viewName, req, resp);
			
//			if(authenticate(memId, memPass)) { // - 성공 : 웰컴 페이지로 이동 - 리다이렉트
//				// 4-1. 로그인 인증이 되었다. 인증된 상태정보가 저장되어야함
//				session.setAttribute("authId", memId);
//				// - 성공 : 웰컴페이지 이동 - redirect
//				resp.sendRedirect(req.getContextPath() + "/");
//			}else { // 인증 여부 판단 
//				// - 실패 : 로그인 페이지로 이동 - 1. 리퀘살려, 2. A는 응답책임없으 -> forward
//				session.setAttribute("message", "로그인 실패"); // Session 으로 메시지를 전달 한 이유 => 페이지를 redirect하기 때문에 request에 정보가 남아 있지 않기 때문이다.
//				// req.getRequestDispatcher("/login/loginForm.jsp").forward(req, resp); // 잘못된사용자의 잘못된정보를 남길 필요가 없기때문에 redirect한다.
//				resp.sendRedirect(req.getContextPath() + "/login/loginForm.jsp");
//			}
		}catch (ResponseStatusException e) {
			// 파라미터 검증 - 검증 실패 : 상태코드 400
			resp.sendError(e.getStatus(), e.getMessage());
		}
	}
}
