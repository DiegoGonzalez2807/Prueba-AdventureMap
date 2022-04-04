# Adventure-Map
Proyecto final ARSW 2022-1 Adventure Map

# Adventure-Map
Proyecto final ARSW 2022-1 Adventure Map

[Despliegue](https://adventuremap.herokuapp.com/AdventureMap/Index.html)

### Diagrama de actividades  
![DiagramaActividades](https://github.com/2022-1-AdventureMap-ARSW/Adventure-Map/blob/main/Img/Diagrama%20de%20actividades.png)  

Se penso hacer el manejo de actividades a traves de distintos topicos en donde se tiene uno general para el pintado del mapa, y uno especifico para cada pelea que se esta dando. el manejo del mapa se realiza desde el backend, como un concurrent hashmap, que permite un manejo de hilos de manera segura, y a partir de este manejamos el desarrollo del front-end.

 ## Ingreso Jugador  
 Para el ingreso del jugador se penso en mandar un mensaje al servidor en donde este ingresara en el back al usuario y se generara un log en donde se informa del ingreso del mismo.  

 ## Movimiento personaje  

 Enviamos una señal al servidor para que por dentro haga el movimiento del personaje y valide en caso de entrar en conflicto con quien se entra en conflicto, y asi de esta manera enviar al topico de pelea la informacion de los dos para actualizar la interfaz de estadisticas de los jugadores. Para el movimiento del personaje se penso en hacer uso de un topico general en donde se avisa a todos los subscritos que actualicen el tablero, para ver los movimientos en tiempo real, cuando se dan 3 movimientos en el tablero culesquiera que sea, los monstruos se mueven.

 ## Pelear jugadores  

 Enviamos una señal al servidor en donde se indica quienes son los jugadores que estan peleando, se genera la accion de atacar y se envia una señal al topico en donde se busca actualizar las estadisticas.



### Diagrama de Clases
![DiagramaActividades](https://github.com/2022-1-AdventureMap-ARSW/Adventure-Map/blob/main/Img/Clases.png)  
Para estos casos se generan 5 entidades que ayudarán a definir el funcionamiento en cuestión de backend del tablero y aquellos que estén dentro de el.
 - Tablero 
 - Monstruo
 - Personaje
 - Tuple
 - Jugador

Vamos a definir un servicio general llamado "AdventureMapServices". Este se encarga de conectar todas las funciones definidas en las entidades tales como "MoverJugador" o "GetPersonaje". Esto con el fin de conectar este con los otros servicios que se usan para la conexión hacia el frontend tales como STOMP y REST.

Se definen dos clases que son las que hacen la conexión directa de peticiones del frontend hacia el backend.
 - StompMessageHandler -> Esta función se genera de acuerdo con las funcionalidades de STOMP. Esto debido a que en el frontend se están usando tópicos para funcionalidades tales como "Mover el jugador", "Estadisticas de los personajes en cuestión de daño y vida", entre otras funcionalidades que se pueden hacer. Lo que hace esta función es que cada que se le envíe al tópico específico un SEND, este proceda a hacer las instrucciones que se le dicen en la función del backend para ese tópico específico.

 - appAPIController -> Esta función se genera de acuerdo con las funcionalidades de REST. Esta función ayuda a meter los jugadores dentro de backend así como los monstruos. Esto nos sirve para tener persistencia en los datos que se encuentran en el frontend con los datos del backend. Funciones de ejemplo tales como las de "Traer Personajes" y "Traer Monstruos"

Se definen tres clases de excepción para el proyecto:
 - AdventureMapPersistenceException
 - AdventureMapNotFoundException
 - AdventureMapServicesPersistenceException.

UN ejemplo de la importancia de las excepciones es que la excepción "ATACAR_EXCCEPTION" sirve para ver si un jugador tiene que entrar a combate de acuerdo a su posición, ya sea contra un monstruo o contra un jugador



### Diagrama de Casos de Uso
![DiagramaActividades](https://github.com/2022-1-AdventureMap-ARSW/Adventure-Map/blob/main/Img/casosUso.png)

1. Como usuario
Quiero decidir el punto de acción
Para poder revisar si se pelea o se huye de algún monstruo o usuario

2. Como usuario
Quiero crear personaje
Para poder entrar a la interfaz de juego

3. Como usuario
Quiero acceder a la interfaz de juego
Para poder interactuar con los demás usuarios y monstruos

4. Como usuario
Quiero moverme en el mapa
Para poder graficar en tiempo real mi nueva posición


### Diagrama de Despliegue
![DiagramaActividades](https://github.com/2022-1-AdventureMap-ARSW/Adventure-Map/blob/main/Img/Deployment.png)


### Diagrama de Componentes
![DiagramaActividades](https://github.com/2022-1-AdventureMap-ARSW/Adventure-Map/blob/main/Img/Components.png)


### Diagramas de Secuencia 
![DiagramaActividades](https://github.com/2022-1-AdventureMap-ARSW/Adventure-Map/blob/main/Img/DieagramaSecuenciaCrear.png)








[Despliegue](https://adventuremap.herokuapp.com/AdventureMap/Index.html)
