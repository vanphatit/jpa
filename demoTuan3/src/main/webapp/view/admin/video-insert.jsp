<%--
  Created by IntelliJ IDEA.
  User: vanph
  Date: 10/12/2024
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>Thêm mới Video</h2>

<form action="<c:url value='/admin/video/insert'></c:url>" method="post" enctype="multipart/form-data">

    <!-- Trường hidden để lưu videoID (nếu cần) -->
    <input type="text" id="videoid" name="videoid" hidden="hidden"
           value="${video.videoID}">

    <!-- Nhập tiêu đề cho video -->
    <label for="title">Video Title:</label><br>
    <input type="text" id="title" name="title" value="${video.title}"><br>

    <!-- Nhập mô tả cho video -->
    <label for="description">Description:</label><br>
    <textarea id="description" name="description">${video.description}</textarea><br>

    <!-- Tải lên poster -->
    <label for="poster">Poster:</label><br>
    <input type="file" onchange="chooseFile(this)" id="poster" name="poster"><br/>

    <!-- Hiển thị poster đã chọn hoặc đã có -->
    <c:if test="${video.poster.substring(0,5) == 'https'}">
        <c:url value="${video.poster}" var="posterUrl"></c:url>
    </c:if>
    <c:if test="${video.poster.substring(0,5) != 'https'}">
        <c:url value="/image?fname=${video.poster}" var="posterUrl" />
    </c:if>
    <img id="imagess" height="150" width="200" src="${posterUrl}" /><br/>

    <!-- Tải lên video -->
    <label for="video">Upload Video:</label><br>
    <input type="file" id="video" name="video"><br/><br/>

    <!-- Hiển thị trạng thái video (Active / Inactive) -->
    <input type="radio" id="active" name="active" value="1" ${video.active == 1 ? 'checked' : ''}>
    <label for="active">Active</label><br>
    <input type="radio" id="inactive" name="active" value="0" ${video.active == 0 ? 'checked' : ''}>
    <label for="inactive">Inactive</label><br>

    <!-- Nút submit để thêm mới video -->
    <input type="submit" value="Insert">
</form>