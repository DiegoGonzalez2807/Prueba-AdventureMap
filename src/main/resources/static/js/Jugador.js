const player_col = 'blue';
const player_border = 'black';
const board_borderr = 'black';
const board_backgroundr = "white";



let jugadores = {x: 100, y: 100}


const GameCanvasp = document.getElementById("gameCanvas");
const GameCanvasp_ctx = GameCanvasp.getContext("2d");
main();


function main() {
    drawPlayer();
}

function drawPlayer() {
   console.log("preuba netrar");
   drawjugadoresPart(jugadores);
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

    function move_monsterderecha() {
          let dx = 10;
          let dy = 0;
          console.info("entrode")
          console.info(jugadores)
          jugadores = {x: jugadores.x + dx, y: jugadores.y + dy}
          console.info(jugadores)
          clear_board();
          maint();
          mainM();
          main();
    }

    function move_monsterizquierda() {
              let dx = -10;
              let dy = 0;
              console.info("entroiz")
              jugadores = {x: jugadores.x + dx, y: jugadores.y + dy}
              console.info(jugadores)
              clear_board();
              maint();
              mainM();
              main();

    }

    function move_monsterarriba() {
              let dx = 0;
              let dy = 10;
              console.info("entroar")
              jugadores = {x: jugadores.x + dx, y: jugadores.y + dy}
              console.info(jugadores)
              clear_board();
              maint();
              mainM();
              main();
    }

    function move_monsterabajo() {
              let dx = 0;
              let dy = -10;
              console.info("entroa")
              jugadores = {x: jugadores.x + dx, y: jugadores.y + dy}
              console.info(jugadores)
              clear_board();
              maint();
              mainM();
              main();
        }

    return{
        derecha:move_monsterderecha,
        izquierda:move_monsterizquierda,
        arriba:move_monsterarriba,
        abajo:move_monsterabajo
        };

})();

