<%@page import="java.util.Arrays"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String accept = request.getHeader("accept");
	if(accept.contains("json")){
		response.setContentType("application/json;charset=UTF-8");
		%>
		{
			"result":"정상 서비스 완료"
		}
		<%
	}else{
		response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/useRequest</title>
</head>
<body>
<h4>request 기본객체 활용</h4>
<pre>
	1. Line
		<%= request.getRequestURL() %>, <%= request.getMethod() %>
	2. Hearder
		getHeader(name), getHeaderNames(), getDate[Int]Header(name)
	3. body
		1) parameter : String getParameter(name), String[] getParmeterValues(name), getParameterNames(), getParameterMap()
		2) multipart
		3) payload
</pre>
<table>
	<thead>
		<tr>
			<th>파라미터명</th>
			<th>파라미터값</th>
		</tr>
	</thead>
	<tbody>
		<%
			StringBuffer trTags = new StringBuffer();
			String ptrn = "<tr><td>%s</td><td>%s</td></tr>\n";
		
			/* Map<String, String[]> map = request.getParameterMap();
			Iterator<String> it = map.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				String[] valueArr = map.get(key);
				for(int i= 0; i < valueArr.length; i++){
					trTags.append(String.format(ptrn, key, valueArr[i]));
				}
			} */
			
			request.setCharacterEncoding("UTF-8");
			
			Map<String, String[]> parameterMap = request.getParameterMap();
			for( Entry<String, String[]> entry :parameterMap.entrySet()){
				String paramName = entry.getKey();
				String[] paramValues = entry.getValue();
				trTags.append(String.format(ptrn, paramName, Arrays.toString(paramValues)));
			}
			
			
		%>
		<%= trTags %>
	</tbody>
</table>

<table>
	<thead>
		<tr>
			<th>헤더명</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
	<%
		//StringBuffer trTag = new StringBuffer();
		//String ptrn = "<tr><td>%s</td><td>%s</td></tr>\n";
		trTags.delete(0, trTags.length());
		
		Enumeration<String> headerNames = request.getHeaderNames();
	
		while(headerNames.hasMoreElements()){
			String name = (String) headerNames.nextElement();
			String value = request.getHeader(name);
			
			trTags.append(String.format(ptrn, name, value));
		}
	%>
	<%= trTags %>
	</tbody>
</table>
</body>
</html>
<%
	}
%>