function outlogin() {
    $.post("/logout", function (result) {
            if (result.success) {
                location.reload("true");
            } else {
                alert(result.msg);
            }
        }
    )
}
function search() {
    var info = document.getElementById("searchbox").value;
    $.post("/search", {info: info}, function (result) {
        if (result.success) {
            window.open("/search")
        } else {
            alert(result.msg);
        }
    })
}