package kr.or.ddit.misison;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/mission/case02")
public class Mission2Controller {

	
	@GetMapping
	public void handler1(
			@RequestHeader(name = "user-agent", required = true) String userAgent
			, @CookieValue(required = false, defaultValue = "1000") int myCookie) {
		
	}
}
