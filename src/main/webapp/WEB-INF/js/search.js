$(
    function () {
        var prtW = window.opener;
        var info = prtW.document.getElementById("searchbox").value;
        document.getElementById("xianshineirong").innerText = "正在浏览关于" + info + "的内容";
        var ul = document.getElementById("fenleixiangqing");
        var ull = document.getElementById("fenleiyouxitupian");
        $.post("/search", {info: info}, function (result) {
            var i = 0;
            var j = 0;
            var k = 0;
            var prices = new Array();
            while (result.data[i]) {
                prices[i] = "¥" + result.data[i].price;
                if (prices[i] == "¥0")
                    prices[i] = "免费";
                i++;
            }
            while (result.data[k]) {
                var li = document.createElement("li");
                var gid = k;
                var u;
                u = "<a href='/game/" + result.data[k].id;
                li.innerHTML = u + "'><div class='row fenleizitiyanse jutiyouxi'><div class='col-md-4'><img id='xianshitupian_" + k +
                    "'onmouseover='xianshitupian(" + k + ")'" +
                    "onmouseout='yingcangtupian(+" + k + ")' src='/img" + result.data[k].img[0] + "' class='imgdx_1'></div><div class='col-md-8'><div class='row youximingzi'>" +
                    result.data[k].name + "</div><div class='row'><div class='col-md-2 col-md-offset-10'>" + prices[k] + "</div></div><div class='row youximingzi' id='tags_" + k + "'></div></div></div>"
                ul.appendChild(li);
                k++;
            }
            k = 0;
            while (result.data[k]) {
                var li = document.createElement("li");
                li.innerHTML = "<div class='row youxitupian' id='yingcangtupian_" + k + "'><div class='row'><div class='font font_1'>" + result.data[k].name + "</div></div><div class='row font'><div class='col-md-offset-1'><img class='imgdx' src='/img" + result.data[k].img[1] + "'></div></div><div class='row font font_2'>用户标签：</div><div class='row font' id='small_tags_" + k + "'>" + "</div></div>";
                ull.appendChild(li);
                k++;
            }
            i = 0;
            j = 0;
            while (result.data[i]) {
                var t = document.getElementById("tags_" + i);
                while (result.data[i].tags[j]) {
                    var l = document.createElement("l");
                    l.innerHTML = "<span class='tags img-rounded'>" + result.data[i].tags[j].name + "</span>";
                    t.appendChild(l);
                    j++;
                }
                i++;
                j = 0;
            }
            i = 0;
            j = 0;
            while (result.data[i]) {
                var tt = document.getElementById("small_tags_" + i);
                while (result.data[i].tags[j]) {
                    var ll = document.createElement("ll");
                    ll.innerHTML = "<span class='tags img-rounded'>" + result.data[i].tags[j].name + "</span>";
                    tt.appendChild(ll);
                    j++;
                }
                i++;
                j = 0;
            }


        });
    }
);

function xianshitupian(k) {
    document.getElementById("yingcangtupian_" + k).style.display = "block";
    var y = document.getElementById("yingcangtupian_" + k);
    y.style.top = 69 * k + "px";
}
function yingcangtupian(k) {
    document.getElementById("yingcangtupian_" + k).style.display = "none";
}