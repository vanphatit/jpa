<%--
  Created by IntelliJ IDEA.
  User: vanph
  Date: 9/26/2024
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<a href="<c:url value='/admin/category/insert'/>">Thêm mới</a>
<br/>
<table>
    <tr>
        <th>STT</th>
        <th>Images</th>
        <th>Category Name</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listcate}" var="cate" varStatus="STT" >
        <tr>
            <td>${STT.index+1}</td>

            <c:if test="${cate.images.substring(0,5)=='https'}">
                <c:url value="${cate.images}" var="imgUrl"></c:url>
            </c:if>

            <c:if test="${cate.images.substring(0,5)!='https'}">
                <c:url value="/image?fname=${cate.images}" var="imgUrl" />
            </c:if>

            <td><img height="150" width="200" src="${imgUrl}" /></td>
            <td>${cate.categoryName}</td>
            <td>${cate.status}</td>
            <td><a href="<c:url value='/admin/category/edit?id=${cate.categoryID}'/>">Sửa</a>
                | <a href="<c:url value='/admin/category/delete?id=${cate.categoryID}'/>" >Xóa</a></td>
        </tr>
    </c:forEach>
</table>