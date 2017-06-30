<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
    <link href="/css/fenleixiangqing.css" rel="stylesheet">
    <script src="/js/biaoqian.js"></script>
    <script src="/js/fenlei.js"></script>
    <title>分类</title>
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
            <div class="row">
                <div class="row clx">正在浏览${name}标签的游戏</div>
                <div class="row">
                    <div class="col-md-8 deletepadding">
                        <ul id="fenleixiangqing">
                        </ul>
                    </div>
                    <div class="col-md-4 tupianfuji">
                        <ul id="fenleiyouxitupian">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/bottom.jsp" %>
</body>
</html>