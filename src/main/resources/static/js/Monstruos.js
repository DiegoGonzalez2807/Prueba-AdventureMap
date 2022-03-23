const Monster_col = 'red';
const monster_border = 'black';



let Monstruo = [
  {x: 200, y: 200},
  {x: 150, y: 150},
  {x: 1, y: 1},
  {x: 390, y:390}
]
let dx = 10;
let dy = 0;

const GameCanva = document.getElementById("gameCanvas");
const GameCanva_ctx = GameCanvas.getContext("2d");
mainM();


function mainM() {
    drawMonster();
    move_monster();
}

function drawMonster() {
   Monstruo.forEach(drawMonsterPart)
}


function drawMonsterPart(MonsterPart) {

    GameCanva_ctx.fillStyle = Monster_col;
    GameCanva_ctx.strokestyle = monster_border;
    GameCanva_ctx.fillRect(MonsterPart.x, MonsterPart.y, 10, 10);
    GameCanva_ctx.strokeRect(MonsterPart.x, MonsterPart.y, 10, 10);
}

function move_monster() {
      console.info("entro")
      const head = {x: Monstruo[0].x + dx, y: Monstruo[0].y + dy}
}