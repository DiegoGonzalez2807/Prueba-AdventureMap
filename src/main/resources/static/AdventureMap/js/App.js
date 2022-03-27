

    var name = $("#playerName").val();
    var stompClient = null;

    function redirect(){
        window.location = "../AdventureMap/Mapa.html"
    }

    function callback(data){
        console.log(data);
    }
    

    function init(){
        connectAndSuscribe();
        getMonstruos(callback);


    }

    function connectAndSuscribe(){
        //SE EMPIEZA CREANDO EL STOMPCLIENT PARA MANDAR PETICIONES
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

        //SUSCRIBIRSE AL CANAL DE JUEGO
        stompClient.connect({},function(frame){
            console.log('Connected: ' + frame);
            stompClient.subscribe('/app/Map', function(eventbody){
                var obj = JSON.parse(eventbody.body);
                alert(obj)
            });
        })
    }


   /**  return {
        //REDIRECCION DESDE LA PAGINA DE INICIO HASTA LA PAGINA DONDE SE ENCUENTRA EL TABLERO
        redirect: function () {
            window.location = "Mapa.html"
        },
        init : function(){
            connectAndSuscribe();
            prueba();
        }
    };*/
