package kr.or.ddit.case6;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case6Playground {
	public static void main(String[] args) {
		Case6Playground run = new Case6Playground();
		run.run();
	}

	private void run() {
		// CollectionDIOuter 에 있는 모든 멤버들을 Context 에 등록된걸 사용해서 모두 출력 해보기
		ConfigurableApplicationContext context = new GenericXmlApplicationContext("classpath:kr/or/ddit/case6/conf/Case6-Context.xml");
		context.registerShutdownHook();
		CollectionDIOuter outer1 = context.getBean("outer1", CollectionDIOuter.class);
		log.info("outer1 : {}", outer1);
		
		CollectionDIOuter outer2 = context.getBean("outer2", CollectionDIOuter.class);
		log.info("outer2 : {}", outer2);
		
	}
}
