<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理登陆</title>
    <link rel="icon" href="/smile.ico" type="image/x-icon">
    <link href="/css/adminlogin.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
    <script src="/js/adminlogin.js"></script>
</head>

<body>
<div class="login">
    <h1>WePlay 后台管理</h1>
    <input id="username" type="text" placeholder="用户名" required="required"/>
    <input id="password" type="password" name="p" placeholder="密码" required="required"/>
    <button onclick="login()" class="btn btn-primary btn-block btn-large">登录</button>
</div>
</body>
</html>
