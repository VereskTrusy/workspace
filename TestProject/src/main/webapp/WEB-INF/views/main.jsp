<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<body>



	<sec:authorize access="isAnonymous()">
		<input type="button" id="loginBtn" value="로그인"/>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<form method="get" id="mainForm">
			<input type="button" id="logoutBtn" value="로그아웃"/>
		</form>
	</sec:authorize>
	<hr/>
	<a href="/board/list.do">게시판</a>
</body>
<script type="text/javascript">
$(function(){
	var mainForm = $("#mainForm");
	var loginBtn = $("#loginBtn");
	var logoutBtn = $("#logoutBtn");
	
	loginBtn.on("click", function(){
		location.href = "/signin.do";
	});
	
	logoutBtn.on("click", function(){
		mainForm.attr("action", "/logout");
		mainForm.attr("method", "post");
		mainForm.submit();
	});
});
</script>
</html>