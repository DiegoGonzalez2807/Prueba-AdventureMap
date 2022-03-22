var cuadros = [];

const board_border = 'black';
const board_background = "white";


const GameCanvas = document.getElementById("gameCanvas");
const GameCanvas_ctx = GameCanvas.getContext("2d");
main();

function main() {
    clearCanvas();
    dibujaGrid();
}

function clearCanvas() {
    GameCanvas_ctx.fillStyle = board_background;
    GameCanvas_ctx.strokestyle = board_border;
    GameCanvas_ctx.fillRect(0, 0, GameCanvas.width, GameCanvas.height);
    GameCanvas_ctx.strokeRect(0, 0, GameCanvas.width, GameCanvas.height);
}

function dibujaGrid(){
    for (var x=0; x<=400; x=x+10){
      GameCanvas_ctx.moveTo(x,0);
      GameCanvas_ctx.lineTo(x,400);
    }
    for (var y=0; y<=400; y=y+10){
      GameCanvas_ctx.moveTo(0,y);
      GameCanvas_ctx.lineTo(400,y);
    }
    GameCanvas_ctx.strokeStyle = "#000";
    GameCanvas_ctx.stroke();
}

