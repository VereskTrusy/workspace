package kr.or.ddit.case2;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case2Playground {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/or/ddit/case2/conf/Case2-Context.xml");
		Foo foo = context.getBean("foo", Foo.class);
		log.info("foo : {}", foo);
	}
}
