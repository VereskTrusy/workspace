package kr.or.ddit.case5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;

import kr.or.ddit.case5.person.controller.PersonController;
import kr.or.ddit.vo.PersonVo;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Case5Playground {
	@Autowired
	private static PersonController controller;
	
	public static void main(String[] args) {
		// Application 에서 가장 먼저 실행되는 부분 : Entry Point
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/case8/conf/Person-Context.xml");
		context.registerShutdownHook(); // 등록. 컨테이너가 사용하던 자원을 정리해준다.
		
		controller = context.getBean("personController", PersonController.class);
		List<PersonVo> list = controller.personListToResponse();
		log.info("리스트 : {}", list);
	}
}
