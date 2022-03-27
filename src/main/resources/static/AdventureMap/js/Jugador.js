const player_col = 'blue';
const player_border = 'black';
const board_borderr = 'black';
const board_backgroundr = "white";
const GameCanvasp = document.getElementById("gameCanvas");
const GameCanvasp_ctx = GameCanvasp.getContext("2d");



let jugadores = {x: random_player(0, GameCanvasp.width - 10), y: random_player(0, GameCanvasp.height - 10)}


function getJugador(){
    return jugadores;
}



function main() {
    drawPlayer();
}

function drawPlayer() {
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
          if (!(comprobar_bordesx(jugadores.x + dx)  )){
               jugadores = {x: jugadores.x + dx, y: jugadores.y + dy}
               clear_board();
               maint();
               mainM();
               main();
               if(comprobar_otro_monstruo(jugadores)){
                    console.info("jugador encontro monstruo derecha")
               }
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
              if(comprobar_otro_monstruo(jugadores)){
                                  console.info("jugador encontro monstruo izquierda")
                             }
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
               if(comprobar_otro_monstruo(jugadores)){
                                   console.info("jugador encontro monstruo abajo")
                              }
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
                  if(comprobar_otro_monstruo(jugadores)){
                                      console.info("jugador encontro monstruo arriba")
                                 }
          }
    }

    function comprobar_otro_monstruo(jugador){

           var monster0 = (jugador.x === Monstruo[0].x && jugador.y === Monstruo[0].y);
           var monster1 = (jugador.x === Monstruo[1].x && jugador.y === Monstruo[1].y);
           var monster2 = (jugador.x === Monstruo[2].x && jugador.y === Monstruo[2].y);
           var monster3 = (jugador.x === Monstruo[3].x && jugador.y === Monstruo[3].y);
           var monster4 = (jugador.x === Monstruo[4].x && jugador.y === Monstruo[4].y);



           return monster0 || monster1 || monster2 || monster3 || monster4;

    }

    return{
        derecha:move_monsterderecha,
        izquierda:move_monsterizquierda,
        arriba:move_monsterarriba,
        abajo:move_monsterabajo
        };

})();

