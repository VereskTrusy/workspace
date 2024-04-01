<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/index.jsp</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp"></jsp:include>
</head>
<body>
<!-- 로그인 여부(authMember)를 판단하고,
로그인이 된 경우, 해당 사용자의 이름을 출력.
로그인 전인 경우, 로그인 페이지로 이동할 수 있는 a 태그 출력. -->
<h4>인덱스 페이지</h4>
<c:choose>
	<c:when test="${not empty sessionScope.authMember}">
		<a id="" href="<c:url value='/mypage'/>">${authMember.memName}</a>
		<form id="logoutForm" method="post">
			<a href="<c:url value="/login/logout.do" />" class="logoutBtn" data-target-form="#logoutForm">로그아웃</a>
			<script type="text/javascript">
				document.querySelector("a[data-target-form]").addEventListener("click", (e)=>{
					e.preventDefault();
					let aTag = e.target;
					let formSelector = aTag.dataset.targetForm;
					let formTag = document.querySelector(formSelector);
					if(formTag){
						formTag.action = aTag.href;
						formTag.requestSubmit();
					}
				});
			</script>		
		</form>
	</c:when>
	<c:otherwise>
		<a href="<c:url value='/login/loginForm.jsp'></c:url>">로그인하기</a>
		<a href="<c:url value='/member/memberInsert.do'></c:url>">가입하기</a>
	</c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/includee/postScript.jsp"></jsp:include>
</body>
</html>