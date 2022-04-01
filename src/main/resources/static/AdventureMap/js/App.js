    //Variables generales
    var name;
    var stompClient;
    var direction;
    var enemigo = {x:0,y:0};
    var subscribePelea;
    var count = 1;
    var h1;
    var h2;
    /**
     * Funcion generada para redireccionar desde la página inicial
     * a la página donde se encuentra el mapa. Se recibe el nombre
     * del jugador 
     * @param {String} data 
     */
    function redirect(data){
        name = data;
        console.log(name);
        window.location = "../AdventureMap/Mapa.html"
    }

    // /**
    //  * Funcion generada para escuchar cuando el usuario haya
    //  * oprimido un botón. 
    //  * @param {String} direccion 
    //  */
    //  function eventButtonListener(){
    //     window.addEventListener("click", function(){
    //         move();
    //     })
    // }

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
    function move(direction){
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
        var h = "(" + getJugadorVie().x + ","+  getJugadorVie().y + ")";
        // main();
        // mainM();
        // maint();
        stompClient.send("/App/map/mover/"+h,{},JSON.stringify(getJugador()));
        count +=1;

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
        //eventButtonListener();
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
        stompClient.send("/App/map/"+name,{},JSON.stringify(getJugador()));
    }


    /**
     * Funcion generada para conectarse a STOMP, así como 
     * poder suscribirse a los topicos que se crearon
     * como canales de escucha a eventos o peticiones
     */
    function connectAndSuscribe(){
        console.log(name);
        //SE EMPIEZA CREANDO EL STOMPCLIENT PARA MANDAR PETICIONES
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

        //SUSCRIBIRSE AL CANAL DE JUEGO
        stompClient.connect({},function(frame){
            console.log('Connected: ' + frame);
            stompClient.subscribe('/App/jugador/map', function(eventbody){
                console.log("EVENTBODY DE /JUGADOR/MAP")
                drawjugadoresPart(JSON.parse(eventbody.body));
                console.log(JSON.parse(eventbody.body));
            });
            // stompClient.subscribe("/App/pelea/", function(eventbody){
            //     console.log()
            //     var personaje = JSON.parse(eventbody.body);
            //     jugador = getJugadorVie();
            //     enemigo = personaje[1];
            //     console.log("Se forma conflicto");
            //     console.log(personaje);
            //     h1 = "(" + jugador.x + ","+  jugador.y + ")";
            //     h2 = "(" + personaje[1].x + ","+  personaje[1].y + ")";
            //     console.log(getJugador());
            //     console.log(h1);
            //     console.log(h2);
            //     if(getJugadorVie().x == personaje[0].x && getJugadorVie().y == personaje[0].y){
            //         $.get(url1+"/AdventureMap/personajes/estadisticas/"+h1,function(data){
            //             console.log("Atacante");
            //             $("#vidaP").text("vidaP: "+data.x)
            //             $("#ataqueP").text("ataqueP: "+" "+data.y)
            //         });
            //         $.get(url1+"/AdventureMap/personajes/estadisticas/"+h2,function(data){
            //             $("#vidaE").text("vidaE: "+" "+data.x)
            //             $("#ataqueE").text("ataqueE: "+" "+data.y)
            //         });
            //         // subscribePelea = stompClient.subscribe("App/pelea/"+h1+"."+h2,function(){
            //         //     actualizarEstadisticas();
            //         // });
            //     }else if(getJugadorVie().x == personaje[1].x && getJugadorVie().y == personaje[1].y){
            //         console.log("Enemigo");
            //         $.get(url1+"/AdventureMap/personajes/estadisticas/"+h2,function(data){
            //             $("#vidaP").text("vidaP: "+" "+data.x)
            //             $("#ataqueP").text("ataqueP: "+" "+data.y)
            //         });
            //         $.get(url1+"/AdventureMap/personajes/estadisticas/"+h1,function(data){
            //             $("#vidaE").text("vidaE: "+" "+data.x)
            //             $("#ataqueE").text("ataqueE: "+" "+data.y)
            //         });
            //         // subscribePelea = stompClient.subscribe("App/pelea/"+h1+"."+h2,function(){
            //         //     actualizarEstadisticas();
            //         // });
            //     }
           // });
            getElementsTablero();
      });      
    };

    function atacarJugador(){
        stompClient.send("/App/map/pelea."+h1,{},h2);
    }

    function huirJugador(){
        if(subscribePelea != null){
            subscribePelea.unsubscribe();
        }
    }

    function actualizarEstadisticas(){
        if(getJugadorVie().x == personaje[0].x && getJugadorVie().y == personaje[0].y){
            $.get(url1+"/AdventureMap/personajes/estadisticas/"+h1,function(data){
                console.log("Atacante");
                $("#vidaP").text("vidaP: "+data.x)
                $("#ataqueP").text("ataqueP: "+" "+data.y)
            });
            $.get(url1+"/AdventureMap/personajes/estadisticas/"+h2,function(data){
                $("#vidaE").text("vidaE: "+" "+data.x)
                $("#ataqueE").text("ataqueE: "+" "+data.y)
            });
        }else if(getJugadorVie().x == personaje[1].x && getJugadorVie().y == personaje[1].y){
            console.log("Enemigo");
            $.get(url1+"/AdventureMap/personajes/estadisticas/"+h2,function(data){
                $("#vidaP").text("vidaP: "+" "+data.x)
                $("#ataqueP").text("ataqueP: "+" "+data.y)
            });
            $.get(url1+"/AdventureMap/personajes/estadisticas/"+h1,function(data){
                $("#vidaE").text("vidaE: "+" "+data.x)
                $("#ataqueE").text("ataqueE: "+" "+data.y)
            });
        }
    }
