<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp"></jsp:include>
</head>
<body>
<div class="container">
<div class="card">
	<%-- <jsp:include page="/09/fragment1.jsp"></jsp:include> --%>
	<iframe src="<%=request.getContextPath() %>/09/fragment1.jsp"></iframe>
</div>
<div class="card">
	<jsp:include page="/09/fragment2.jsp"></jsp:include>
</div>
</div>
<%-- <jsp:include page="/WEB-INF/includee/postScript.jsp"></jsp:include> --%>
	<iframe src="/WEB-INF/includee/postScript.jsp"></iframe>
</body>
</html>
