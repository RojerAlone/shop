$(
    function () {
        var j = 1;
        $.post("/everyday", function (result) {
            for (i = 0; i < 5; i++) {
                document.getElementById("gameurl_" + i).href = "/game/" + result.data[i].id;
                document.getElementById("gameimg_" + i).src = "/img" + result.data[i].img[0];
                document.getElementById("gamename_" + i).innerText = result.data[i].name;
            }
        })
        var myTabContent = document.getElementById("myTabContent");
        $.post("/newestgames", function (result) {
            var i = 0;
            var div = document.createElement("div");
            div.className = "tab-pane fade in active";
            div.id = "home";
            div.innerHTML = "";
            while (result.data[i]) {
                if (result.data[i].price == '0') {
                    result.data[i].price = "免费"
                } else {
                    result.data[i].price = "¥" + result.data[i].price;
                }
                div.innerHTML += "<div class='row rowpy'>" +
                    "<div class='col-md-6 cancelpadding'>" +
                    "<a href='/game/" + result.data[i].id + "'>" +
                    "<img src='/img" + result.data[i].img[0] + "' onmouseover='hoverShowDiv_1(" + i + ")' class='zximgwh'>" +
                    "</a>" +
                    "</div>" +
                    "<div class='col-md-6 cancelpadding'>" +
                    "<div class='smargin'>游戏名：" + result.data[i].name + "</div>" +
                    "<div class='smargin_1'>价格：" + result.data[i].price + "</div>" +
                    "<div class='smargin_2'>制造商：" + result.data[i].creater + "</div>" +
                    "</div>" +
                    "</div>";
                myTabContent.appendChild(div);
                i++;
                var divHover = document.getElementById("divHover");
                divHover.className = "divHover";
                divHover.innerHTML = "";
                divHover.innerHTML += "<div class='smargin_3'>" + result.data[0].name + "</div>" +
                    "<div class='smargin_4'>" + result.data[0].desc + "</div> ";
                j = 1;
                while (result.data[0].img[j]) {
                    divHover.innerHTML += "<img src='/img" + result.data[0].img[j] + "'  class='zximgwh_1'>";
                    j++;
                }
            }
        })
        j = 1;
        var myTabContent = document.getElementById("myTabContent");
        $.post("/preupgames", function (result) {
            var i = 0;
            var div = document.createElement("div");
            div.className = "tab-pane fade in";
            div.id = "jijiang";
            div.innerHTML = "";
            while (result.data[i]) {
                if (result.data[i].price == '0') {
                    result.data[i].price = "免费"
                } else {
                    result.data[i].price = "¥" + result.data[i].price;
                }
                div.innerHTML += "<div class='row rowpy'>" +
                    "<div class='col-md-6 cancelpadding'>" +
                    "<a href='/game/" + result.data[i].id + "'>" +
                    "<img src='/img" + result.data[i].img[0] + "' onmouseover='hoverShowDiv_2(" + i + ")' class='zximgwh'>" +
                    "</a>" +
                    "</div>" +
                    "<div class='col-md-6 cancelpadding'>" +
                    "<div class='smargin'>游戏名：" + result.data[i].name + "</div>" +
                    "<div class='smargin_1'>价格：" + result.data[i].price + "</div>" +
                    "<div class='smargin_2'>制造商：" + result.data[i].creater + "</div>" +
                    "</div>" +
                    "</div>";
                myTabContent.appendChild(div);
                i++;
                var divHover = document.getElementById("divHover");
                divHover.className = "divHover";
                divHover.innerHTML = "";
                divHover.innerHTML += "<div class='smargin_3'>" + result.data[0].name + "</div>" +
                    "<div class='smargin_4'>" + result.data[0].desc + "</div> ";
                j = 1;
                while (result.data[0].img[j]) {
                    divHover.innerHTML += "<img src='/img" + result.data[0].img[j] + "'  class='zximgwh_1'>";
                    j++;
                }
            }
        })

        $.post("/freegames", function (result) {
            var freegames_1 = document.getElementById("freegames_1");
            for (var i = 0; i < 6; i++) {
                var div = document.createElement("div");
                div.className = "item_1_2";
                div.innerHTML = "<a href='/game/" + result.data[i].id + "'><img class='item_1_1' src='/img" + result.data[i].img[0] + "'></a>";
                freegames_1.appendChild(div);
            }
            var freegames_0 = document.getElementById("freegames_0");
            for (var i = 6; i < 12; i++) {
                var div = document.createElement("div");
                div.className = "item_1_2";
                div.innerHTML = "<a href='/game/" + result.data[i].id + "'><img class='item_1_1' src='/img" + result.data[i].img[0] + "'></a>";
                freegames_0.appendChild(div);
            }
        })
    }
);

function hoverShowDiv_1(i) {
    var j = 1;
    var divHover = document.getElementById("divHover");
    divHover.className = "divHover";
    divHover.innerHTML = "";
    $.post("/newestgames", function (result) {
        var desc = (result.data[i].desc).substring(0, 100);
        divHover.innerHTML += "<div class='smargin_3'>" + result.data[i].name + "</div>" +
            "<div class='smargin_4'>" + desc + "</div> ";
        while (result.data[i].img[j]) {
            divHover.innerHTML += "<img src='/img" + result.data[i].img[j] + "'  class='zximgwh_1'>";
            j++;
        }
    })
}
function hoverShowDiv_2(i) {
    var j = 1;
    var divHover = document.getElementById("divHover");
    divHover.className = "divHover";
    divHover.innerHTML = "";
    $.post("/preupgames", function (result) {
        var desc = (result.data[i].desc).substring(0, 100);
        divHover.innerHTML += "<div class='smargin_3'>" + result.data[i].name + "</div>" +
            "<div class='smargin_4'>" + desc + "</div> ";
        while (result.data[i].img[j]) {
            divHover.innerHTML += "<img src='/img" + result.data[i].img[j] + "'  class='zximgwh_1'>";
            j++;
        }
    })
}
