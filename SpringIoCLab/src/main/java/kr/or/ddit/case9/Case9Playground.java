package kr.or.ddit.case9;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.case9.conf.Case9Context;
import kr.or.ddit.case9.obj.Hunter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case9Playground {
	public static void main(String[] args) {
		ConfigurableApplicationContext context;
		context = new AnnotationConfigApplicationContext(Case9Context.class);
		Hunter hunter1 = context.getBean("hunter1", Hunter.class);
//		Hunter hunter2 = context.getBean("hunter2", Hunter.class);
		log.info("hunter : {}", hunter1);
//		log.info("hunter : {}", hunter2);
	}
}
