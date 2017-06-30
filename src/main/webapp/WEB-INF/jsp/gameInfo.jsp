<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/sanji.css" rel="stylesheet" type="text/css">
    <script src="/js/biaoqian.js"></script>
    <script src="/js/sanji.js"></script>
    <title>游戏页</title>
</head>

<body>
<%@ include file="common/top.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-xs-2 col-sm-2 col-md-2">
            <div class="gutter_header">
                <ul class="list-group" id="leixing"></ul>
            </div>
        </div>
        <div class="col-xs-10 col-sm-10 col-md-10">
            <!--游戏详情页-->
            <div calss="row">
                <!--游戏名-->
                <div class="row">
                    <div class="col-md-4">
                        <h3 style="color:white" id="gamename"></h3>
                    </div>
                </div>
                <!--游戏视频图片以及文字介绍-->
                <div class="row">
                    <div class="col-md-8">
                        <div class="row">
                            <div id="myCarousel" class="carousel slide">
                                <!-- 轮播（Carousel）指标 -->
                                <ol class="carousel-indicators" id="carousel_1">

                                </ol>
                                <!-- 轮播（Carousel）项目 -->
                                <div class="carousel-inner" id="carousel_2">

                                </div>
                                <!-- 轮播（Carousel）导航 -->
                            </div>

                        </div>
                        <div class="row addmargin">
                            <div class="col-md-1 btn_1" onClick="move('left')">
                                <span class="glyphicon glyphicon-chevron-left"></span>
                            </div>
                            <div class="col-md-10 deletelrpadding" id="carousel_3">
                                <ul id="carousel_4"></ul>
                            </div>
                            <div class="col-md-1 btn_1" onClick="move('right')">
                                <span class="glyphicon glyphicon-chevron-right"></span>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-4" id="getheader">

                    </div>
                </div>

                <!--购物车及游戏支持语言-->
                <div class="row">
                    <div class="col-md-12" style="margin-top:50px;padding-left:0px;padding-right:9px">
                        <div class="row"
                             style=" background-color:#356F95 ; color:#FFF ; margin-left:0px; margin-right:40px">
                            <div class="col-md-6" style="padding-left:30px" id="shoppingcar">

                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <p class="text-right" style="padding-top:30px;padding-right:30px"
                                       id="shopingcartbtn"><a href="#" class="btn btn-info btn-lg"
                                                              onclick="addshopingcar()"> <span
                                            class="glyphicon glyphicon-shopping-cart"></span>添加至购物车 </a></p>
                                </div>
                            </div>
                        </div>
                        <div class="row"> &nbsp;</div>

                    </div>

                </div>

                <!--最新更行以及配置需求-->
                <div class="row">
                    <div class="col-md-4">
                        <h4 style="color:white">游戏配置</h4>

                        <table class="table table-hover">
                            <h4 style="color:#4582A5">最低配置</h4>
                            <tbody id="systemcfg">

                            </tbody>
                        </table>


                    </div>
                    <div class="col-md-4 col-md-offset-2" style=" padding-left:0px; padding-right:0px">
                        <table class="table table-hover">
                            <caption style="color:white">
                                已支持的语言
                            </caption>
                            <thead>
                            <tr>
                                <th></th>
                                <th style="color:white">界面</th>
                                <th style="color:white">完全音频</th>
                                <th style="color:white">字幕</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td align="center" style="color:#acb2b8">简体中文</td>
                                <td align="center" style="color:gray"></td>
                                <td align="center" style="color:gray">不支持</td>
                                <td align="center" style="color:gray"></td>
                            </tr>
                            <tr>
                                <td align="center" style="color:#acb2b8">英语</td>
                                <td align="center" style="color:#66C0F4">√</td>
                                <td align="center" style="color:#66C0F4">√</td>
                                <td align="center" style="color:#66C0F4">√</td>
                            </tr>
                            <tr>
                                <td align="center" style="color:#acb2b8">法语</td>
                                <td align="center" style="color:#66C0F4">√</td>
                                <td align="center" style="color:#66C0F4"></td>
                                <td align="center" style="color:#66C0F4">√</td>
                            </tr>
                            <tr>
                                <td align="center" style="color:#acb2b8">意大利语</td>
                                <td align="center" style="color:#66C0F4">√</td>
                                <td align="center" style="color:#66C0F4"></td>
                                <td align="center" style="color:#66C0F4">√</td>
                            </tr>
                            <tr>
                                <td align="center" style="color:#acb2b8">德语</td>
                                <td align="center" style="color:#66C0F4">√</td>
                                <td align="center" style="color:#66C0F4"></td>
                                <td align="center" style="color:#66C0F4">√</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>