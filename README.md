# Adventure-Map
Proyecto final ARSW 2022-1 Adventure Map

# Adventure-Map
Proyecto final ARSW 2022-1 Adventure Map

[Despliegue](https://adventuremap.herokuapp.com/AdventureMap/Index.html)

### Diagra de actividades  
![DiagramaActividades](https://github.com/2022-1-AdventureMap-ARSW/Adventure-Map/blob/main/Img/Diagrama%20de%20actividades.png)  


### Diagrama de Clases
![DiagramaActividades](https://github.com/2022-1-AdventureMap-ARSW/Adventure-Map/blob/main/Img/Clases.png)  

Se penso hacer el manejo de actividades a traves de distintos topicos en donde se tiene uno general para el pintado del mapa, y uno espcifico para cada pelea que se esta dando.

 ## Ingreso Jugador  
 Para el ingreso del jugador se penso en mandar un mensaje al servidor en donde este ingresara en el back al usuario y se generara un log en donde se informa del ingreso del mismo.  

 ## Movimiento personaje  

 Enviamos una señal al servidor para que por dentro haga el movimiento del personaje y valide en caso de entrar en conflicto con quien se entra en conflicto, y asi de esta manera enviar al topico de pelea la informacion de los dos para actualizar la interfaz de estadisticas de los jugadores. Para el movimiento del personaje se penso en hacer uso de un topico general en donde se avisa a todos los subscritos que actualicen el tablero, para ver los movimientos en tiempo real, cuando se dan 3 movimientos en el tablero culesquiera que sea, los monstruos se mueven.

 ## Pelear jugadores  

 Enviamos una señal al servidor en donde se indica quienes son los jugadores que estan peleando, se genera la accion de atacar y se envia una señal al topico en donde se busca actualizar las estadisticas.






[Despliegue](https://adventuremap.herokuapp.com/AdventureMap/Index.html)
