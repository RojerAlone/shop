<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/base.css" rel="stylesheet">
    <link href="/css/login.css" rel="stylesheet">
    <link href="/css/regist.css" rel="stylesheet">
    <title>用户验证</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid headheight">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="col-md-offset-4">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false"><span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                </button>
                <img alt="Brand" src="/img/logo.jpg" width="90" height="50"></div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="./index.html">商城 <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">客服</a></li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                            aria-haspopup="true" aria-expanded="false">游戏 <span
                            class="caret"></span></a>
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
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="login.html">登录</a></li>
                    <li><a href="regist.html">注册</a></li>
                </ul>
            </div>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<!--左侧导航栏-->
<div class="container">
    <div class="row">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <div class="gutter_header">
                <ul class="list-group">
                    <li>浏览分类</li>
                    <a href="#">
                        <li class="list-group-item">
                            <div class="bs">热销商品</div>
                        </li>
                    </a> <a href="#">
                    <li class="list-group-item">
                        <div class="bs">最近更新</div>
                    </li>
                </a> <a href="#">
                    <li class="list-group-item">
                        <div class="bs">新品</div>
                    </li>
                </a> <a href="#">
                    <li class="list-group-item">
                        <div class="bs">即将推出</div>
                    </li>
                </a> <a href="#">
                    <li class="list-group-item">
                        <div class="bs">优惠</div>
                    </li>
                </a>
                    <li>按类型浏览</li>
                    <a href="#">
                        <li class="list-group-item">
                            <div class="bs">免费</div>
                        </li>
                    </a> <a href="#">
                    <li class="list-group-item">
                        <div class="bs">休闲</div>
                    </li>
                </a> <a href="#">
                    <li class="list-group-item">
                        <div class="bs">体育</div>
                    </li>
                </a> <a href="#">
                    <li class="list-group-item">
                        <div class="bs">冒险</div>
                    </li>
                </a> <a href="#">
                    <li class="list-group-item">
                        <div class="bs">动作</div>
                    </li>
                </a> <a href="#">
                    <li class="list-group-item">
                        <div class="bs">竞速</div>
                    </li>
                </a> <a href="#">
                    <li class="list-group-item">
                        <div class="bs">策略</div>
                    </li>
                </a> <a href="#">
                    <li class="list-group-item">
                        <div class="bs">角色扮演</div>
                    </li>
                </a>
                </ul>
            </div>
            <!-- /.gutter_header -->
        </div>
        <!-- /.col-xs-2 col-sm-2 col-md-2 -->
        <!-- 广告 -->
        <div class="col-xs-10 col-sm-10 col-md-10">
            <div class="row">
                <nav class="navbar navbar-default daohangyanse">
                    <div class="container-fluid">
                        <!-- Collect the nav links, forms, and other content for toggling -->
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
                        <!-- /.navbar-collapse -->
                    </div>
                    <!-- /.container-fluid -->
                </nav>
            </div><!--在这后面加入自己的代码-->
            <div class="row zczt"><h1>创建账户</h1></div>
            <div class="row zczt">
                <div class="col-xs-offset-1 col-xs-5" id="regist_2">
                    <div class="row zczt">
                        输入您的验证码：<br/>
                        <input class="inputcolor" type="text" id="code">
                        <input id="send" type="button" class="btnsz" value="重新获取验证码"
                               style="background-color:#417a9b;" onclick="sendMail()">
                    </div>
                    <div class="form-group">
                        <div class=" col-sm-10">
                            <button type="submit" class="btn btn-default" style="background-color:#417a9b;"
                                    onclick="zhuce()">确定
                            </button>
                        </div>
                    </div>
                </div>
                <div class="col-xs-offset-1 col-xs-5" id="regist_3">
                    <div class="row zczt">
                        恭喜您注册成功，<a href="/">点此回到首页</a>
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
                    <img src="/img/login.png"/>
                </div>
            </div>
        </div>
    </div>
</div>
<!--col-10-->
</div>
<!--row-->
</div>
<!--continer-->
<div class="row dibu"> ©2017 Weplay Corporation。保留所有权利。所有商标均为其在美国及其它国家/地区的各自持有者所有。
    所有的价格均已包含增值税（如适用）。<a href="#">隐私政策 | <a href="#">法律信息</a> | <a href="#">Weplay用户协议 | </a> <a href="#">退款</a></div>
<%--jquery要在bootstrap之前引入--%>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<script src="/js/regist.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
