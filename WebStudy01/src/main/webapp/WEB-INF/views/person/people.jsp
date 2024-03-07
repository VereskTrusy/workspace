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
<style type="text/css">
	/* CSS */
</style>
</head>
<body>
<form name="personForm" action="<%=request.getContextPath()%>/people.do" method="post">
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
			<%
				List<PersonVo> people = (List<PersonVo>) request.getAttribute("people");
				for(PersonVo once : people){
					%>
					<tr>
						<td><%= once.getId() %></td>
						<td><a href="javascript:;" onclick="clickHandler(event)" data-member-id="<%=once.getId() %>"><%=once.getName() %></a></td>
						<!-- data-member-id : HTML5에 추가된 data-XXX속성이다. -->
						<!-- HTML의 속성은 대소문자 구분이 없기때문에 member-id -> javascript로 접근하면 memberId로 변환된다. '-'이후 첫글자 대문자로 변경됨 -->
						<!-- <a href="javascript:;"> 더미스크립트라고 부른다. -->
						<!-- onclick="clickHandler()" 여기서 ""쌍따음표 안에는 익명함수 영역이다. -->
					</tr>
					<%
				}
			%>
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
		console.log(location.host); // localhost
		console.log(location.href.indexOf(location.host)); // 7
		console.log(location.host.length); // 9
		console.log(location.href); // http://localhost/WebStudy01/peopleList.do
		
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