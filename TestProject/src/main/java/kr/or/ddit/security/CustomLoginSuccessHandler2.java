package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class CustomLoginSuccessHandler2 implements AuthenticationSuccessHandler {

	private static final Logger log = LoggerFactory.getLogger(CustomLoginSuccessHandler2.class);
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	// 인증 전에 접근 시도했던 URL로 리다이렉트하는 기능
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("Login Success");
		User customUser =  (User) authentication.getPrincipal();
		log.info("username : " + customUser.getUsername());
		log.info("password : " + customUser.getPassword());
		
		clearAuthenticationAttribute(request);
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String targetUrl = "";
		if(savedRequest != null) {	// 타겟 정보가 존재하면 타겟으로 이동
			targetUrl = savedRequest.getRedirectUrl();
		}else {		// 타겟 정보가 존재하지 않으면 아래 경로로 리다이렉트
			targetUrl = "/board/list.do";
		}
		log.info("Login Success targetUrl : " + targetUrl);
		response.sendRedirect(targetUrl);
		
		// 로그 결과
//		INFO : kr.or.ddit.security.CustomLoginSuccessHandler2 - Login Success
//		INFO : kr.or.ddit.security.CustomLoginSuccessHandler2 - username : admin
//		INFO : kr.or.ddit.security.CustomLoginSuccessHandler2 - password : null
//		INFO : kr.or.ddit.security.CustomLoginSuccessHandler2 - Login Success targetUrl : http://localhost/board/register
	}
	
	public void clearAuthenticationAttribute(HttpServletRequest req) {
		// session 정보가 존재한다면 현재 session을 반환하고 존재하지 않으면 null을 반환합니다
		HttpSession session = req.getSession(false);
		if(session == null) {
			return;
		}
		
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);	// SPRING_SECURITY_LAST_EXCEPTION 값
	}

}
