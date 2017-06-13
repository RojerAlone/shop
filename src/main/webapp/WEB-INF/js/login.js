function login() {
    var name = document.getElementById("name").value;
    var password = document.getElementById("password").value;
    $.post("/login", {username: name, password: password},
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