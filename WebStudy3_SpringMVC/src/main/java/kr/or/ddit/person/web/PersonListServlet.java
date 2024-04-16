package kr.or.ddit.person.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.person.service.PersonService;
import kr.or.ddit.vo.PersonVo;



/**
 * RESTful URI
 * (명사(URI)와 동사(METHOD)를 분리형태)
 * /memeber/memberList.do 회원목록
 * /memeber/memberInsert.do 회원목록
 * 
 * /member (GET) "회원의 목록"을 "조회"하다.
 * /member/a001 (GET) "a001"을 "조회"하다.
 * /member/a001 (PUT) "a001"을 "수정"하다.
 * /member/NEW (POST) "새로운 회원"을 "등록"하다.
 * 
 */
@Controller
@RequestMapping("/peple")
public class PersonListServlet {
	
	@Autowired
	private PersonService service;
	
	@GetMapping
	protected String doGet(Model model) {
		List<PersonVo> people = service.retrievePersonList();
		model.addAttribute("people", people);
		return "person/people";
	}
	
	@GetMapping("{who}")
	protected String service(@PathVariable("who") String id, Model model) {
		PersonVo person = service.retrievePerson(id);
		model.addAttribute("person", person);
		return "person/detail";
	}
}
