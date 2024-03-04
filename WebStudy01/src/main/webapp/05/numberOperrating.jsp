<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String numParam = request.getParameter("num");
if(numParam != null && !numParam.isEmpty()){
	try{
		num = Integer.parseInt(numParam);
		if(num < 1 || num > 10){
			num = 5;
		}
	}catch(NumberFormatException e){
		response.sendError(response.SC_BAD_REQUEST, e.getMessage());
		return;
	}
}
%>
<%!
// 전역 데이터
int num = 5;

public long sigma(int num){
	long result = 0;
	for(int i = 1; i <= num; i++ ){
		result += i;
	}
	return result;
	
	// 재귀호출 방식
// 	if(num > 1 || num < 10){
// 		throw new IllegalAccessException("1부터 10사이의 피연산자만 처리 가능.");
// 	}
// 	if(num == 1) return 1;
// 	else return num + sigma(num - 1);
}

public long factorial(int num){
	long result = 1;
	for(int i = 1; i <= num; i++){
		result *= i;
	}
	return result;
	
	
	// 재귀호출 방식
// 	if(num > 1 || num < 10){
// 		throw new IllegalAccessException("1부터 10사이의 피연산자만 처리 가능.");
// 	}
// 	if(num == 1) return 1;
// 	else return num * sigma(num - 1);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>numberOperrating.jsp</title>
</head>
<body>
<form action="">
	<input type="number" name="num" min="1" max="10" value="<%= num %>"/>
	<button type="submit">전송</button>
</form>
<% 
	try{
%>
<h4>누적 합 : <%= sigma(num) %></h4>
<h4>누적 곱 : <%= factorial(num) %></h4>
<%
	} catch(IllegalArgumentException e){
		response.sendError(response.SC_BAD_REQUEST, e.getMessage());
	}
%>
</body>
</html>
<!-- 
0. 메서드 선언
1. num 기본값 5
2. 리퀘스트에서 파라미터 1개 받기
3. 파라미터의 널여부, 공백 검사
4. 파라미터의 입력 숫자 범위 검사
5. 연산처리
 -->