    //Variables generales
    var name;
    var stompClient;
    var direction;

    /**
     * Funcion generada para redireccionar desde la página inicial
     * a la página donde se encuentra el mapa. Se recibe el nombre
     * del jugador 
     * @param {String} data 
     */
    function redirect(data){
        name = data;
        window.location = "../AdventureMap/Mapa.html"
    }

    /**
     * Funcion generada para escuchar cuando el usuario haya
     * oprimido un botón. 
     * @param {String} direccion 
     */
     function eventButtonListener(){
        window.addEventListener("click", function(){
            move()
        })
    }

    /**
     * Funcion generada para guardar la dirección en la que va el 
     * jugador. Esto en vista que se maneja por botones su movimiento
     * @param {String} direccion 
     */
    function newDirection(direccion){
        console.log("ENTRA A NEW DIRECTION "+direccion)
        direction = direccion
    }

    /**
     * Funcion generada para generar movimiento en el jugador de acuerdo
     * a la direccion que nos de el listener de botones.
     */
    function move(){
        switch (direction){
            case "ABA": movimiento.arriba();
            break;

            case "ARR": movimiento.abajo();
            break;

            case "IZQ": movimiento.izquierda();
            break;

            case "DER": movimiento.derecha();
            break;
        }
        stompClient.send("/App/map",{},JSON.stringify(getJugador()));

    }
    
    
    /**
     * Funcion generada para crear el tablero de juego, sus monstruos,
     * jugadores entre otros elementos.
     */
    function getElementsTablero(){
        clear_board()
        maint();
        getPlayerInCanva();
    }

    /**
     * Funcion generada para iniciar el mapa. Esta función es la primera
     * en ejecutar apenas se entra en Mapa.html. Se hace las conexiones con 
     * los topicos y se pone en escucha cuando se oprima un boton
     */
    function init(){
        eventButtonListener();
        connectAndSuscribe();
    }

    /**
     * Funcion generada para insertar el jugador en el mapa de acuerdo
     * con una peticion generada en STOMP donde se manda la ubicación
     * aleatoria
     */
    function getPlayerInCanva(){
        console.log(name)
        console.log("ESTE ES EL JUGADOR"+name);
        stompClient.send("/App/map",{},JSON.stringify(getJugador()));
    }

    /**
     * Funcion generada para conectarse a STOMP, así como 
     * poder suscribirse a los topicos que se crearon
     * como canales de escucha a eventos o peticiones
     */
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
            getElementsTablero();
      });      
    };