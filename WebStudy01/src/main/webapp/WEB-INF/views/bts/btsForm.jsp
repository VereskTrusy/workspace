<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp"></jsp:include>
</head>
<body>
<form name="btsForm" action="" method="post" enctype="application/x-www-form-urlencoded">
	<select name="member" onchange="this.form.requestSubmit();" required="required">
		<option value="">선택</option>
		<c:forEach var="bts" items="${btsMap}">
			<option value="${bts.key}" label="${bts.value[0]}"/>
		</c:forEach>
	</select>
</form>
<div id="result-area"></div>
<script type="text/javascript">
	document.btsForm.addEventListener("submit", function(e){
		e.preventDefault();
		
		let form = e.target;
		let url = form.action;
		let method = form.method;
		let headers = {
			"content-type" : form.enctype,
			"accept" : "text/html"
		};
		let formData = new FormData(form);
		let queryString = new URLSearchParams(formData).toString();
		let body = queryString;
		let option = {
			method : method,
			headers : headers,
			body : body
		};
		fetch(url, option)
		.then((resp)=>{ // 리절브 함수 1
			return resp.text();
		})
		.then((text)=>{
			window["result-area"].innerHTML = text;
		})
		.catch((e)=>{ // 리젝트 함수
			console.error(e);
		});
	}
</script>
<jsp:include page="/WEB-INF/includee/postScript.jsp"></jsp:include>
</body>
</html>
