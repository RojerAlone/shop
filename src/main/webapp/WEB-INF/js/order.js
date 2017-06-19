$(
    function () {
        $.post("/order/paid", function (result) {
            var i=0;
            var orderTab = document.getElementById("orderTab");
            var disc = document.createElement("tr");
            disc.innerHTML = "<div class='row rowpy'><td>序号</td><td>总价</td><td>下单时间</td></div>";
            orderTab.appendChild(disc);
            while (result.data[i]) {
                var orderclass = document.createElement("tr");
                var time = new Date(result.data[i].ctime);
                orderclass.class = "tab-pane fade in active";
                orderclass.id = "already";
                orderclass.innerHTML = "<div class='row rowpy'><a href='#'><td class='td_1'>"+i+"</td><td class='td_1'>"+result.data[i].total+ "</td><td'>"+time+"</td></a></div>";
                orderTab.appendChild(orderclass);
                i++;
         }
        })
    }
)