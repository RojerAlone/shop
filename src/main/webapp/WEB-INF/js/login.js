function login() {
    var name = document.getElementById("name").value;
    var password = document.getElementById("password").value;
    var remember = document.getElementById("remember").checked;
    $.post("/login", {username: name, password: password, remember: remember},
        function (result) {
            if (result.success) {
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