// JavaScript Document
$(
    function () {
        // 获取用户信息
        var userInfo = document.getElementById("userInfo");
        $.post("/admin/getuser", function (result) {
            for (i = 0; i < 10; i++) {
                if (result.data.user[i]) {
                    var tr = document.createElement("tr");
                    var j = i + 1;
                    var uid = result.data.user[i].id;
                    var stat = result.data.user[i].stat;
                    var time = new Date(result.data.user[i].ctime).toLocaleString();
                    if (stat == '0') {
                        stat = "未激活";
                    }
                    if (stat == '1') {
                        stat = "正常";
                    }
                    if (stat == '2') {
                        stat = '限制登录';
                    }
                    if (stat == '3') {
                        stat = '删除';
                    }
                    tr.innerHTML = "<td>" + j + "</td><td>"
                        + result.data.user[i].username + "</td><td>"
                        + result.data.user[i].nickname + "</td><td>"
                        + result.data.user[i].email + "</td><td>"
                        + result.data.user[i].phone + "</td><td>"
                        + time + "</td><td id='ustat_" + j + "'>"
                        + stat + "</td><td><button class='btn' onclick='restrict(" + uid + "," + j + ")'>"
                        + "限制登录" + "</button><button class='btn' onclick='del(" + uid + "," + j + ")'>"
                        + "删除" + "</button><button class='btn' onclick='relieve(" + uid + "," + j + ")'>"
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
            for (i = 0; i < pages; i++) {
                var li = document.createElement("li");
                var j = i + 1;
                li.innerHTML = "<a href='#'>" + j + "</a>";
                ul.appendChild(li);
            }
            var li_last = document.createElement("li");
            li_last.innerHTML = "<a href='#'>&raquo;</a>";
            ul.appendChild(li_last);
        })

        // 获取游戏信息
        var gameInfo = document.getElementById("gameInfo");
        $.post("/admin/getgames", function (result) {
            for (i = 0; i < 10; i++) {
                if (result.data.game[i]) {
                    var tr = document.createElement("tr");
                    var j = i + 1;
                    var gid = result.data.game[i].id;
                    var stat = result.data.game[i].stat;
                    if (stat == "1") {
                        stat = "已上架";
                    }
                    if (stat == "2") {
                        stat = "已下架";
                    }
                    if (stat == "0") {
                        stat = "未上架";
                    }
                    tr.innerHTML = "<td>" + j + "</td><td id='gname_" + gid + "'>"
                        + result.data.game[i].name + "</td><td id='gprice_" + gid + "'>"
                        + result.data.game[i].price + "</td><td id='gstat_" + j + "'>"
                        + stat + "</td><td><button class='btn' onclick='upgame(" + gid + "," + j + ")'>"
                        + "上架" + "</button><button class='btn' onclick='downgame(" + gid + "," + j + ")'>"
                        + "下架" + "</button><button type='button' class='btn' data-toggle='modal' data-target='#myModal_1' onclick='getgamekind(" + gid + ")'>"
                        + "修改种类" + "</button>" + text_1 +
                        "<p>编号" + "<input type='text' readOnly='true' class='inputmargin_2'id='gamekindid" + "'></p><br>" +
                        "<p><div id='gamekind'>种类" + "</div></p><br>" +
                        "</div>" +
                        "<div class='modal-footer' id='savebtn'>" +
                        "<button type='button' class='btn btn-default' data-dismiss='modal'>取消</button>" +
                        "<button type='button' class='btn btn-primary' onclick='savekinds(" + ")'>保存</button>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "<button type='button' class='btn' data-toggle='modal' data-target='#myModal' onclick='getgameinfo(" + gid + ")'>"
                        + "修改信息" +
                        "</button>" + text +
                        "<p>编号" + "<input type='text' readOnly='true'class='inputmargin_2'id='gameid" + "'></p><br>" +
                        "<p>游戏" + "<input type='text' class='inputmargin_2'id='gamename" + "'></p><br>" +
                        "<p>价格" + "<input type='text'class='inputmargin_2' id='gameprice" + "'></p><br>" +
                        "<p>开发商" + "<input type='text'class='inputmargin_1' id='gamecreater" + "'></p><br>" +
                        "<p>描述" + "<textarea  rows='5'  class='inputmargin_2' id='gamedesc" + "'></textarea></p><br>" +
                        "<p>配置" + "<textarea  rows='5'  class='inputmargin_2' id='sys" + "'></textarea></p><br>" +
                        "</div>" +
                        "<div class='modal-footer' id='savebtn'>" +
                        "<button type='button' class='btn btn-default' data-dismiss='modal'>取消</button>" +
                        "<button type='button' class='btn btn-primary' onclick='savegameupdate(" + ")'>保存</button>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>" + "</td>";
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
            if (last < 1) {
                last = 1
            }
            if (next > pages) {
                next = pages
            }
            var li_first = document.createElement("li");
            li_first.id = "li_first";
            li_first.innerHTML = "<a href='#'onclick='getgeame(" + last + ")'>&laquo;</a>";
            ul.appendChild(li_first);
            document.getElementById("li_first").className = "disabled";
            for (i = 0; i < pages; i++) {
                var j = i + 1;
                var li = document.createElement("li");
                li.id = "li_" + j;
                li.innerHTML = "<a href='#'onclick='getgeame(" + j + ")'>" + j + "</a>";
                ul.appendChild(li);
            }
            document.getElementById("li_" + current).className = "active";
            var li_last = document.createElement("li");
            li_last.innerHTML = "<a href='#'onclick='getgeame(" + next + ")'>&raquo;</a>";
            ul.appendChild(li_last);
        })

        //种类管理
        var kind_all = document.getElementById("kind_all");
        $.post("/kind/all", function (result) {
            var i = 0;
            while (result.data[i]) {
                j = i + 1;
                var tr = document.createElement("tr");
                var kid = result.data[i].id;
                tr.innerHTML = "<th id='kindid_" + j + "'>"
                    + result.data[i].id + "</th><th id='kindname_" + j + "'>"
                    + result.data[i].name + "</th><th><button type='button' class='btn' data-toggle='modal' data-target='#myModal_2' onclick='managekind(" + kid + ")'>"
                    + "管理" + "</button>" + text_2 +
                    "<p>种类编号" + "<input type='text' readOnly='true' class='inputmargin_2'id='kindgameid" + "'></p><br>" +
                    "<p><div id='kindgame'>游戏" + "</div></p><br>" +
                    "</div>" +
                    "<div class='modal-footer' id='savebtn'>" +
                    "<button type='button' class='btn btn-default' data-dismiss='modal'>取消</button>" +
                    "<button type='button' class='btn btn-primary' onclick='savekindsgames(" + ")'>保存</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</th>";
                i++;
                kind_all.appendChild(tr);
            }
        })
    }
)
//游戏信息分页的函数
function getgeame(pagenum) {
    var gameInfo = document.getElementById("gameInfo");
    gameInfo.innerHTML = "";
    $.post("/admin/getgames", {page: pagenum}, function (result) {
        for (i = 0; i < 10; i++) {
            if (result.data.game[i]) {
                var tr = document.createElement("tr");
                var j = 10 * (pagenum - 1) + i + 1;
                var gid = result.data.game[i].id;
                var stat = result.data.game[i].stat;
                if (stat == "1") {
                    stat = "已上架";
                }
                if (stat == "2") {
                    stat = "已下架";
                }
                if (stat == "0") {
                    stat = "未上架";
                }
                tr.innerHTML = "<td>" + j + "</td><td id='gname_" + gid + "'>"
                    + result.data.game[i].name + "</td><td id='gprice_" + gid + "'>"
                    + result.data.game[i].price + "</td><td id='gstat_" + j + "'>"
                    + stat + "</td><td><button class='btn' onclick='upgame(" + gid + "," + j + ")'>"
                    + "上架" + "</button><button class='btn' onclick='downgame(" + gid + "," + j + ")'>"
                    + "下架" + "</button><button type='button' class='btn' data-toggle='modal' data-target='#myModal_1' onclick='getgamekind(" + gid + ")'>"
                    + "修改种类" + "</button>" + text_1 +
                    "<p>编号" + "<input type='text' readOnly='true' class='inputmargin_2'id='gamekindid" + "'></p><br>" +
                    "<p><div id='gamekind'>种类" + "</div></p><br>" +
                    "</div>" +
                    "<div class='modal-footer' id='savebtn'>" +
                    "<button type='button' class='btn btn-default' data-dismiss='modal'>取消</button>" +
                    "<button type='button' class='btn btn-primary' onclick='savekinds(" + ")'>保存</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "<button type='button' class='btn' data-toggle='modal' data-target='#myModal' onclick='getgameinfo(" + gid + ")'>"
                    + "修改信息" +
                    "</button>" + text +
                    "<p>编号" + "<input type='text' readOnly='true' class='inputmargin_2'id='gameid" + "'></p><br>" +
                    "<p>游戏" + "<input type='text' class='inputmargin_2'id='gamename" + "'></p><br>" +
                    "<p>价格" + "<input type='text'class='inputmargin_2' id='gameprice" + "'></p><br>" +
                    "<p>开发商" + "<input type='text'class='inputmargin_1' id='gamecreater" + "'></p><br>" +
                    "<p>描述" + "<textarea  rows='5'  class='inputmargin_2' id='gamedesc" + "'></textarea></p><br>" +
                    "<p>配置" + "<textarea  rows='5'  class='inputmargin_2' id='sys" + "'></textarea></p><br>" +
                    "</div>" +
                    "<div class='modal-footer' id='savebtn'>" +
                    "<button type='button' class='btn btn-default' data-dismiss='modal'>取消</button>" +
                    "<button type='button' class='btn btn-primary' onclick='savegameupdate(" + ")'>保存</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>" + "</td>";
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
        if (last < 1) {
            last = 1
        }
        if (next > pages) {
            next = pages
        }
        var li_first = document.createElement("li");
        li_first.id = "li_first";
        li_first.innerHTML = "<a href='#'onclick='getgeame(" + last + ")'>&laquo;</a>";
        ul.appendChild(li_first);
        for (i = 0; i < pages; i++) {
            var j = i + 1;
            var li = document.createElement("li");
            li.id = "li_" + j;
            li.innerHTML = "<a href='#'onclick='getgeame(" + j + ")'>" + j + "</a>";
            ul.appendChild(li);
        }
        document.getElementById("li_" + pagenum).className = "active";
        if (current <= "1") {
            document.getElementById("li_first").className = "disabled";
        }
        var li_last = document.createElement("li");
        li_last.id = "li_next";
        li_last.innerHTML = "<a href='#'onclick='getgeame(" + next + ")'>&raquo;</a>";
        ul.appendChild(li_last);
        if (current >= i) {
            document.getElementById("li_next").className = "disabled";
        }

    })
}

//限制用户登录
function restrict(uid, j) {
    $.post("/admin/restrictuser", {uid: uid}, function (result) {
        if (result.success) {
            alert("该用户已被限制登录！");
            document.getElementById("ustat_" + j).innerHTML = "限制登录";
        }
        else {
            alert(result.msg);
        }
    })
}
//恢复用户
function relieve(uid, j) {
    $.post("/admin/relieveuser", {uid: uid}, function (result) {
        if (result.success) {
            alert("该用户已被恢复登录！");
            document.getElementById("ustat_" + j).innerHTML = "正常";
        }
        else {
            alert(result.msg);
        }
    })
}
//删除用户
function del(uid, j) {
    $.post("/admin/deluser", {uid: uid}, function (result) {
        if (result.success) {
            alert("该用户已被删除！");
            document.getElementById("ustat_" + j).innerHTML = "删除";
        }
        else {
            alert(result.msg);
        }
    })
}
//上架游戏
function upgame(gid, j) {
    $.post("/admin/upgame", {game: gid}, function (result) {
        if (result.success) {
            alert("游戏已上架！")
            document.getElementById("gstat_" + j).innerHTML = "已上架";
        }
        else {
            alert(result.msg);
        }
    })
}
//下架游戏
function downgame(gid, j) {
    $.post("/admin/downgame", {game: gid}, function (result) {
        if (result.success) {
            alert("游戏已下架！")
            document.getElementById("gstat_" + j).innerHTML = "已下架";
        }
        else {
            alert(result.msg);
        }
    })
}
//获取游戏种类
function getgamekind(gid) {
    document.getElementById("gamekindid").value = gid;
    var kinds = new Array();
    $.post("/kind/all", function (result) {
        var i = 0;
        document.getElementById("gamekind").innerHTML = "种类";
        while (result.data[i]) {
            document.getElementById("gamekind").innerHTML += "<br>" + "<input type='checkbox' id='kindbox_" + gid + "_" + i + "'>" + result.data[i].name;
            kinds[i] = result.data[i].name;
            i++;
        }
    })
    $.post("/admin/getgamekind", {game: gid}, function (result) {
        var i = 0;
        var j = 0;
        while (result.data[i]) {
            while (kinds[j]) {
                if (result.data[i].name == kinds[j]) {
                    document.getElementById("kindbox_" + gid + "_" + j).checked = "true";
                }
                j++;
            }
            i++;
            j = 0;
        }
        i = 0;
    })
}
//保存游戏种类
function savekinds() {
    var i = 0;
    var j = 0;
    var kid = document.getElementById("gamekindid").value;
    var check = document.getElementById("kindbox_" + kid + "_" + i);
    var kinds = new Array();
    while (check) {
        if (check.checked) {
            kinds[j] = parseInt(i + 1);
            j++;
        }
        i++;
        check = document.getElementById("kindbox_" + kid + "_" + i);
    }
    $.ajax({
        type: "post",
        url: "/admin/updategamekind",
        traditional: true,
        data: {game: kid, kinds: kinds},
        success: function (result) {
            if (result.success) {
                alert("修改成功！");
            } else
                alert(result.msg);
        }
    })
    $('#myModal_1').modal('hide');
}


function managekind(kid) {
    // var kidpages=2;
    // var currentpage = 1;
    document.getElementById("kindgameid").value = kid;
    var i = 0;
    $.post("/admin/getallgames", function (result) {
        document.getElementById("kindgame").innerHTML = "游戏";
        while (result.data[i]) {
            var j = result.data[i].id;
            document.getElementById("kindgame").innerHTML += "<br>" + "<input type='checkbox' class='gamebox' id='kind_" + kid + "_" + j + "'>" + result.data[i].name;
            i++;
        }
        var j = 0;
        i = 0;
        while (result.data[i]) {
            if (result.data[i].kinds) {
                while (result.data[i].kinds[j]) {
                    if (result.data[i].kinds[j].id == kid) {
                        var gid = result.data[i].id;
                        document.getElementById("kind_" + kid + "_" + gid).checked = true;
                    }
                    j++;
                }
            }
            i++;
            j = 0;
        }
    })
}


function savekindsgames() {
    var kid = document.getElementById("kindgameid").value;
    var i = 0;
    var games = new Array();
    var num = 0;
    var gamebox = document.getElementsByClassName("gamebox");
    while (gamebox[i]) {
        if (gamebox[i].checked) {
            games[num] = (gamebox[i].id).split('_')[2];
            num++;
        }
        i++;
    }
    i = 0;
    $.ajax({
        type: "post",
        url: "/admin/managerkind",
        traditional: true,
        data: {kind: kid, games: games},
        success: function (result) {
            if (result.success) {
                alert("修改成功！");
            } else
                alert(result.msg);
        }
    })
    $('#myModal_2').modal('hide');
}
function showright_0() {
    document.getElementById("right_0").style.display = "block";
    document.getElementById("right_1").style.display = "none";
    document.getElementById("right_2").style.display = "none";
    document.getElementById("right_3").style.display = "none";
}
function showright_1() {
    document.getElementById("right_0").style.display = "none";
    document.getElementById("right_1").style.display = "block";
    document.getElementById("right_2").style.display = "none";
    document.getElementById("right_3").style.display = "none";
}
function showright_2() {
    document.getElementById("right_0").style.display = "none";
    document.getElementById("right_1").style.display = "none";
    document.getElementById("right_2").style.display = "block";
    document.getElementById("right_3").style.display = "none";
}
function showright_3() {
    document.getElementById("right_0").style.display = "none";
    document.getElementById("right_1").style.display = "none";
    document.getElementById("right_2").style.display = "none";
    document.getElementById("right_3").style.display = "block";
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
        if ($(this).hasClass("on")) {
            return;
        }
        $(this).addClass("hover");
    }, function () {
        if ($(this).hasClass("on")) {
            return;
        }
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
window.onload = function () {
    oTable = document.getElementById("tab");//找表格
    aTr = document.getElementsByTagName("tr");//找所有的行
    for (i = 0; i < aTr.length; i++) {
        if (i % 2 == 0) {
            aTr[i].style.background = "#fff";
        } else {
            aTr[i].style.background = "#ccc";
        }
        ;
    }
    ;
};
var text = "<div class='modal fade' id='myModal' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>" +
    "<div class='modal-dialog' role='document'>" +
    "<div class='modal-content'>" +
    "<div class='modal-header'>" +
    "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
    "<h4 class='modal-title' id='myModalLabel'>游戏信息</h4>" +
    "</div>" +
    "<div class='modal-body'>"

var text_1 = "<div class='modal fade' id='myModal_1' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>" +
    "<div class='modal-dialog' role='document'>" +
    "<div class='modal-content'>" +
    "<div class='modal-header'>" +
    "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
    "<h4 class='modal-title' id='myModalLabel'>游戏种类</h4>" +
    "</div>" +
    "<div class='modal-body'>"

var text_2 = "<div class='modal fade' id='myModal_2' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>" +
    "<div class='modal-dialog' role='document'>" +
    "<div class='modal-content'>" +
    "<div class='modal-header'>" +
    "<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
    "<h4 class='modal-title' id='myModalLabel'>种类管理</h4>" +
    "</div>" +
    "<div class='modal-body'>"

function getgameinfo(gid) {
    $.post("/game/" + gid, function (result) {
        document.getElementById("gameid").value = result.data.id;
        document.getElementById("gamename").value = result.data.name;
        document.getElementById("gameprice").value = result.data.price;
        document.getElementById("gamecreater").value = result.data.creater;
        document.getElementById("gamedesc").value = result.data.desc;
        document.getElementById("sys").value = result.data.systemcfg;
    })
}

function savegameupdate() {
    var gid = document.getElementById("gameid").value;
    var gamename = document.getElementById("gamename").value;
    var gameprice = document.getElementById("gameprice").value;
    var gamedesc = document.getElementById("gamedesc").value;
    var gamecreater = document.getElementById("gamecreater").value;
    var sys = document.getElementById("sys").value;
    $.post("/admin/updategameinfo", {
        id: gid,
        creater: gamecreater,
        name: gamename,
        desc: gamedesc,
        systemcfg: sys,
        price: gameprice
    }, function (result) {
        if (result.success) {
            alert("修改成功！");
        }
        else {
            alert(result.msg);
        }
    })
    document.getElementById("gname_" + gid).innerHTML = gamename;
    document.getElementById("gprice_" + gid).innerHTML = gameprice;
    $('#myModal').modal('hide');
}

function setImagePreviews(avalue) {
    //获取选择图片的对象
    var docObj = document.getElementById("doc");
    //后期显示图片区域的对象
    var dd = document.getElementById("dd");
    dd.innerHTML = "";
    //得到所有的图片文件
    var fileList = docObj.files;
    //循环遍历
    for (var i = 0; i < fileList.length; i++) {
        //动态添加html元素
        dd.innerHTML += "<div style='float:left' > <img id='img" + i + "'  /> </div>";
        //获取图片imgi的对象
        var imgObjPreview = document.getElementById("img" + i);

        if (docObj.files && docObj.files[i]) {
            //火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '200px';
            imgObjPreview.style.height = '180px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要以下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);   //获取上传图片文件的物理路径
        }
        else {
            //IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            //alert(imgSrc)
            var localImagId = document.getElementById("img" + i);
            //必须设置初始大小
            localImagId.style.width = "200px";
            localImagId.style.height = "180px";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            }
            catch (e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
    }
    return true;
}
function setImagePreviews_0(avalue) {
    //获取选择图片的对象
    var docObj = document.getElementById("doc_0");
    //后期显示图片区域的对象
    var dd = document.getElementById("dd_0");
    dd.innerHTML = "";
    //得到所有的图片文件
    var fileList = docObj.files;
    //循环遍历
    for (var i = 0; i < fileList.length; i++) {
        //动态添加html元素
        dd.innerHTML += "<div style='float:left' > <img id='img_" + i + "'  /> </div>";
        //获取图片imgi的对象
        var imgObjPreview = document.getElementById("img_" + i);

        if (docObj.files && docObj.files[i]) {
            //火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '200px';
            imgObjPreview.style.height = '180px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要以下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);   //获取上传图片文件的物理路径
        }
        else {
            //IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            //alert(imgSrc)
            var localImagId = document.getElementById("img_" + i);
            //必须设置初始大小
            localImagId.style.width = "200px";
            localImagId.style.height = "180px";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            }
            catch (e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
    }
    return true;
}
var xhr = new XMLHttpRequest();
function addgame() {
    var form = new FormData();
    var creater = document.getElementById("addgamecreater").value;
    var name = document.getElementById("addgamename").value;
    var desc = document.getElementById("addgamedesc").value;
    var syscfg = document.getElementById("addgamesyscfg").value;
    var price = document.getElementById("addgameprice").value;
    var discount = document.getElementById("addgamediscount").value;
    var cover = document.getElementById("doc_0").files[0];
    var i = 0;
    while (document.getElementById("doc").files[i]) {
        form.append("pics", document.getElementById("doc").files[i]);
        i++;
    }

    form.append("creater", creater);
    form.append("name", name);
    form.append("desc", desc);
    form.append("systemcfg", syscfg);
    form.append("price", price);
    form.append("discount", discount);
    form.append("kinds", '1');
    form.append("header", cover);
    xhr.open("POST", "/admin/addgame", true);
    xhr.onreadystatechange = call;
    xhr.send(form);
}
function call() {
    //判断对象的状态是否完成
    //alert(xmlh.status);
    if (xhr.readyState == 4) {
        if (xhr.status == 200) { //等于200表示成功
            //alert("成功了");
            //获取服务器端返回的数据
            //纯文本
            //var respons = xmlh.responseText;
            //var text = respons;
            var result = xhr.responseText;
            var s = result.substring(result.indexOf(':') + 1, result.indexOf(','));
            if (s == "true") {
                alert("添加成功！")
            }
            else {
                var s1 = result.substring(result.indexOf(':"') + 2, result.indexOf('",'));
                alert(s1);
            }
        }
    }
}

function cleartext() {
    var creater = document.getElementById("addgamecreater");
    var name = document.getElementById("addgamename");
    var desc = document.getElementById("addgamedesc");
    var syscfg = document.getElementById("addgamesyscfg");
    var price = document.getElementById("addgameprice");
    var discount = document.getElementById("addgamediscount");
    creater.value = "";
    name.value = "";
    desc.value = "";
    syscfg.value = "";
    price.value = "";
    discount.value = "";
}

function addkind() {
    var kind = document.getElementById("addkindinput").value;
    $.post("/admin/addkind", {kind: kind}, function (result) {
        if (result.success) {
            alert("添加成功！");
        }
        else {
            alert(result.msg);
        }
    })
}