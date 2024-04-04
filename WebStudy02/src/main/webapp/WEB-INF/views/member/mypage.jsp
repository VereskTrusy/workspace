<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp"></jsp:include>

<!-- 반복적 패턴 : spring 의 flashMapManager를 통해 해결한다. -->
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session" />
</c:if>
</head>
<body>

	<h4>${member.memName}님의마이페이지</h4>
	<table class="table table-bordered">
		<tr>
			<th>회원번호</th>
			<td><input type="text" name="memId" required="required"
				value="${member.memId}" class="form-control" /><span
				class="text-danger">${errors.memId}</span></td>
		</tr>
		<tr>
			<th>암호</th>
			<td><input type="text" name="memPass" required="required"
				value="${member.memPass}" class="form-control" /><span
				class="text-danger">${errors.memPass}</span></td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><input type="text" name="memName" required="required"
				value="${member.memName}" class="form-control" /><span
				class="text-danger">${errors.memName}</span></td>
		</tr>
		<tr>
			<th>주민번호 앞자리</th>
			<td><input type="text" name="memRegno1"
				value="${member.memRegno1}" class="form-control" /><span
				class="text-danger">${errors.memRegno1}</span></td>
		</tr>
		<tr>
			<th>주민번호 뒤자리</th>
			<td><input type="text" name="memRegno2"
				value="${member.memRegno2}" class="form-control" /><span
				class="text-danger">${errors.memRegno2}</span></td>
		</tr>
		<tr>
			<th>생일년월일</th>
			<td><input type="date" name="memBir" value="${member.memBir}"
				class="form-control" /><span class="text-danger">${errors.memBir}</span></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="memZip" required="required"
				value="${member.memZip}" class="form-control" /><span
				class="text-danger">${errors.memZip}</span></td>
		</tr>
		<tr>
			<th>기본주소</th>
			<td><input type="text" name="memAdd1" required="required"
				value="${member.memAdd1}" class="form-control" /><span
				class="text-danger">${errors.memAdd1}</span></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><input type="text" name="memAdd2" required="required"
				value="${member.memAdd2}" class="form-control" /><span
				class="text-danger">${errors.memAdd2}</span></td>
		</tr>
		<tr>
			<th>집전화</th>
			<td><input type="text" name="memHometel"
				value="${member.memHometel}" class="form-control" /><span
				class="text-danger">${errors.memHometel}</span></td>
		</tr>
		<tr>
			<th>회사전화</th>
			<td><input type="text" name="memComtel"
				value="${member.memComtel}" class="form-control" /><span
				class="text-danger">${errors.memComtel}</span></td>
		</tr>
		<tr>
			<th>핸드폰</th>
			<td><input type="text" name="memHp" value="${member.memHp}"
				class="form-control" /><span class="text-danger">${errors.memHp}</span></td>
		</tr>
		<tr>
			<th>메일주소</th>
			<td><input type="text" name="memMail" required="required"
				value="${member.memMail}" class="form-control" /><span
				class="text-danger">${errors.memMail}</span></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input type="text" name="memJob" value="${member.memJob}"
				class="form-control" /><span class="text-danger">${errors.memJob}</span></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input type="text" name="memLike" value="${member.memLike}"
				class="form-control" /><span class="text-danger">${errors.memLike}</span></td>
		</tr>
		<tr>
			<th>기념일 종류</th>
			<td><input type="text" name="memMemorial"
				value="${member.memMemorial}" class="form-control" /><span
				class="text-danger">${errors.memMemorial}</span></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><input type="date" name="memMemorialday"
				value="${member.memMemorialday}" class="form-control" /><span
				class="text-danger">${errors.memMemorialday}</span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input type="number" name="memMileage"
				value="${member.memMileage}" class="form-control" /><span
				class="text-danger">${errors.memMileage}</span></td>
		</tr>
		<tr>
			<th>상태코드</th>
			<td><input type="text" name="memDelete"
				value="${member.memDelete}" class="form-control" /><span
				class="text-danger">${errors.memDelete}</span></td>
		</tr>
		<tr>
			<th>구매기록</th>
			<td>어떤 상품을 몇개를 구매 했는지 (상품명, 구매량)
				<table class="table table-bordered">
					<thead>
						<th>상품명</th>
						<th>구매량</th>
					</thead>
					<tbody>
						<c:forEach items="${member.cartList}" var="cart">
							<tr>
								<td>
									<c:url value="/prod/prodDetail.do" var="prodDetail">
										<c:param name="what" value="${cart.cartProd}" />
									</c:url>
									<a href="${prodDetail}">${cart.prod.prodName}</a>
								</td>
								<td>${cart.cartQty}</td>
								<td>${cart.cartDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" class="btn btn-primary" id="updateBtn">member
					update</button>
				<button type="button" class="btn btn-danger" data-bs-toggle="modal"
					data-bs-target="#exampleModal">탈퇴</button>
			</td>
		</tr>
	</table>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Modal
						title</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form id="deleteForm" method="post"
					action="<c:url value='/member/memberDelete.do'/>">
					<div class="modal-body">
						<input type="password" name="password" required>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-danger">탈퇴</button>
					</div>
				</form>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		const $updateBtn = $("#updateBtn")
				.on(
						"click",
						function() {
							let who = $(this).data("who");
							location.href = `${pageContext.request.contextPath}/member/memberUpdate.do`;
						});
	</script>
	<jsp:include page="/WEB-INF/includee/postScript.jsp"></jsp:include>
</body>
</html>