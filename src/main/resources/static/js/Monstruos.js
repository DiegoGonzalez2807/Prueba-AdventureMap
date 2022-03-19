const Monster_col = 'red';
const monster_border = 'black';
let Monstruo = [
  {x: 400, y: 400}
]

const GameCanvas = document.getElementById("gameCanvas");
const GameCanvas_ctx = GameCanvas.getContext("2d");
main();



function main() {
    drawMonster();
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