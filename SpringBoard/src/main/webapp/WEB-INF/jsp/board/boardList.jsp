<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty boardList }">
				<c:forEach items="${boardList }" var="board">
				<tr>
					<td>${board.rnum }</td>
					<td>
						<a href="<c:url value='/board/${board.boNo }' />">
							${board.boTitle }
						</a>
					</td>
					<td>${board.boWriter }</td>
					<td>${board.boDate }</td>
					<td>${board.boHit }</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5">검색 결과 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>	
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">
				<div id="searchUI"  class="row g-3 d-flex justify-content-center mb-3">
					<div class="col-auto">
						<form:select path="paginationInfo.simpleCondition.searchType" class="form-select">
							<form:option label="전체" value="" />
							<form:option value="title" label="제목" />
							<form:option value="writer" label="작성자" />
							<form:option value="content" label="내용" />
						</form:select>
					</div>
					<div class="col-auto">
						<form:input path="paginationInfo.simpleCondition.searchWord" class="form-control"/>
					</div>
										<div class="col-auto">
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary" />
					</div>
				</div>
				${pagingHTML}
			</td>
		</tr>
	</tfoot>
</table>
<form:form id="searchForm" modelAttribute="paginationInfo" method="get" >
	<form:hidden path="simpleCondition.searchType"/>
	<form:hidden path="simpleCondition.searchWord"/>
	<input type="hidden" name="currentPage" value="1" />
</form:form>
<script>
	function ${pagingFunction}(page){
		searchForm.currentPage.value = page;
		$searchForm.submit();
	}
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
					
