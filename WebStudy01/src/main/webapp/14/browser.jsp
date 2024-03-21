<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="url" value="${pageContext.request.parameterMap.url[0]}"></c:set>
	<c:set var="srcFlag" value="${pageContext.request.parameterMap['view_source'][0]}"></c:set>
<form>
	<input type="text" name="url" value="${url}"/>
	<input type="checkbox" name="view_source" value="true" ${srcFlag ? 'cheked' : ''} />소스로 보기
	<button type="submit">브라우징</button>
</form>

<div>
	<c:if test="${not empty url}">
		<c:import var="site" url="${url}"></c:import>
		<c:out value="${site}" escapeXml="${srcFlag eq true}"></c:out>
	</c:if>
</div>
</body>
<!-- 
문제 : url input Box 에 입력된 url을 받아서 div에 뿌리기
그리고 브라우저 url의 파라미터가 있는지 없는지 확인 
페이지보기, 소스보기를 구현

1. 입력상자의 값을 받아 변수로 저장
2. 변수의 값으로 c:import 의 url 값을 대입
3. import의 값으로 c:out 해줌
4. 
 -->
</html>

