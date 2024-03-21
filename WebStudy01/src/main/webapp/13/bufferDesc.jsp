<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb" autoFlush="true"%>
<!-- 속성변경 : buffer="8kb" autoFlush="true" -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/bufferDesc.jsp</title>
</head>
<body>
<h4>웹 어플리케이션에서 버퍼의 활용</h4>
<!-- 
아무리 <jsp:include page="" ... 를 해도 버퍼는 한개를 공유한다.
 -->
<%--
	// 공통점 : 원본 request, response 를 같이 가져간다.
	// 버퍼도 한개를 공유한다.
	// 
	request.getRequestDispatcher("").forward(request, response);
	request.getRequestDispatcher("").include(request, response);
--%>
<pre>
	버퍼제어에 사용하는 객체 : out(JspWriter)
	버퍼 크기 : <%= out.getBufferSize() %>
	버퍼 잔여 크기 : <%= out.getRemaining() %>
	자동 방출 지원 여부 : <%= out.isAutoFlush() %>
	<%
		for(int i = 0; i <= 100; i++){
			out.println(String.format("%d 번째 반복으로 메시지 기록.", i));
			if(out.getRemaining() < 100){
				// out.clear(); // 버퍼 방출 1. 클리어 해줌
				out.flush(); // 버퍼 방출 2.
				System.out.printf("%d 번째 버퍼 방출\n", i);
			}
			if( i == 50){
				//throw new RuntimeException("강제 발생 예외");
			}
		}
	%>
	장점 : 전송효율 높음
	단점 : 버퍼의 동작구조를 이해못하면 forward, include 시 문제 발생 가능성
	
</pre>
</body>
</html>