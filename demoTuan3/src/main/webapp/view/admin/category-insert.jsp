<%--
  Created by IntelliJ IDEA.
  User: vanph
  Date: 9/26/2024
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<form action="<c:url value='/admin/category/insert'></c:url>" method="post" enctype="multipart/form-data">

  <input type="text" id="categoryid" name="categoryid" hidden="hidden"
         value="${cate.categoryID}">

  <label for="fname">Category name:</label><br>
  <input type="text" id="fname" name="categoryname" value="${cate.categoryName}"><br>
  <label for="images">Images:</label><br>
  <input type="file" onchange="chooseFile(this)" id="images" name="images">
  <br/>
  <c:if test="${cate.images.substring(0,5)=='https'}">
    <c:url value="${cate.images}" var="imgUrl"></c:url>
  </c:if>

  <c:if test="${cate.images.substring(0,5)!='https'}">
    <c:url value="/image?fname=${cate.images}" var="imgUrl" />
  </c:if>

  <td><img id="imagess" height="150" width="200" src="${imgUrl}" /></td>
  <br/>
  <input type="radio" id="ston" name="status" value="1" ${cate.status == 1 ? 'checked' : ''}>
  <label for="ston">Online</label><br>
  <input type="radio" id="stoff" name="status" value="0" ${cate.status == 0 ? 'checked' : ''}>
  <label for="stoff">Offline</label><br>
  <input type="submit" value="Insert">
</form>
