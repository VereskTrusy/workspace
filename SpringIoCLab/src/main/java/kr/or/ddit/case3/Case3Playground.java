package kr.or.ddit.case3;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case1.service.SampleService;
import kr.or.ddit.vo.SampleVO;
import lombok.extern.slf4j.Slf4j;

/**
 * Spring container(Bean container, DI container) 사용 단계
 * 1. Spring IoC container 모듈 의존성 추가 : spring-context(core, beans, el)
 * 2. Spring Bean Configuration file 작성 : bean metadata 파일
 *    1) 사용 할 객체 bean 으로 등록 : bean(class, id)
 *    2) 등록된 bean 간의 의존관계를 형성(의존성 주입 구조)
 *       - constructor injection(required) : constructor-arg -> c prefix
 *       - setter injection(optional) : property -> p prefix
 *       주입될 대상에 따라 : value(기본형), ref(객체참조) 분리하여 주입.
 * 3. entrypoint 에서 컨테이너 객체 생성
 *    ApplicationContext -> ConfigurableApplicationContext -> 컨테이너의 특성에 따라 구현체 결정하여 사용하면 된다.
 * 4. 컨테이너로부터 필요한 객체 주입 : getBean(bean 검색조건 - class,id)
 * 
 * 스프링 컨테이너의 빈관리 정책
 * 1. Singleton : 등록된 빈을 대상으로 싱글턴 인스턴스를 운영함
 *    scope 속성으로 변경 가능
 *    - singleton : 기본값 : 한개의 빈으로 하나의 인스턴스 운영
 *    - prototype : 주입 시 마다 새로운 인스턴스를 생성하고 운영
 *    - request / session : 웹 어플리케이션에서 한정적으로 사용하는 값으로
 *    			   			인스턴스를 request 나 sesison 단위로 생성하고 운영.
 * 2. 컨테이너가 초기화될때 등록된 모든 빈의 인스턴스를 일시에 생성함.
 *    lazy-init - true : 주입 시점까지 객체의 생성이 미뤄짐
 *    	        - false : 컨테이너 초기화될때 미리 생성한다.
 *              - default : 상위 컨테이너나 beans의 기본설정을 따라감.
 * 3. 주입을 하지 않고, 생성순서만 제어할 때 : depends-on 
 * 4. 컨테이너는 내부의 생명주기를 관리하는 객체의 생명주기 이벤트에 따라 callback 을 호출하는 구조를 가진다.
 *     init-method, destroy-method 속성으로 callback 지정.
 *    *** init-method는 
 */
@Slf4j
public class Case3Playground {
	
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = 
				// classpath:, File:, web: 을 사용하여 위치를 유동적으로 지정가능
				new GenericXmlApplicationContext("classpath:kr/or/ddit/case3/conf/Case3-Context.xml");
		context.registerShutdownHook(); // 종료시점시 컨테이너 자동 클로즈 시킨다.
		// 액티브 넌 데몬스레드가 하나도 없을때 스프링컨테이너가 자동클로조 한단.
		
//		SampleService service1 = context.getBean("sampleService1", SampleService.class);
		SampleService service2 = context.getBean("sampleService2", SampleService.class);
//		SampleService service3 = context.getBean("sampleService2", SampleService.class);
//		SampleVO sample = service1.readSample("T003");
//		
//		log.info("list ; {}", sample);
//		log.info("service1 과 service2 동일객체? : {}", service1 == service2);
//		log.info("service2 과 service3 동일객체? : {}", service2 == service3);
//		
		context.close();
//		
//		Date now1 = context.getBean("now", Date.class);
//		log.info("now1 : {}", now1);
//		LocalDateTime ldt1 = (LocalDateTime)context.getBean("ldtnow");
//		log.info("ldt1 : {}", ldt1);
//		
//		Thread.sleep(3000);
//		Date now2 = context.getBean("now", Date.class);
//		log.info("now2 : {}", now2);
//		LocalDateTime ldt2 = (LocalDateTime)context.getBean("ldtnow");
//		log.info("ldt2 : {}", ldt2);
		
	}
	
}
