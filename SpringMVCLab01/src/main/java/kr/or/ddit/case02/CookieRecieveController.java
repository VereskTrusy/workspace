package kr.or.ddit.case02;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/case2")
public class CookieRecieveController {
	
	@RequestMapping(value = "cookie2")
	public void handler2(@CookieValue String newCookie) {
		log.info("newCookie : {}", newCookie);
	}

	@RequestMapping(value = "cookie1")
	public void handler1(HttpServletRequest req) throws UnsupportedEncodingException{
		
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie single : cookies) {
				String value = URLDecoder.decode(single.getValue(), "UTF-8");
				log.info("{} : {}", single.getName(), value);
			}
		}
	}
}
