// JavaScript Document
$(
    function () {
        // 获取用户信息
        var userInfo = document.getElementById("userInfo");
        $.post("/admin/getuser",function (result) {
            for(i=0;i<10;i++) {
                if( result.data.user[i]){
                var tr = document.createElement("tr");
                var j = i + 1;
                var uid =  result.data.user[i].id;
                var stat = result.data.user[i].stat;
                var time = new Date(result.data.user[i].ctime).toLocaleString();
                if(stat == '1'){
                    stat = "正常";
                }
                if(stat == '2'){
                    stat = '限制登录';
                }
                if (stat == '3'){
                    stat = '删除';
                }
                tr.innerHTML = "<td>" + j + "</td><td>"
                    + result.data.user[i].username + "</td><td>"
                    + result.data.user[i].nickname + "</td><td>"
                    + result.data.user[i].email + "</td><td>"
                    + result.data.user[i].phone + "</td><td>"
                    + time + "</td><td id='ustat_"+j+"'>"
                    + stat + "</td><td><button class='btn' onclick='restrict("+uid+","+j+")'>"
                    + "限制登录" + "</button><button class='btn' onclick='del("+uid+","+j+")'>"
                    + "删除" + "</button><button class='btn' onclick='relieve("+uid+","+j+")'>"
                    + "恢复" + "</button></td>";
                userInfo.appendChild(tr);
            }
            }
            // 用户信息的分页
            var ul = document.getElementById("paging_0");
            ul.className = "pagination";
            var pages = result.data.page.pages;
            var li_first = document.createElement("li");
            li_first.innerHTML = "<a href='#'>&laquo;</a>";
            ul.appendChild(li_first);
            for(i=0;i<pages;i++){
               var li = document.createElement("li");
               var  j = i + 1;
               li.innerHTML = "<a href='#'>"+j+"</a>";
               ul.appendChild(li);
            }
            var li_last = document.createElement("li");
            li_last.innerHTML = "<a href='#'>&raquo;</a>";
            ul.appendChild(li_last);
        })

        // 获取游戏信息
        var gameInfo = document.getElementById("gameInfo");
        $.post("/admin/getgames",function (result) {
            for(i=0;i<10;i++) {
                if( result.data.game[i]){
                    var tr = document.createElement("tr");
                    var j = i + 1;
                    var gid =  result.data.game[i].id;
                    var stat = result.data.game[i].stat;
                    if(stat == "1"){
                        stat = "已上架";
                    }
                    if(stat == "2"){
                        stat = "已下架";
                    }
                    if(stat == "0"){
                        stat = "未上架";
                    }
                    tr.innerHTML = "<td>" + j + "</td><td>"
                        + result.data.game[i].name + "</td><td>"
                        + result.data.game[i].price + "</td><td id='gstat_"+j+"'>"
                        + stat+"</td><td><button class='btn' onclick='upgame("+gid+","+j+")'>"
                        + "上架" + "</button><button class='btn' onclick='downgame("+gid+","+j+")'>"
                        + "下架" + "<button type='button' class='btn' data-toggle='modal' data-target='#myModal' onclick='getgameinfo("+gid+")'>"+
                        "修改信息"+
                        "</button>" + text + "</td>";
                    gameInfo.appendChild(tr);
                }
            }
            // 游戏信息的分页
            var ul = document.getElementById("paging_1");
            ul.className = "pagination";
            var pages = result.data.page.pages;
            var current = result.data.page.current;
            var last = current - 1;
            var next = current + 1;
            var li_first = document.createElement("li");
            li_first.id = "li_first";
            li_first.innerHTML = "<a href='#'onclick='getgeame("+last+")'>&laquo;</a>";
            ul.appendChild(li_first);
            document.getElementById("li_first").className = "disabled";
            for(i=0;i<pages;i++){
                var  j = i + 1;
                var li = document.createElement("li");
                li.id = "li_"+ j ;
                li.innerHTML = "<a href='#'onclick='getgeame("+j+")'>"+j+"</a>";
                ul.appendChild(li);
            }
            document.getElementById("li_"+current).className = "active";
            var li_last = document.createElement("li");
            li_last.innerHTML = "<a href='#'onclick='getgeame("+next+")'>&raquo;</a>";
            ul.appendChild(li_last);
        })
    }

)
//游戏信息分页的函数
function getgeame(pagenum) {
    var gameInfo = document.getElementById("gameInfo");
    gameInfo.innerHTML = "";
    $.post("/admin/getgames",{page:pagenum},function (result) {
        for(i=0;i<10;i++) {
            if( result.data.game[i]){
                var tr = document.createElement("tr");
                var j = 10 * (pagenum-1) + i + 1;
                var gid =  result.data.game[i].id;
                var stat = result.data.game[i].stat;
                if(stat == "1"){
                    stat = "已上架";
                }
                if(stat == "2"){
                    stat = "已下架";
                }
                if(stat == "0"){
                    stat = "未上架";
                }
                tr.innerHTML = "<td>" + j + "</td><td>"
                    + result.data.game[i].name + "</td><td>"
                    + result.data.game[i].price + "</td><td id='gstat_"+j+"'>"
                    + stat+"</td><td><button class='btn' onclick='upgame("+gid+","+j+")'>"
                    + "上架" + "</button><button class='btn' onclick='downgame("+gid+","+j+")'>"
                    + "下架" + "<button type='button' class='btn' data-toggle='modal' data-target='#myModal' onclick='getgameinfo("+gid+")'>"+
                    "修改信息"+
                    "</button>" + text +"</td>";
                gameInfo.appendChild(tr);
            }
        }
        var ul = document.getElementById("paging_1");
        ul.className = "pagination";
        ul.innerHTML = "";
        var pages = result.data.page.pages;
        var current = result.data.page.current;
        var last = current - 1;
        var next = current + 1;
        var li_first = document.createElement("li");
        li_first.id = "li_first";
        li_first.innerHTML = "<a href='#'onclick='getgeame("+last+")'>&laquo;</a>";
        ul.appendChild(li_first);
        for(i=0;i<pages;i++){
            var  j = i + 1;
            var li = document.createElement("li");
            li.id = "li_" + j ;
            li.innerHTML = "<a href='#'onclick='getgeame("+j+")'>"+j+"</a>";
            ul.appendChild(li);
        }
        document.getElementById("li_"+pagenum).className = "active";
        if(current <= "1"){
            document.getElementById("li_first").className = "disabled";
        }
        var li_last = document.createElement("li");
        li_last.id = "li_next";
        li_last.innerHTML = "<a href='#'onclick='getgeame("+next+")'>&raquo;</a>";
        ul.appendChild(li_last);
        if(current >= i){
            document.getElementById("li_next").className = "disabled";
        }

})}

//限制用户登录
function restrict(uid,j) {
    $.post("/admin/restrictuser",{uid:uid},function (result) {
        if(result.success){
            alert("该用户已被限制登录！");
            document.getElementById("ustat_"+j).innerHTML = "限制登录";
        }
        else{
            alert(result.msg);
        }
    })
}
//恢复用户
function relieve(uid,j) {
    $.post("/admin/relieveuser",{uid:uid},function (result) {
        if(result.success){
            alert("该用户已被恢复登录！");
            document.getElementById("ustat_"+j).innerHTML = "正常";
        }
        else{
            alert(result.msg);
        }
    })
}
//删除用户
function del(uid,j) {
    $.post("/admin/deluser",{uid:uid},function (result) {
        if(result.success){
            alert("该用户已被删除！");
            document.getElementById("ustat_"+j).innerHTML = "删除";
        }
        else{
            alert(result.msg);
        }
    })
}
//上架游戏
function upgame(gid,j) {
    $.post("/admin/upgame",{game:gid},function (result) {
        if(result.success){
            alert("游戏已上架！")
            document.getElementById("gstat_"+j).innerHTML = "已上架";
        }
        else{
            alert(result.msg);
        }
    })
}
//下架游戏
function downgame(gid,j) {
    $.post("/admin/downgame",{game:gid},function (result) {
        if(result.success){
            alert("游戏已下架！")
            document.getElementById("gstat_"+j).innerHTML = "已下架";
        }
        else{
            alert(result.msg);
        }
    })
}
function showright_0() {
    document.getElementById("right_0").style.display = "block";
    document.getElementById("right_1").style.display = "none";
}
function showright_1() {
    document.getElementById("right_0").style.display = "none";
    document.getElementById("right_1").style.display = "block";
}










//导航菜单
function navList(id) {
    var $obj = $("#nav_dot"), $item = $("#J_nav_" + id);
    $item.addClass("on").parent().removeClass("none").parent().addClass("selected");
    $obj.find("h4").hover(function () {
        $(this).addClass("hover");
    }, function () {
        $(this).removeClass("hover");
    });
    $obj.find("p").hover(function () {
        if ($(this).hasClass("on")) { return; }
        $(this).addClass("hover");
    }, function () {
        if ($(this).hasClass("on")) { return; }
        $(this).removeClass("hover");
    });
    $obj.find("h4").click(function () {
        var $div = $(this).siblings(".list-item");
        if ($(this).parent().hasClass("selected")) {
            $div.slideUp(600);
            $(this).parent().removeClass("selected");
        }
        if ($div.is(":hidden")) {
            $("#nav_dot li").find(".list-item").slideUp(600);
            $("#nav_dot li").removeClass("selected");
            $(this).parent().addClass("selected");
            $div.slideDown(600);

        } else {
            $div.slideUp(600);
        }
    });
}

/****表格隔行高亮显示*****/
window.onload=function(){
	oTable=document.getElementById("tab");//找表格
	aTr=document.getElementsByTagName("tr");//找所有的行
	for(i=0;i<aTr.length;i++){
		if(i%2==0){
			aTr[i].style.background="#fff";	
		}else{
			aTr[i].style.background="#ccc";	
		};
	};
};
var text = "<div class='modal fade' id='myModal' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>"+
    "<div class='modal-dialog' role='document'>"+
    "<div class='modal-content'>"+
    "<div class='modal-header'>"+
    "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>"+
    "<h4 class='modal-title' id='myModalLabel'>游戏信息</h4>"+
    "</div>"+
    "<div class='modal-body'>"+
    "<p>游戏"+"<input type='text' class='inputmargin_2'id='gamename"+"'></p><br>"+
    "<p>价格"+"<input type='text'class='inputmargin_2' id='gameprice"+"'></p><br>"+
    "<p>开发商"+"<input type='text'class='inputmargin_1' id='gamecreater"+"'></p><br>"+
    "<p>描述"+"<textarea  rows='5'  class='inputmargin_2' id='gamedesc"+"'></textarea></p><br>"+
    "<p>配置"+"<textarea  rows='5'  class='inputmargin_2' id='sys"+"'></textarea></p><br>"+
    "</div>"+
    "<div class='modal-footer'>"+
    "<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>"+
    "<button type='button' class='btn btn-primary'>Save changes</button>"+
    "</div>"+
    "</div>"+
    "</div>"+
    "</div>";

function getgameinfo(gid) {
    $.post("/game/"+gid,function (result) {
        document.getElementById("gamename").value = result.data.name;
        document.getElementById("gameprice").value = result.data.price;
        document.getElementById("gamecreater").value = result.data.creater;
        document.getElementById("gamedesc").value = result.data.desc;
        document.getElementById("sys").value = result.data.systemcfg;
    })
}