// JavaScript Document
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

var wait = 60;
function time(o) {
    if (wait == 0) {
        o.removeAttribute("disabled");
        o.value = "免费获取验证码";
        wait = 60;
        //alert(uid);
        $.post("/user/sendMail", {uid: uid}, function (result) {
        });
    }
    else {
        o.setAttribute("disabled", true);
        o.value = wait + "s后可以重新发送";
        wait--;
        setTimeout(function () {
            time(o)
        }, 1000)
    }
}

function zhuce() {
    var code = document.getElementById("code").value;
    $.post("/user/validate", {uid: uid, code: code}, function (result) {
        if (!result.success) {
            alert(result.msg)
        }
        else {
            document.getElementById("regist_2").style.display = "none";
            document.getElementById("regist_3").style.display = "block";
        }
    })
}
/*function regist_1(){
 var yonghuming=document.getElementById("yonghuming").value;
 var nichen=document.getElementById("nichen").value;
 var password=document.getElementById("password").value;
 var email=document.getElementById("email").value;
 var phone=document.getElementById("phone").value;
 $.ajax({
 type:"post",
 url:"http://172.29.17.99:8080/user/register",
 data:{username:yonghuming,password:password,nickname:nichen,email:email,phone:phone},
 dataType:"json",
 crossDomain:true,
 success:function(result){
 console.log(result);
 },
 error:function(){
 console.log('fail');
 }
 });
 }*/