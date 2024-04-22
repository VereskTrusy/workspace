<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
leftOp, rightOp, operator
<form name="calForm" action="${pageContext.request.contextPath }/mission/case08_9" method="post" enctype="application/x-www-form-urlencoded">
	<input type="number" name="leftOp" />
	<select name="operator">
		<option value="PLUS" >+</option>
		<option value="MINUS" >-</option>
		<option value="MULTIPLY" >*</option>
		<option value="DIVIDE" >/</option>
	</select>
	<input type="number" name="rightOp" />
	
	<c:url value="/mission/case08_9/operator" var="url" />
	<button type="submit">=</button>
</form>
</body>
<script src="${pageContext.request.contextPath }/resources/app/calForm/calForm.js"></script>
</html>
