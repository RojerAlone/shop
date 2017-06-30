<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/login.css" rel="stylesheet">
    <script src="/js/login.js" type="text/javascript" charset="utf-8"></script>
    <title>登陆</title>
</head>
<body>
<%@ include file="common/top.jsp" %>
<div class="container">
    <div class="row">
        <div class="row bjys ztys dlkwz">
            <div class="col-xs-4">
                <h1 class="btys">登录</h1>
                <p>到现有的WePlay账户</p> <br>
                <p>用户名</p>
                <input type="text" class="form-control" placeholder="用户名" id="name">
                <h5>密码</h5>
                <input type="password" class="form-control" placeholder="密码" id="password">
                <input type="checkbox" id="remember">记住我一周
                <br/><br/>
                <button type="submit" class="btnys" onclick="login()"><p class="btys">登录</p></button>
                <br/><br/>
                <a href="/user/findpassword">忘记密码？</a>
            </div>
            <div class="col-xs-4">
                <h1 class="btys">创建</h1>
                <h5>一个免费的Weplay账户</h5>
                <h5>欢迎免费加入及轻松使用。继续创建Weplay 帐户<br/>
                    并获取 Weplay - 适合 PC 和 Mac 玩家的前沿数字解决<br/>
                    方案。</h5>
                <a href="/register">
                    <button type="submit" class="btnys"><p class="btys">加入Weplay</p></button>
                </a>
            </div>
            <div class="col-xs-4">
                <h3 class="btys">为什么加入Weplay？</h3>
                <ul type="disc">
                    <li>购买和下载完整零售游戏</li>
                    <li>加入Weplay社区</li>
                    <li>游戏时与好友聊天</li>
                    <li>在任何电脑上都能玩</li>
                    <li>安排游戏、比赛或 LAN 聚会</li>
                    <li>获取自动游戏更新以及更多！</li>
                    <img class="imgdx" src="/img/login.png"/>
                </ul>
            </div>
        </div>
    </div>
</div>

<%@ include file="common/bottom.jsp" %>
</body>
</html>
