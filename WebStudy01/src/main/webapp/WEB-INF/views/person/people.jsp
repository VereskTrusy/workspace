<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="cPath" scope="application"></c:set><!-- var 저장 : page scope에 저장됨 -->
<!-- 웹에서 기본 컨텐츠 타입으로 사용되는 HTML 컨텐츠를 생성하기위한 View Layer -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="personForm" action="${cPath}/people.do" method="post">
	<input type="text" name="who" />
</form>
<div>
	<div id="memberInfo"></div>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="once" items="${people}">
				<tr>
					<td>${once.id}</td>
					<td><a href="javascript:;" onclick="clickHandler(event)" data-member-id="${once.id}">${once.name}</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript">
	function clickHandler(event){
		event.preventDefault(); // 기존 이벤트 제거
		let aTag = event.target; // 특정 이벤트가 발생한 요소 가져옴 
		//console.log(aTag.dataset.memberId); // 요소가 가진 데이터에 접근가능(data- 이후의 속성명으로 접근해야함) 
		document.personForm.who.value = aTag.dataset.memberId;
		//document.personForm.requestSubmit(); // 폼의 submit() 이벤트 실행이 아닌 requestSubmit()으로 이벤트를 발생시키는거다
		let memberId = aTag.dataset.memberId;
		
		// 컨텍스트 패스 가져오기
		//console.log(location.host); // localhost
		//console.log(location.href.indexOf(location.host)); // 7
		//console.log(location.host.length); // 9
		//console.log(location.href); // http://localhost/WebStudy01/peopleList.do
		
		let hostIndex = location.href.indexOf( location.host ) + location.host.length; // 
		let contextPath = location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
		console.log(contextPath); // /WebStudy01
		// 이제 컨텍스트 패스는 구햇당
		
		fetch(contextPath + "/people.do", {
		    method: 'POST',
		    headers: {
		        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
		    },
		    body: "who=" + memberId
		})
		    .then(response => {
		        if (!response.ok) {
		            throw new Error('서버 응답에 오류가 있습니다.');
		        }
		        return response.text();
		    })
		    .then(data => {
		        //console.log(data);
		    	//console.log(document.querySelector("#memberInfo"));
		    	var infoTag = document.querySelector("#memberInfo");
		    	infoTag.innerHTML = data;
		    })
		    .catch(error => {
		        console.error(error);
		    });
	}
</script>
</body>
</html>