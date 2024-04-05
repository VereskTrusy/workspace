package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class TrickFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request; // 캐스팅
		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(req) { // 랩핑
			@Override
			public String getParameter(String name) {
				if("who".equals(name)) {
					return "a001";
				}else {
					return super.getParameter(name);
				}
			} 
		};
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
