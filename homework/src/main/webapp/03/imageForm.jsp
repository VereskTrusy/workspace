<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	List<String> list = (List<String>)request.getAttribute("selImageList");
%>
</head>
<body>
<form action="<%= application.getContextPath() %>/image.do" method="GET">
	<select name="selImageList">
	<%
	for(String item : list){
	%>
        <option><%= item %></option>
    <%
	}
    %>
	</select>
	<button type="submit">이미지 랜더링</button>
</form>
</body>
</html>