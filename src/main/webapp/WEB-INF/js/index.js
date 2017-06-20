$(
	function(){
		var j=1;
		$.post("/everyday",function(result){
			for(i=0;i<5;i++){
				document.getElementById("gameurl_"+i).href = "/game/" + result.data[i].id;
				document.getElementById("gameimg_"+i).src = "/img"+result.data[i].img[0];
				document.getElementById("gamename_"+i).innerText = result.data[i].name;
				//document.getElementById("gameprice_"+i).innerText = result.data[i].price + "¥";
			}
		})
	}
);
function hoverShowDiv(i){	
	//document.getElementById("divHover"+"_"+i).style.display = "block";
	document.getElementById("divHover").src="./img/static/zx_"+i+"_1.png";
	//document.getElementById("divHover"+"_"+i).style.top = document.getElementById("smimg_1").style.top + 50;
	//document.getElementById("divHover"+"_"+i).style.top = document.getElementById("smimg_1").style.left + 50;
}
function hoverHiddenDiv(i){
	//document.getElementById("divHover"+"_"+i).style.display = "none";
	//document.getElementById("divHover"+"_"+i).src="images/zx_1_1.png";
	//hoverShowDiv(1);
}
