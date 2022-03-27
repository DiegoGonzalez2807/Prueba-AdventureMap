    var name = $("#playerName").val();
    var stompClient = null;

    function redirect(){
        window.location = "../AdventureMap/Mapa.html"
    }

    function callback(data){
        console.log(data);
    }
    

    function init(){
        console.log("CONECTANDO....")
        connectAndSuscribe();
        console.log("INSERTANDO JUGADOR EN POSICION ALEATORIA...");
    }


    function getPlayerInCanva(){
        stompClient.send("/App/map",{},JSON.stringify(getJugador()));
    }


    function connectAndSuscribe(){
        //SE EMPIEZA CREANDO EL STOMPCLIENT PARA MANDAR PETICIONES
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

        //SUSCRIBIRSE AL CANAL DE JUEGO
        stompClient.connect({},function(frame){
            console.log('Connected: ' + frame);
            stompClient.subscribe('/App/map', function(eventbody){
                console.log("ESTE ES EL EVENTBODY")
                console.log(eventbody)
                var obj = JSON.parse(eventbody.body);
                alert(obj)
            });
        })
    }

    getPlayerInCanva();

