package kr.or.ddit.case02;

import java.util.Enumeration;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case02")
public class RequestHeaderReceiveController {
	
	@RequestMapping("handler6")
	public void handler6(@RequestHeader Optional<String> otpHeader) {
		log.info("myHeader : {}", otpHeader.orElse("default value"));
	}
	
	@RequestMapping("handler5")
	public void handler5(@RequestHeader(name = "myHeader", required = false, defaultValue = "1000") String myHeader) {
		log.info("myHeader : {}", myHeader);
	}
	
	@RequestMapping("handler4")
	public void handler4(@RequestHeader(name = "accept", required = true) String accept) {
		log.info("accept : {}", accept);
	}
	
	@RequestMapping("header3")
	public void handler3(@RequestHeader MultiValueMap<String, String> headers) {
		headers.forEach((k, v)->{log.info("{} : {}", k, v);});
	}
	
	@RequestMapping(value = "header2")
	public void handler2(@RequestHeader Map<String, Object> headers) {
		headers.forEach((k, v)->{log.info("{} : {}", k, v);});
	}

	@RequestMapping(value = "header1")
	public void handler1(HttpServletRequest req) {
		Enumeration<String> names = req.getHeaderNames();
		
		while(names.hasMoreElements()) {
			String headerName = (String) names.nextElement();
			String headerValue = req.getHeader(headerName);
			log.info("{} : {}", headerName, headerValue);
		}
	}
}
// 필요한게 있으면 스프링 한테 받으면된다. -> args로 받으면됨