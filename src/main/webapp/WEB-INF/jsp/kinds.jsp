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
<!--左侧导航栏-->
<div class="container">
  <div class="row">
    <div class="col-xs-2 col-sm-2 col-md-2">
      <div class="gutter_header">
        <ul class="list-group" id="leixing"> 
          
        </ul>
      </div>
      <!-- /.gutter_header -->
    </div>
    <!-- /.col-xs-2 col-sm-2 col-md-2 -->
    <!-- 广告 -->
    <div class="col-xs-10 col-sm-10 col-md-10">
      <div class="row">
          <div class="row clx">正在浏览${name}类型的游戏</div>
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
    <!--col-10-->
  </div>
  <!--row-->
</div>
<!--continer-->
<div class="row dibu"> ©2017 Weplay Corporation。保留所有权利。所有商标均为其在美国及其它国家/地区的各自持有者所有。
  所有的价格均已包含增值税（如适用）。<a href="#">隐私政策 | <a href="#">法律信息</a> | <a href="#">Weplay用户协议 | </a> <a href="#">退款</a></div>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->

</body>
</html>