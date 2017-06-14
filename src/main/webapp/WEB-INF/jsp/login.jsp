<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8"/>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/base.css" rel="stylesheet">
    <link href="/css/login.css" rel="stylesheet">
    <script src="/js/login.js" type="text/javascript" charset="utf-8"></script>
    <title>登陆</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid headheight">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false"><span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></button>
            <img alt="Brand" src="/img/logo.jpg" width="90" height="50" class="logowz"></div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="./index.html" class="logoztdx">商城 <span class="sr-only">(current)</span></a>
                </li>
                <li><a href="#" class="logoztdx">客服</a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle logoztdx" data-toggle="dropdown" role="button"
                                        aria-haspopup="true" aria-expanded="false">游戏 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
        </div>

    </div>
</nav>
<!--左侧导航栏-->
<div class="container">
    <div class="row">

        <div class="col-md-12">
            <div class="row">
                <nav class="navbar navbar-default daohangyanse">
                    <div class="container-fluid">
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                                        role="button" aria-haspopup="true" aria-expanded="false">您的商店
                                    <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Action</a></li>
                                        <li><a href="#">Another action</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="#">Separated link</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="#">One more separated link</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                                        role="button" aria-haspopup="true" aria-expanded="false">游戏
                                    <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Action</a></li>
                                        <li><a href="#">Another action</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="#">Separated link</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="#">One more separated link</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                                        role="button" aria-haspopup="true" aria-expanded="false">软件
                                    <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Action</a></li>
                                        <li><a href="#">Another action</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="#">Separated link</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="#">One more separated link</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                                        role="button" aria-haspopup="true" aria-expanded="false">视频
                                    <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Action</a></li>
                                        <li><a href="#">Another action</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="#">Separated link</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="#">One more separated link</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-left">
                                <div class="form-group inputmargin">
                                    <input type="text" class="form-control" placeholder="Search">
                                </div>
                                <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"
                                                                                    aria-hidden="true"></span></button>
                            </form>
                        </div>
                    </div>
                </nav>
            </div><!--在这后面加入自己的代码-->
            <div class="row bjys ztys dlkwz">
                <div class="col-xs-4">
                    <h1 class="btys">登录</h1>
                    <p>到现有的Steam账户</p>
                    <p>weplay账户名称</p>
                    <input type="text" class="form-control" placeholder="Account" id="name">
                    <h5>密码</h5>
                    <input type="password" class="form-control" placeholder="Password" id="password">
                    <input type="checkbox" id="remember">记住我一周
                    <br  /><br  />

                    <%--<h5>密码</h5>--%>
                    <%--<input type="password" class="form-control" placeholder="Password" id="password">--%>
                    <%--<br/>--%>
                    <button type="submit" class="btnys" onclick="login()"><p class="btys">登录</p></button>
                    <br/>
                    <br/>
                    <a href="Forget.html">忘记密码？</a></div>
                <div class="col-xs-4">
                    <h1 class="btys">创建</h1>
                    <h5>一个免费的Weplay账户</h5>
                    <h5>欢迎免费加入及轻松使用。继续创建Weplay 帐户<br/>
                        并获取 Weplay - 适合 PC 和 Mac 玩家的前沿数字解决<br/>
                        方案。</h5>
                    <a href="regist.html">
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
</div>

<div class="row dibu"> &copy;2017 Weplay Corporation。保留所有权利。所有商标均为其在美国及其它国家/地区的各自持有者所有。
    所有的价格均已包含增值税（如适用）。<a href="#">隐私政策</a> | <a href="#">法律信息</a> | <a href="#">Weplay用户协议 | </a> <a href="#">退款</a>
</div>
<%--jquery要在bootstrap之前引入--%>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
</body>
</html>
