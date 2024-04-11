package kr.or.ddit.case10.conf;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.case5.person.controller.PersonController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case8Playground {
	public static void main(String[] args) {
		ConfigurableApplicationContext parent = new ClassPathXmlApplicationContext("kr/or/ddit/case8/conf/hierarchy/root-context.xml");
		ConfigurableApplicationContext child1 = new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/case8/conf/hierarchy/child-context.xml"}, parent);
		
		PersonController controller = child1.getBean(PersonController.class);
		
	}
}
