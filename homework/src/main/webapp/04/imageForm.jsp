<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style type="text/css">
			form{
				background-color: blue;
			}
		</style>
	</head>
    <body>
    <%
    File folder = new File("F:/00.medias/images");
	String[] filelist = folder.list(new FilenameFilter() { // 익명객체 : 특징 : 해당 객체를 여기서만 사용한다.
		@Override
		public boolean accept(File dir, String name) {
			String mime = application.getMimeType(name);
			return mime != null && mime.startsWith("image/");
		}
	});
	
	StringBuffer options = new StringBuffer();
	String optPtrn = "\n<option>%s</option>";
	for(String item : filelist) {
		options.append( String.format(optPtrn, item) );
	}
	
	String cPath = request.getContextPath(); 
    %>
        <form action="<%= cPath %>/image.do" method="GET">
            <select name="name">
            <%= options %>
            </select>
            <button type="submit">이미지 랜더링</button>
        </form>
        <script type="text/javascript" src="<%= cPath %>/resources/js/app/04/imageForm.js"></script>
    </body>
</html>
