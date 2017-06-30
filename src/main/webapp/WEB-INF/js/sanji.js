// JavaScript Document
var url;
var ggid;
$(
    function () {
        var loc = location.href;
        var n4 = loc.indexOf("gameid");
        n4 = n4 + 7;
        var gameid = decodeURI(loc.substring(n4));
        url = "";

        var carousel_1 = document.getElementById("carousel_1");
        var carousel_2 = document.getElementById("carousel_2");
        var carousel_3 = document.getElementById("carousel_3");
        var carousel_4 = document.getElementById("carousel_4");
        var getheader = document.getElementById("getheader");
        var shoppingcar = document.getElementById("shoppingcar");
        var systemcfg = document.getElementById("systemcfg");
        var i = 0;
        $.post(url, function (result) {
                document.getElementById("gamename").innerText = result.data.name;
                ggid = result.data.id;
                ggname = result.data.name;
                ggprice = result.data.price;


                var li_1 = document.createElement("li");
                li_1.className = "'active' data-target='#myCarousel' data-slide-to='" + i + "'>";
                carousel_1.appendChild(li_1);
                while (result.data.img[i + 2]) {
                    var li_1 = document.createElement("li");
                    li_1.className = "data-target='#myCarousel' data-slide-to='" + i + "'>";
                    carousel_1.appendChild(li_1);
                    i++;
                }
                i = 2;
                var li_2 = document.createElement('div');
                li_2.className = "item active";
                li_2.innerHTML = "<img width='600' height='337' src='/img" + result.data.img[1] + "'>";
                carousel_2.appendChild(li_2);
                while (result.data.img[i]) {
                    var li_2 = document.createElement('div');
                    li_2.className = "item";
                    li_2.innerHTML = "<img width='600' height='337' src='/img" + result.data.img[i] + "'>";
                    carousel_2.appendChild(li_2);
                    i++;
                }
                i = 0;
                while (result.data.img[i + 1]) {
                    var li_3 = document.createElement("li");
                    var j = i + 1;
                    li_3.className = "col-md-2 deletecolpadding";
                    li_3.innerHTML = "<img src='/img" + result.data.img[j] + "' onClick='change(" + i + ")'>";
                    carousel_4.appendChild(li_3);
                    i++;
                }
                var header = document.createElement("div");
                header.className = "row";
                header.innerHTML = "<img src='/img" + result.data.img[0] + "'width='320' height='151'>";
                getheader.appendChild(header);

                var discribe = document.createElement("div");
                discribe.className = "row";
                discribe.innerHTML = " <p style='color:#acb2b8'>" + result.data.desc + "</p>";
                getheader.appendChild(discribe);

                var time = document.createElement("div");
                var utime = new Date(result.data.utime);
                utime = utime.toLocaleString();
                time.className = "row";
                time.innerHTML = "<p><h6 style='color:white'>发行日期：" + utime + "<h6></p><p><h6 style='color:white'>开发商：" + result.data.creater + "<h6></p><p> <h6 style='color:gray'>用户自定义标签：</h6><div class='btn-group btn-group-xs' id='tags'></div> </h6></p>";
                getheader.appendChild(time);

                var spc = document.createElement("p");
                spc.className = "text-left";
                var stat;
                if (result.data.stat == '1') {
                    spc.innerHTML = "<h2>购买" + result.data.name + ":¥" + result.data.price + "</h2>";
                    shoppingcar.appendChild(spc);
                } else {
                    if (result.data.stat == '0') {
                        stat = "未上架"
                    } else if (result.data.stat == '2') {
                        stat = "已下架"
                    }
                    document.getElementById("shopingcartbtn").innerHTML = "";
                    spc.innerHTML = "<h2>" + stat + "</h2>";
                    shoppingcar.appendChild(spc);
                }

                j = 0;
                if (result.data.tags) {
                    var tt = document.getElementById("tags");
                    while (result.data.tags[j]) {
                        var ll = document.createElement("ll");
                        var tid = result.data.tags[j].id;
                        ll.innerHTML = "<a href='/tag/" + tid + "/games" + "'><span class='tags'>" + result.data.tags[j].name + "</span></a>";
                        tt.appendChild(ll);
                        j++;
                    }
                }

                i = 0;
                var system = result.data.systemcfg;
                var sys = system.split("\n");
                while (sys[i]) {
                    var s = document.createElement("tr");
                    s.innerHTML = "<td style='color:#acb2b8'>" + sys[i] + "</td>";
                    systemcfg.appendChild(s);
                    i++;
                }


            }
        )
    }
)

$(function () {
    // 循环轮播到某个特定的帧 
    $(".slide-one").click(function () {
        $("#myCarousel").carousel(0);
    });
    $(".slide-two").click(function () {
        $("#myCarousel").carousel(1);
    });
    $(".slide-three").click(function () {
        $("#myCarousel").carousel(2);
    })
    $(".slide-one").click(function () {
        $("#myCarousel").carousel(3);
    });
    $(".slide-one").click(function () {
        $("#myCarousel").carousel(4);
    });
});

function change(i) {
    console.log(i);
    $("#myCarousel").carousel(i);
}

function move(to) {
    var imgList = document.getElementById("carousel_3");
    if (to == "left")
        imgList.scrollLeft -= 126; //li的宽度
    else imgList.scrollLeft += 126; //li的宽度
}
function addshopingcar() {
    var carInfo = {id: ggid, name: ggname, price: ggprice};
    var i = JSON.parse(localStorage.getItem("i"));
    if (i) {
        localStorage.setItem("i", JSON.stringify(i + 1));
        i = JSON.parse(localStorage.getItem("i"));
    }
    else {
        localStorage.setItem("i", JSON.stringify(1));
        i = JSON.parse(localStorage.getItem("i"));
    }
    localStorage.setItem("data_" + i, JSON.stringify(carInfo));
    alert("添加成功！");
}

Date.prototype.toLocaleString = function () {
    return this.getFullYear() + "年" + (this.getMonth() + 1) + "月" + this.getDate() + "日 ";
};