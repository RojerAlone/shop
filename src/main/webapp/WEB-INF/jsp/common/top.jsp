<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid headheight">
        <div class="col-md-offset-4">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <img alt="Brand" src="/img/logo.jpg" width="90" height="50">
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/">商城<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right" id = "nologin">
                    <li><a href="/login">登录</a></li>
                    <li><a href="/register">注册</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right yingcang" id = "allogin">
                    <li><a href="/user/personal" id = "username"></a></li>
                    <li><a href="/shoppingcart">购物车</a></li>
                    <li><a onclick="outlogin()">退出登录</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
