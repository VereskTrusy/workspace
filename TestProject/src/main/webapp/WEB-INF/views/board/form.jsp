<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set value="등록" var="name"/>
	<c:if test="${status eq 'u' }">
		<c:set value="수정" var="name"/>
	</c:if>
<div class="container-fluid px-2 px-md-4">
	<div class="page-header min-height-300 border-radius-xl mt-4"
		style="background-image: url('https://images.unsplash.com/photo-1531512073830-ba890ca4eba2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1920&q=80');">
		<span class="mask  bg-gradient-secondary opacity-6"></span>
	</div>
	<form class="card card-body mx-3 mx-md-4 mt-n6" id="boardForm" method="post" action="/board/insert.do" enctype="multipart/form-data">
		<c:if test="${status eq 'u' }">
			<input type="hidden" name="boNo" value="${board.boNo }" id="boNo"/>
		</c:if>
		<div class="row gx-4 mb-2">
			<div class="col-md-12">
				<div class="input-group input-group-outline mb-4">
					<input type="text" class="form-control" id="boTitle" name="boTitle" value="${board.boTitle }" placeholder="제목을 입력해주세요.">
				</div>
			</div>
			<div class="col-md-12">
				<div class="input-group input-group-outline mb-4">
					<textarea class="form-control" cols="50" rows="20" id="boContent" name="boContent">${board.boContent }</textarea>
				</div>
			</div>
			<div class="col-md-12">
				<div class="input-group input-group-outline mb-4">
					<input type="file" class="form-control" id="boFile" name="boFile" multiple="multiple" />
				</div>
			</div>
			<div class="col-md-12"><br/></div>
			<c:if test="${status eq 'u' }">
				<c:set value="${board.boardFileList }" var="boardFileList"/>
				<c:if test="${not empty boardFileList }">
					<div class="col-md-12">
						<div class="row">
							<c:forEach items="${boardFileList }" var="boardFile">
								<div class="col-md-2">
									<div class="card">
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
													class="btn btn-primary btn-sm fileDelete" 
													id="btn_${boardFile.fileNo }" title="삭제">
													delete
												</button>
											</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</c:if>
			<div class="col-md-12">
				<button type="button" class="btn btn-primary" id="insertBtn">${name }</button>
				<c:if test="${status eq 'u' }">
					<button type="button" class="btn btn-danger" onclick="javascript:location.href='/board/detail.do?boNo=${board.boNo}'">취소</button>
				</c:if>
				<c:if test="${status ne 'u' }">
					<button type="button" class="btn btn-info" onclick="javascript:location.href='/board/list.do'">목록</button>
				</c:if>
			</div>
		</div>
		<sec:csrfInput/>
	</form>
</div>
<script type="text/javascript">
$(function(){
	CKEDITOR.replace("boContent", {
		filebrowserUploadUrl : "/imageUpload.do?${_csrf.parameterName}=${_csrf.token}"
	});
	CKEDITOR.config.width="100%";
	CKEDITOR.config.height="400px";
	
	var insertBtn = $("#insertBtn");
	var boardForm = $("#boardForm");
	var fileDelete = $(".fileDelete");
	
	insertBtn.on("click", function(){
		var title = $("#boTitle").val();
		var content = CKEDITOR.instances.boContent.getData();	// 내용 데이터
		
		if(title == null || title == ""){
			alert("제목을 입력해주세요.");
			return false;
		}
		if(content == null || content == ""){
			alert("내용을 입력해주세요.");
			return false;
		}
		
		if($(this).text() == "수정"){
			boardForm.attr("action", "/board/update.do");
		}
		
		boardForm.submit();
	});
	
	fileDelete.on("click", function(){
		var id = $(this).prop("id");
		var idx = id.indexOf("_");
		var fileNo = id.substring(idx + 1);	// fileNo 가져오기
		var ptrn = "<input type='hidden' name='delBoardNo' value='%V'/>";
		boardForm.append(ptrn.replace("%V", fileNo));
		$(this).parents("div[class=col-md-2]").hide();
	});
});
</script>