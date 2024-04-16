package kr.or.ddit.login;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
public class LoginProcessController {
	
	@Autowired
	private AuthenticateService service;
	
	// 핸들러 어댑터로부터 직접 세션을 받도록 아규먼트로 설정한다면
	// 세션이 없어도 핸들러 어댑터가 강제로 세션을 만들어서 전달해주기 때문에
	// 세션이 널 인지 여부를 검사하는 
	@RequestMapping("/login/loginProcess.do")
	public String loginProcess(
			@Valid @RequestParam(name = "memId") String memId
			, @Valid @RequestParam(name = "memPass") String memPass
			, HttpServletRequest req
	) {
		HttpSession session = req.getSession();
		if(session.isNew()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인을 하려면 로그인 폼이 최초의 요청으로 전송되어야한다.");
		}
		
		String viewName = null;
		try {
			MemberVO inputData = new MemberVO();
			inputData.setMemId(memId);
			inputData.setMemPass(memPass);
			MemberVO authMember = service.authenticate(inputData);
			
			session.setAttribute("authMember", authMember);
			viewName = "redirect:/index.do";
		}catch(AuthenticateException e) {
			session.setAttribute("message", e.getMessage());
			viewName = "redirect:/login/loginForm.jsp";
		}
		return viewName;
	}
}
