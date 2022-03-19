const board_border = 'black';
const board_background = "white";
const Monster_col = 'lightblue';
const monster_border = 'black';

let Monstruo = [
  {x: 400, y: 400}
]

const GameCanvas = document.getElementById("gameCanvas");
const GameCanvas_ctx = GameCanvas.getContext("2d");
main();

function main() {
    clearCanvas();
    drawMonster();
}

function clearCanvas() {
    GameCanvas_ctx.fillStyle = board_background;
    GameCanvas_ctx.strokestyle = board_border;
    GameCanvas_ctx.fillRect(0, 0, GameCanvas.width, GameCanvas.height);
    GameCanvas_ctx.strokeRect(0, 0, GameCanvas.width, GameCanvas.height);
}

function drawMonster() {
   Monstruo.forEach(drawMonsterPart)
}


function drawMonsterPart(MonsterPart) {

    GameCanvas_ctx.fillStyle = Monster_col;
    GameCanvas_ctx.strokestyle = monster_border;
    GameCanvas_ctx.fillRect(MonsterPart.x, MonsterPart.y, 10, 10);
    GameCanvas_ctx.strokeRect(MonsterPart.x, MonsterPart.y, 10, 10);
}
