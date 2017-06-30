var wait;
$(function () {
    wait = 0;
    time();
})

function schedule() {
    setTimeout(function () {
        time()
    }, 1000)
}

function time() {
    if (wait == 0) {
        document.getElementById("btna").removeAttribute("disabled");
        document.getElementById("btna").value = "发送邮件";
        wait = 60;
    } else {
        document.getElementById("btna").setAttribute("disabled", true);
        document.getElementById("btna").value = wait + "s后可以重新发送";
        wait--;
        schedule();
    }
}

function sendMail() {
    var email = document.getElementById("email").value;
    $.post("/user/sendfetchpwdmail", {email: email}, function (result) {
        if (result.success) {
            alert("发送成功！");
            time()
        } else {
            alert(result.msg);
        }
    });
}

function findpassword() {
    var code = document.getElementById("code").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("newpassword").value;
    var password_1 = document.getElementById("newpassword_1").value;
    if (password != password_1) {
        alert("您输入的密码不一致！");
    }
    else {
        $.post("/user/findpassword", {password: password, email: email, code: code}, function (result) {
            if (result.success) {
                alert("成功找回！您的用户名是:" + result.data);
                window.location.href = "/login";
            }
            else {
                alert(result.msg);
            }
        })
    }
}
