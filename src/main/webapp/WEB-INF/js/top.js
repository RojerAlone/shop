$(
    function() {
        var storage = window.localStorage;
        var username = storage.getItem("user");
        //alert(username);
        if(username){
            document.getElementById("nologin").style.display = "none";
            document.getElementById("allogin").style.display = "block";
            document.getElementById("username").innerHTML =  "<a href='/user/personal'>"+username + "您好</a>";
        }
    }
)
function outlogin() {
    $.post("/logout",function (result) {
        if(result.success){
            var storage = window.localStorage;
           storage.removeItem("user");
            location.reload("true");
        }
        }
        )
}