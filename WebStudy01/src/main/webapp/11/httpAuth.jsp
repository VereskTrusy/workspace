<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Http 프로토콜 인증 시스템</h4>
<pre>
보안 처리의 기본 구조
	인증(Authentication) + 인가(Authorization) 기반의 접근 제어 구조를 통한 보안 처리.
	- 인증 : 사용자가 본인이 맞는지 신원을 확인하는 작업.
		- was기반의 inmemory 구조, db 접근을 통한 사용자 정보
	- 인가 : 보호자원에 대한 접근 권한을 획득 했는지 여부를 확인하는 작업.
	JAAS(Java Authentication and Authorization Service) 프레임워크에 의해 로그인 인증 처리
		 : 인증된 사용자 정보를 Principal 의 구현체로 표현함.
사용자의 인증 시스템 구현
1. 헤더 기반의 인증
	ex)
	보호 자원(/09/mbti)에 대해 미인증된 사용자가 접근한 경우,
	응답 으로 401 상태코드와 www-authenticate:authType[BASIC | BEARER] 헤더를 전송함
	브라우저가 응답을 받고, 인증 정보를 입력받을 수 있는 UI를 선택함
	인증된 후, 인증 상태 정보 유지에 authorization:authType[BASIC | BEARER] base64(로인코딩된사용자정보) 헤더를 사용함.
	사용자 확인 : 헤더 정보를 통해 확인 
	
	1) BASIC 인증 : 브라우저가 가진 기본 인증 UI 활용 방식.
	2) BEARER 인증 : 토큰 기반의 인증 시스템, oAuth 프로토콜에서 사용되는 인증 방식.
		access token 표현
			authorization:Bearer encrypt(base64(token)) 
2. 세션 기반의 인증
	사용자 확인 : 세션 정보를 통해 확인
	1) FORM 인증 : form UI를 활용하는 방식.
		필요한 정보
		action : j_securty_check
		id parameter : j-username
		password parameter : j_password
		3가지가 일치해야한다
</pre>
</body>
</html>