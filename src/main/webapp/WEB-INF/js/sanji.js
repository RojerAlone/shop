// JavaScript Document
var url;
var ggid;
$(
    function () {
        var loc = location.href;
        //var n1 = loc.length;//地址的总长度
        //var n1 = loc.indexOf("cid");//取得'id+3'的位置
        //var n2 = loc.indexOf("&");//取得'&'的位置
        //var n3 = loc.indexOf("tid");
        var n4 = loc.indexOf("gameid");
        //var url;
        /*
        if (n1 > 0) {
            n1 = n1 + 4;
            var id = decodeURI(loc.substring(n1, n2));//取从n1开始，n2位置结束的字符
            var gid = decodeURI(loc.substring(n2 + 5));
            url = "http://172.29.17.99:8080/kind/" + id + "/games";

        }
        if (n3 > 0) {
            n3 = n3 + 4;
            var tid = decodeURI(loc.substring(n3, n2));
            var gid = decodeURI(loc.substring(n2 + 5));
            //alert(tid);
            url = "http://172.29.17.99:8080/tag/" + tid + "/games";

        }*/
        //if (n4 > 0) {
            n4 = n4 + 7;
            var gameid = decodeURI(loc.substring(n4));
            //alert(gameid);
            //url = "/game/" + gameid;
            url = "";

       // }

        var carousel_1 = document.getElementById("carousel_1");
        var carousel_2 = document.getElementById("carousel_2");
        var carousel_3 = document.getElementById("carousel_3");
        var carousel_4 = document.getElementById("carousel_4");
        var getheader = document.getElementById("getheader");
        var shoppingcar = document.getElementById("shoppingcar");
        var systemcfg = document.getElementById("systemcfg");
        var i = 0;
        //alert(id + " "+gid);
        $.post(url, function (result) {
                //alert(result.data.name);
                document.getElementById("gamename").innerText = result.data.name;
                ggid = result.data.id;
                ggname = result.data.name;
                ggprice = result.data.price;
          
           

            //alert(result.data[gid].img[2]);
            var li_1 = document.createElement("li");
            li_1.className = "'active' data-target='#myCarousel' data-slide-to='" + i + "'>";
            //li_1.innerHTML = "<li data-target='#myCarousel' data-slide-to='" + i + "'></li>";
            carousel_1.appendChild(li_1);
            //i = 1;
            while (result.data.img[i + 2]) {
                var li_1 = document.createElement("li");
                li_1.className = "data-target='#myCarousel' data-slide-to='" + i + "'>";
                //li_1.innerHTML = "<li data-target='#myCarousel' data-slide-to='" + i + "'></li>";
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
                //<div class="item"> <img src="3_1.jpg" alt="Third slide"> </div>
            }
            i = 0;
            while (result.data.img[i + 1]) {
                var li_3 = document.createElement("li");
                var j = i + 1;
                li_3.className = "col-md-2 deletecolpadding";
                li_3.innerHTML = "<img src='/img" + result.data.img[j] + "' onClick='change(" + i + ")'>";
                //<div class="col-md-2" style="border:5 double #FFFFFF"> <img src="1.jpg" alt="First slide" width="115" height="65" onClick="change(0)"> </div>
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
            if(result.data.stat == '1') {
                spc.innerHTML = "<h2>购买" + result.data.name + ":¥" + result.data.price + "</h2>";
                shoppingcar.appendChild(spc);
            }else {
                if(result.data.stat == '0'){
                    stat = "未上架"
                }else if(result.data.stat == '2'){
                    stat = "已下架"
                }
                document.getElementById("shopingcartbtn").innerHTML = "";
                spc.innerHTML = "<h2>"+stat+"</h2>";
                shoppingcar.appendChild(spc);
            }

            j = 0;
            if(result.data.tags) {
                var tt = document.getElementById("tags");
                while (result.data.tags[j]) {
                    var ll = document.createElement("ll");
                    var tid = result.data.tags[j].id;
                    ll.innerHTML = "<a href='/tag/" + tid + "/games" + "'><span class='tags'>" + result.data.tags[j].name + "</span></a>";
                    //<a href='fenlei.html?id="+id+"&class="+result.data[i].name+"'>
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
    //alert(ggid);
    var carInfo = { id: ggid, name: ggname, price: ggprice };
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
    //alert(img);
    alert("添加成功！");
}

Date.prototype.toLocaleString = function() {
    return this.getFullYear() + "年" + (this.getMonth() + 1) + "月" + this.getDate() + "日 ";
};