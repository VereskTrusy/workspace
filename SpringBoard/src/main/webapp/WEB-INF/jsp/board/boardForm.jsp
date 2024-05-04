<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="kr.co.sample.board.controller.BoardInsertController" %>

<form:form modelAttribute="${BoardInsertController.MODELNAME }" enctype="multipart/form-data">
<table class="table table-bordered">
	<tr>
		<th>제목</th>
		<td><form:input path="boTitle" class="form-control"/>
			<form:errors path="boTitle" element="span" cssClass="text-danger"/></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><form:input path="boWriter" class="form-control"/>
			<form:errors path="boWriter" element="span" cssClass="text-danger"/></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><form:password path="boPasswd" class="form-control"/>
			<form:errors path="boPasswd" element="span" cssClass="text-danger"/></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><form:input type="email" path="boEmail" class="form-control"/>
			<form:errors path="boEmail" element="span" cssClass="text-danger"/></td>
	</tr>
	<tr>
		<th>아이피</th>
		<td><form:input path="boIp" value="${pageContext.request.remoteAddr }"
				 readonly="true" class="form-control" />
			<form:errors path="boIp" element="span" cssClass="text-danger"/></td>
	</tr>
	<tr>
		<th>파일</th>
		<td>
			<input type="file" name="boFiles" class="form-control" />
			<input type="file" name="boFiles" class="form-control" />
			<input type="file" name="boFiles" class="form-control" />
			<form:errors path="boFiles" element="span" cssClass="text-danger"/>
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><form:textarea path="boContent" class="form-control"/>
			<form:errors path="boContent" element="span" cssClass="text-danger"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="등록" class="btn btn-primary"/>
			<input type="reset" value="취소" class="btn btn-warning"/>
		</td>
	</tr>
</table>
</form:form>
