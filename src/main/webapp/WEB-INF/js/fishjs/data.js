var dataObj = function(){
    this.fruitNum = 0;
    this.double = 1;
    this.score = 0;
    this.gameOver = false;
    this.alpha = 0;
}


dataObj.prototype.draw = function(){
    var w = can1.width;
    var h = can1.height;

    ctx1.save();
    ctx1.shadowBlur = 10;
    ctx1.shadowColor = "white";
    ctx1.fillStyle = "white";
    ctx1.fillText("SCORE: "+this.score,w*0.5,h -20);

    if(this.gameOver){
        this.alpha += deltaTime * 0.0005;
        if(this.alpha > 1){
            this.alpha = 1;
        }
        ctx1.fillStyle = "rgba(255,255,255," + this.alpha +")";
        ctx1.fillText("GAMEOVER",w *0.5,h*0.5);
        ctx1.fillText("GAMEOVER",w *0.5,h*0.5);
        ctx1.drawImage(startbtn, w * 0.45,h*0.55);
        can1.onclick = function (e) {
            e=e || event;
            var x = e.clientX -can1.offsetLeft;
            var y = e.clientY - can1.offsetTop;
            if(x>=(w*0.45+380)&&x<=(w*0.45+450)&&y>=h*0.55+100&&y<=(h*0.55+300)){
                game();
            }
        }
}
    ctx1.restore();
}

dataObj.prototype.addScore = function(){
    this.score += this.fruitNum * 100 * this.double;
    this.fruitNum = 0;
    this.double = 1;
}