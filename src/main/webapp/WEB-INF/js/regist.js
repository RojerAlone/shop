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

var uid;
function regist() {
    var yonghuming = document.getElementById("yonghuming").value;
    var nichen = document.getElementById("nichen").value;
    var password = document.getElementById("password").value;
    var email = document.getElementById("email").value;
    var phone = document.getElementById("phone").value;
    $.post("/register",
        {username: yonghuming, password: password, nickname: nichen, email: email, phone: phone}
        , function (result) {
            if (!result.success) {
                alert(result.msg)
            }
            else {
                uid = result.data;
                //alert(uid);
                window.location.href = '/user/validate';
            }
        });
};

function time() {
    if (wait == 0) {
        document.getElementById("send").removeAttribute("disabled");
        document.getElementById("send").value = "重新获取验证码";
        wait = 60;
    } else {
        document.getElementById("send").setAttribute("disabled", true);
        document.getElementById("send").value = wait + "s后可以重新发送";
        wait--;
        schedule();
    }
}

function sendMail() {
    $.post("/user/sendMail", function (result) {
        if (result.success) {
            time()
        } else {
            alert(result.msg);
        }
    });
}

function validate() {
    var code = document.getElementById("code").value;
    $.post("/user/validate", {uid: uid, code: code}, function (result) {
        if (result.success) {
            window.location.href = result.data;
        }
        else {
            alert(result.msg);
        }
    })
}