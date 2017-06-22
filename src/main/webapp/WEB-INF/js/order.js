$(
    function () {
        $.post("/order/paid/1",function (result) {
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
                row.innerHTML = "<td>" + "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>"+k+"<span class='caret'></span> </button><ul id = 'ula_"+i+"'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" + "</td><td>" + prices + "</td><td>" + time + "</td>";
                //row.innerHTML = "<td>" + i + "</td><td>" + prices + "</td><td>" + time + "</td>";
                orderclass.appendChild(row);
                i++;
            }
            var k = 0;
            var j = 0;
            while(result.data.order[k]) {
                var ul = document.getElementById("ula_"+k);
                while(result.data.order[k].orderitems[j]){
                    var li = document.createElement("li");
                    var gid = result.data.order[k].orderitems[j].game.id;
                    var price = result.data.order[k].orderitems[j].game.price;
                    li.setAttribute("role","presentation");
                    li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/"+gid+"'><div class='divwidth'><div class='setleft'>"+result.data.order[k].orderitems[j].game.name+"</div><div class='setright'>"+"¥"+price+"</div></div></a>";
                    ul.appendChild(li);
                    j++;
                }
                k++;
                j = 0;
            }
        })

        $.post("/order/notpay/1",function (result) {
            var i = 0;
            var disc = document.createElement("tr");
            disc.innerHTML = "<td>序号</td><td>总价</td><td class='td_1'>下单时间</td>";
            var orderclass = document.getElementById("yet");
            orderclass.appendChild(disc);
            while (result.data.order[i]) {
                var row = document.createElement("tr");
                var k = i +1 ;
                var oid = result.data.order[i].id;
                //row.className = "row";
                var time = new Date(result.data[i].ctime).toLocaleString();
                var prices = result.data.order[i].total;
                row.innerHTML = "<td>" + "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>"+k+"<span class='caret'></span> </button><ul id = 'ulb_"+i+"'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" + "</td><td>" + prices + "</td><td>" + time + "</td><td><button class='btn' onclick='cancel("+oid+")'>取消</button></td>";
                orderclass.appendChild(row);
                i++;
            }
            i = 0;
            j = 0;
            while(result.data.order[i]) {
                var ul = document.getElementById("ulb_"+i);
                while(result.data.order[i].orderitems[j]){
                    var li = document.createElement("li");
                    var gid = result.data.order[i].orderitems[j].game.id;
                    var price = result.data.order[i].orderitems[j].game.price;
                    li.setAttribute("role","presentation");
                    li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/"+gid+"'><div class='divwidth'><div class='setleft'>"+result.data.order[i].orderitems[j].game.name+"</div><div class='setright'>"+"¥"+price+"</div></div></a>";
                    ul.appendChild(li);
                    j++;
               }
                i++;
                j = 0;
            }

        })

        $.post("/order/cancel/1", function (result) {
            var i = 0;
            var disc = document.createElement("tr");
            disc.innerHTML = "<td>序号</td><td>总价</td><td class='td_1'>下单时间</td>";
            var orderclass = document.getElementById("cancel");
            orderclass.appendChild(disc);
            while (result.data.order[i]) {
                var row = document.createElement("tr");
                var k = i +1;
                //row.className = "row";
                var time = new Date(result.data.order[i].ctime).toLocaleString();
                var prices = result.data.order[i].total;
                row.innerHTML = "<td>" + "<div class='dropdown'> <button type='button' class='btn dropdown-toggle' id='dropdownMenu1' data-toggle='dropdown'>"+k+"<span class='caret'></span> </button><ul id = 'ulc_"+i+"'class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" + "</td><td>" + prices + "</td><td>" + time + "</td>";
                //row.innerHTML = "<td>" + i + "</td><td>" + prices + "</td><td>" + time + "</td>";
                orderclass.appendChild(row);
                i++;
            }
            i = 0;
            j = 0;
            while(result.data.order[i]) {
                var ul = document.getElementById("ulc_"+i);
                while(result.data.order[i].orderitems[j]){
                    var li = document.createElement("li");
                    var gid = result.data.order[i].orderitems[j].game.id;
                    var price = result.data.order[i].orderitems[j].game.price;
                    li.setAttribute("role","presentation");
                    li.innerHTML = "<a role='menuitem' tabindex='-1' href='/game/"+gid+"'><div class='divwidth'><div class='setleft'>"+result.data.order[i].orderitems[j].game.name+"</div><div class='setright'>"+"¥"+price+"</div></div></a>";
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

// // 获取游戏信息
// var gameInfo = document.getElementById("gameInfo");
// $.post("/admin/getgames",function (result) {
//     for(i=0;i<10;i++) {
//         if( result.data.game[i]){
//             var tr = document.createElement("tr");
//             var j = i + 1;
//             var gid =  result.data.game[i].id;
//             var stat = result.data.game[i].stat;
//             if(stat == "1"){
//                 stat = "已上架";
//             }
//             if(stat == "2"){
//                 stat = "已下架";
//             }
//             if(stat == "0"){
//                 stat = "未上架";
//             }
//             tr.innerHTML = "<td>" + j + "</td><td>"
//                 + result.data.game[i].name + "</td><td>"
//                 + result.data.game[i].price + "</td><td id='gstat_"+j+"'>"
//                 + stat+"</td><td><button class='btn' onclick='upgame("+gid+","+j+")'>"
//                 + "上架" + "</button><button class='btn' onclick='downgame("+gid+","+j+")'>"
//                 + "下架" + "<button type='button' class='btn' data-toggle='modal' data-target='#myModal' onclick='getgameinfo("+gid+")'>"+
//                 "修改信息"+
//                 "</button>" + text + "</td>";
//             gameInfo.appendChild(tr);
//         }
//     }
//     // 游戏信息的分页
//     var ul = document.getElementById("paging_1");
//     ul.className = "pagination";
//     var pages = result.data.page.pages;
//     var current = result.data.page.current;
//     var last = current - 1;
//     var next = current + 1;
//     var li_first = document.createElement("li");
//     li_first.id = "li_first";
//     li_first.innerHTML = "<a href='#'onclick='getgeame("+last+")'>&laquo;</a>";
//     ul.appendChild(li_first);
//     document.getElementById("li_first").className = "disabled";
//     for(i=0;i<pages;i++){
//         var  j = i + 1;
//         var li = document.createElement("li");
//         li.id = "li_"+ j ;
//         li.innerHTML = "<a href='#'onclick='getgeame("+j+")'>"+j+"</a>";
//         ul.appendChild(li);
//     }
//     document.getElementById("li_"+current).className = "active";
//     var li_last = document.createElement("li");
//     li_last.innerHTML = "<a href='#'onclick='getgeame("+next+")'>&raquo;</a>";
//     ul.appendChild(li_last);
// })
// }
//
// )
// //游戏信息分页的函数
// function getgeame(pagenum) {
//     var gameInfo = document.getElementById("gameInfo");
//     gameInfo.innerHTML = "";
//     $.post("/admin/getgames",{page:pagenum},function (result) {
//         for(i=0;i<10;i++) {
//             if( result.data.game[i]){
//                 var tr = document.createElement("tr");
//                 var j = 10 * (pagenum-1) + i + 1;
//                 var gid =  result.data.game[i].id;
//                 var stat = result.data.game[i].stat;
//                 if(stat == "1"){
//                     stat = "已上架";
//                 }
//                 if(stat == "2"){
//                     stat = "已下架";
//                 }
//                 if(stat == "0"){
//                     stat = "未上架";
//                 }
//                 tr.innerHTML = "<td>" + j + "</td><td>"
//                     + result.data.game[i].name + "</td><td>"
//                     + result.data.game[i].price + "</td><td id='gstat_"+j+"'>"
//                     + stat+"</td><td><button class='btn' onclick='upgame("+gid+","+j+")'>"
//                     + "上架" + "</button><button class='btn' onclick='downgame("+gid+","+j+")'>"
//                     + "下架" + "<button type='button' class='btn' data-toggle='modal' data-target='#myModal' onclick='getgameinfo("+gid+")'>"+
//                     "修改信息"+
//                     "</button>" + text +"</td>";
//                 gameInfo.appendChild(tr);
//             }
//         }
//         var ul = document.getElementById("paging_1");
//         ul.className = "pagination";
//         ul.innerHTML = "";
//         var pages = result.data.page.pages;
//         var current = result.data.page.current;
//         var last = current - 1;
//         var next = current + 1;
//         var li_first = document.createElement("li");
//         li_first.id = "li_first";
//         li_first.innerHTML = "<a href='#'onclick='getgeame("+last+")'>&laquo;</a>";
//         ul.appendChild(li_first);
//         for(i=0;i<pages;i++){
//             var  j = i + 1;
//             var li = document.createElement("li");
//             li.id = "li_" + j ;
//             li.innerHTML = "<a href='#'onclick='getgeame("+j+")'>"+j+"</a>";
//             ul.appendChild(li);
//         }
//         document.getElementById("li_"+pagenum).className = "active";
//         if(current <= "1"){
//             document.getElementById("li_first").className = "disabled";
//         }
//         var li_last = document.createElement("li");
//         li_last.id = "li_next";
//         li_last.innerHTML = "<a href='#'onclick='getgeame("+next+")'>&raquo;</a>";
//         ul.appendChild(li_last);
//         if(current >= i){
//             document.getElementById("li_next").className = "disabled";
//         }
//
//     })}