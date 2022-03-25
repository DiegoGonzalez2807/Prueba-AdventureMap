const player_col = 'blue';
const player_border = 'black';
const board_borderr = 'black';
const board_backgroundr = "white";
const GameCanvasp = document.getElementById("gameCanvas");
const GameCanvasp_ctx = GameCanvasp.getContext("2d");



let jugadores = {x: random_player(0, GameCanvasp.width - 10), y: random_player(0, GameCanvasp.height - 10)}



main();


function main() {
    drawPlayer();
}

function drawPlayer() {
   console.log("preuba netrar");
   drawjugadoresPart(jugadores);
}

function random_player(min, max)
{
   return Math.round((Math.random() * (max-min) + min) / 10) * 10;
}

function drawjugadoresPart(MonsterPart) {

    GameCanvasp_ctx.fillStyle = player_col;
    GameCanvasp_ctx.strokestyle = player_border;
    GameCanvasp_ctx.fillRect(MonsterPart.x, MonsterPart.y, 10, 10);
    GameCanvasp_ctx.strokeRect(MonsterPart.x, MonsterPart.y, 10, 10);
}

function clear_board() {
      GameCanvasp_ctx.fillStyle = board_backgroundr;
      GameCanvasp_ctx.strokestyle = board_borderr;
      GameCanvasp_ctx.fillRect(0, 0, GameCanvasp.width, GameCanvasp.height);
      GameCanvasp_ctx.strokeRect(0, 0, GameCanvasp.width, GameCanvasp.height);
}


var movimiento = (function(){

    function comprobar_bordesx(dx) {
        const hitLeftWallp = dx < 0;
        const hitRightWallp = dx > GameCanvasp.width - 10;

        return hitLeftWallp ||  hitRightWallp;
    }

    function comprobar_bordesy(dy){
        const hitToptWallp = dy < 0;
        const hitBottomWallp = dy > GameCanvasp.height -10;
        return hitToptWallp || hitBottomWallp;
    }

    function move_monsterderecha() {
          let dx = 10;
          let dy = 0;
          console.info(jugadores)
          if (!(comprobar_bordesx(jugadores.x + dx)  )){
               jugadores = {x: jugadores.x + dx, y: jugadores.y + dy}
               clear_board();
               maint();
               mainM();
               main();
          }
    }

    function move_monsterizquierda() {
          let dx = -10;
          let dy = 0;
          if (!(comprobar_bordesx(jugadores.x + dx)  )){
              jugadores = {x: jugadores.x + dx, y: jugadores.y + dy}
              clear_board();
              maint();
              mainM();
              main();
          }

    }

    function move_monsterarriba() {
          let dx = 0;
          let dy = 10;
          if(!(comprobar_bordesy(jugadores.y + dy))){
               jugadores = {x: jugadores.x + dx, y: jugadores.y + dy}
               clear_board();
               maint();
               mainM();
               main();
          }
    }

    function move_monsterabajo() {
          let dx = 0;
          let dy = -10;
          if(!(comprobar_bordesy(jugadores.y + dy))){
                  jugadores = {x: jugadores.x + dx, y: jugadores.y + dy}
                  clear_board();
                  maint();
                  mainM();
                  main();
          }
    }

    return{
        derecha:move_monsterderecha,
        izquierda:move_monsterizquierda,
        arriba:move_monsterarriba,
        abajo:move_monsterabajo
        };

})();

