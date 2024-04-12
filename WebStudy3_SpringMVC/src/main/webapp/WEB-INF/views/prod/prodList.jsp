<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- tiles를 사용하더라도 템플릿에 적용된 사항이 아니기 때문에 모든 jsp에 적용해주어야한다. -->

<table class="table table-bordered table-striped">
	<thead class="table-dark">
		<tr>
			<th>상품명</th>
			<th>분류명</th>
			<th>거래처명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty prodList}">
			<c:forEach items="${prodList}" var="prod">
				<tr>
					<td>
						<c:url value="/prod/prodDetail.do" var="detailURL">
							<c:param name="what" value="${prod.prodId}" />
						</c:url>
						<a href="${detailURL}">${prod.prodName}</a>
					</td>
					<td>${prod.lprod.lprodNm}</td>
					<td>${prod.buyer.buyerName}</td>
					<td>${prod.prodCost}</td>
					<td>${prod.prodPrice}</td>
					<td>${prod.prodMileage}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty prodList}">
			<tr>
				<td colspan="6">상품 정보 없음</td>
			</tr>
		</c:if>
	</tbody>
</table>
