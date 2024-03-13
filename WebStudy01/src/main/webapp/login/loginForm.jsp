<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/login/loginProcess.do" method="post" enctype="application/x-www-form-urlencoded">
	<input type="text" name="memId" placeholder="ID"/>
	<input type="password" name="memPass" placeholder="Password"/>
	<button type="submit">로그인</button>
</form>
</body>
</html>
