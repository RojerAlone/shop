<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/login.css" rel="stylesheet">
    <link href="/css/regist.css" rel="stylesheet">
    <script src="/js/regist.js" type="text/javascript" charset="utf-8"></script>
    <title>用户注册</title>
</head>
<body>
<%@ include file="common/top.jsp" %>
<!--左侧导航栏-->
<div class="container">
    <div class="row">
        <div class="col-xs-2 col-sm-2 col-md-2"></div>
        <div class="col-xs-10 col-sm-10 col-md-10">
            <div class="row zczt"><h1>创建账户</h1></div>
            <div class="row zczt">
                <div class="col-xs-offset-1 col-xs-5" id="regist_1">
                    <div class="row zczt">
                        创建 WePlay 用户名称：<br/>
                        <input class="inputcolor" type="text" id="yonghuming"><br/>
                    </div>
                    <div class="row zczt">
                        请输入昵称：<br/>
                        <input class="inputcolor" type="text" id="nichen"><br/>
                    </div>
                    <div class="row zczt">
                        请输入密码：<br/>
                        <input class="inputcolor" type="password" id="password"><br/>
                    </div>
                    <div class="row zczt">
                        请输入邮件地址：<br/>
                        <input class="inputcolor" type="text" id="email"><br/>
                        WePlay 将向此帐户发送一封用于确认的电子邮件。请通过邮件中的验证码验证您的 WePlay 电子邮件帐户。
                    </div>
                    <div class="row zczt">
                        请输入您的手机号:<br/>
                        <input class="inputcolor" type="text" id="phone"><br/>
                    </div>
                    <div class="form-group" style="float:left;clear:both;">
                        <div class=" col-sm-10">
                            <button type="submit" class="btn btn-default" style="background-color:#417a9b;"
                                    onclick="regist()">下一步
                            </button>
                        </div>
                    </div>
                </div>

                <div class="col-xs-6">
                    <h3>为什么加入我们？</h3>
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
                    <img src="/img/login.png"/>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>
