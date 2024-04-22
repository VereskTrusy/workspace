<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 인증과 관련된 모든 요청은 get방식을 사용하지 않고, post 요청만을 사용한다. -->
<h4>Principal : ${pageContext.request.userPrincipal} </h4>
<c:set value="${pageContext.request.userPrincipal}" var="principal"/>
<h4>인덱스 페이지</h4>
<c:choose>
	<c:when test="${not empty principal}">
		
	</c:when>
	<c:otherwise>
		<a href="<c:url value='/login/loginForm.jsp'></c:url>">로그인하기</a>
		<a href="<c:url value='/member/memberInsert.do'></c:url>">가입하기</a>
	</c:otherwise>
</c:choose>
