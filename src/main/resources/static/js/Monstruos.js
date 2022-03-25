const Monster_col = 'red';
const monster_border = 'black';
const board_borderm = 'black';
const board_backgroundm = "white";
const GameCanva = document.getElementById("gameCanvas");
const GameCanva_ctx = GameCanvas.getContext("2d");
const movement = [-10, 10]

var booleans = [];


let Monstruo = [
  {x: random_monster(0, GameCanva.width - 10), y: random_monster(0, GameCanva.height - 10)},
  {x: random_monster(0, GameCanva.width - 10), y: random_monster(0, GameCanva.height - 10)},
  {x: random_monster(0, GameCanva.width - 10), y: random_monster(0, GameCanva.height - 10)},
  {x: random_monster(0, GameCanva.width - 10), y: random_monster(0, GameCanva.height - 10)},
  {x: random_monster(0, GameCanva.width - 10), y: random_monster(0, GameCanva.height - 10)}
]


mainM();


function mainM() {
    drawMonster();
    move_monster(0);
    move_monster(1);
    move_monster(2);
    move_monster(3);
    move_monster(4);
}

function random_monster(min, max){
   return Math.round((Math.random() * (max-min) + min) / 10) * 10;
}

function random_movement(){
   return movement[Math.floor(Math.random() * movement.length)];
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

function comprobar_bordesx(dx, i) {
    const hitLeftWall = dx < 0;
    const hitRightWall = dx > GameCanva.width - 10;
    return hitLeftWall ||  hitRightWall;
}

function comprobar_bordesy(dy, i){
    const hitToptWall = dy < 0;
    const hitBottomWall = dy > GameCanva.height -10;

    return hitToptWall || hitBottomWall;
}

function comprobar_otro_monstruo(monstruo, i){

       var another_monster0 = false;
       var another_monster1 = false;
       var another_monster2 = false;
       var another_monster3 = false;
       var another_monster4 = false;

       if(i === 0){
               another_monster0 = (monstruo.x == Monstruo[0].x && monstruo.y == Monstruo[0].y);
       }
       if(i === 1){
              another_monster1 = (monstruo.x == Monstruo[1].x && monstruo.y == Monstruo[1].y);
       }
       if(i === 2){
              another_monster2 = (monstruo.x == Monstruo[2].x && monstruo.y == Monstruo[2].y);
       }
       if(i === 3){
              another_monster3 = (monstruo.x == Monstruo[3].x && monstruo.y == Monstruo[3].y);
       }
       if(i === 4){
              another_monster4 = (monstruo.x == Monstruo[4].x && monstruo.y == Monstruo[4].y);
       }


       return another_monster0 && another_monster1 && another_monster2 && another_monster3 && another_monster4;

}

function move_monster(i) {
      let dx = 0;
      let dy = 0;
      const xoy = Math.floor(Math.random() * 2)
      if (xoy == 0){
        dx = random_movement();
      }
      else{
        dy = random_movement();
      }
      if (!(comprobar_bordesx(Monstruo[i].x + dx))){
        if(!(comprobar_bordesy(Monstruo[i].y + dy))){
            if(!(comprobar_otro_monstruo({x: Monstruo[i].x + dx, y: Monstruo[i].y + dy}, i))){
                Monstruo[i] = {x: Monstruo[i].x + dx, y: Monstruo[i].y + dy};
            }
        }
      }

}