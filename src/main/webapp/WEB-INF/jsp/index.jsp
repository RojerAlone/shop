<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/self.css" rel="stylesheet">
    <script src="/js/biaoqian.js"></script>
    <script src="/js/index.js"></script>
    <title>We Play</title>
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
            <div class="row">
                <div class="tit tit_0"> 精 选 和 推 荐</div>
            </div>
            <div class="row">
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                    </ol>
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <div class="item_0"><a id="gameurl_0"><img class="imgwh" id="gameimg_0"></a></div>
                            <div class="item_1" id="gamename_0">
                                <div class="shoujia shoujia_1" id="gameprice_0"></div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item_0"><a id="gameurl_1"><img class="imgwh" id="gameimg_1"></a></div>
                            <div class="item_1" id="gamename_1">
                                <div class="shoujia" id="gameprice_1"></div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item_0"><a id="gameurl_2"><img class="imgwh" id="gameimg_2"></a></div>
                            <div class="item_1" id="gamename_2">
                                <div class="shoujia" id="gameprice_2"></div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item_0"><a id="gameurl_3"><img class="imgwh" id="gameimg_3"></a></div>
                            <div class="item_1" id="gamename_3">
                                <div class="shoujia" id="gameprice_3"></div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item_0"><a id="gameurl_4"><img class="imgwh" id="gameimg_4"></a></div>
                            <div class="item_1" id="gamename_4">
                                <div class="shoujia shoujia_1" id="gameprice_4"></div>
                            </div>
                        </div>
                    </div>
                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button"
                       data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="tit tit_1"> 免 费 游 戏</div>
            </div>
            <div class="row">
                <div id="carousel_1" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carousel_1" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel_1" data-slide-to="1"></li>
                    </ol>
                    <div class="carousel-inner" role="listbox">
                        <div class="item active" id="freegames_0">

                        </div>
                        <div class="item" id="freegames_1">

                        </div>
                    </div>
                    <a class="left carousel-control" href="#carousel_1" role="button" data-slide="prev"> <span
                            class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel_1" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="tit tit_1"> 休 闲 一 下</div>
            </div>

            <div class="all_bg">
                <div id="allcanvas">
                    <img src="img/fish/play.png" id="playbtn" onclick="game()">
                    <canvas id="canvas1" width="800" height="600"></canvas>
                    <canvas id="canvas2" width="800" height="600"></canvas>
                </div>
            </div>


            <div class="row zx">
                <div class="col-md-8">
                    <ul id="myTab" class="nav nav-tabs">
                        <li role="presentation"><a href="#home" data-toggle="tab">新品与热门商品</a></li>
                        <li role="presentation"><a href="#jijiang" data-toggle="tab">即将推出</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content" ;>

                    </div>
                </div>
                <div class="col-md-4 spadding">
                    <div id="divHover"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
<%@ include file="fishjs.jsp" %>
</body>
</html>