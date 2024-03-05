<%@page import="kr.or.ddit.vo.PersonVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 웹에서 기본 컨텐츠 타입으로 사용되는 HTML 컨텐츠를 생성하기위한 View Layer -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
		</tr>
	</thead>
	<tbody>
	<%
		List<PersonVo> people = (List<PersonVo>) request.getAttribute("people");
		for(PersonVo once : people){
			%>
			<tr>
				<td><%= once.getId() %></td>
				<td><%= once.getName() %></td>
			</tr>
			<%
		}
	%>
	</tbody>
</table>
</body>
</html>