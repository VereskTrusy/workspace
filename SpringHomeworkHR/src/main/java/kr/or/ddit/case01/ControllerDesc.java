package kr.or.ddit.case01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 스프링으로 컨트롤러를 구현하는 방법
 * 1. POJO 로 컨트롤러 구현.
 * 2. @Controller 로 컨테이너에 빈 등록.
 * 3. 각 커맨드 핸들러를 메소드로 구현.
 * 4. @RequestMapping 계열의 어노테이션으로 요청 매핑 조건 설정.
 *    - url, method, accept, content-type, parameter, path variable
 * 
 */
@Slf4j
@Controller
@RequestMapping(value = "/case01/mapping1")
public class ControllerDesc {
	
	@RequestMapping(value = "request1")
	public void handler1() {
		log.info("handler1 동작");
	}
	
	@RequestMapping(value = "request2")
	public void handler2() {
		log.info("handler2 동작");
	}
	
	@RequestMapping(value = "request3")
	public void handler3() {
		log.info("handler3 동작");
	}
}
