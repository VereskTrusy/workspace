package kr.or.ddit.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter;

import lombok.extern.slf4j.Slf4j;

/**
 * session scope 의 authmember 라는 속성이 존재하면,
 * 현재 request 에 Wrapper 를 만들고, 
 * 해당 wrapper 를 통해 UserPrincipal 을 관리하기 위한 필터
 * 
 * 
 */
@Slf4j
public class GeneratePrincipalFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화 되었습니다.", this.getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		if(session != null) {
			Object authMember = session.getAttribute("authMember");
			if(authMember != null) {
				UserPrincipalRequestMapper wrapper = new UserPrincipalRequestMapper(req);
				chain.doFilter(wrapper, response); // 랩핑된 리퀘스트 넘기기
				return;
			}
		}
		chain.doFilter(request, response); // 원본 리퀘스트 넘기기
	}

	@Override
	public void destroy() {
		log.info("{} 소멸 되었습니다.", this.getClass().getSimpleName());
	}

}
