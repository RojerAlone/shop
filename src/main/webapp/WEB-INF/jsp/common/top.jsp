<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid headheight">
        <div class="col-md-offset-3">
            <div class="navbar-header">
                <a href="/">
                    <img alt="Brand" src="/img/logo.jpg" width="90" height="60">
                </a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/">商城</a>
                    </li>
                    <li class="navbar-form">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search" id="searchbox">
                        </div>
                        <button type="submit" class="btn btn-default" onclick="search()"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${user == null}">
                            <li><a href="/login" methods="get">登录</a></li>
                            <li><a href="/register" methods="get">注册</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/user/personal">${user}，您好</a></li>
                            <li><a href="/shoppingcart">购物车</a></li>
                            <li><a onclick="outlogin()">退出</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</nav>
