function login() {
    var name = document.getElementById("name").value;
    var password = document.getElementById("password").value;
    $.post("/login", {username: name, password: password},
        function (result) {
            if (result.success) {
                // 登陆成功获取cookie并添加到localstorage
                window.localStorage.token = result.data.token
                // 跳转到登陆之前的页面
                window.location.href = result.data.link;
            }
            else {
                if (result.msg == "OK") {
                    window.location.href = result.data;
                } else {
                    alert(result.msg);
                }
            }
        })
}