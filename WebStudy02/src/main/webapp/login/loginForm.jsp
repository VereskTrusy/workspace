<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	<c:if test="${not empty message} }">
		<c:remove var="message" scope="session"/>
	</c:if>
</script>
</head>
<body>
<form action="<c:url value='/login/loginProcess.do' />" method="post" enctype="application/x-www-form-urlencoded">
	<input type="text" name="memId" placeholder="ID"/>
	<input type="password" name="memPass" placeholder="Password"/>
	<button type="submit">로그인</button>
</form>
</body>
</html>
