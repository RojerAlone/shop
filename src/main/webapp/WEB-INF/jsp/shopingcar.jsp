<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="common/head.jsp" %>
<link href="/css/shopingcar.css" rel="stylesheet">
<script src="/js/shopingcar.js"></script>
<title>购物车</title>
</head>
<body>
<%@ include file="common/top.jsp" %>
  <!--左侧导航栏-->
  <div class="container">
    <div class="row">
      <div class="col-xs-2 col-sm-2 col-md-2">
        
        <!-- /.gutter_header -->
      </div>
      <!-- /.col-xs-2 col-sm-2 col-md-2 -->
      <!-- 广告 -->
      <div class="col-xs-10 col-sm-10 col-md-10">
      <h1 style="color:#FFF"><span class="glyphicon glyphicon-shopping-cart"></span>您的购物车</h1>
            <div id="grad1">
            </div>
           
<table style="color:#FFF" class="table">
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
                        
                        <th width="25%">
                            操作
                        </th>
                    </tr>
                </thead>
                <tbody style=" background-color:#066" id = "spcar">
                
                </tbody>
            </table>
            
            <h2 style="color:#CFF" align="right">总价格：￥<span id="prices"></span></h2>
            
            <div align="right" style=" border-top-width:10px;padding:20px">
            <button type="button" class="btn btn-default btn-lg active" style="background-color:#090">确定购买</button>
            </div>

           
            <br>
            <br>
            <h3 style="color:#fff" align="left">交付</h3>
            <div style="background-color:#000;padding:10px 40px 10px 100px;">
          <h4 style="color:#FFF"><a href="#"><img src="./img/static/logo.jpg" width="61" height="50"></a>&nbsp;&nbsp;&nbsp;&nbsp; 所有电子商品将会经有WEPLAY桌面应用程序递送给您</h4>
          </div>
          <br/>
          <br/>
          
          <div align="left" style="border:30px">
          <h4><a href="#" class="btn btn-info btn-lg">
          <span class="glyphicon glyphicon-shopping-cart"></span>
            继续购物</a></h4>
      </div>
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
