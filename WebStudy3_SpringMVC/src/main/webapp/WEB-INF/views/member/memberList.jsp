<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <table>
        	<tr><th>회원번호</th><td id="memId"></td></tr>
			<tr><th>회원명</th><td id="memName"></td></tr>
			<tr><th>생일년월일</th><td id="memBir"></td></tr>
			<tr><th>우편번호</th><td id="memZip"></td></tr>
			<tr><th>기본주소</th><td id="memAdd1"></td></tr>
			<tr><th>상세주소</th><td id="memAdd2"></td></tr>
			<tr><th>집전화</th><td id="memHometel"></td></tr>
			<tr><th>회사전화</th><td id="memComtel"></td></tr>
			<tr><th>핸드폰</th><td id="memHp"></td></tr>
			<tr><th>메일주소</th><td id="memMail"></td></tr>
			<tr><th>직업</th><td id="memJob"></td></tr>
			<tr><th>취미</th><td id="memLike"></td></tr>
			<tr><th>기념일 종류</th><td id="memMemorial"></td></tr>
			<tr><th>기념일자</th><td id="memMemorialday"></td></tr>
			<tr><th>마일리지</th><td id="memMileage"></td></tr>
			<tr><th>상태코드</th><td id="memDelete"></td></tr>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<style type="text/css">
	tr[data-mem-Id]{ /* 속성으로 그룹핑 */
		cursor: pointer;
	}
	
</style>

<body data-context-path="${pageContext.request.contextPath}">
<table class="table table-bordered table-striped">
	<thead class="table-dark">
		<tr>
			<th>번호</th>
			<th>회원명</th>
			<th>기본주소</th>
			<th>상세주소</th>
			<th>핸드폰</th>
			<th>메일주소</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
	<c:if test="${not empty memberList}">
		<c:forEach items="${memberList}" var="member">
			<tr data-mem-id="${member.memId}" data-bs-toggle="modal" data-bs-target="#exampleModal">
				<td>${member.rnum}</td>
				<td>${member.memName}</td>
				<td>${member.memAdd1}</td>
				<td>${member.memAdd2}</td>
				<td>${member.memHp}</td>
				<td>${member.memMail}</td>
				<td>${member.memMileage}</td>				
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty memberList}">
		<tr>
			<td colspan="6">회원 정보 없음</td>				
		</tr>
	</c:if>
	</tbody>
</table>
<jsp:include page="/WEB-INF/includee/postScript.jsp"></jsp:include>
<script src="<c:url value='/resources/js/app/member/memberList.js' />"></script>


<tfoot>
	<tr>
		<td colspan="6">
			이름 지역 전체 세가지 검색 조건으로 검색 및 페이징 처리
			레코드 카운트 3 페이지 사이즈 2
			<!-- search -->
			<div id="searchUI">
				<select name="searchType">
					<option value="">전체</option>
					<option value="memName" >이름</option>
					<option value="memAdd1">지역</option>
				</select>
				<input type="text" name="searchWord"/>
				<button id="searchBtn">검색</button>						
			</div>
			<!-- paging -->
			${pagingHTML }
		</td>
	</tr>
</tfoot>
<!-- 검색 전달용 -->
<form id="searchForm" action="<c:url value='/prod/prodList.do'/>">
	<input type="text" name="searchType" />
	<input type="text" name="searchWord"/>
	<input type="text" name="currentPage"/>
</form>

<script>
	$("[name='searchType']").val("${condition.searchType}");
	$("[name='searchWord']").val("${condition.searchWord}");

	function paging(page){
// 		location.href = "?currentPage=" + page;
		searchForm.currentPage.value = page;
		$searchForm.submit();
	}
// 	searchBtn을 클릭하면 , searchUI 가 가진 모든 입력값을 searchForm 으로 복사하고 searchForm 을 전송
	const $searchForm = $("#searchForm");
	
	$("#searchBtn").on("click", function(event){
		let $searchUI = $(this).parents("#searchUI");
		$searchUI.find(":input[name]").each(function(idx, ipt){
			let name = this.name;
			let value = $(this).val();
			searchForm[name].value = value;
		});
		
		$searchForm.submit();
	});
</script>

























