<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>ServletContext application 기본객체</h4>
<pre>
	ServletContext
	: 서버와 현재 컨텍스트에 대한 정보를 가진 싱글턴 객체. 서블릿 스펙의 모든 객체를 ServletContext 참조를 가지고 있음.
	
	주로 활용하는 부분
	1. 서버 정보 확보 : <%= application %>, <%= request.getServletContext() %>, <%= session.getServletContext() %>
		Tomcat Servlet info : <%=application.getServerInfo() %>
		Servlet info : <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %>
	2. 특정 파일의 mime 타입 조회
		.jpg 의 mime type : <%= application.getMimeType("dummy.jpg") %>
	3. 컨텍스트 파라미터 확보
		<%= application.getInitParameter("param1") %>
	4. 로그 기록
		<%
			application.log("로그기록 메시지");
		%>
	<a href="resourceType.jsp">5(*****). 웹리소스 확보</a> <!-- 이거 중요 -->
		
</pre>
</body>
</html>