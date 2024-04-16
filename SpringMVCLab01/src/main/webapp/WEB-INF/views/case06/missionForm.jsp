<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<form name="calForm" action="" method="post">
	<input type="number" name="leftOp" value="${leftOp}"/>
	+
	<input type="number" name="rightOp" value="${rightOp}"/>
	
	<button type="submit" name="btn">=</button>
	<span id="result-area">${result}</span>
</form>
<script>
/* 1. 이벤트 타겟 고르기
2. 처리할 이벤트 종류
3. 핸들러 구현
4. 타겟에 부착 */
	const $resultArea = $("#result-area");
	$("[name='calForm']").on("submit", function(event){
		
		event.preventDefault();
		
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		
		
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "json", // accept : application/json
			success : function({result}) { // 구조분해문법	
				$resultArea.html(result);
			}
		});
	});
</script>