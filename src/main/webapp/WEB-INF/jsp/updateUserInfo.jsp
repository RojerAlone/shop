<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/personal.css" rel="stylesheet">
    <script src="/js/updateUserInfo.js"></script>
    <title>个人中心</title>
</head>

<body>
<%@ include file="common/top.jsp" %>
<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <div class="row divcolor setmargin_2"><h3>>>修改个人资料</h3></div>
        <div class="row">
            <!--左侧资料列-->
            <div class="col-md-7 col-md-offset-1 ziti">
                <div class="row setmargin">用户名</div>
                <div class="row setmargin">昵称<input class="setmargin_1 setpadding" type="text" id="userInfo_0"></div>
                <div class="row setmargin">邮箱<input class="setmargin_1 setpadding" type="text" id="userInfo_1"></div>
                <div class="row setmargin">电话<input class="setmargin_1 setpadding" type="text" id="userInfo_2"></div>
                <div class="row setmargin ">
                    <button class="setbtnpadding" onclick="updateUserInfo()">确定</button>
                </div>
            </div>
            <!--右侧选项列-->
            <div class="col-md-4 setmargin divcolor_1">
                <div class="row ziti">编辑</div>
                <div class="row ziti_1 setpadding setmargin"><a href="/user/personal">我的个人资料</a></div>
                <div class="row ziti_1 setmargin"><a href="/order/orders">我的订单</a></div>
            </div>
        </div>

    </div>
</div>

<%@ include file="common/bottom.jsp" %>
</body>

</html>