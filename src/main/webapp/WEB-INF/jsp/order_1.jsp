<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<%@ include file="common/head.jsp" %>
	<link rel="stylesheet" href="/css/order.css" />
<title>选择支付方式</title>
</head>

<body >
<div class="container">
<br />
<br />
<div class="row">
 	<div class="col-xs-3">
 	<h1 style="color:#FFE">收银台</h1>
 	</div>
 	<div class="col-xs-3">
 	</div>
 	<div class="col-xs-6">
 	<a href="order_1.html"><img src="./img/static/order_1_1.png" align="middle" height="60" width="120" /></a>
 	<a href="order_2.html"><img src="./img/static/order_2.png" align="middle" height="60" width="120" /></a>
    </div>
</div>
<hr size="5" color="#000000" width="100%"></hr>
<div class="row" style="background-color:#000">
	<div class="col-xs-3">
    <img src="./images/logo.jpg" height="60" width="60" />
    </div>
    <div class="col-xs-9" style="color:#FFF;">
    <h3 align="center">请选择支付方式</h3>
    </div>
</div>
<br />
<br />
<div class="row">
<div class="col-xs-3">
<input type="radio" name="payway" value="zhifubao" class="radio"/><img src="./img/static/zhifubao.png" height="50" width="100" align="middle" />
</div>
<div class="col-xs-3">
<input type="radio" name="payway" value="weixin" class="radio" /><img src="./img/static/weixin.png" height="50" width="100" align="middle" />
</div>
<div class="col-xs-3">
<input type="radio" name="payway" value="zhonghang" class="radio" /><img src="./img/static/yinhang1.png" height="50" width="100" align="middle" />
</div>
<div class="col-xs-3">
<input type="radio" name="payway" value="nonghang" class="radio" /><img src="./img/static/yinhang2.png" height="50" width="100" align="middle" />
</div>
</div>
<br />
<div class="row">
<div class="col-xs-3">
<input type="radio" name="payway" value="jiaohang" class="radio" /><img src="./img/static/yinhang3.png" height="50" width="100" align="middle" />
</div>
<div class="col-xs-3">
<input type="radio" name="payway" value="jianhang" class="radio" /><img src="./img/static/yinhang4.png" height="50" width="100" align="middle" />
</div>
<div class="col-xs-3">
<input type="radio" name="payway" value="gonghang" class="radio" /><img src="./img/static/yinhang5.png" height="50" width="100" align="middle" />
</div>
</div>
<br />
<br />
<br />
<div class="row">
<div class="col-xs-10" style="color:#FFF;">
<h4 align="left">请确保你的银行卡已经开通网银支付功能，否则可能无法支付。</h4>
</div>
<div class="col-xs-2">
<a href="order_2.jsp"><button type="submit">前去支付</button></a>
</div>
</div>
</body>

<style>
.radio{
    display:inline-block
}
</style>
</html>
