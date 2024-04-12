package kr.or.ddit.case01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/case01/mapping2")
public class HttpMethodMappingController {
	
	@RequestMapping(method = {RequestMethod.HEAD, RequestMethod.PATCH})
	public void handlerOthers() {
		log.info("Other Method Handler 동작");
	}
	
	@GetMapping
	public void handler1Get() {
		log.info("GET Handler 동작");
	}
	
	@PostMapping
	public void handler2Post() {
		log.info("POST Handler 동작");
	}
	
	@PutMapping
	public void handler3Put() {
		log.info("PUT Handler 동작");
	}
	
	@DeleteMapping
	public void handler4Delete() {
		log.info("DELETE Handler 동작");
	}
}
