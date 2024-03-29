<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
   request.setCharacterEncoding("utf-8");

   Calendar cal = Calendar.getInstance();
   
   // 시스템 오늘날짜
   int ty = cal.get(Calendar.YEAR);
   int tm = cal.get(Calendar.MONTH)+1;
   int td = cal.get(Calendar.DATE);
   
   int year = cal.get(Calendar.YEAR);
   int month = cal.get(Calendar.MONTH)+1;
   
   // 파라미터 받기
   String sy = request.getParameter("year");
   String sm = request.getParameter("month");
   
   if(sy!=null) { // 넘어온 파라미터가 있으면
      year = Integer.parseInt(sy);
   }
   if(sm!=null) {
      month = Integer.parseInt(sm);
   }
   
   cal.set(year, month-1, 1);
   year = cal.get(Calendar.YEAR);
   month = cal.get(Calendar.MONTH)+1;
   
   int week = cal.get(Calendar.DAY_OF_WEEK); // 1(일)~7(토)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<style type="text/css">
* {
   margin: 0;
   padding: 0;
   box-sizing: border-box;
}
body {
   font-size: 20px;
   font-family: "맑은 고딕", 나눔고딕, 돋움, sans-serif;
}
label {
   font-size: 40px;
}
a {
   font-size: 40px;
   color: #000;
   text-decoration: none;
   cursor: pointer;
}
a:active, a:hover {
   text-decoration: underline;
   color: #F28011;
}
#fdiv {
   margin: 40px 0;
   display: flex;
   justify-content: center;
}
select{
   margin: 0 20px;
   font-size: 25px;
}
option{
   font-size: 25px;
}
.calendar {
   width: auto;
   margin: 30px auto;
   padding: 0 150px;
}
.calendar .title {
   height: 37px;
   line-height: 37px;
   text-align: center;
   font-weight: 600;
}
.calendar .selectField {
   border: 1px solid #999;
   border-radius: 3px;
   vertical-align: baseline;
}
.calendar table {
   width: 100%;
   border-collapse: collapse;
   border-spacing: 0;
}
.calendar table thead tr:first-child {
   background: #f6f6f6;
}
.calendar table td {
   padding: 30px 10px;
   text-align: center;
   border: 1px solid #ccc;
}
.calendar table td:nth-child(7n+1) {
   color: red;
}
.calendar table td:nth-child(7n) {
   color: blue;
}
.calendar table td.gray {
   color: #ccc;
}
.calendar table td.today {
   font-weight: 700;
   background: #E6FFFF;
}
.calendar .footer {
   height: 25px;
   line-height: 25px;
   text-align: right;
   font-size: 12px;
}
</style>

<script type="text/javascript">
function change() {
   var f = document.frm;
   f.action="calendar2.jsp";
   f.submit();
}
</script>

</head>
<body>

<div class="calendar">
   <div class="title">
   <a href="calendar.jsp?year=<%=year%>&month=<%=month-1%>">◀◀◀</a>
      <label><%=year%>년 <%=month%>월</label>
      <a href="calendar.jsp?year=<%=year%>&month=<%=month+1%>">▶▶▶</a>
   </div>
   <div id = "fdiv">
      <form name="frm" method="post">
         <select name="year" class="selectField" onchange="change()">
            <% for(int i=year-5; i<=year+5; i++){ %>
               <option value="<%=i%>" <%=year==i?"selected='selected'":"" %>><%=i%>년</option>
            <%} %>
         </select>
         <select name="month" class="selectField" onchange="change()">
            <% for(int i=1; i<=12; i++){ %>
               <option value="<%=i%>" <%=month==i?"selected='selected'":"" %>><%=i%>월</option>
            <%} %>
         </select>
         <select name="month" class="selectField" onchange="change()">
            <% for(int i=1; i<=12; i++){ %>
               <option value="<%=i%>" <%=month==i?"selected='selected'":"" %>><%=i%>월</option>
            <%} %>
         </select>
         <select name="month" class="selectField" onchange="change()">
            <% for(int i=1; i<=12; i++){ %>
               <option value="<%=i%>" <%=month==i?"selected='selected'":"" %>><%=i%>월</option>
            <%} %>
         </select>
      </form>
   </div>
   
   <table>
      <thead>
         <tr>
            <td>일</td>
            <td>월</td>
            <td>화</td>
            <td>수</td>
            <td>목</td>
            <td>금</td>
            <td>토</td>
         </tr>
      </thead>
      <tbody>
<%
         // 1일 앞 달
         Calendar preCal = (Calendar)cal.clone();
         preCal.add(Calendar.DATE, -(week-1));
         int preDate = preCal.get(Calendar.DATE);
         
         out.print("<tr>");
         // 1일 앞 부분
         for(int i=1; i<week; i++) {
            //out.print("<td> </td>");
            out.print("<td class='gray'>"+(preDate++)+"</td>");
         }
         
         // 1일부터 말일까지 출력
         int lastDay = cal.getActualMaximum(Calendar.DATE);
         String cls;
         for(int i=1; i<=lastDay; i++) {
            cls = year==ty && month==tm && i==td ? "today":"";
            
            out.print("<td class='"+cls+"'>"+i+"</td>");
            if(lastDay != i && (++week)%7 == 1) {
               out.print("</tr><tr>");
            }
         }
         
         // 마지막 주 마지막 일자 다음 처리
         int n = 1;
         for(int i = (week-1)%7; i<6; i++) {
            // out.print("<td> </td>");
            out.print("<td class='gray'>"+(n++)+"</td>");
         }
         out.print("</tr>");
%>      
      </tbody>
   </table>
</div>

</body>
</html>

수정 버전입니다