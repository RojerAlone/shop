$(
    function () {
        $.post("/order/paid", function (result) {
            var i=0;
            while (result.data[i]) {
                var orderclass = document.getElementById("already");
                orderclass.class = "tab-pane fade in active";
                orderclass.innerHTML = "<div class='row rowpy'><a href='#'><tr><th>"+i+"</th><th>"+result.data[i].total+ "</th><th>"+result.data[i].ctime+"</th></tr></a></div>";
                i++;
         }
        })
    }
)