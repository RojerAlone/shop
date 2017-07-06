<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/shoppingcart.css" rel="stylesheet">
    <script src="/js/shoppingcart.js"></script>
    <title>购物车</title>
</head>
<body>
<%@ include file="common/top.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-xs-1 col-sm-1 col-md-1"></div>
        <div class="col-xs-10 col-sm-10 col-md-10">
            <h1 style="color:#FFF;font-family: 'Adobe 黑体 Std R'"><span class="glyphicon glyphicon-shopping-cart"></span>您的购物车
            </h1>
            <br>
            <div id="grad1"></div>

            <table style="color: #fbfbfb" class="table">
                <thead>
                <tr>
                    <th width="20%">
                        序号
                    </th>
                    <th width="30%">
                        游戏名
                    </th>
                    <th width="25%">
                        单价
                    </th>

                    <th width="15%">
                        操作
                    </th>
                </tr>
                </thead>
                <tbody style="background-color:#1f486a" id="spcar">

                </tbody>
            </table>

            <h2 style="color:#CFF" align="right">总价格：￥<span id="prices"></span></h2>

            <div align="right" style=" border-top-width:10px;padding:20px">
                <button type="button" class="btn btn-default btn-lg active" style="background-color:#090"
                        onclick="orderadd()">确定购买
                </button>
            </div>
            <br>
            <br>
            <h3 style="color:#fff" align="left">交付</h3>
            <div style="background-color:#000;padding:10px 40px 10px 100px;">
                <h4 style="color:#FFF"><a href="#"><img src="/img/static/logo.jpg" width="61" height="50"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                    所有电子商品将会经有WEPLAY桌面应用程序递送给您</h4>
            </div>
            <br/>
            <br/>

            <div align="left" style="border:30px">
                <h4><a href="/" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-shopping-cart"></span>
                    继续购物</a></h4>
            </div>
        </div>
        <div class="col-xs-1 col-sm-1 col-md-1"></div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>
