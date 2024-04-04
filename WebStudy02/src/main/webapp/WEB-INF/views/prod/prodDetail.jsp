<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/prod/prodDetail.jsp</title>
<jsp:include page="/WEB-INF/includee/preScript.jsp"></jsp:include>
</head>
<body>
<table class="table table-bordered table-striped">
	<tr>
		<th>상품명</th><td>${prod.prodName}</td>
	</tr>
	<tr>
		<th>제조사정보</th>
		<td>
			<table class="table table-bordered table-striped">
				<thead class="table-dark">
					<tr>
						<th>제조사명</th>
						<th>소재지</th>
						<th>담당자명</th>
						<th>연락처</th>
						<th>팩스번호</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty prod.buyer}">
						<tr>
							<td>${prod.buyer.buyerName}</td>
							<td>${prod.buyer.buyerAdd1}&nbsp;${prod.buyer.buyerAdd2}</td>
							<td>${prod.buyer.buyerCharger}</td>
							<td>${prod.buyer.buyerComtel}</td>
							<td>${prod.buyer.buyerFax}</td>
						</tr>
					</c:if>
					<c:if test="${empty prod.buyer}">
						<tr>
							<td colspan="5">제조사정보 정보 없음</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<th>구매자 목록</th>
		<td>
			<table>
				<thead>
					<tr>
						<th>이름</th>
						<th>이메일</th>					
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${prod.cartList}" var="cart">
						<tr>
							<td>${cart.member.memName }</td>
							<td>${cart.member.memMail }</td>
						</tr>					
					</c:forEach>
				</tbody>
			</table>
		</td>
	</tr>
</table>
<jsp:include page="/WEB-INF/includee/postScript.jsp"></jsp:include>
</body>
</html>