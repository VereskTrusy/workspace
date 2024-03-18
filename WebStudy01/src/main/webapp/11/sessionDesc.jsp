<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/sessionDesc.jsp</title>
</head>
<body data-context-path="<%=request.getContextPath() %>>">
<h4>session(HttpSession)</h4>
<pre>
	세션 ?? 
	세션은 두가지 특성으로 나뉜다.
	- ConnectFull : 한개의 connection 과 한개의 session 이 동일한 의미의 통로로 정의됨.
		연결 시작부터 ~ 연결을 끊기 전까지 한 세션으로 정의됨.
	- ConnectLess(HTTP) : 시간으로 정의됨.
		클라이언트가 최초의 연결이 발생한 시간부터 ~ 클라이언트를 떠난 시간 까지가 한 세션으로 정의.
		한세션이 시작되고, 해당 세션내에서 더이상의 명령이 발생하지 않을때가지의 시간.
		timeout 이란, 마지막 요청이 발생하고, 이후 새로운 요청이 발생할때까지의 시간의 간격.
		하나의 세션내에서 하나의 클라이언트가 요청을 보냈는지 식별할 수 있어야하느넫 그때 사용되는게 세션아이디다
		쿠키가 없으면 세션 유지가 안된다.
	
	세션의 생명주기(시작부터 종료까지)
	시작 : 특정클라이언트가 최초의 요청을 발생시키면 세션 시작. ==> 세션아이디가 부여됨
		세션 아이디 : <%= session.getId() %>
		세션 생성 시점 : <%= new Date(session.getCreationTime()) %>
		세션 timeout : <%=session.getMaxInactiveInterval() %> second
		마지막 요청 시간 : <%= new Date(session.getLastAccessedTime()) %> 
		유지방법(tracking mode) : 클라이언트와 서버가 동일한 세션 아이디를 공유.
			1) COOKIE
				쿠키가 없으면 세션 유지가 안된다.
			2) URL : <a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">엣지(쿠키없음)에서 세션 유지</a>
			3) SSL : secure socket layer 구조를 이횽해 전송되는 모든 데이터를 암호화하는 방식.
				openssl 참고
	종료
		timeout 이내에 새로운 요청이 발생하지 않은 경우.
		세션이 종료될 수 있는 상황
		상황1) 클라이언트가 의도적으로 쿠키(세션)를 삭제한 경우, 클라이언트(쿠키삭제) - 서버(dummy세션이 남음)
		상황2) 클라이언트가 브라우저를 종료 후 다시 요청한 경우, 클라이언트(쿠키없음) - 서버(dummy세션이 남음)
			브라우저 설정에 따라 달라질 수 있음.
		상황3) 세션의 종료를 서버에 알린다. 클라이언트(쿠키없음) - 서버(세션만료)
		상황4) 직접 invalidate() 호출하여 쿠키 만료시킴(매우중요)
		<%
			//session.invalidate();
		%>
</pre>
<%-- <input type="hidden" id="sessionCreateTime" value="<%=session.getCreationTime()%>" />
<input type="hidden" id="sessionLastAccessedTime" value="<%=session.getLastAccessedTime()%>" /> --%>

<h4>세션 타이머 : 
<span id="time-area1" data-ts-timeout="<%=session.getMaxInactiveInterval()%>"></span>
<span id="time-area2" data-ts-timeout="180"></span>
</h4>
<button id="stopBtn">STOP</button>
<!-- <div id="msg-area"></div> -->
<!-- <button type="button" id="yesBtn" class="controll">예</button> -->
<!-- <button type="button" id="noBtn" class="controll">아니오</button> -->

<pre>
문제) 은행 타이머 처럼 기능 구현해보기

유지시간(밀리세컨드) = 마지막요청시간(밀리세컨드) + 인터벌(2 * 60 * 1000) 

남은시간 = 유지시간 - 현재시간
남은시간->date객체 분/초 획득

</pre>
<script src="<%=request.getContextPath() %>/resources/js/app/11/sessionDesc.js">
</script>
</body>
</html>
