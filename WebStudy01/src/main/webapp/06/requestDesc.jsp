<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/requestDesc.jsp</title>
</head>
<body>
<h4>request(HttpServletRequest)</h4>
<form method="post">
	<input type="hidden" name="_method" value="put"/>
	<input type="text" name="param1" value="VALUE1" />
	<input type="text" name="param2" value="한글값" />
	<input type="file" name="sendFile" />
	<button type="submit"> 전송 </button>
</form>
<pre>
	: http 프로토콜로 패키징된 요청에 대한 정보와 해당 요청을 발생시킨 클라이언트에 대한 정보를 가진 객체이다.
	HTTP(Hyper Text Transfer Protocol) : HTML 문서 전송시 패키징 규칙성에 대한 정의
	1. Request Line : URL(수신자 정보. 명사, 자원에대한식별자), request Method(현재 요청의 목적)
		request Method(자원에 대한 행위를 표현하는 동사) : 요청의 목적(의도)과 패키징 구조에 대한 표현
		Get-R(클라이언트가 사용하는 기본 method) : 조회가 목적이다. 
		Post-C : 새로운 자원 등록이 목적이다.
		PUT/PATCH-U : 기존 자원의 수정을 의미한다. PATCH 방식은 업데이트된 속성만 수정하는거, PUT 방식은 기본값+수정값으로 덮어쓰는 방식으로 수정하는거
		DELETE-D : 기존 자원의 삭제를 의미한다.
		OPTION : preFlight 요청에 사용된다.
		HEAD : reponse에 body가 없는 line과 header 로만 구성된 response 를 받기위한 요청이다. 서버에 어떤 액션을 취하고 결과는 가져오지 않는 요청이다. reponse에 body가 없다.
		TRACE : 
		
		RESTful URI 구조. : 한개의 URL을 가지고 4가지 이상의 다른 요청을 보내는것
		
		ex) /member/memberInsert.do
		ex) /member/memberDelete.do
		
		- RESTful 방식
		ex) /member GET
		ex) /member POST
		ex) /member PUT
		ex) /member DELETE
		
	2. Request Header : 클라이언트의 요청을 부가적으로 수식해주는 메타데이터의 영역이다.
		header name : header value 의 한쌍으로 만들어져있다.
		Accept-* : 클라이언트가 뭘 받을 수 있다라는걸 알림
		Accept : response content-type 을 표현한다.
			ex) text/html
				application/json
		Accept-Language : response language 표현
			ex ) ko_KR,en_US
		Accpet-Ecoding : response data compress 방식을 표현한다
			ex ) gzip
		
		Content-* : POST 요청으로 request body가 있다.
		
		Contentent-Type : request body content mime type 설정한다
			ex) application/x-www-from-urlencoded
			    multipart/form-data
		Content-length : body content length 를 표현한다.
	
		User-Agent : 클라이언트가 사용하고 있는 시스템이나 브라우저 랜더링 엔진에 대한 표현이다. 현재 클라이언트의 환경을 이 헤더에서 표현해준다.
		
	3. Request Body(Content Body, Message Body) : POST 요청에서만 형성되는 영역으로 전송 컨텐츠의 영역이다.
		1) request parameter : application/x-www-from-urlencoded MIME 에 따라
			전송 가능한 직렬화되 문장으로 데이터가 전송된다
			ex) name=value1&name=value2
			파라미터 형태의 전송데이터는 body 가 없는 경우, 제한적으로 request line 을 통해 전송되기도 한다.
			-> Query string 의 형태로 전송된다.
			GET : URL?name=value1&name=value2
			
		2) multipart data : multipart/form-data MIME 에 따라
			body 영역이 여러개의 part 로 분리되고, 각 part 에 입력 데이터가 포함되어 전송된다.
			
		3) Object payload : applicatin/json MIME 에 따라
			body 영역을 통해 json 이나 xml 페이로드를 전송하게된다.
		
	
	
	
	
	프리플라이트 요청 : 서버가 get, post 이외의 요청(put,delete)을 받아 들일 수 있는지 판단하기 위한 요청
</pre>

</body>
</html>



































