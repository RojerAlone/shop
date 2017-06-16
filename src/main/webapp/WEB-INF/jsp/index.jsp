<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
</head>
<body>
<h2>Hello World!</h2>
<p id="test"></p>
<p>${str}</p>
</body>
<script src="https://cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>
    <script>
        $(function () {
            document.getElementById("test").innerText = "${str}";
            alert("${str}");
            console.log(${str});
        })
    </script>
</html>
