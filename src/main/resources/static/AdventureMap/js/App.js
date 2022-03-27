    var name;
    var stompClient;

    function redirect(data){
        name = data;
        window.location = "../AdventureMap/Mapa.html"
    }

    function callback(data){
        console.log(data);
    }
    
    function getElementsTablero(){
        getPlayerInCanva();
    }
    function init(){
        connectAndSuscribe();
    }


    function getPlayerInCanva(){
        console.log(name)
        console.log("ESTE ES EL JUGADOR"+name);
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
                console.log(JSON.parse(eventbody.body));
                drawjugadoresPart(JSON.parse(eventbody.body))
            });
            stompClient.subscribe('App/prueba/', function(eventbody){
                console.log("ESTE ES EL EVENTBODY")
                console.log(eventbody)
            });
            getElementsTablero();
      });      
    };