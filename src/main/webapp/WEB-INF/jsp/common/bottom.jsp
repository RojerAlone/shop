<%--
  User: RojerAlone
  Date: 2017/5/8
  Time: 21:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="footer" style="margin-top: 80px;">
    <div class="row dibu"> ©2017 Weplay Corporation。保留所有权利。所有商标均为其在美国及其它国家/地区的各自持有者所有。
        所有的价格均已包含增值税（如适用）。<a href="#">隐私政策</a> | <a href="#">法律信息</a> | <a href="#">Weplay用户协议 | </a> <a href="#">退款</a>
    </div>
</footer>

<div class="scroll-bar pull-right">
    <%--http://www.w3cdream.com/content-sort-7-article-206.html  返回顶部插件--%>
    <a id="scrollUp" href="#top" class="back-to-top"></a>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<script>
    $(window).bind('scroll', function () {
        var len = $(this).scrollTop();
        if (len <= 100) {
            $("#scrollUp").hide();
        } else {
            $("#scrollUp").show();
        }
    });

    $('body').css({
        "overflow-x":"hidden"
    });
</script>