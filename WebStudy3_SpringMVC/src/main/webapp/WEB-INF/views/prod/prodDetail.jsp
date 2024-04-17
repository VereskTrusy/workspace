<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered">
	<tr>
		<th>상품명</th>
		<td>${prod.prodName}</td>
	</tr>
	<tr>
		<th>상품분류명</th>
		<td>${prod.lprod.lprodNm}</td>
	</tr>
	<tr>
			<th>제조사정보</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>제조사명</th>
							<th>소재지</th>
							<th>담당자명</th>
							<th>연락처</th>
							<th>팩스번호</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>${prod.prodCost}</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>${prod.prodPrice}</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>${prod.prodSale}</td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td>${prod.prodOutline}</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>${prod.prodDetail}</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<img src="<c:url value='/resources/prodImages/${prod.prodImg}'/>">
			</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td>${prod.prodTotalstock}</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>${prod.prodInsdate}</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>${prod.prodProperstock}</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prod.prodSize}</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prodColor}</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>${prod.prodDelivery}</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>${prod.prodUnit}</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>${prod.prodQtyin}</td>
		</tr>
		<tr>
			<th>출고량</th>
			<td>${prod.prodQtysale}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${prod.prodMileage}</td>
		</tr>
	<tr>
		<th>구매자<br>목록
		</th>
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
							<c:set value="${cart.member }" var="member" />
							<td>${member.memName }</td>
							<td>${member.memMail }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</td>
	</tr>
</table>
<c:url value="/prod/prodUpdate.do" var="updateURL">
	<c:param name="what" value="${prod.prodId}" />
</c:url>
<button type="submit" onclick="location.href='${updateURL}';">수정하기</button>
<!--  -->
