<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/app/08/calculateForm.js"></script>
</head>
<body>
<!-- 비동기 처리 기반의 사칙연산기. -->
<form action="<%=request.getContextPath() %>/calculate.do" name="frm" method="post"><!--  -->
	<input type="number" name="leftOp" />
	<select name="operator">
		<option value>연산자</option>
		<option value="PLUS">+</option>
		<option value="MINUS">-</option>
		<option value="MULTIPLY">*</option>
		<option value="DIVIDE">/</option>
	</select>
	<input type="number" name="rightOp" />
	<input type="submit" id="cal" value="="/>
</form>
<div id="resultArea">
	ex) 2 * 2 = 4
</div>
</body>
</html>
