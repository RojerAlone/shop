var url;
$(
    function () {
        getgame(1);
    }
);

function getgame(page) {
    var ul = document.getElementById("fenleixiangqing");
    var ull = document.getElementById("fenleiyouxitupian");
    ul.innerHTML = "";
    ull.innerHTML = "";
    $.post(url, {page: page}, function (result) {
        if(result.data){
            var i = 0;
            var j = 0;
            var k = 0;
            //var tags = new Array();
            var prices = new Array();
            while (result.data.game[i]) {
                // tags[i]="";
                prices[i] = "¥" + result.data.game[i].price;
                if (prices[i] == "¥0")
                    prices[i] = "免费";
                i++;
            }
            while (result.data.game[k]) {
                var li = document.createElement("li");
                var gid = k;
                var u;
                u = "<a href='/game/" + result.data.game[k].id;
                li.innerHTML = u + "'><div class='row fenleizitiyanse jutiyouxi'><div class='col-md-4'><img id='xianshitupian_" + k +
                    "'onmouseover='xianshitupian(" + k + ")'" +
                    "onmouseout='yingcangtupian(+" + k + ")' src='/img" + result.data.game[k].img[0] + "' class='imgdx_1'></div><div class='col-md-8'><div class='row youximingzi'>" +
                    result.data.game[k].name + "</div><div class='row'><div class='col-md-2 col-md-offset-10'>" + prices[k] + "</div></div><div class='row youximingzi' id='tags_" + k + "'></div></div></div>"
                ul.appendChild(li);
                k++;
            }
            k = 0;
            while (result.data.game[k]) {
                var li = document.createElement("li");
                li.innerHTML = "<div class='row youxitupian' id='yingcangtupian_" + k + "'><div class='row'><div class='font font_1'>" + result.data.game[k].name + "</div></div><div class='row font'><div class='col-md-offset-1'><img class='imgdx' src='/img" + result.data.game[k].img[1] + "'></div></div><div class='row font font_2'>用户标签：</div><div class='row font' id='small_tags_" + k + "'>" + "</div></div>";
                ull.appendChild(li);
                k++;
            }
            i = 0;
            j = 0;
            while (result.data.game[i]) {
                var t = document.getElementById("tags_" + i);
                if(result.data.game[i].tags){
                    while (result.data.game[i].tags[j]) {
                        var l = document.createElement("l");
                        l.innerHTML = "<span class='tags img-rounded'>" + result.data.game[i].tags[j].name + "</span>";
                        t.appendChild(l);
                        j++;
                    }
                }
                i++;
                j = 0;
            }
            i = 0;
            j = 0;
            while (result.data.game[i]) {
                var tt = document.getElementById("small_tags_" + i);
                if(result.data.game[i].tags){
                    while (result.data.game[i].tags[j]) {
                        var ll = document.createElement("ll");
                        ll.innerHTML = "<span class='tags img-rounded'>" + result.data.game[i].tags[j].name + "</span>";
                        tt.appendChild(ll);
                        j++;
                    }
                }
                i++;
                j = 0;
            }
            var uul = document.getElementById("paging");
            uul.className = "pagination";
            uul.innerHTML = "";
            var pages = result.data.page.pages;
            var current = result.data.page.current;
            var last = current - 1;
            var next = current + 1;
            if (last < 1) {
                last = 1
            }
            if (next > pages) {
                next = pages
            }
            var li_first = document.createElement("li");
            li_first.id = "li_first";
            li_first.innerHTML = "<a href='#'onclick='getgame(" + last + ")'>&laquo;</a>";
            uul.appendChild(li_first);
            for (i = 0; i < pages; i++) {
                var j = i + 1;
                var li = document.createElement("li");
                li.id = "li_" + j;
                li.innerHTML = "<a href='#'onclick='getgame(" + j + ")'>" + j + "</a>";
                uul.appendChild(li);
            }
            document.getElementById("li_" + current).className = "active";
            var li_last = document.createElement("li");
            li_last.innerHTML = "<a href='#'onclick='getgame(" + next + ")'>&raquo;</a>";
            uul.appendChild(li_last);
        }

    })
}
function xianshitupian(k) {
    document.getElementById("yingcangtupian_" + k).style.display = "block";
    var y = document.getElementById("yingcangtupian_" + k);
    y.style.top = 69 * k + "px";
}
function yingcangtupian(k) {
    document.getElementById("yingcangtupian_" + k).style.display = "none";
}