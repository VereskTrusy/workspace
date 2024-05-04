package kr.or.ddit.common.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginPage(String username, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		return "common/login";
	}
}
