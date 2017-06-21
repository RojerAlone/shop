function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    $.post("/admin/login", {username: username, password: password}, function (result) {
        if (result.success) {
            window.location.href = "/admin/home"
        } else {
            alert(result.msg);
        }
    })
}