<%@page import="kr.or.ddit.servlet02.ImageSteamingServlet"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="kr.or.ddit.servlet07.MbtiControllerServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/12/resourceType.jsp</title>
</head>
<body>
<h4>자원의 분류</h4>
<pre>
	해당 자원에 접근하는 방법에 따른 분류
	1. Web resource : URL 로 접근 할 수 있는 웹을 통해 서비스되는 리소스. 논리주소로 접근 
		ex) http://localhost:80/WebStudy01/resources/images/cat2.png
		<%
			String url = "/resources/images/cat2.png";
			String realPath = application.getRealPath(url); 
			out.println(new File(realPath));
		%>
		
	2. FileSystem Resource : 파일시스템상의 절대 경로로 접근. 물리주소로 접근
		ex) F:\00.medias\images\cute1.PNG
		<%= new File("F:\\00.medias\\images\\cute1.PNG") %>
		
	3. ClassPath Resource : classpath 이후의 경로(qualified name)로 접근. 논리주소로 접근
		ex) /kr/or/ddit/MemberData.properties
		<%
			String logicalPath = "/kr/or/ddit/MemberData.properties";
			String physicalPath = ImageSteamingServlet.class.getResource(logicalPath).getFile();
			out.println(new File(physicalPath));
			URL url2 = MbtiControllerServlet.class.getResource(logicalPath);
			out.println(url2.getPath());
		%>
		<%
			// 소스경로 + 파일네임
			String srcFURL = "/resources/images";
			File srcFolder = new File(application.getRealPath(srcFURL)); out.println("<br>원본폴더 경로 : " + srcFolder + "<br>");
			String srcName = "cat1.png";
			File srcFile = new File(srcFolder, srcName);
			
			String desFURL = "/12";
			File destFolder = new File(application.getRealPath(desFURL)); out.println("<br>복사폴더 경로 : " + destFolder + "<br>");
			File destFile = new File(destFolder, srcName);
			
			// 파일 복사 시작~
			try(InputStream is = new FileInputStream(srcFile);
				BufferedInputStream bis = new BufferedInputStream(is);
				OutputStream os = new FileOutputStream(destFile);
				BufferedOutputStream bos = new BufferedOutputStream(os); 
			){
				int readbyte = -1;
				while((readbyte = bis.read()) != -1) {
					bos.write(readbyte);
				}
				bos.flush();
			} catch(IOException e) {
				out.println(e.getMessage());
			}
%>
</pre>

<img alt="test" src="<%= request.getContextPath() %>/12/cat4.png">
</body>
</html>



























