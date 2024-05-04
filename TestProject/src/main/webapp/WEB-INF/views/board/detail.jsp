<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="container-fluid px-2 px-md-4">
	<div class="page-header min-height-300 border-radius-xl mt-4"
		style="background-image: url('https://images.unsplash.com/photo-1531512073830-ba890ca4eba2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1920&q=80');">
		<span class="mask  bg-gradient-secondary opacity-6"></span>
	</div>
	<div class="card card-body mx-3 mx-md-4 mt-n6">
		<div class="row gx-4 mb-2">
			<div class="col-md-8">
				<div class="h-100">
					<h5 class="mb-1">${board.boTitle }</h5>
				</div>
			</div>
		</div>
		<ul class="list-group">
			<li
				class="list-group-item border-0 d-flex align-items-center px-0 mb-2 pt-0">
				<div class="avatar me-3">
					<img src="${pageContext.request.contextPath }/resources/assets/img/kal-visuals-square.jpg" alt="kal"
						class="border-radius-lg shadow">
				</div>
				<div
					class="d-flex align-items-start flex-column justify-content-center">
					<h6 class="mb-0 text-sm">${board.boDate } / ${board.boHit }</h6>
					<p class="mb-0 text-xs">${board.boWriter }</p>
				</div>
			</li>
		</ul>
		<div class="row">
			<div class="col-md-12">
				<div class="card card-plain h-100">
					<div class="card-header pb-0 p-3">
						<h6 class="mb-0">내용</h6>
					</div>
					<div class="card-body p-3">${board.boContent }</div>
					<hr />
					<c:set value="${board.boardFileList }" var="boardFileList"/>
					<c:if test="${not empty boardFileList }">
						<div class="col-md-12">
							<div class="row">
								<c:forEach items="${boardFileList }" var="boardFile">
									<div class="col-md-2">
										<div class="card shadow-lg">
											<div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
												<a class="d-block blur-shadow-image text-center">
													<c:choose>
														<c:when test="${fn:split(boardFile.fileMime, '/')[0] eq 'image'}">
															<img src="/resources/board/${board.boNo }/${fn:split(boardFile.fileSavepath, '/')[1] }" style="width:50%;"/>
														</c:when>
														<c:otherwise>
															<img src="https://demos.creative-tim.com/test/material-dashboard-pro/assets/img/products/product-1-min.jpg" alt="img-blur-shadow" class="img-fluid shadow border-radius-lg">
														</c:otherwise>
													</c:choose>
												</a>
												<div class="colored-shadow" style="background-image: url(&quot;https://demos.creative-tim.com/test/material-dashboard-pro/assets/img/products/product-1-min.jpg&quot;);"></div>
											</div>
											<div class="card-body text-center bg-white border-radius-lg p-3 pt-0">
												<h6 class="mt-3 mb-1 d-md-block d-none">
													${boardFile.fileName } (${boardFile.fileFancysize })
												</h6>
												<p class="mb-0 text-xs font-weight-bolder text-info text-uppercase">
													<button type="button" 
														class="btn btn-primary btn-sm fileDownload" 
														data-file-no="${boardFile.fileNo }" title="다운로드">
														download
													</button>
												</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:if>

					<form action="/board/delete.do" method="post" id="procForm">
						<input type="hidden" name="boNo" value="${board.boNo }"/>
						<sec:csrfInput/>
					</form>

					<div class="card-footer p-3">
						<button type="button" class="btn btn-outline-primary" id="delBtn">삭제</button>
						<button type="button" class="btn btn-outline-secondary" id="udtBtn">수정</button>
						<button type="button" class="btn btn-outline-success" onclick="javascript:location.href='/board/list.do'">목록</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	var procForm = $("#procForm");
	var delBtn = $("#delBtn");
	var udtBtn = $("#udtBtn");
	
	delBtn.on("click", function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			procForm.submit();
		}
	});
	
	udtBtn.on("click", function(){
		procForm.attr("method", "get");
		procForm.attr("action", "/board/update.do");
		procForm.submit();
	});
	
	$(".fileDownload").on("click", function(){
		var fileNo = $(this).data("file-no");
		location.href="/board/download.do?fileNo="+fileNo+"&${_csrf.parameterName}=${_csrf.token}";
	});
});
</script>