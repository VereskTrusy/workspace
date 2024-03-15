<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
String message = (String) session.getAttribute("message");
if(message != null && !message.isEmpty()) {
	session.removeAttribute("message"); // flash attribute : 한번쓰고 지우는거.
%>
<script type="text/javascript">
	alert("<%=message%>");
</script>
<%
}
%>
</head>
<body>
<form action="<%=request.getContextPath()%>/login/loginProcess.do" method="post" enctype="application/x-www-form-urlencoded">
	<%
		String paramId = Optional.ofNullable(request.getParameter("memId"))
							.orElse("");
		
	%>
	<input type="text" name="memId" placeholder="ID" value="<%=paramId%>"/>
	<input type="password" name="memPass" placeholder="Password"/>
	<button type="submit">로그인</button>
</form>
</body>
</html>
