<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File List</title>
<style type="text/css">
	.folder{
		text-decoration: underline;
		color: blue;
	}
</style>
</head>
<body>
<!-- 파일 | 폴더 확인 하기 -->
<c:set value="${pageContext.request.contextPath}" var="cPath"></c:set>
<ul>
	<c:forEach items="${fileList}" var="single">
		<c:set value="${cPath}/serverFile?base=/${single.name}/" var="folderPath"></c:set>
		<li id="${single.name}" class="${single.file ? 'file':'folder'}" ondblclick="navigator('${folderPath}')">
			${single.name}
		</li>
	</c:forEach>
</ul>
<script type="text/javascript">
function navigator(path){
	location.href=path;
}
</script>
</body>
</html>