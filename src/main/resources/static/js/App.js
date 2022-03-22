var app = (function () {
    /**
     * Clase coordenadas definida para que el jugador pueda moverse a través de
     * el tablero de juego. Al ser 2D solo se manejan direccion X,Y
     */
    class Coordinates{
        constructor(x,y){
            this.x = x;
            this.y = y;
        }      
    }

    var name = $("#playerName").val();
    var stompClient = null;

    var connectAndSuscribe = function(){
        //SE EMPIEZA CREANDO EL STOMPCLIENT PARA MANDAR PETICIONES
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

        //SUSCRIBIRSE AL CANAL DE JUEGO
        stompClient.connect({},function(frame){
            console.log('Connected: ' + frame);
        })
    }
    return {
        //REDIRECCION DESDE LA PAGINA DE INICIO HASTA LA PAGINA DONDE SE ENCUENTRA EL TABLERO
        redirect: function () {
            window.location = "Mapa.html"
        }
    };

})();