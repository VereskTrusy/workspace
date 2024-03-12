<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ResponseDesc</title>
</head>
<body>
<h4>Http response packaging</h4>
<h4>Http 의 기본 특성 : ConnectLess, StateLess</h4>
<pre>
	1. Response Line : 요청 처리 결과를 표현하는 세자리 숫자(status code)
		1) 1XX : ING..., websoket 에서 한정적으로 사용되는 상태코드.
		2) 2XX : 200(OK)
		3) 3XX : 아직 처리가 완료되지 않았으므로, 클라이언트의 추가 액션()이 필요함.
			: response body 가 없고, 연관된 헤더가 함께 전송됨.
			302/307 : moved, 이동한 이후의 주소를 응답과 함께 전송함(location).
			304 : Not Modified
				브라우저의 캐싱 정책과 연관된 상태코드로 정적자원이 캐싱되어있는 경우, 캐싱 자원의 유효 여부를 알려줄때 사용됨.
		실패를 표현하는 상태코드
		4) 4XX : 클라이언트측 원인으로 처리 실패
			404 : <%=HttpServletResponse.SC_NOT_FOUND %>, 사용자의 URL 식별이 잘못됐을때 발생
			400 : <%=HttpServletResponse.SC_BAD_REQUEST %>, 사용자의 요청을 검증하는 과정에서 주로 발생함.
				ex) 필수파라미터 누락, 잘못된 데이터 타입 전송, 잘못된 데이터 길이 등 검증 할때 직접 발생시킴.
			405 : <%=HttpServletResponse.SC_METHOD_NOT_ALLOWED %>,
				서블릿의 기본 요청 콜백에 정의된 상태코드로 재정의 하기전까지 기본 전송되는 상태코드.
			401/403(보안처리를 위한 접근제어 구조에 사용됨) : 
				<%=HttpServletResponse.SC_UNAUTHORIZED %>, 인증되지 않은 사용자를 표현할 때 발생시킴.
				<%=HttpServletResponse.SC_FORBIDDEN %>, 인가되지 않은 사용자를 표현 할때 발생시킴. 권한이 없는 자원에 접근할 때 발생시킴.
			406 : <%=HttpServletResponse.SC_NOT_ACCEPTABLE %>,  
				accept 요청 헤더로 표현된 response ontent-type 을 처리할 수 없을 때 발생시킴.
			415 : <%=HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE %>
				content-type 헤더와 함께 전송된 request body 를 해석할 수 없을때 발생시킴.
		5) 5XX : 서버측 원인으로 요청 처리 실패
	2. Response Header : response meta data 영역(name/value 로 구성되는 문자열)
	3. Response Body(Content Body, Message Body) : 응답 본문(content) 가 기록되는 영역
</pre>
</body>
</html>