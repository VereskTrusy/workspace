package kr.or.ddit.lprod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.lprod.service.OthersService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

/**
 * POJO(plain old java object)
 * advice 구조
 * 스프링의 AOP에서 활용한다.
 * kr.or.ddit.prod 패키지 아래으 모든 컨트롤러를 대상으로 사전 weaving되는 advice
 */
@ControllerAdvice(basePackages = "kr.or.ddit.prod")
public class OthersControllerAdvice {
	
	@Autowired
	private OthersService service;
	
	@ModelAttribute("lprodList")
	public List<LprodVO> addLprodList(Model model) {
		return service.retriveLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> addBuyerList(Model model) {
		return service.retriveBuyerList();
	}
}
