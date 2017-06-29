
$(
    function () {
        var spcar = document.getElementById("spcar");
        var i = JSON.parse(localStorage.getItem("i"));
        var k = 1;
        var prices = 0;
        //var game;
        for (j=1; j <= i; j++) {
            var game = JSON.parse(localStorage.getItem("data_" + j));
            if(game)
            {
                var gname = game.name;
                var price = game.price;
                prices += price; 
                 //alert(prices);
                var tr = document.createElement("tr");
                tr.className = "cart_item";
                tr.id = "game_" + j;
                tr.innerHTML = "<td>" + k + "</td><td>" + gname + "</td><td id='price_"+j+"'>￥" + price + "</td><td onclick='del("+j+")'>移除</td>";
                spcar.appendChild(tr);
                k++;
                document.getElementById("prices").innerText = prices;
            }
        }
    }
)
//var int = self.setInterval("check()",50)

function del(i){
var t = document.getElementById("game_"+i);
t.parentNode.remove(t);
var storage = window.localStorage;
var ii = storage.getItem("i");
ii = ii - 1;
storage.removeItem("data_"+i);
storage.setItem("i",ii);
location.reload("true");
}

function orderadd() {
    var storage = window.localStorage;
    var gamesid=new Array();
    var i = storage.getItem("i");
    var j = 1;
    var k = 0;
    //var game = (storage.getItem(("data_"+j)));
    //var game = JSON.parse(localStorage.getItem("data_" + j));
    //alert(game.id);
    for(j=1;j<=i;j++){
        var game = JSON.parse(localStorage.getItem("data_" + j));
        gamesid[k] = parseInt(game.id);
        //alert(gamesid[k]);
        k++;
    }

    //alert(gamesid);
    $.post({url:"/order/order",
        traditional: true,
        data:{games:gamesid},
        success:function(result){
        if(result.success){
            self.location='/order/' + result.data.id + '/payway';
        }
    }
        }
    )

}






