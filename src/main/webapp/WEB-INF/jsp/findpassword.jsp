<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/login.css" rel="stylesheet">
    <link href="/css/regist.css" rel="stylesheet">
    <script src="/js/findpassword.js" type="text/javascript" charset="utf-8"></script>
    <title>邮箱验证</title>
</head>
<body>
<%@ include file="common/top.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <div class="gutter_header">
                <ul class="list-group" id="leixing">

                </ul>
            </div>
        </div>
        <div class="col-xs-10 col-sm-10 col-md-10">

            <div class="row zczt"><h1>找回密码</h1></div>
            <div class="row zczt">
                <div class="col-xs-offset-1 col-xs-5" id="regist_2">
                    <div class="row zczt">
                        输入您的邮箱：<br/>
                        <input class="inputcolor" id="email"><input id="btna" type="button" class="btnsz" value="发送邮件"
                                                                    style="background-color:#417a9b;"
                                                                    onclick="sendMail()"><br>
                        输入您的验证码：<br/>
                        <input class="inputcolor" type="text" id="code"><br>
                        输入您的新密码：<br/>
                        <input class="inputcolor" type="password" id="newpassword"><br>
                        再次输入您的新密码：<br/>
                        <input class="inputcolor" type="password" id="newpassword_1"><br>
                    </div>
                    <div class="form-group">
                        <div class=" col-sm-10">
                            <button type="submit" class="btn btn-default" style="background-color:#417a9b;"
                                    onclick="findpassword()">确定
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <h3>
                        为什么加入我们？
                    </h3>
                    <ul>
                        <li>
                            购买和下载完整零售游戏
                        </li>
                        <li>
                            游戏时与好友聊天
                        </li>
                        <li>
                            在任何电脑上都能玩
                        </li>
                        <li>
                            安排游戏、比赛或 LAN 聚会
                        </li>
                        <li>
                            获取自动游戏更新以及更多！
                        </li>
                    </ul>
                    <img src="/img/static/login_1.png"/>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>
