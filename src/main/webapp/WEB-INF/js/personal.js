$(
    function () {
        $.post("/user/personal", function (result) {
            document.getElementById("userinfo_0").innerText = "用户名:   " + result.data.username;
            document.getElementById("userinfo_1").innerText = "昵称:   " + result.data.nickname;
            document.getElementById("userinfo_2").innerText = "邮箱:   " + result.data.email;
            document.getElementById("userinfo_3").innerText = "电话:   " + result.data.phone;
        })
        var ul = document.getElementById("fenleixiangqing");
        var ull = document.getElementById("fenleiyouxitupian");
        $.post("/order/paid/1", function (result) {
                var i = 0;
                var j = 0;
                while (result.data.order[i]) {
                    while (result.data.order[i].orderitems[j]) {
                        var li = document.createElement("li");
                        var gid = result.data.order[i].orderitems[j].game.id;
                        var price = result.data.order[i].orderitems[j].game.price;
                        if (price == '0') {
                            price = "免费";
                        } else {
                            price = "¥" + price;
                        }
                        var u;
                        u = "<a href='/game/" + gid;
                        li.innerHTML = u + "'><div class='row fenleizitiyanse jutiyouxi'><div class='col-md-4'><img src='/img/" + gid + "/header.jpg' class='imgdx_1'></div><div class='col-md-8'><div class='row youximingzi'>" +
                            result.data.order[i].orderitems[j].game.name + "</div><div class='row'><div class='col-md-2 col-md-offset-10'>" + price + "</div>" +
                            "</div><div class='row youximingzi'>激活码：" + result.data.order[i].orderitems[j].code + "</div></div></div>";
                        ul.appendChild(li);
                        j++;
                    }
                    console.log(i + ":" + j);
                    i++;
                    j = 0;
                }
            }
        )
    })