<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String minDanParam = request.getParameter("minDan");
String maxDanParam = request.getParameter("maxDan");

if(minDanParam != null && !maxDanParam.isEmpty() && maxDanParam != null && !maxDanParam.isEmpty()){
	try{
		minDan = Integer.parseInt(minDanParam);
		maxDan = Integer.parseInt(maxDanParam);
		if(minDan < 1 || minDan > 9) minDan = 1;
		if(minDan < 1 || maxDan > 9) maxDan = 9;
		if(minDan > maxDan) {
			minDan = 1;
			maxDan = 9;
		}
	} catch(NumberFormatException e){
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		return;
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! 
int minDan = 1;
int maxDan = 10;


// 구구단 단수표기 만들기 
String danTitle(int dan){
	return String.format("<th>%d단 &nbsp;</th>", dan);
}

// 구구단 내용 만들기
String danContent(int dan){
	StringBuffer result = new StringBuffer();
	
	for(int i = 1; i <= 9; i++){
		result.append(String.format("<td>%d * %d = %d &nbsp;</td>", dan, i, (dan*i)));
	}
	return result.toString();
}

// 구구단 만들기
String gugugu(int minDan, int maxDan){
	StringBuffer result = new StringBuffer();
	
	for(int i = minDan; i <= maxDan; i++){
		result.append("<tr>");
		result.append(danTitle(i));
		result.append(danContent(i));
		result.append("</tr>");
	}
	
	return result.toString();
}
%>
<form action="" method="GET">
	<!-- 사용자 입력 -->
	<input type="number" name="minDan" value="<%= minDan %>"/>
	<input type="number" name="maxDan" value="<%= maxDan %>"/>
	
	<!-- 구구단 출력 버튼 -->
	<button type="submit">구구단 출력</button>
<table>
	<%= gugugu(minDan, maxDan) %>
</table>
</form>
</body>
</html>
<!--  **** 다음 조건에 맞는 구구단 서비스를 구현하세요.

1. request URI : /05/gugudan.jsp
2. 승수의 범위 : 1 ~ 9
2. 단의 범위는 전송 파라미터로 결정됨 : minDan, maxDan
3. 출력 형태 : ex) minDan=3, maxDan=5
  <table>
        <tr>
            <th>3단</th><td>3*1=3</td><td>3*2=6</td>....<td>3*8=24</td><td>3*9=27</td>
        </tr>
        <tr>
            <th>4단</th><td>4*1=4</td><td>4*2=8</td>....<td>4*8=32</td><td>4*9=36</td>
        </tr>
        <tr>
            <th>5단</th><td>5*1=5</td><td>5*2=10</td>....<td>5*8=40</td><td>5*9=45</td>
        </tr>
    </table>
**** jsp 파일 하나의 model1 아키텍처로 구현하도록 합니다.

1. 메서드 선언
2. null 여부, 공백 여부 검사
3. 승수 범위 검사
4. 메서드 호출
 -->