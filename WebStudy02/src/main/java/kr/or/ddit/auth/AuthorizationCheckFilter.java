package kr.or.ddit.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * AuthorizationCheckFilter :
 * 1. 보호자원에 대해서 요청이 발생한 경우, 
 *    인증된 사용자의 롤을 확인하고,
 *    해당 보호자원에 설정된 인가정보에 일치하는지 여부를 확인 하는 필터이다.
 * 2. 비인가자원에 대해선 할일이 없다.
 * 
 * 
 * 인가된 사용자 : 통과
 * 비 인가된 사용자 : 403 에러(forbidden)
 * 
 * 
 * 보안 : 인증, 인가
 */
@Slf4j
public class AuthorizationCheckFilter implements Filter {
	
	private Map<String, String[]> securedResources;
	private ServletContext application;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		application = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(securedResources == null) {
			securedResources = (Map<String, String[]>) application.getAttribute("securedResources");
		}
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		boolean pass = true;
		
		String uri = req.getRequestURI();
		
		uri = uri.substring(req.getContextPath().length());
		MemberVOWrapper principal = null;
		if(securedResources.containsKey(uri)) {
			// 사용자의 롤, 자원 설정
			principal = (MemberVOWrapper) req.getUserPrincipal();
			String userRole = principal.getRealUser().getMemRole();
			
			String[] roles = securedResources.get(uri);
			Arrays.sort(roles);
			pass = Arrays.binarySearch(roles, userRole) >= 0; // 이진 탐색, 먼저 정렬되어 있어야함
			
		}else {
			pass = true;
		}
		
		if(pass) {
			chain.doFilter(request, response);
		} else {
			log.warn("{} 자원에 대한 요청이 권한이 없는 {} 사용자로부터 발생 했습니다.", uri, principal.getName());
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
