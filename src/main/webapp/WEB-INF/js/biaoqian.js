$(
    function () {
        var ul = document.getElementById("leixing");
        $.post("/kind/all",
            function (result) {
                var i = 0;
                var lli = document.createElement("lli");
                lli.innerHTML = "<li>浏览分类</li>"
                ul.appendChild(lli);
                while (result.data[i]) {
                    var li = document.createElement("li");
                    var id = result.data[i].id;
                    li.innerHTML = "<a href='/kind/" + id + "/games" + "'><li class='list-group-item'><div class='bs'>" + result.data[i].name + "</div></li></a>";
                    ul.appendChild(li);
                    i++;
                }

            })
    }
);