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