<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传测试</title>
</head>
<body>

<form action="/admin/test" method="post" enctype="multipart/form-data">
    <input type="file" name="img">
    <input type="submit">
</form>

</body>
</html>
