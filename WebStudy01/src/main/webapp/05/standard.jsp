<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/standard.jsp</title>
</head>
<body> -->
	<h4>JSP(Java Server Page)</h4>
<pre>
	: 자바와 서블릿 스펙을 기반으로 SSR 을 구현하는 템플릿 엔진.
	출력결과물이 랜더링 되는 방식?
	SSR 방식(Server Side Rendering) : document 의 모든 엘리먼트가 서버에서 텍스트 템플릿으로 생성되는 구조.(jsp)
		템플릿 엔진 : 데이터와 템플릿이 분리되어 개발되고, 분리된 두 요소를 결합해 최종 컨텐츠를 생성하는 소프트웨어
			content -> Mime type (maintype/subtype)
			ex) HTML, JSON, Image, XML(application/xml)
	CSR 방식(Client Side Rendering) : 엘리먼트는 클라이언트측 에서 생성되고, 서버에서 데이터만 서비스 받는 구조.(리액트, 뷰js)
	
	jsp 문법 구성요소
	1. 정적 텍스트 : 일반 텍스트, HTML, JS, CSS / 백엔드 입장에서 본다면 <- 이것들은 모두 텍스트이다.
	2. 스크립트릿(scriptlet, 동적 요소를 위한 백그라운드 코드)
		1) scriptlet : &lt;% // java code %&gt; _JspService 내의 지역코드 작성에 사용.
			<%
				// 변수의 사용 범위 종류
				// 블럭변수 < 지역변수 < 인스턴스 변수 < 클래스(static) 변수
				
				String data = "데이터";
				LocalDateTime now = LocalDateTime.now();
			%>
		2) directive : &lt;%@  %&gt; : 실행코드와 무관하게 jsp 페이지에 대한 부가적인 환경 설정에 사용 
			- 필수 지시자와 옵셔널 지시자로 나뉜다.
			- page(required) 
			- taglib(optional) : 커스텀 태그 로딩에 사용
			- include(optional) : 정적 include에 사용
			<%-- <jsp:include page=""></jsp:include> --%> : 동적 include 방식
		3) expresssion : &lt;%= 출력할 값이나 표현식(javacode)%&gt;
			<%= data %>, <%= now %>
			<% out.println(now); %>
		4) declaration : &lt;%! %&gt; :전역코드 선언에 사용
			<%!
				static String staticData = "전역 데이터";
				void dummy(){}
			%>
		5) comment : <%-- --%>
		주석의 종류 
		- client side comment : 주석으로 처리되는 시점이 클라이언트 : HTML, JS, CSS Comment
		- server side comment : 주석으로 처리되는 시점이 서버 : Java, JSP Commnet
<!-- 		HTML 주석 -->
<!-- 	<style type="text/css"> -->
<!-- /* 		body{ */ -->
<!-- /* 			background-color : red; */ -->
<!-- /* 		} */ -->
<!-- 	</style> -->
		<script type="text/javascript">
// 			var dummy = "더미"; // javascript 주석처리
		</script>
		<%-- JSP 주석 처리 --%>
		
	3. 기본 객체 
		<%-- <% 여기서 코드어시스트로 나오는 객체들 %> --%>
	4. action Tag
		
	5. 표현언어(EL) ${attributeName}
	6. JSTL 커스텀 태그 라이브러리
</pre>
<!-- </body>
</html> -->



































