<%--
  Created by IntelliJ IDEA.
  User: vanph
  Date: 9/19/2024
  Time: 1:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<c:url value="/" var="URL"></c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <sitemesh:write property='body'/>
    <script src="${URL}assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script>
        function chooseFile(fileInput) {
            if(fileInput.files && fileInput.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#imagess').attr('src', e.target.result);
                }
                reader.readAsDataURL(fileInput.files[0]);
            }
        }
    </script>
</body>
</html>
