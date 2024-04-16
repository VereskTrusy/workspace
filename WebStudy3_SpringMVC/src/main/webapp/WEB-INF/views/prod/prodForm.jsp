<%@page import="kr.or.ddit.prod.controller.ProdInsertController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form modelAttribute="${ProdInsertController.MODELNAME }" method="post" enctype="application/x-www-form-urlencoded">
   <table>
      <tr>
         <th>상품명</th>
         <td><form:input type="text" path="prodName"
               cssClass="form-control" />
            <form:errors path="prodName" element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>상품분류</th>
         <td>
            <form:select path="prodLgu" items="${lprodList }" itemValue="lprodGu" itemLabel="lprodNm"></form:select>
            <form:errors path="prodLgu" element="span" cssClass="textdanger"/>
      </td>
      </tr>
      <tr>
         <th>거래처</th>
         <td>
            <form:select path="prodBuyer">
               <option value>제조사선택</option>
               <c:forEach items="${buyerList }" var="buyer">
                  <form:option value="${buyer.buyerId }" label="${buyer.buyerName }" cssClass="${buyer.buyerLgu }"/>
               </c:forEach>
            </form:select>
         <form:errors path="prodBuyer" element="span" cssClass="textdanger"/>
         </td>
      </tr>
      <tr>
         <th>구매가</th>
         <td><form:input type="number" path="prodCost"
               cssClass="form-control" /> <form:errors path="prodCost"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>판매가</th>
         <td><form:input type="number" path="prodPrice"
               cssClass="form-control" /> <form:errors path="prodPrice"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>세일가</th>
         <td><form:input type="number" path="prodSale"
               cssClass="form-control" /> <form:errors path="prodSale"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>요약정보</th>
         <td><form:input type="text" path="prodOutline"
               cssClass="form-control" /> <form:errors path="prodOutline"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>상세정보</th>
         <td><form:input type="text" path="prodDetail"
               cssClass="form-control" /> <form:errors path="prodDetail"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>이미지</th>
         <td><form:input type="text" path="prodImg"
               cssClass="form-control" /> <form:errors path="prodImg"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>총재고</th>
         <td><form:input type="number" path="prodTotalstock"
               cssClass="form-control" /> <form:errors path="prodTotalstock"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>입고일</th>
         <td><form:input type="date" path="prodInsdate"
               cssClass="form-control" /> <form:errors path="prodInsdate"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>적정재고</th>
         <td><form:input type="number" path="prodProperstock"
               cssClass="form-control" /> <form:errors path="prodProperstock"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>크기</th>
         <td><form:input type="text" path="prodSize"
               cssClass="form-control" /> <form:errors path="prodSize"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>색상</th>
         <td><form:input type="text" path="prodColor"
               cssClass="form-control" /> <form:errors path="prodColor"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>배송방법</th>
         <td><form:input type="text" path="prodDelivery"
               cssClass="form-control" /> <form:errors path="prodDelivery"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>단위</th>
         <td><form:input type="text" path="prodUnit"
               cssClass="form-control" /> <form:errors path="prodUnit"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>입고량</th>
         <td><form:input type="number" path="prodQtyin"
               cssClass="form-control" /> <form:errors path="prodQtyin"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>출고량</th>
         <td><form:input type="number" path="prodQtysale"
               cssClass="form-control" /> <form:errors path="prodQtysale"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <th>마일리지</th>
         <td><form:input type="number" path="prodMileage"
               cssClass="form-control" /> <form:errors path="prodMileage"
               element="span" cssClass="text-danger"></form:errors></td>
      </tr>
      <tr>
         <td colspan="2"><input type="submit" value="저장" /></td>
      </tr>
   </table>
</form:form>
<script
   src="${pageContext.request.contextPath }/resources/js/app/prod/prodForm.js"></script>
