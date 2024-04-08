package kr.or.ddit.case5.person.controller;

import java.util.List;

import kr.or.ddit.case5.person.service.PersonService;
import kr.or.ddit.vo.PersonVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonController {
	private PersonService service;

	// 생성자
	public PersonController(PersonService service) {
		super();
		this.service = service;
	}
	
	public List<PersonVo> personListToResponse(){
		log.info("실행");
		return service.retrievePersonList();
	}
}
