package edu.escuelaing.arsw.Controllers;


import edu.escuelaing.arsw.services.AdventureMapServices;
import edu.escuelaing.arsw.services.persistence.AdventureMapServicesPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;

import edu.escuelaing.arsw.model.Jugador;
import edu.escuelaing.arsw.model.Monstruo;
import edu.escuelaing.arsw.model.Personaje;
import edu.escuelaing.arsw.model.Tuple;
import edu.escuelaing.arsw.persistence.AdventureMapPersistenceException;
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
    @Autowired
    AdventureMapServices ams;

    ArrayList<Jugador> jugadores = new ArrayList<>();
    ArrayList<Monstruo> monstruos = new ArrayList<>();  

    @MessageMapping("/map/{nombre}")
    public void handleIngresarJugador(@DestinationVariable String nombre, Tuple coordenada){
        System.out.println("INGRESO UN NUEVO JUGADOR  "+ nombre);
        try {
            Jugador j = new Jugador(coordenada, nombre, ams.getTablero());
            System.out.println("Jugador adicionado" + j);
        } catch (AdventureMapPersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // @MessageMapping("/map/mover/monstruos")
    // public void handleMoverMonstruo(@DestinationVariable String monstruo){

    // }

    // @MessageMapping("/jugador/atacar.{j}")
    // public void handleAtacarJugador(){
    // }

    @MessageMapping("/map/mover/{origen}")
    public void handleMoverJugador(@DestinationVariable String origen, Tuple destino){
        try {
            System.out.println("Origen:    "+new Tuple(origen).toString());
            Personaje p = ams.getPersonaje(new Tuple(origen));
            ams.moverPersonaje(p, destino);
            System.out.println("Nuevas "+p.getCoordenadas()+"\n");
            System.out.println("Monstruos: " + ams.getMonstruos()+"\n");
            msgt.convertAndSend("/App/jugador/map",ams.getJugadores());
            msgt.convertAndSend("/App/monstruo/map",ams.getMonstruos());
        } catch (AdventureMapServicesPersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
