package kr.or.ddit.utils;
/**
 * List<Object> dummyList = new ArrayList<>();
 * int number = 34;
 * dummyList.add(new Integer(number);
 */

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieMapReqeustWrapper {
	private HttpServletRequest adaptee;
	private Map<String, Cookie> cookieMap;

	public CookieMapReqeustWrapper(HttpServletRequest adaptee) {
		super();
		this.adaptee = adaptee;
		cookieMap = new LinkedHashMap<String, Cookie>();
		Cookie[] cookies = adaptee.getCookies();
		if(cookies != null) {
			for(Cookie single : cookies) {
				cookieMap.put(single.getName(), single);
			}
		}
	}
	
	/**
	 * 쿠키 잇냐 없냐 확인
	 * @param cookieName
	 * @return 참거짓~
	 */
	public boolean hasCookie(String cookieName) {
		return cookieMap.containsKey(cookieName);
	}
	
	/**
	 * 쿠키 가져가~
	 * @param cookieName
	 * @return
	 */
	public Cookie getCookie(String cookieName) {
		return cookieMap.get(cookieName);
	}
	
	/**
	 * 쿠키 값 가져가~
	 * @param cookieName
	 * @return
	 */
	public String getCookieValue(String cookieName) {
		if(hasCookie(cookieName)) {
			Cookie finded = getCookie(cookieName);
			try {
				return URLDecoder.decode(finded.getValue(), "UTF-8");
			} catch (IOException e) {
				// $(".selector")
				throw new UncheckedIOException(e);
			}
		} else {
			return null;
		}
	}
}
