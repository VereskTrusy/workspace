<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MBTI</title>
<%
	String mbtiCookieValue = (String) request.getAttribute("mbtiCookie");
	if(StringUtils.isNotBlank(mbtiCookieValue)){
		%>
		<script>
			document.addEventListener("DOMContentLoaded", function(){
				window["mbti-form"].type.value = "<%= mbtiCookieValue %>";
				window["mbti-form"].requestSubmit();
			});
		</script>
		<%
	}
%>
</head>
<body>
    <form id="mbti-form" method="post" enctype="application/x-www-form-urlencoded">
        <select name="type" onchange="this.form.requestSubmit()">
        <!-- this.form.submit() : submit 이벤트를 발생시키지 않고 바로 전송 -->
        <!-- this.form.requestSubmit() : submit 이벤트를 발생 시킨다. -->
        	<%
	    		Map<String, String> mbtiMap = (Map<String, String>) application.getAttribute("mbtiMap");
	    		for(Entry<String, String> entry : mbtiMap.entrySet()){
	    			%>
	    			<option value="<%= entry.getKey()%>"><%=entry.getValue()%></option>
	    			<%
	    		}
	    	%>
        </select>
    </form>
    <div id="mbti-area"></div>
    
<script src="<%=request.getContextPath() %>/resources/js/app/mbti/mbti.js">
// 동기 비동기
// 1. 대상이 되는 form 태그를 ㅅ너택
// 2. submit 이벤트 안에서
// 3. 기본 액션 막기 후리밴투 디포르트르

// 4. 패치 요청
// 5. 메서드, 컨텐트 타입
// 6. 리절브 리젝트


// 모든 컨텐츠 상단에 "mbti 유형 입ㄴ다." 타이틀 넣기

</script>
</body>
</html>