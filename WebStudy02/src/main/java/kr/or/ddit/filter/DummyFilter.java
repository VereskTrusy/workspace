package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * @WebFilter("/*") : 매번 요청이 들어 올 때마다 doFilter에서 해당 요청을 받아서 필터링 한다.
 * 
 * FilterChain : 여러 필터를 체이닝 한다.
 */
@Slf4j
public class DummyFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("=================== before : 요청을 필터링 ===========================");
		chain.doFilter(request, response);
		
		log.info("===================After : ===========================");
	}

	@Override
	public void destroy() {
		
	}
	
}
