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
<h4>Principal : ${pageContext.request.userPrincipal}</h4>
<!-- 인증과 관련된 모든 요청은 get방식을 사용하지 않고, post 요청만을 사용한다. -->
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