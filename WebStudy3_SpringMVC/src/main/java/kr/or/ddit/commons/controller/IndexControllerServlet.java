package kr.or.ddit.commons.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControllerServlet{
	
	@RequestMapping("/index.do")
	protected String hello(HttpSession session){
		return "index";
	}
}
