<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! //전역 선언
	public String data = "DATA"; // 다른 jsp에서 접근하지 못한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>pageContext</h4>
<pre>
	기본 객체 중 가장 먼저 생성된고, 나머지 기본객체에 대한 참조를 가진 객체.
	객체 참조 주소 비교 해서 동일한 객체 인지 확인 
	<%= request == pageContext.getRequest() %>
	<%= session == pageContext.getSession() %>
	<%= request.getContextPath() %>, ${pageContext.request.contextPath}
	EL태그 안에서 pageContext로 나머지 기본객체에 대해 접근 해야한다.
	
	
	Scope(Map형태이다) : 웹 어플리케이션에서 공유 데이터를 저장하기 위한 저장소 개념.
		생명주기가 다른 4개의 기본객체가 가진 Map.
		scope 를 통해 공유되는 name/value 로 구성된 데이터 : attribute
		스코프는 서버의 부하를 줄이기 위해서 최소한의 스코프를 사용해야한다.
		
	1. page scope : 기본객체인 pageContext가 관리하는 Map
	2. request scope : 기본객체인 request 가 관리하는 Map
	3. session scope : 기본객체인 session 이 관리하는 Map
	4. application scope : servletContext 가 관리하는 Map
	공통적인 메소드
		setAttribute(String name, Object value)
		Object getAttribute(String name)
		removeAttribute(String name)
	<%
		pageContext.setAttribute("pageAttr", "페이지속성");
		request.setAttribute("requestAttr1", "요청속성");
		pageContext.setAttribute("requestAttr2", "요청속성2", PageContext.REQUEST_SCOPE);
		session.setAttribute("sessionAttr", "세션속성");
		application.setAttribute("applicationAttr", "어플리케이션 속성");
		
		//request.getRequestDispatcher("/13/attrView.jsp").include(request, response);
		response.sendRedirect(request.getContextPath() + "/13/attrView.jsp");
	%>
	다른 화면으로 이동하면서 각 스코프의 생명주기 확인
	<!-- <a href="attrView.jsp">속성확인</a> -->
	
	
	
	
	
</pre>
</body>
</html>