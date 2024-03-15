<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="frm" action="/bts" method="post" enctype="application/x-www-form-urlencoded">
	<select id="btsSel">
		<option value="bui">뷔</option>
	</select>
</form>
<script type="text/javascript">
let frm = window['frm'][btsSel];
console.log();
</script>
</body>
</html>