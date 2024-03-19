<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<style type="text/css">
	form{
		background-color: blue;
	}
</style>
<%
	String imageCookieValue = (String) request.getAttribute("imageCookieValue");
	if(StringUtils.isNotBlank(imageCookieValue)){
		%>
		<script>
			document.addEventListener("DOMContentLoaded", function(){
				document.forms[0].name.value = "<%= imageCookieValue %>";
				document.forms[0].requestSubmit();
			});
		</script>
		<%
	}
%>
</head>
<body>
    <form action="${pageContext.request.contextPath}/image.do" method="GET"><%-- ${} 익스프레션랭귀지, el태그는 백그라운드에서 처리되므로 html주석말고 스크립틀릿 주석을 사용해야 주석처리 된다. --%>
       <select name="name">
       ${options}
       </select>
       <button type="submit">이미지 랜더링</button>
   </form>
   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app/04/imageForm.js"></script>
</body>
</html>
