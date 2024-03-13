<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/flowControl.jsp</title>
</head>
<body>
<h4>웹 어플리케이션의 흐름제어방식</h4>
<pre>
	1. Request Dispatch : 페이지를 이동하기 전에 발생한 원본 요청을 그대로 전달하는 구조.
			A 와 B 는 동일한 요청을 공유하게 됨.
		response 에 대한 책임 담당에 대해 2가지로 나뉜다.
		1) forward : 최종 목적지인 B 에서 응답이 전송되는 구조. -> Model 2 구조를 표현할때 사용.
		2) include : A 와 B 가 응답에 대한 책임을 공유하는 구조. -> 페이지 모듈화 구조를 표현할 때 사용.
		<%
			//RequestDispatcher rd = request.getRequestDispatcher("/05/standard.jsp");
			//rd.forward(request, response);
			//rd.include(request, response);
			
		%>
<%-- 		<jsp:include page="/05/standard.jsp"></jsp:include><!-- 커스텀 태그, 백엔드에서 동작, java method 이다, jsp flegment라고 한다 --> --%>
	2. Redirect : 이동하기 전에 body 가 없고, 302 상태코드와 Location 헤더로만 구성된 응답이 먼저 전송되고(최초의 요청이 제거된다.)
		Location 방향으로 클라이언트와 완전히 새로운 요청을 발생시키는 구조.
		최초의 요청이 POST 방식으로 전송되고, 서버에서 Redirect 로 이동하면, 이후 요청은 GET 으로 전송되는
		P-R-G 패턴을 구현할 때 사용된다.
		<%
			String location = request.getContextPath()+"/05/standard.jsp";
			response.sendRedirect(location);
		%>
</pre>
</body>
</html>
