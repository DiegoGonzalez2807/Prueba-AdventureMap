const Monster_col = 'red';
const monster_border = 'black';
const board_borderm = 'black';
const board_backgroundm = "white";



let Monstruo = [
  {x: 200, y: 200},
  {x: 150, y: 150},
  {x: 1, y: 1},
  {x: 390, y:370},
  {x: 40, y:170}
]

const GameCanva = document.getElementById("gameCanvas");
const GameCanva_ctx = GameCanvas.getContext("2d");
mainM();


function mainM() {
    drawMonster();
    move_monster1();
    move_monster2();
    move_monster3();
    move_monster4();
    move_monster5();
}

function drawMonster() {
   Monstruo.forEach(drawMonsterPart)
}

function clear_boardm() {
      GameCanva_ctx.fillStyle = board_backgroundm;
      GameCanva_ctx.strokestyle = board_borderm;
      GameCanva_ctx.fillRect(0, 0, GameCanva.width, GameCanva.height);
      GameCanva_ctx.strokeRect(0, 0, GameCanva.width, GameCanva.height);
}


function drawMonsterPart(MonsterPart) {

    GameCanva_ctx.fillStyle = Monster_col;
    GameCanva_ctx.strokestyle = monster_border;
    GameCanva_ctx.fillRect(MonsterPart.x, MonsterPart.y, 10, 10);
    GameCanva_ctx.strokeRect(MonsterPart.x, MonsterPart.y, 10, 10);
}

function comprobar_bordesx(dx) {
    const hitLeftWall = dx < 0;
    const hitRightWall = dx > GameCanva.width - 10;

    return hitLeftWall ||  hitRightWall;
    }

function comprobar_bordesy(dy){
    const hitToptWall = dy < 0;
    const hitBottomWall = dy > GameCanva.height -10;
    return hitToptWall || hitBottomWall;
    }

function move_monster1() {
      let dx = 10;
      let dy = 0;
      if (!(comprobar_bordesx(Monstruo[0].x + dx)  )){
        if(!(comprobar_bordesy(Monstruo[0].y + dy))){
            Monstruo[0] = {x: Monstruo[0].x + dx, y: Monstruo[0].y + dy}
        }
      }
}

function move_monster2() {
      let dx = -10;
      let dy = 0;
      if (!(comprobar_bordesx(Monstruo[1].x + dx)  )){
              if(!(comprobar_bordesy(Monstruo[1].y + dy))){
                Monstruo[1] = {x: Monstruo[1].x + dx, y: Monstruo[1].y + dy}
              }
      }
}

function move_monster3() {
      let dx = 0;
      let dy = -10;
      if (!(comprobar_bordesx(Monstruo[2].x + dx)  )){
              if(!(comprobar_bordesy(Monstruo[2].y + dy))){
                    Monstruo[2] = {x: Monstruo[2].x + dx, y: Monstruo[2].y + dy}
              }
      }
}

function move_monster4() {
      let dx = -10;
      let dy = 0;
      if (!(comprobar_bordesx(Monstruo[3].x + dx)  )){
              if(!(comprobar_bordesy(Monstruo[3].y + dy))){
                    Monstruo[3] = {x: Monstruo[3].x + dx, y: Monstruo[3].y + dy}
              }
      }
}

function move_monster5() {
      let dx = 0;
      let dy = 10;
      if (!(comprobar_bordesx(Monstruo[4].x + dx)  )){
              if(!(comprobar_bordesy(Monstruo[4].y + dy))){
                    Monstruo[4]  = {x: Monstruo[4].x + dx, y: Monstruo[4].y + dy}
              }
      }
}