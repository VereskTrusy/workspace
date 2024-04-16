package kr.or.ddit.login;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/login/logout.do")
public class LogoutControllerServlet extends HttpServlet{
	
	@PostMapping
	protected String doPost( HttpServletRequest req ) throws UnsupportedEncodingException {
		HttpSession session = req.getSession();
		if(session.isNew()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "현재의 요청이 최초의 요청일 수 없습니다.");
		}
		session.invalidate();
		String message = "로그아웃 성공";
		message = URLEncoder.encode(message, "UTF-8");
		String viewName = "redirect:/?message=" + message;
		return viewName;
	}
}
