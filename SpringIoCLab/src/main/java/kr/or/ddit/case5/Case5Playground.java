package kr.or.ddit.case5;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case5.person.controller.PersonController;
import kr.or.ddit.vo.PersonVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case5Playground {
	private static PersonController controller;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/case5/conf/Case5-Context.xml");
		
		controller = context.getBean("PersonController", PersonController.class);
		List<PersonVo> list = controller.personListToResponse();
		log.info("리스트 : {}", list);
	}
}
