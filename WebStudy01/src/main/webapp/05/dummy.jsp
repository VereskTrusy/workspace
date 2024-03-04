<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int max = 10;
	String maxParam = request.getParameter("max");
	
	if(maxParam != null && !maxParam.isEmpty()){
		try{
			max = Integer.parseInt(maxParam);
			if(max < 0 ) max = 10;
		} catch(NumberFormatException e){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
li.even{
	background-color : yellow;
}
li.odd{
	background-color : red;
}
</style>

</head>
<body>
<!-- 1~10까지 ul태그를 사용해서 출력 -->
<!-- scriptlet 기호와 지시자??? 만 사용해서 출력하기 -->
<!-- 클라이언트에게 max를 받아서 파라미터 설정된 값만큼 li 태그를 보여주고 해당 파라미터가 존재하지 않거나 음수가 돌아온다면 기본 10개를 출력한다

 1. 사용자에게 파라미터 값 입력 받기
 	- null : li 10개 출력
 	- 음수 :  li 10개 출력
 2. 입력 받은 값을 저장 후 printNumber() 호출
 -->
<%! StringBuffer printNumber(int max){
	StringBuffer liTags = new StringBuffer();
	String liPtrn = "<li class='%s'>%d</li>";
	
	for(int i = 1; i <= max; i++){
		boolean odd = i % 2 == 1;
		String clzValue = odd ? "odd" : "even";

		liTags.append(String.format(liPtrn, clzValue, i));
	}
	return liTags;
}
%>
<a href="?max=5">더미</a>
<img src=""/>
<form>
<input type="number" name="max" value="<%= max %>"/>
<button type="submit" id="btn" value="전송"></button>

<ul id="numList">
	<%= printNumber(max) %>
</ul>
</form>

</body>
</html>