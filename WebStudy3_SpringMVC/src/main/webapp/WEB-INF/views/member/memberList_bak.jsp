<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<style type="text/css">
	tr[data-mem-Id]{ /* 속성으로 그룹핑 */
		cursor: pointer;
	}
</style>
<button type="button" onclick="location.href='<c:url value='/member/memberInsert.do'/>'">회원가입</button>
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
			<th>기능</th>
		</tr>
	</thead>
	<tbody>
	<c:if test="${not empty memberList}">
		<c:forEach items="${memberList}" var="member">
			<c:set value="${member.memId eq lastCreated.memId ? 'active' : ''}" var="clzValue"/>
			<c:url value="/member/memberDetail.do" var="memberURL">
				<c:param name="who" value="${member.memId}" />
			</c:url>
			<tr class="${clzValue}" data-mem-id="${member.memId}" data-bs-toggle="modal" data-bs-target="#exampleModal" data-url="${memberURL}">
				<td>${member.rnum}</td>
				<td>${member.memName}</td>
				<td>${member.memAdd1}</td>
				<td>${member.memAdd2}</td>
				<td>${member.memHp}</td>
				<td>${member.memMail}</td>
				<td>${member.memMileage}</td>
				<td><button type="button" id="" onclick="location.href='${memberURL}'">수정하기</button></td>			
<%-- 				<td><button type="button" id="" onclick="location.href='<c:url value="/member/memberUpdate.do?who=${member.memId}"/>'">수정하기</button></td>			 --%>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty memberList}">
		<tr>
			<td colspan="6">회원 정보 없음</td>				
		</tr>
	</c:if>
	<c:remove var="lastCreated" scope="session"/> <!-- 세션안에 해당 속성 지우기 -->
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<!-- paging -->
				${pagingHTML }
				<!-- search -->
				<div id="searchUI">
					<form:select path="paging.simpleCondition.searchType">
						<form:option value="" lable="전체" />
						<form:option value="name" lable="이름" />
						<form:option value="address" lable="지역" />
					</form:select>
					<form:input path="paging.simpleCondition.searchWord"/>
					<button id="searchBtn">검색</button>						
				</div>
			</td>
		</tr>
	</tfoot>
	<!-- 검색 전달용 -->
	<form:form id="searchForm" modelAttribute="paging" action="${pageContext.request.contextPath}/member/memberList.do" method="get"> <!-- 컨트롤러에서 보낸 모델과 연동됨 -->
		<form:input path="simpleCondition.searchType"/>
		<form:input path="simpleCondition.searchWord"/>
		<input type="text" name="currentPage" value="1"/>
	</form:form>
<%-- 	<form id="searchForm" action="<c:url value='/member/memberList.do'/>"> --%>
<!-- 		<input type="text" name="searchType" /> -->
<!-- 		<input type="text" name="searchWord"/> -->
<!-- 		<input type="text" name="currentPage"/> -->
<%-- 	</form> --%>
	
	<script>
		function ${pagingFunction}(page){
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
</table>
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
      </div>
    </div>
  </div>
</div>
<script src="<c:url value='/resources/js/app/member/memberList.js' />"></script>
