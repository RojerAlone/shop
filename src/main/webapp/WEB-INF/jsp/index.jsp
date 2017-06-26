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
                                <div class="zhichi zhichi_1"><img src="./img/static/zhichi_win.png"></div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item_0"><a id="gameurl_1"><img class="imgwh" id="gameimg_1"></a></div>
                            <div class="item_1" id="gamename_1">
                                <div class="shoujia" id="gameprice_1"></div>
                                <div class="zhichi"><img src="./img/static/zhichi_win.png"></div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item_0"><a id="gameurl_2"><img class="imgwh" id="gameimg_2"></a></div>
                            <div class="item_1" id="gamename_2">
                                <div class="shoujia" id="gameprice_2"></div>
                                <div class="zhichi"><img src="./img/static/zhichi_win.png"></div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item_0"><a id="gameurl_3"><img class="imgwh" id="gameimg_3"></a></div>
                            <div class="item_1" id="gamename_3">
                                <div class="shoujia" id="gameprice_3"></div>
                                <div class="zhichi"><img src="./img/static/zhichi_win.png"></div>
                                <div class="zhichi zhichi_2"><img src="./img/static/zhichi_app.png"></div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item_0"><a id="gameurl_4"><img class="imgwh" id="gameimg_4"></a></div>
                            <div class="item_1" id="gamename_4">
                                <div class="shoujia shoujia_1" id="gameprice_4"></div>
                                <div class="zhichi zhichi_1"><img src="./img/static/zhichi_win.png"></div>
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
                <div class="tit tit_1"> 特 别 优 惠</div>
            </div>
            <div class="row">
                <div id="carousel_1" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carousel_1" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel_1" data-slide-to="1"></li>
                    </ol>
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <div class="item_1"><a href="#"><img src="./img/static/youhui_1.png"></a></div>
                            <div class="item_1"><a href="#"><img src="./img/static/youhui_2.png"></a></div>
                            <div class="item_1_1"><a href="#"><img src="./img/static/youhui_3.png"></a></div>
                            <div class="item_1_1"><a href="#"><img src="./img/static/youhui_4.png"></a></div>
                        </div>
                        <div class="item">
                            <div class="item_1"><a href="#"><img src="./img/static/youhui_5.png"></a></div>
                            <div class="item_1_1"><a href="#"><img src="./img/static/youhui_6.png"></a></div>
                            <div class="item_1_1"><a href="#"><img src="./img/static/youhui_7.png"></a></div>
                            <div class="item_1_1"><a href="#"><img src="./img/static/youhui_8.png"></a></div>
                            <div class="item_1_1"><a href="#"><img src="./img/static/youhui_9.png"></a></div>
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
                <div class="tit tit_1"> 浏 览 W E P L A Y</div>
            </div>
            <div class="row">
                <div class="col-xs-3 col-sm-3 col-md-3 col-md-offset-1">
                    <a href="#">
                        <div class="liulan">
                            <div class="bs">新 品</div>
                        </div>
                    </a>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3">
                    <a href="#">
                        <div class="liulan">
                            <div class="bs">优 惠</div>
                        </div>
                    </a>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3">
                    <a href="#">
                        <div class="liulan">
                            <div class="bs">免 费 游 戏</div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="row zx">
                <div class="col-xs-8 col-sm-8 col-md-8">
                    <ul id="myTab" class="nav nav-tabs">
                        <li role="presentation"><a href="#home" data-toggle="tab">新品与热门商品</a></li>
                        <li role="presentation"><a href="#rexiao" data-toggle="tab">热销商品</a></li>
                        <li role="presentation"><a href="#jijiang" data-toggle="tab">即将推出</a></li>
                        <li role="presentation"><a href="#youhui" data-toggle="tab">优惠</a></li>
                    </ul>
                    <div id="myTabContent" class="tab-content" ;>
                        <div class="tab-pane fade in active" id="home">
                            <div class="row rowpy">
                                <a href="#">
                                    <img src="/img/static/zx_1.png" onmouseover="hoverShowDiv(1);">
                                </a>
                            </div>
                            <div class="row rowpy">
                                <a href="#">
                                    <img src="/img/static/zx_2.png" onmouseover="hoverShowDiv(2);">
                                </a>
                            </div>
                            <div class="row rowpy">
                                <a href="#">
                                    <img src="/img/static/zx_3.png" onmouseover="hoverShowDiv(3);">
                                </a>
                            </div>
                            <div class="row rowpy">
                                <a href="#">
                                    <img src="/img/static/zx_9.png" onmouseover="hoverShowDiv(9);">
                                </a>
                            </div>
                            <div class="row rowpy">
                                <a href="#">
                                    <img src="/img/static/zx_10.png" onmouseover="hoverShowDiv(10);">
                                </a>
                            </div>
                            <div class="row rowpy">
                                <a href="#">
                                    <img src="/img/static/zx_11.png" onmouseover="hoverShowDiv(11);">
                                </a>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="rexiao">
                            <div class="row rowpy"><a href="#"><img src="./img/static/zx_4.png"></a></div>
                            <div class="row rowpy"><a href="#"><img src="./img/static/zx_5.png"></a></div>
                            <div class="row rowpy"><a href="#"><img src="./img/static/zx_6.png"></a></div>
                        </div>
                        <div class="tab-pane fade" id="jijiang">
                            <div class="row rowpy"><a href="#"><img src="./img/static/zx_7.png"></a></div>
                            <div class="row rowpy"><a href="#"><img src="./img/static/zx_8.png"></a></div>
                            <div class="row rowpy"><a href="#"><img src="./img/static/zx_3.png"></a></div>
                        </div>
                        <div class="tab-pane fade" id="youhui">
                            <div class="row rowpy"><a href="#"><img src="./img/static/zx_3.png"></a></div>
                            <div class="row rowpy"><a href="#"><img src="./img/static/zx_7.png"></a></div>
                            <div class="row rowpy"><a href="#"><img src="./img/static/zx_1.png"></a></div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-2 col-sm-2 col-md-2">
                    <img src="img/static/zx_1_1.png" id="divHover"></div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>

</body>

</html>