$(
    function () {
        $.post("/order/paid/1", function (result) {
                var i = 0;
                //var orderTab = document.getElementById("orderTab");
                var disc = document.createElement("tr");
                disc.innerHTML = "<td>序号</td><td>总价</td><td class='td_1'>下单时间</td>";
                //orderTab.appendChild(disc);
                var orderclass = document.getElementById("already");
                orderclass.appendChild(disc);
                while (result.data.order[i]) {
                    var row = document.createElement("tr");
                    //row.className = "row";
                    var time = new Date(result.data.order[i].ctime).toLocaleString();
                    var prices = result.data.order[i].total;
                    var k = i + 1;
                    row.innerHTML = "<td>" + "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>" + k + "<span class='caret'></span> </button><ul id = 'ula_" + i + "'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" + "</td><td>" + prices + "</td><td>" + time + "</td>";
                    //row.innerHTML = "<td>" + i + "</td><td>" + prices + "</td><td>" + time + "</td>";
                    orderclass.appendChild(row);
                    i++;
                }
                var div = document.createElement("div");
                div.className = "pagination pagination-right yema divsetcenter";
                div.innerHTML = "<ul id='paging_1'></ul>";
                orderclass.appendChild(div);
                var k = 0;
                var j = 0;
                while (result.data.order[k]) {
                    var ul = document.getElementById("ula_" + k);
                    while (result.data.order[k].orderitems[j]) {
                        var li = document.createElement("li");
                        var gid = result.data.order[k].orderitems[j].game.id;
                        var price = result.data.order[k].orderitems[j].game.price;
                        li.setAttribute("role", "presentation");
                        li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/" + gid + "'><div class='divwidth'><div class='setleft'>" + result.data.order[k].orderitems[j].game.name + "</div><div class='setright'>" + "¥" + price + "</div></div></a>";
                        ul.appendChild(li);
                        j++;
                    }
                    k++;
                    j = 0;
                }

                var ul = document.getElementById("paging_1");
                ul.className = "pagination";
                var pages = result.data.page.pages;
                var current = result.data.page.current;
                var last = current - 1;
                var next = current + 1;
                if(last<1){last=1}
                if(next>pages){next=pages}
                var li_first = document.createElement("li");
                li_first.id = "li_first";
                li_first.innerHTML = "<a href='#'onclick='getpaid(" + last + ")'>&laquo;</a>";
                ul.appendChild(li_first);
                for (i = 0; i < pages; i++) {
                    var j = i + 1;
                    var li = document.createElement("li");
                    li.id = "li_" + j;
                    li.innerHTML = "<a href='#'onclick='getpaid(" + j + ")'>" + j + "</a>";
                    ul.appendChild(li);
                }
                document.getElementById("li_" + current).className = "active";
                var li_last = document.createElement("li");
                li_last.innerHTML = "<a href='#'onclick='getpaid(" + next + ")'>&raquo;</a>";
                ul.appendChild(li_last);
            }
        )

        $.post("/order/notpay/1", function (result) {
            var i = 0;
            var disc = document.createElement("tr");
            disc.innerHTML = "<td>序号</td><td>总价</td><td class='td_1'>下单时间</td>";
            var orderclass = document.getElementById("yet");
            orderclass.appendChild(disc);
            while (result.data.order[i]) {
                var row = document.createElement("tr");
                var k = i + 1;
                var oid = result.data.order[i].id;
                //row.className = "row";
                var time = new Date(result.data.order[i].ctime).toLocaleString();
                var prices = result.data.order[i].total;
                row.innerHTML = "<td>" +
                    "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>" + k +
                    "<span class='caret'></span> </button><ul id = 'ulb_" + i + "'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" +
                    "</td><td>" + prices + "</td><td>" + time + "</td><th><button class='btn smargin' onclick='topay(" + oid + ")'>去支付</button></th>"+
                    "<th><button class='btn smargin' onclick='cancel(" + oid + ")'>取消</button></th>";
                orderclass.appendChild(row);
                i++;
            }
            var div = document.createElement("div");
            div.className = "pagination pagination-right yema divsetcenter";
            div.innerHTML = "<ul id='paging_2'></ul>";
            orderclass.appendChild(div);
            i = 0;
            j = 0;
            while (result.data.order[i]) {
                var ul = document.getElementById("ulb_" + i);
                while (result.data.order[i].orderitems[j]) {
                    var li = document.createElement("li");
                    var gid = result.data.order[i].orderitems[j].game.id;
                    var price = result.data.order[i].orderitems[j].game.price;
                    li.setAttribute("role", "presentation");
                    li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/" + gid + "'><div class='divwidth'><div class='setleft'>" + result.data.order[i].orderitems[j].game.name + "</div><div class='setright'>" + "¥" + price + "</div></div></a>";
                    ul.appendChild(li);
                    j++;
                }
                i++;
                j = 0;
            }
            var ul = document.getElementById("paging_2");
            ul.className = "pagination";
            var pages = result.data.page.pages;
            var current = result.data.page.current;
            var last = current - 1;
            var next = current + 1;
            if(last<1){last=1}
            if(next>pages){next=pages}
            var li_first = document.createElement("li");
            li_first.id = "li_first";
            li_first.innerHTML = "<a href='#'onclick='getnotpay(" + last + ")'>&laquo;</a>";
            ul.appendChild(li_first);
            for (i = 0; i < pages; i++) {
                var j = i + 1;
                var li = document.createElement("li");
                li.id = "li_" + j;
                li.innerHTML = "<a href='#'onclick='getnotpay(" + j + ")'>" + j + "</a>";
                ul.appendChild(li);
            }
            if(current > 0){
                document.getElementById("li_" + current).className = "active";
            }
            var li_last = document.createElement("li");
            li_last.innerHTML = "<a href='#'onclick='getnotpay(" + next + ")'>&raquo;</a>";
            ul.appendChild(li_last);

        })

        $.post("/order/cancel/1", function (result) {
            var i = 0;
            var disc = document.createElement("tr");
            disc.innerHTML = "<td>序号</td><td>总价</td><td class='td_1'>下单时间</td>";
            var orderclass = document.getElementById("cancel");
            orderclass.appendChild(disc);
            while (result.data.order[i]) {
                var row = document.createElement("tr");
                var k = i + 1;
                //row.className = "row";
                var time = new Date(result.data.order[i].ctime).toLocaleString();
                var prices = result.data.order[i].total;
                row.innerHTML = "<td>" + "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>" + k + "<span class='caret'></span> </button><ul id = 'ulc_" + i + "'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" + "</td><td>" + prices + "</td><td>" + time + "</td>";
                //row.innerHTML = "<td>" + i + "</td><td>" + prices + "</td><td>" + time + "</td>";
                orderclass.appendChild(row);
                i++;
            }
            i = 0;
            j = 0;
            var div = document.createElement("div");
            div.className = "pagination pagination-right yema divsetcenter";
            div.innerHTML = "<ul id='paging_3'></ul>";
            orderclass.appendChild(div);
            while (result.data.order[i]) {
                var ul = document.getElementById("ulc_" + i);
                while (result.data.order[i].orderitems[j]) {
                    var li = document.createElement("li");
                    var gid = result.data.order[i].orderitems[j].game.id;
                    var price = result.data.order[i].orderitems[j].game.price;
                    li.setAttribute("role", "presentation");
                    li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/" + gid + "'><div class='divwidth'><div class='setleft'>" + result.data.order[i].orderitems[j].game.name + "</div><div class='setright'>" + "¥" + price + "</div></div></a>";
                    ul.appendChild(li);
                    j++;
                }
                i++;
                j = 0;
            }
            var ul = document.getElementById("paging_3");
            ul.className = "pagination";
            var pages = result.data.page.pages;
            var current = result.data.page.current;
            var last = current - 1;
            var next = current + 1;
            if(last<1){last=1}
            if(next>pages){next=pages}
            var li_first = document.createElement("li");
            li_first.id = "li3_first";
            li_first.innerHTML = "<a href='#'onclick='getcancel(" + last + ")'>&laquo;</a>";
            ul.appendChild(li_first);
            for (i = 0; i < pages; i++) {
                var j = i + 1;
                var li = document.createElement("li");
                li.id = "li3_" + j;
                li.innerHTML = "<a href='#'onclick='getcancel(" + j + ")'>" + j + "</a>";
                ul.appendChild(li);
            }
            document.getElementById("li3_" + current).className = "active";
            var li_last = document.createElement("li");
            li_last.innerHTML = "<a href='#'onclick='getcancel(" + next + ")'>&raquo;</a>";
            ul.appendChild(li_last);
        })
    }
)
function getpaid(pagenum) {
    $.post("/order/paid/"+pagenum, function (result) {
            var i = 0;
            //var orderTab = document.getElementById("orderTab");
            var disc = document.createElement("tr");
            disc.innerHTML = "<td>序号</td><td>总价</td><td class='td_1'>下单时间</td>";
            //orderTab.appendChild(disc);
            var orderclass = document.getElementById("already");
            orderclass.innerHTML = "";
            orderclass.appendChild(disc);
            while (result.data.order[i]) {
                var row = document.createElement("tr");
                //row.className = "row";
                var time = new Date(result.data.order[i].ctime).toLocaleString();
                var prices = result.data.order[i].total;
                var k = 10 * (pagenum-1) + i + 1;
                row.innerHTML = "<td>" + "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>" + k + "<span class='caret'></span> </button><ul id = 'ula_" + i + "'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" + "</td><td>" + prices + "</td><td>" + time + "</td>";
                //row.innerHTML = "<td>" + i + "</td><td>" + prices + "</td><td>" + time + "</td>";
                orderclass.appendChild(row);
                i++;
            }
            var div = document.createElement("div");
            div.className = "pagination pagination-right yema divsetcenter";
            div.innerHTML = "<ul id='paging_1'></ul>";
            orderclass.appendChild(div);
            var k = 0;
            var j = 0;
            while (result.data.order[k]) {
                var ul = document.getElementById("ula_" + k);
                while (result.data.order[k].orderitems[j]) {
                    var li = document.createElement("li");
                    var gid = result.data.order[k].orderitems[j].game.id;
                    var price = result.data.order[k].orderitems[j].game.price;
                    li.setAttribute("role", "presentation");
                    li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/" + gid + "'><div class='divwidth'><div class='setleft'>" + result.data.order[k].orderitems[j].game.name + "</div><div class='setright'>" + "¥" + price + "</div></div></a>";
                    ul.appendChild(li);
                    j++;
                }
                k++;
                j = 0;
            }
            var ul = document.getElementById("paging_1");
            ul.className = "pagination";
            var pages = result.data.page.pages;
            var current = result.data.page.current;
            var last = current - 1;
            var next = current + 1;
            if(last<1){last=1}
            if(next>pages){next=pages}
            var li_first = document.createElement("li");
            li_first.id = "li1_first";
            li_first.innerHTML = "<a href='#'onclick='getpaid(" + last + ")'>&laquo;</a>";
            ul.appendChild(li_first);
            for (i = 0; i < pages; i++) {
                var j = i + 1;
                var li = document.createElement("li");
                li.id = "li1_" + j;
                li.innerHTML = "<a href='#'onclick='getpaid(" + j + ")'>" + j + "</a>";
                ul.appendChild(li);
            }
            document.getElementById("li1_" + pagenum).className = "active";
            var li_last = document.createElement("li");
            li_last.id = "li1_next";
            li_last.innerHTML = "<a href='#'onclick='getpaid(" + next + ")'>&raquo;</a>";
            ul.appendChild(li_last);
        }
    )}
function getnotpay(pagenum) {
    $.post("/order/notpay/"+pagenum, function (result) {
            var i = 0;
            //var orderTab = document.getElementById("orderTab");
            var disc = document.createElement("tr");
            var oid = result.data.order[i].id;
            disc.innerHTML = "<td>序号</td><td>总价</td><td class='td_1'>下单时间</td>";
            //orderTab.appendChild(disc);
            var orderclass = document.getElementById("yet");
            orderclass.innerHTML = "";
            orderclass.appendChild(disc);
            while (result.data.order[i]) {
                var row = document.createElement("tr");
                //row.className = "row";
                var time = new Date(result.data.order[i].ctime).toLocaleString();
                var prices = result.data.order[i].total;
                var k = 10 * (pagenum-1) + i + 1;
                row.innerHTML = "<td>" +
                    "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>" + k +
                    "<span class='caret'></span> </button><ul id = 'ulb_" + i + "'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" +
                    "</td><td>" + prices + "</td><td>" + time + "</td><th><button class='btn smargin' onclick='topay(" + oid + ")'>去支付</button></th>"+
                    "<th><button class='btn smargin' onclick='cancel(" + oid + ")'>取消</button></th>";
                //row.innerHTML = "<td>" + i + "</td><td>" + prices + "</td><td>" + time + "</td>";
                orderclass.appendChild(row);
                i++;
            }
            var div = document.createElement("div");
            div.className = "pagination pagination-right yema divsetcenter";
            div.innerHTML = "<ul id='paging_2'></ul>";
            orderclass.appendChild(div);
            var k = 0;
            var j = 0;
            while (result.data.order[k]) {
                var ul = document.getElementById("ulb_" + k);
                while (result.data.order[k].orderitems[j]) {
                    var li = document.createElement("li");
                    var gid = result.data.order[k].orderitems[j].game.id;
                    var price = result.data.order[k].orderitems[j].game.price;
                    li.setAttribute("role", "presentation");
                    li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/" + gid + "'><div class='divwidth'><div class='setleft'>" + result.data.order[k].orderitems[j].game.name + "</div><div class='setright'>" + "¥" + price + "</div></div></a>";
                    ul.appendChild(li);
                    j++;
                }
                k++;
                j = 0;
            }
            var ul = document.getElementById("paging_2");
            ul.className = "pagination";
            var pages = result.data.page.pages;
            var current = result.data.page.current;
            var last = current - 1;
            var next = current + 1;
            if(last<1){last=1}
            if(next>pages){next=pages}
            var li_first = document.createElement("li");
            li_first.id = "li2_first";
            li_first.innerHTML = "<a href='#'onclick='getnotpay(" + last + ")'>&laquo;</a>";
            ul.appendChild(li_first);
            for (i = 0; i < pages; i++) {
                var j = i + 1;
                var li = document.createElement("li");
                li.id = "li2_" + j;
                li.innerHTML = "<a href='#'onclick='getnotpay(" + j + ")'>" + j + "</a>";
                ul.appendChild(li);
            }
            document.getElementById("li2_" + pagenum).className = "active";
            var li_last = document.createElement("li");
            li_last.id = "li2_next";
            li_last.innerHTML = "<a href='#'onclick='getnotpay(" + next + ")'>&raquo;</a>";
            ul.appendChild(li_last);
        }
    )}
function getcancel(pagenum) {
$.post("/order/cancel/"+pagenum, function (result) {
    var i = 0;
    //var orderTab = document.getElementById("orderTab");
    var disc = document.createElement("tr");
    disc.innerHTML = "<td>序号</td><td>总价</td><td class='td_1'>下单时间</td>";
    //orderTab.appendChild(disc);
    var orderclass = document.getElementById("cancel");
    orderclass.innerHTML = "";
    orderclass.appendChild(disc);
    while (result.data.order[i]) {
        var row = document.createElement("tr");
        //row.className = "row";
        var time = new Date(result.data.order[i].ctime).toLocaleString();
        var prices = result.data.order[i].total;
        var k = 10 * (pagenum-1) + i + 1;
        row.innerHTML = "<td>" + "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>" + k + "<span class='caret'></span> </button><ul id = 'ulc_" + i + "'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" + "</td><td>" + prices + "</td><td>" + time + "</td>";
        //row.innerHTML = "<td>" + i + "</td><td>" + prices + "</td><td>" + time + "</td>";
        orderclass.appendChild(row);
        i++;
    }
    var div = document.createElement("div");
    div.className = "pagination pagination-right yema divsetcenter";
    div.innerHTML = "<ul id='paging_3'></ul>";
    orderclass.appendChild(div);
    var k = 0;
    var j = 0;
    while (result.data.order[k]) {
        var ul = document.getElementById("ulc_" + k);
        while (result.data.order[k].orderitems[j]) {
            var li = document.createElement("li");
            var gid = result.data.order[k].orderitems[j].game.id;
            var price = result.data.order[k].orderitems[j].game.price;
            li.setAttribute("role", "presentation");
            li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/" + gid + "'><div class='divwidth'><div class='setleft'>" + result.data.order[k].orderitems[j].game.name + "</div><div class='setright'>" + "¥" + price + "</div></div></a>";
            ul.appendChild(li);
            j++;
        }
        k++;
        j = 0;
    }
        var ul = document.getElementById("paging_3");
        ul.className = "pagination";
        var pages = result.data.page.pages;
        var current = result.data.page.current;
        var last = current - 1;
        var next = current + 1;
        if(last<1){last=1}
        if(next>pages){next=pages}
        var li_first = document.createElement("li");
        li_first.id = "li3_first";
        li_first.innerHTML = "<a href='#'onclick='getcancel(" + last + ")'>&laquo;</a>";
        ul.appendChild(li_first);
        for (i = 0; i < pages; i++) {
            var j = i + 1;
            var li = document.createElement("li");
            li.id = "li3_" + j;
            li.innerHTML = "<a href='#'onclick='getcancel(" + j + ")'>" + j + "</a>";
            ul.appendChild(li);
        }
        document.getElementById("li3_" + pagenum).className = "active";
        var li_last = document.createElement("li");
        li_last.id = "li3_next";
        li_last.innerHTML = "<a href='#'onclick='getcancel(" + next + ")'>&raquo;</a>";
        ul.appendChild(li_last);
}
)}

function cancel(oid) {
    $.post("/order/" + oid + "/cancel", function () {
        location.reload();
    })
}
function topay(oid) {
    self.location='/order/' + oid + '/payway'
}
