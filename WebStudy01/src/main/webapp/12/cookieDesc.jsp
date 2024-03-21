<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Cookie</h4>
<pre>
	Http : ConnectLess, StateLess 
	? Http 가 가진 stateless 특성의 단점을 보완하기 위해 최소한의 상태 정보를 클라이언트 사이드에 저장하는 구조. 
	때문에 상태정보를 저장하기 위해 쿠키를 사용한다. 
	암튼 저장하는데 이런게 쓰인다 > 쿠키, 세션, 스코프(application, DB)
	** 쿠키 사용 방법
	--------------------------------------------------------------서버에서 이루어지는 작업
	1. 쿠키 생성(기본속성 : name/value)
	2. 쿠키의 부가 옵션 설정.
	3. response 에 포함시켜 클라이언트로 전송(헤더에 포함)
	--------------------------------------------------------------클라이언트에서 이루어지는 작업
	4. 브라우저가 가진 쿠키 저장소에 저장(response header)
	(브라우저 단위로 쿠키 저장소 보유하고, 타브라우저간 공유되지 않음)
	--------------------------------------------------------------서버에서 이루어지는 작업
	5. 다음번 요청이 발생할 때, 쿠키를 서버로 재전송함.(request header)
	--------------------------------------------------------------클라이언트에서 이루어지는 작업
	6. 재전송된 request cookie 헤더를 통해 상태 복원.
	
	
	<%
		// 1. 쿠키 생성, 생성자에 기본속성 설정
		// 3. response 에 포함시켜 클라이언트로 전송(헤더에 포함)
		Cookie firstCookie = new Cookie("firstName","FIRSTVALUE");
		response.addCookie(firstCookie);
		
		Cookie koreanCookie = new Cookie("korean", URLEncoder.encode("한글값", "UTF-8"));
		response.addCookie(koreanCookie);
		
		Cookie otherPathCookie = new Cookie("path11Cookie", "PATH_11_VALUE");
		otherPathCookie.setPath(request.getContextPath() + "/11"); // 11번 경로로 재전송
		response.addCookie(otherPathCookie);
		
		Cookie allPathCookie = new Cookie("allPathCookie", "ALL_PATH_COOKIE");
		allPathCookie.setPath("/");
		response.addCookie(allPathCookie);
		
		Cookie longLiveCookie = new Cookie("longLiveCookie", "LONG_LINE_COOKIE");
		longLiveCookie.setPath(request.getContextPath());
		longLiveCookie.setMaxAge(60*60*24*7);
		response.addCookie(longLiveCookie);
		// 브라우저 종료 후 다시 들어가보니까 쿠키 다없어지고 롱만 남았넹~~~~~아 롱롱 롱
		
		int[] array = new int[]{1,2,3};
		String json = new ObjectMapper().writeValueAsString(array);
		Cookie arrayCookie = new Cookie("arrayCookie", URLEncoder.encode(json, "UTF-8")); // xml과 json이양 !!! 두가지양 !! -> native to json, json to native
		arrayCookie.setPath(request.getContextPath());
		response.addCookie(arrayCookie);
		
	%>
	<a href="cookieView.jsp">동일 경로에서 확인</a>
	<a href="../11/cookieView.jsp">다른 경로에서 확인</a>
	
	** 쿠키 속성의 종류
	-------------------------------------------------------------- required
	1. name(required) : 변수(identifier)생성 규칙에 따른 이름.
	2. value(required) : 특수문자가 포함된 값은 매체가 이해할 수 있는 방식으로 변환해서 전송한다. 인코딩(URLEncoding)해서 전송함. 문자이외의 데이터는 저장할 수 없다. 
	-------------------------------------------------------------- optional
	3. domain/host : 쿠키의 재전송 여부를 결정하는 조건.
					생략시 기본값으로 쿠키 생성시의 도메인이 반영됨.
					domain name 의 구조
					3레벨(GTLD) 구조 www.naver.com
					4레벨(NTLD) 구조 www.naver.co.kr
						(Global top level domain, national top level domain)
					ex) cookie's domain : news.naver.com, www.naver.com, email.naver.com, .naver.com//이건 모든 호스트 서버로 재전송되는 쿠키 설정
	4. path : 쿠키의 재전송 여부를 결정하는 조건. 설정된 경로 이하로 발생하는 요청을 통해 재전송.
				생략시 기본값으로 쿠키의 생성 경로가 반영된다.
	5. max-age/expires : 쿠키의 만료 시한 설정.
	
	
	쿠키의 이름과 값은 텍스트로 저장되고 서버와 통신 시 텍스트로 주고 받는다. 물론 헤더에 저어자앙~* ( ^-^)/`
</pre>
</body>
</html>
