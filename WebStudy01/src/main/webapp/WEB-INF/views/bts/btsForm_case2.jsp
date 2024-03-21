<%@page import="kr.or.ddit.vo.BtsVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp"></jsp:include>
<% 
	// 속성에서 data 꺼내기
	List<BtsVO> btsList = (List<BtsVO>) request.getAttribute("btsList");
%>
</head>
<body>
<form name="btsForm" action="" method="post" enctype="application/x-www-form-urlencoded"><!-- onchange : 익명함수???. onchange="event.target"???  onchange="this.form"???-->
	<select name="member" onchange="this.form.requestSubmit();" required="required">
		<option value="">선택</option><!-- 프롬프트 텍스트,  -->
		<c:forEach var="bts" items="${btsList}">
			<option value="${bts.code}" label="${bts.name}${bts.count}"/>
		</c:forEach>
	</select>
</form>
<div id="result-area"></div>
<script type="text/javascript">
	/* document.btsForm.addEventListener("submit", function({target}){ // target 이라는 속성으로 e.target을 받아 올 수 있다.
		
	} */
	
	/* document.btsForm.addEventListener("submit", function(e){
		e.preventDefault();
		// 비동기 전송 정보 만들기 
		let form = e.target; // form 요소 접근
		let url = form.action; // http://localhost/WebStudy01/bts
		let method = form.method;
		let headers = {
			"content-type" : form.enctype, // 보낼 컨텐츠 설정
			"accept" : "text/html" // 받을 컨텐츠 설정
		};
		let formData = new FormData(form); //  폼데이터 키, 값
		let queryString = new URLSearchParams(formData).toString(); // 직렬화
		let body = queryString;
		let option = {
			method : method,
			headers : headers,
			body : body
		};
		fetch(url, option)
		.then((resp)=>{ // 리절브 함수 1
			resp.text();
		})
		.then((text)=>{
			window["result-area"].innerHTML = text;
		})
		.catch((e)=>{ // 리젝트 함수
			console.error(e);
		});
	} */
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/app/bts/btsForm.js"></script>
<jsp:include page="/WEB-INF/includee/postScript.jsp"></jsp:include>
</body>
</html>
