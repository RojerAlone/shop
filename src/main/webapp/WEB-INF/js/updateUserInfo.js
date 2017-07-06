$.post("/user/personal", function (result) {
    document.getElementById("userInfo_0").value = result.data.nickname;
    ;
    document.getElementById("userInfo_1").value = result.data.email;
    document.getElementById("userInfo_2").value = result.data.phone;
})

function updateUserInfo() {
    var userInfo_0 = document.getElementById("userInfo_0").value;
    var userInfo_1 = document.getElementById("userInfo_1").value;
    var userInfo_2 = document.getElementById("userInfo_2").value;
    if (userInfo_0) {
        $.post("/user/update", {nickname: userInfo_0, email: userInfo_1, phone: userInfo_2}, function (result) {
            if (result.success) {
                window.location.href = '/user/personal';
            }
            else {
                alert(result.msg);
            }
        })
    }
    else {
        alert("昵称不能为空");
    }
}
