<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="common/head.jsp" %>
    <link rel="stylesheet" href="/css/order.css"/>
    <title>支付结果信息</title>
    <script type="text/javascript">
        function show_confirm() {
            var r = confirm("是否确认付款！");
            if (r == true) {
                $.post("/order/${id}/pay", {orderid: ${id}}, function (result) {
                    if (result.success) {
                        window.location = "/order/orders";
                    } else {
                        alert(result.msg);
                    }
                })
            }
            else {
                window.location = "/order/orders";
            }
        }
    </script>
</head>

<body>
<%@ include file="common/top.jsp" %>
<br/>
<br/>
<div class="container">

    <div style=" padding:15px; height:60px;background-color:#7A8D96"><span style="background-color:#84CF30"
                                                                           class="glyphicon glyphicon-ok btn btn-info"></span>&nbsp;&nbsp;
        <h4 style=" font-family:'黑体'; font-weight:bold; display:inline">您已选择购买的商品，请尽快支付！ </h4>
    </div>
    <br/>
    <hr size="5" color="#000000" width="100%"></hr>
    <br/>
    <br/>

    <div class="row">
        <div class="col-xs-4">
            <div class="row mar" align="center">
                <input type="button" onclick="show_confirm()"
                       class="btn btn-default btn-lg" style="width:80px; height:40px;" value="支付"/>
            </div>
        </div>
        <div class="col-xs-2"></div>
        <div class="col-xs-6">
            <table class="tishi" align="center">
                <tr><h3 style="color:#FFF">在WePlay上购物</h3></tr>
                <tr><h4 style="color:#FFF">当您提交您的支付信息时，您的资料将得到具有安全套接层（SSL）技术认证的数字证书的保护。
                    当您完成此笔交易后，我们将向您发送一封电子邮件来确认购物收据。</h4></tr>
                <tr><h3 style="color:#FFF">客户提示</h3></tr>
                <tr><h4 style="color:#FFF">该过程可能会长达 60 秒。为了避免购买失败，在交易结束前请不要点击后退按钮或关闭此窗口。</h4></tr>
            </table>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>
