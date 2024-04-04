package kr.or.ddit.lprod.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.lprod.service.OthersServiceImpl;
import kr.or.ddit.lprod.service.OthersService;

/**
 * POJO(plain old java object)
 * advice 구조
 * 스프링의 AOP에서 활용한다.
 */
public class OthersControllerAdvice {
	private OthersService service = new OthersServiceImpl();
	
	public void addLprodList(HttpServletRequest req) {
		req.setAttribute("lprodList", service.retriveLprodList());
	}
	
	public void addBuyerList(HttpServletRequest req) {
		req.setAttribute("buyerList", service.retriveBuyerList());
	}
}
