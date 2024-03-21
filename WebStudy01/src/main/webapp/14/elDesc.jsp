<%@page import="kr.or.ddit.vo.BtsVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14/elDesc.jsp</title>
</head>
<body>
<h4>EL(expression Language, 표현 언어)</h4>
<pre>
	값을 출력하기 위한 목적으로 사용되는 스크립트 언어
	표현식을 대체하는 용도로 사용됨.
	EL표현시을 사용하면 연산의 경우 스크립트 처럼 동작한다.
	
	<%=23 %>, ${23 }
	<%
	   String text = "TEXT";
	   session.setAttribute("textAttr", text);
	%>
	스크립트릿 text : <%=text %>, 
	EL textAttr : ${textAttr} , 
	스크립트릿 <%=session.getAttribute("textAttr") %>
	
	request : ${request}, 
				 ${pageContext.request} 
	pageContext : ${pageContext}
	session : ${session}, 
				 ${pageContext.session} 
	<%
	pageContext.setAttribute("attr", "23");
	 %>
	산술연산자 : ${23 + 12 }, ${attr + 12 }, ${attr * 12 }, ${attr + 12 }, \${attr++ } "\"를 EL연산자 앞에 붙여주면 "$"기호가 단순 문자열로 인식 된다.
	=, ++, concat 은 지원하지 않음. 하위버전에서는 연산자를 지원하지 않지만, 상위버전 EL에서는 지원한다.
	 논리 연산자 : &&(and), ||(or), !(not), 
	 		 ${true and true }, ${dummy and true }, ${not true }, ${not dummy }
	비교 연산자 : >(gt), <(lt), ==(eq), >=(ge), <=(le), !=(ne)
			${23 ne 35 }, ${23 lt 35 }, ${23 gt 35 }, ${23 ge 35 }
	삼항 연산자 : 조건식의 논리값 ? 참일때 표현식 : 거짓일때 표현식
		${23 lt 35 ? "작다" : "크거나 같다"}
	<%
		// empty 의 여부 검사
		//pageContext.setAttribute("dummy", null); // true 비었음
		//pageContext.setAttribute("dummy", " "); // false 비어있지않음
		pageContext.setAttribute("dummy", ""); // true 비었음
		
		List sample = new ArrayList();
		sample.add("element1");
		pageContext.setAttribute("sample", sample); // true 비었음
		
		Map dummyMap = new HashMap();
		dummyMap.put("key1", "value1");
		dummyMap.put("key-2", "value2");
		dummyMap.put("key_3", "value3");
		pageContext.setAttribute("dummyMap", dummyMap);
		BtsVO bts = new BtsVO("B001", "뷔", "path", 100);
		pageContext.setAttribute("bts", bts);
	%>
	단항 연산자 : empty
		${empty dummy}, ${not empty dummy}
		// empty 작동 순서 : 객체의 타입 확인 > 컬렉션이면 랭스 체크 
		sample 의 존재 여부 : ${empty sample ? "비었음" : "비어있지않음" }//비었음
		${sample.get(0)}, ${sample[0]}
		${dummyMap.get("key1")}, ${dummyMap["key1"]}, ${dummyMap.key1} // map으로 접근
		${dummyMap.get("key-2")}, ${dummyMap["key-2"]}, ${dummyMap.key-2} // 하이픈 문자 주의
		${dummyMap.get("key_3")}, ${dummyMap["key_3"]}, ${dummyMap.key_3} // 언더바 문자는 그대로 인식
		${bts}, ${bts.getName()}, ${bts["name"]}, ${bts.name} 
</pre>
<script type="text/javascript">
	//자바스크립트에 EL연산자를 사용하면 
	let attr = 35;
	console.log(`${attr}`); // EL로 사용
	console.log(`\${attr}`); // 템플릿으로 사용
</script>
</body>
</html>