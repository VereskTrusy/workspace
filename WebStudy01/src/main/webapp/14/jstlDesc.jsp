<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14/jstlDesc.jsp</title>
</head>
<body>
	<h4>JSTL(Jsp Standard Tag Library)</h4>
	<pre>
	JSTL
	: 커스텀 태그 라이브러리
	*** 커스텀 태그 로딩이 필요함 &lt;%@ taglib uri="태그식별" prefix="namespace" %&gt;
	&lt;namespace:tagname attribute_name="attribute_value"&gt;
	
	*** Core 태그(c 태그)
	1. EL변수(속성) 지원 : set, remove (변수의 개념으로 접근하면 안되고 속성의 개념으로 접근해야한다. EL변수의 개념으로 생각해도 된다.)
		c:set 태그의 속성
		var : 속성의 이름 설정
		value : 속성의 이름에 대한 값을 설정
		scope : application, session, request, page, 미설정시:page

		<!-- flash Attribute 방식 -->
		<c:set var="dummy" value="TEXT" scope="session" />
		dummy : ${dummy}
<%-- 		<c:remove var="dummy" scope="session"/> <!-- 스코프 미설정 시 4개의 스코프 영역에서 dummy 속성을 지우게된다. --> --%>
		dummy : ${dummy}
		
	2. 조건문 : if, choose_when_otherwise
		삼항연산자 사용
		<c:set var="dummyClone" value="${not empty dummy ? dummy : 'default'}" />
		dummyClone : ${dummyClone} <!-- default 출력 -->
		
		if 문 사용
		<c:if test="${not empty dummy}">
			${dummy}
		</c:if>
		<c:if test="${empty dummy}">
			${"default"}
		</c:if>
		
		choose when otherwise 사용
		<c:choose>
			<c:when test="${not empty dummy}">
				${dummy}
			</c:when>
			<c:otherwise>
				${"default"}
			</c:otherwise>
		</c:choose>
		
	3. 반복문 : foreach, forTokens
		일반 for문 사용
		for(선언절; 조건절; 증감절){ }
		<c:forEach var="i" begin="1" step="2" end="5" varStatus="vs">
			첫번째 반복문 여부 : ${vs.first} 
			마지막 반복문 여부 : ${vs.last}
			몇번째 반복문 : ${vs.count}
			${i}
		</c:forEach>
	
		forTokens 사용 : token(문장의 구성요소중 의미를 부여할 수 있는 최소한의 단위구성요소)
		토큰?
		intnumber=3; // 한개의 토큰
		int number = 3; // 여러개의 토큰
		아버지가 방에 들어가신다
		아버지 가방에 들어가신다
		<c:forTokens items="아버지 가방에 들어가신다" delims=" " var="token">
			${token}
		</c:forTokens>
		items : 문장
		delims : 구분자
		
		<c:set var="numbers" value="1,2,3,4,5"></c:set>
		<c:forTokens items="${numbers}" delims="," var="num">
			${num*100}
		</c:forTokens>
		
	4. 흐름제어 : redirect
		jsp 스펙에서 기본적으로 제공하는 태그 : 액션태그
<%-- 		<c:redirect url="/14/elDesc.jsp" /> --%>
	5. 기타 : url, out, import
		url
		<c:url value="/14/elDesc.jsp" var="elDesc">
			<c:param name="q1" value="v1"></c:param>
			<c:param name="q2" value="v2"></c:param>
		</c:url>
		${elDesc}
		
		out
		<c:out value="<h4>출력텍스트</h4>" escapeXml="false" />
		
		<c:set var="htmlSource" value="<h4>출력텍스트</h4>"></c:set>
		<c:out value="${htmlSource}" escapeXml="true"></c:out>
</pre>
	<%-- <c:import url="http://www.naver.com" var="naver"></c:import> --%>
	<%-- <c:out value="${naver}" escapeXml="true"></c:out> --%>
</body>
</html>