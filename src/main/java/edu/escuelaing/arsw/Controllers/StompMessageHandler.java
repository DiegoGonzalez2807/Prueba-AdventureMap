package edu.escuelaing.arsw.Controllers;


import edu.escuelaing.arsw.services.AdventureMapServices;
import edu.escuelaing.arsw.services.persistence.AdventureMapServicesPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;

import edu.escuelaing.arsw.model.Jugador;
import edu.escuelaing.arsw.model.Monstruo;
import edu.escuelaing.arsw.model.Tuple;
import edu.escuelaing.arsw.services.AdventureMapServices;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class StompMessageHandler {

    @Autowired

    SimpMessagingTemplate msgt;
    AdventureMapServices ams = new AdventureMapServices();

    ArrayList<Jugador> jugadores = new ArrayList<>();
    ArrayList<Monstruo> monstruos = new ArrayList<>();   

    @MessageMapping("/jugador/mover.{j}")
    public void handleMoveEventJugador(@DestinationVariable Jugador j, Tuple destino) throws Exception{
        System.out.println("Se recibio al jugador "+j.getName());
        ams.moverPersonaje(j, destino);
        //Despues de mover al jugador devolvemos el jugador con su nueva posicion
        msgt.convertAndSend("topic/jugador."+j.getName()+"/mover",j);


    }

    @MessageMapping("/jugador/atacar.{j}")
    public void handleAtacarJugador(){
    }

    @MessageMapping("/map")
    public void handleRetornarJugador(){
        System.out.println("SE ENTRA AL MESSAGEMAPPING DE RETORNAR JUGADOR");
    }


}
