$(
    function () {
        $.post("/order/paid", function (result) {
            var i = 0;
            //var orderTab = document.getElementById("orderTab");
            var disc = document.createElement("tr");
            disc.innerHTML = "<td>序号</td><td>总价</td><td class='td_1'>下单时间</td>";
            //orderTab.appendChild(disc);
            var orderclass = document.getElementById("already");
            orderclass.appendChild(disc);
            while (result.data[i]) {
                var row = document.createElement("tr");
                //row.className = "row";
                var time = new Date(result.data[i].ctime).toLocaleString();
                var prices = result.data[i].total;
                var k = i + 1;
                row.innerHTML = "<td>" + "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>"+k+"<span class='caret'></span> </button><ul id = 'ula_"+i+"'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" + "</td><td>" + prices + "</td><td>" + time + "</td>";
                //row.innerHTML = "<td>" + i + "</td><td>" + prices + "</td><td>" + time + "</td>";
                orderclass.appendChild(row);
                i++;
            }
            var k = 0;
            var j = 0;
            while(result.data[k]) {
                var ul = document.getElementById("ula_"+k);
                while(result.data[k].orderitems[j]){
                    var li = document.createElement("li");
                    var gid = result.data[k].orderitems[j].game.id;
                    var price = result.data[k].orderitems[j].game.price;
                    li.setAttribute("role","presentation");
                    li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/"+gid+"'><div class='divwidth'><div class='setleft'>"+result.data[k].orderitems[j].game.name+"</div><div class='setright'>"+"¥"+price+"</div></div></a>";
                    ul.appendChild(li);
                    j++;
                }
                k++;
                j = 0;
            }
        })

        $.post("/order/notpay", function (result) {
            var i = 0;
            var disc = document.createElement("tr");
            disc.innerHTML = "<td>序号</td><td>总价</td><td class='td_1'>下单时间</td>";
            var orderclass = document.getElementById("yet");
            orderclass.appendChild(disc);
            while (result.data[i]) {
                var row = document.createElement("tr");
                var oid = result.data[i].id;
                //row.className = "row";
                var time = new Date(result.data[i].ctime).toLocaleString();
                var prices = result.data[i].total;
                row.innerHTML = "<td>" + "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>"+i+"<span class='caret'></span> </button><ul id = 'ulb_"+i+"'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" + "</td><td>" + prices + "</td><td>" + time + "</td><td><button class='btn' onclick='cancel("+oid+")'>取消</button></td>";
                orderclass.appendChild(row);
                i++;
            }
            i = 0;
            j = 0;
            while(result.data[i]) {
                var ul = document.getElementById("ulb_"+i);
                while(result.data[i].orderitems[j]){
                    var li = document.createElement("li");
                    var gid = result.data[i].orderitems[j].game.id;
                    var price = result.data[i].orderitems[j].game.price;
                    li.setAttribute("role","presentation");
                    li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/"+gid+"'><div class='divwidth'><div class='setleft'>"+result.data[i].orderitems[j].game.name+"</div><div class='setright'>"+"¥"+price+"</div></div></a>";
                    ul.appendChild(li);
                    j++;
               }
                i++;
                j = 0;
            }

        })

        $.post("/order/cancel", function (result) {
            var i = 0;
            var disc = document.createElement("tr");
            disc.innerHTML = "<td>序号</td><td>总价</td><td class='td_1'>下单时间</td>";
            var orderclass = document.getElementById("cancel");
            orderclass.appendChild(disc);
            while (result.data[i]) {
                var row = document.createElement("tr");
                //row.className = "row";
                var time = new Date(result.data[i].ctime).toLocaleString();
                var prices = result.data[i].total;
                row.innerHTML = "<td>" + "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>"+i+"<span class='caret'></span> </button><ul id = 'ulc_"+i+"'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" + "</td><td>" + prices + "</td><td>" + time + "</td>";
                //row.innerHTML = "<td>" + i + "</td><td>" + prices + "</td><td>" + time + "</td>";
                orderclass.appendChild(row);
                i++;
            }
            i = 0;
            j = 0;
            while(result.data[i]) {
                var ul = document.getElementById("ulc_"+i);
                while(result.data[i].orderitems[j]){
                    var li = document.createElement("li");
                    var gid = result.data[i].orderitems[j].game.id;
                    var price = result.data[i].orderitems[j].game.price;
                    li.setAttribute("role","presentation");
                    li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/"+gid+"'><div class='divwidth'><div class='setleft'>"+result.data[i].orderitems[j].game.name+"</div><div class='setright'>"+"¥"+price+"</div></div></a>";
                    ul.appendChild(li);
                    j++;
                }
                i++;
                j = 0;
            }
        })
}
)
function cancel(oid) {
    $.post("/order/"+oid+"/cancel",function(){location.reload(); })
}
