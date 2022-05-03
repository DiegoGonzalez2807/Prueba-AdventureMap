    //Variables generales
    var name;
    var monstruo1;
    var jugador1;
    var stompClient;
    var direction;
    var enemigo = {x:0,y:0};
    var subscribePelea;
    var count = 1;
    var h1;
    var h2;
    var rol;
    const boton = document.querySelector("#botonAtaque");
    //const url5 = 'http://adventuremap.herokuapp.com/AdventureMap';

    /**
     * Funcion generada para redireccionar desde la página inicial
     * a la página donde se encuentra el mapa. Se recibe el nombre
     * del jugador 
     * @param {String} data 
     */
    function redirect(data){
        name = data;
        console.log(name);
        window.location = "/AdventureMap/Mapa.html"
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
                console.log("EVENTBODY DE /JUGADOR/MAP 1")
                drawjugadoresPart(JSON.parse(eventbody.body));
                console.log(JSON.parse(eventbody.body));
            });

            //SUSCRIPCION AL CANAL DE PELEA
            // ESTE CANAL ACTUALIZA LAS ESTADISTICAS DE LOS JUGADORES
             stompClient.subscribe("/App/pelea/", function(eventbody){
                 var personaje = JSON.parse(eventbody.body);
                 console.log("ESTE ES EL EVENTBODY1 " + eventbody);
                 enemigo = personaje[1];
                 document.getElementById("imagenJugador").src ="img/ATACANDO.jpg"

                 //SI SOY ATACANTE
                 if(getJugadorVie().x == personaje[0].x && getJugadorVie().y == personaje[0].y){
                    h1 = "(" + personaje[0].x + ","+  personaje[0].y + ")";
                    h2 = "(" + personaje[1].x + ","+  personaje[1].y + ")";
                 }

                 //SI SOY ENEMIGO
                 else if(getJugador().x == personaje[1].x && getJugadorVie().y == personaje[1].y){
                    h1 = "(" + personaje[1].x + ","+  personaje[1].y + ")";
                    h2 = "(" + personaje[0].x + ","+  personaje[0].y + ")";
                 }

                 //console.log("H1 ES "+h1)
                 //console.log("H2 ES "+h2)

                 stompClient.send("/App/atacando",{},h1);
                 stompClient.send("/App/atacando",{},h2);
                 console.log("H1 ES "+h1)
                 //console.log("H2 ES "+h2)
                 $.get(url2+"/AdventureMap/personajes/estadisticas/"+h1,function(data){
                     $("#vidaP").text("vidaP: "+data.x);
                     $("#ataqueP").text("ataqueP: "+" "+data.y);
                 });
                 $.get(url2+"/AdventureMap/personajes/estadisticas/"+h2,function(data){
                    $("#vidaE").text("vidaE: "+" "+data.x);
                    $("#ataqueE").text("ataqueE: "+" "+data.y);
                });
                estadoJugador(h1);
             });

             // SUSCRIPCION PELEA O HUIDA
             stompClient.subscribe("/App/atacando", function(eventbody){
                 console.log("ENTRA A EVENTBODY DE HUIDA O PELEA")
                 console.log(eventbody.body)
                $(".movement").prop('disabled', true);
             });

            // SUSCRIPCION PELEA DOS
            stompClient.subscribe("/App/atacando/masDos",function(eventbody){
                var nombreAtacante = eventbody.body;
                if(nombreAtacante == name){
                    alert("El destino esta en combate");
                }
            });

            //SUSCRIPCION MONSTRUO VS JUGADOR
            stompClient.subscribe("/App/pelea/jugaVSmons", function(eventbody){
                console.log("EVENTBODY PELEA ENTRE JUGADOR Y MONSTRUO "+eventbody.body);
                var contrincantes = JSON.parse(eventbody.body);
                monstruo1 = "(" + contrincantes[1].x + ","+  contrincantes[1].y + ")";
                jugador1 = "(" + contrincantes[0].x + ","+  contrincantes[0].y + ")";
                console.log(monstruo1)
                console.log(jugador1)
                setInterval('ataqueMonstruo()',2000);
            })
            getElementsTablero();
      });      
    };

    /**
     * Funcion generada para revisar el estado del jugador llamandolo desde el API REST.
     * En este caso el valor debe ser un booleano
     * @param {Jugador} jugador 
     */
    function estadoJugador(jugador){
        $.get(url2+"/AdventureMap/estado/"+jugador,function(data){
            console.log(data)
        });
    }

    /**
     * Funcion generada para que se envie mensaje donde el jugador recibe el ataque
     */
    function ataqueMonstruo(){
        console.log("ENTRA A ATAQUE MONSTRUO")
        stompClient.send("/App/map/pelea."+monstruo1,{},jugador1);
    }

    /**
     * Funcion generada para atacar al jugador y que se envie la solicitud de ataque al backend JAVA para que
     * los jugadores guardados peleeen y al que hayan atacado, reciba daño en sus estadisticas
     */
    function atacarJugador(){
        console.log("YO SOY EL "+h1)
        console.log("EL ENEMIGO ES "+h2)
        console.log(h1+" ATACA A "+h2)
        stompClient.send("/App/map/pelea."+h1,{},h2);
    }

    /**
     * Funcion generada para que el jugador huya de la pelea que tiene con otro jugador o monstruo
     * Lo primero que se hace es desuscribirse del topico de pelea que se genero al entrar en combate
     */
    function huirJugador(){
        $(".movement").prop('disabled', false);
        if(subscribePelea != null){
            subscribePelea.unsubscribe();
        }
    }

    /**
     * Funcion generada para actualizar las estadisticas del jugador en cuestion de daño y de vida
     * Esto se ve reflejado en la interfaz. Donde se actualiza Mapa.html con las estadísticas del jugador
     */
    function actualizarEstadisticas(){
        console.log("SE ESTA ENTRANDO A ACTUALIZAR ESTADISTICAS")
        if(getJugadorVie().x == personaje[0].x && getJugadorVie().y == personaje[0].y){
            $.get(url2+"/AdventureMap/personajes/estadisticas/"+h1,function(data){
                console.log("Atacante");
                $("#vidaP").text("vidaP: "+data.x)
                $("#ataqueP").text("ataqueP: "+" "+data.y)
            });
            $.get(url2+"/AdventureMap/personajes/estadisticas/"+h2,function(data){
                $("#vidaE").text("vidaE: "+" "+data.x)
                $("#ataqueE").text("ataqueE: "+" "+data.y)
            });
        }else if(getJugador().x == personaje[1].x && getJugador().y == personaje[1].y){
            console.log("Enemigo");
            $.get(url2+"/AdventureMap/personajes/estadisticas/"+h2,function(data){
                $("#vidaP").text("vidaP: "+" "+data.x)
                $("#ataqueP").text("ataqueP: "+" "+data.y)
            });
            $.get(url2+"/AdventureMap/personajes/estadisticas/"+h1,function(data){
                $("#vidaE").text("vidaE: "+" "+data.x)
                $("#ataqueE").text("ataqueE: "+" "+data.y)
            });
        }
        else{
            console.log("error")
        }
    }
    

    $(document).ready(
        function(){
            $(".movement").prop('disabled', false);
        }
    );
