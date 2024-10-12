<%--
  Created by IntelliJ IDEA.
  User: vanph
  Date: 10/12/2024
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<a href="<c:url value='/admin/video/insert'/>">Thêm mới video</a>

<form action="<c:url value='/admin/videos'/>" method="get">
    <input type="text" name="search" placeholder="Search videos..." value="${param.search}">
    <input type="submit" value="Search">
</form>

<br/>
<table>
    <tr>
        <th>STT</th>
        <th>Poster</th>
        <th>Title</th>
        <th>Description</th>
        <th>Views</th>
        <th>Status</th>
        <th>Action</th>
        <th>Play</th>
    </tr>
    <c:forEach items="${listvideo}" var="video" varStatus="STT">
        <tr>
            <td>${STT.index + 1}</td>

            <!-- Hiển thị poster của video -->
            <c:if test="${video.posterURL.substring(0,5) == 'https'}">
                <c:url value="${video.posterURL}" var="posterUrl"></c:url>
            </c:if>
            <c:if test="${video.posterURL.substring(0,5) != 'https'}">
                <c:url value="/image?fname=${video.posterURL}" var="posterUrl" />
            </c:if>
            <td><img height="150" width="200" src="${posterUrl}" /></td>

            <td>${video.title}</td>
            <td>${video.description}</td>
            <td>${video.views}</td>
            <td><c:choose>
                <c:when test="${video.active == 1}">Active</c:when>
                <c:otherwise>Inactive</c:otherwise>
            </c:choose>
            </td>
            <td>
                <a href="<c:url value='/admin/video/edit?id=${video.videoID}'/>">Sửa</a> |
                <a href="<c:url value='/admin/video/delete?id=${video.videoID}'/>"
                   onclick="return confirm('Are you sure you want to delete this video?');">Xóa</a>
            </td>

            <!-- Hiển thị poster của video -->
            <c:if test="${video.videoURL.substring(0,5) == 'https'}">
                <c:url value="${video.videoURL}" var="videoUrl"></c:url>
            </c:if>
            <c:if test="${video.videoURL.substring(0,5) != 'https'}">
                <c:url value="/video?fname=${video.videoURL}" var="videoUrl" />
            </c:if>

            <!-- Thêm nút để phát video -->
            <td>
                <button onclick="playVideo('${videoUrl}')">Play</button>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Thêm khung phát video -->
<div id="videoPlayerContainer" style="display:none;">
    <h3>Now Playing:</h3>
    <video id="videoPlayer" width="600" controls>
        <source id="videoSource" src="" type="video/mp4">
        Your browser does not support the video tag.
    </video>
</div>

<script>
    // Hàm để phát video khi người dùng nhấn nút "Play"
    function playVideo(videoURL) {
        // Xây dựng đường dẫn đầy đủ nếu videoURL chỉ là tên file
        var fullVideoURL = videoURL;

        // Hiển thị và phát video
        document.getElementById('videoPlayerContainer').style.display = 'block';
        var videoPlayer = document.getElementById('videoPlayer');
        var videoSource = document.getElementById('videoSource');
        videoSource.src = fullVideoURL;
        videoPlayer.load();
        videoPlayer.play();
    }
</script>
