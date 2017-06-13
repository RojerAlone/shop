function login() {
    var name = document.getElementById("name").value;
    var password = document.getElementById("password").value;
    $.post("/login", {username: name, password: password},
        function (result) {
            if (result.success) {
                if (!window.localStorage) {
                    alert("请允许浏览器本地存储，否则可能无法使用登陆功能")
                }
                // 登陆成功获取cookie并添加到localstorage
                console.log(result.data.token)
                window.localStorage.token = result.data.token
                // window.localStorage.setItem("token", result.data.token)
                // 跳转到登陆之前的页面
                window.location.href = result.data.referer;
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