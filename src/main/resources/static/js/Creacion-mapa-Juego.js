const board_border = 'black';
const board_background = "white";


const GameCanvas = document.getElementById("gameCanvas");
const GameCanvas_ctx = GameCanvas.getContext("2d");
main();

function main() {
    clearCanvas();
}

function clearCanvas() {
    GameCanvas_ctx.fillStyle = board_background;
    GameCanvas_ctx.strokestyle = board_border;
    GameCanvas_ctx.fillRect(0, 0, GameCanvas.width, GameCanvas.height);
    GameCanvas_ctx.strokeRect(0, 0, GameCanvas.width, GameCanvas.height);
}

