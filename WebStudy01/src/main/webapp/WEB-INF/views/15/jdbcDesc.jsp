<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>JDBC(Java Database Connectivity) 프로그래밍 방법론</h4>
<pre>
	1. JDBC 드라이버를 빌드패스에 로딩.
		/WEB-INF/lib/ojdbc.jar
	2. class loader 를 이용해 드라이버를 메모리에 로딩.(상수 메모리)
	3. 연결(Connection) 수립
	4. 쿼리 객체 생성
		- statement(나중에 컴파일)
		- preparedStatement(미리 컴파일)
		- CallableStatement(plsql 의 펑션 등..)
	5. 쿼리 실행
		- ResultSet executeQuery : SELECT // 돌아오는 결과 집합이 있을때 사용, 결과집합에서 중복되지 않는 primarykey가 필요하다. 
		- int executeUpdate : INSERT, UPDATE, DELETE // 몇개의 쿼리가 반영이 됐는지 리턴
	6. 결과 집합 핸들링
	7. Connection, Statement, ResultSet release // 자원 해제
	
	SSR : Server Side Randering
	CSR : Client Side Randering
</pre>

<table>
	<thead id="head-area">
	</thead>
	<tbody id="data-area">
	</tbody>
</table>
<script src="<c:url value='/resources/js/app/15/jdbcDesc.js'></c:url>"></script>
</body>
</html>