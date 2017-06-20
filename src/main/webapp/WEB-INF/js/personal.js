$(
    function(){
        var storage=window.localStorage;
        var username = storage.user;
        document.getElementById("uname").innerHTML = "<h3>"+username + "    >>  个人资料</h3>";
        $.post("/user/personal",function (result) {
            document.getElementById("userinfo_0").innerText = "用户名:   " + result.data.username;
            document.getElementById("userinfo_1").innerText = "昵称:   " + result.data.nickname;
            document.getElementById("userinfo_2").innerText = "邮箱:   " + result.data.email;
            document.getElementById("userinfo_3").innerText = "电话:   " + result.data.phone;
        })
    }
)