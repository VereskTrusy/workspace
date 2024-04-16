<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<h4>입력양식구성</h4>
<form:form modelAttribute="bank">
	<form:input path="bankNo" placeholder="계좌번호" />
	<form:errors path="bankNo"/>
	<form:input path="bankName" placeholder="은행명" />
	<form:errors path="bankName"/>
	<form:input path="bankUserName" placeholder="계좌주" />
	<form:errors path="bankUserName"/>
	<form:input path="bankDate" placeholder="개설일" />
	<form:errors path="bankDate"/>
	<button type="submit">전송</button>
</form:form>


<%-- <form method="post"> --%>
<%-- 	<input type="text" name="bankNo" placeholder="계좌번호" value="${bankinfo.bankNo}" /> --%>
<%-- 	<input type="text" name="bankName" placeholder="은행명" value="${bankinfo.bankName}" /> --%>
<%-- 	<input type="text" name="bankUserName" placeholder="계좌주" value="${bankinfo.bankUserName}" /> --%>
<%-- 	<input type="date" name="bankDate" placeholder="개설일" value="${bankinfo.bankDate}" /> --%>
<!-- 	<button type="submit">전송</button> -->
<%-- </form> --%>
