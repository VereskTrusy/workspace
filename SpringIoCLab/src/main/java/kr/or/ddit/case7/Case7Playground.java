package kr.or.ddit.case7;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.case1.dao.SampleDAO;
import kr.or.ddit.case2.Bar;
import kr.or.ddit.case2.Foo;
import lombok.extern.slf4j.Slf4j;

/**
 * Case1-Context 와 Case2-Context, Case4-Context 를 계층구조로 묶기위한 컨텍스트.
 * parent Container : Case1-Context
 * Child Container : Case2-Context
 * Child Container : Case4-Context
 */
@Slf4j
public class Case7Playground {
	public static void main(String[] args) {
		ConfigurableApplicationContext parent = new ClassPathXmlApplicationContext("kr/or/ddit/case1/conf/Sample-Context.xml");
		ConfigurableApplicationContext child1 = new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/case2/conf/Case2-Context.xml"}, parent);
		ConfigurableApplicationContext child2 = new ClassPathXmlApplicationContext(new String[] {"kr/or/ddit/case4/conf/Case4-Context.xml"}, parent);
		
		Foo foo = child1.getBean("foo", Foo.class);
		log.info("foo : {}", foo);
		
		SampleDAO dao = child1.getBean(SampleDAO.class);
		log.info("부모로부터 자식에게 주입된 dao : {}", dao);
		
//		parent.getBean(Bar.class);// 부모 컨테이너에서 자식의 빈을 주입 받을 수 없음.
		child1.getBean(Bar.class);
		parent.getBean(SampleDAO.class);
		
		child1.getBean(SampleDAO.class);
		child2.getBean(SampleDAO.class);
		
//		child1.getBean(BtsDAO.class);
//		child2.getBean(Foo.class);
	}
}
