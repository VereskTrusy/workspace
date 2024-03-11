<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h4>POST 요청</h4>
<form action="<%=request.getContextPath() %>/06/case2Req.do" method="post">
	<pre>
		<a href="<%=request.getContextPath() %>/06/case2Req.do?" data-p5="34">요청1</a> <!-- 인풋태그 만 파라미터가 날라간다. -->
		<input type="text" name="p1" placeholder="p1" />
		<input type="number" name="p2" placeholder="p2" />
		<input type="text" name="p2" placeholder="p2" />
		<input type="date" name="p3" placeholder="p3" />
		<input type="datetime-local" name="p4" placeholder="p4" />
		<input type="text" id="p6" placeholder="p6" />	<!-- name 값이 없으면 파라미터가 날라가지 않는다. -->
		<input type="radio" id="p7_y" name="p7" value="true" checked="checked"/>true <!-- 키보드로 입력하지 않은 요소는 선택하기 전엔 파라미터가 날라가지 않는다. -->
		<input type="radio" id="p7_f" name="p7" value="false"/>false
		<label><input type="checkbox" id="p8_1" name="p8" value="CH1" />ch1</label>
		<input type="checkbox" id="p8_2" name="p8" value="CH2"/>
		<lable for="p8_2">ch2</lable>
		<input type="checkbox" id="p8_3" name="p8" value="CH3"/>ch3
		<select name="p9"> <!-- 단일선택 -->
			<!-- <option value>선택</option> --> <!-- 프롬프트 텍스트, value 속성을 안주면 선택이란 텍스트가 파라미터로 넘어간다. -->
			<option value="value1">text1</option>
			<option value="value2">text2</option>
		</select>
		<select name="p10" multiple="multiple"> <!-- 다중선택 -->
			<!-- <option value>선택</option> --> <!-- 멀티플 셀렉트 박스에선 프롬프트 속성을 아예 안준다 -->
			<option value="value1">text1</option>
			<option value="value2">text2</option>
		</select>
		<button type="submit">전송</button>
		<button type="rest">취소</button>
	</pre>
</form>
<div id="resultArea"></div>

<script src="<%=request.getContextPath() %>/resources/js/app/06/formCase2.js"></script>
</body>
</html>









