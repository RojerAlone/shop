<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/shoppingcart.css" rel="stylesheet">
    <link href="/css/order.css" rel="stylesheet">
    <script src="/js/order.js"></script>
    <title>我的订单</title>
</head>

<body>
<%@ include file="common/top.jsp" %>
<!--左侧导航栏-->
<div class="container">
    <div class="row">

        <!-- /.col-xs-2 col-sm-2 col-md-2 -->
        <!-- 广告 -->

        <h1 style="color:#FFF"><span class="glyphicon glyphicon-shopping-cart"></span>我的订单</h1>
        <div id="grad1">
        </div>

        <!--<table style="color:#FFF" class="table">
            <thead>
                <tr>
                    <th width="33%">
                        序号
                    </th>

                    <th width="33%">
                        价格
                    </th>

                    <th width="33%">
                        状态
                    </th>
                </tr>
            </thead>
            <tbody style=" background-color:#066" id="spcar">

            </tbody>
        </table>-->
        <div class="row zx">
            <div class="col-xs-8 col-sm-8 col-md-8">
                <ul id="myTab" class="nav nav-tabs">
                    <li role="presentation"><a href="#already" data-toggle="tab">已支付</a></li>
                    <li role="presentation"><a href="#yet" data-toggle="tab">未支付</a></li>
                    <li role="presentation"><a href="#cancel" data-toggle="tab">已取消</a></li>
                </ul>
                <div id="orderTab" class="tab-content" ;>
                    <div class="tab-pane fade in active" id="already">
                    </div>
                    <div class="tab-pane fade" id="yet">
                    </div>
                    <div class="tab-pane fade" id="cancel">
                    </div>

                </div>

            </div>

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
    <!--col-10-->
</div>
<!--row-->
</div>
<!--continer-->
<%@ include file="common/bottom.jsp" %>
</body>

</html>