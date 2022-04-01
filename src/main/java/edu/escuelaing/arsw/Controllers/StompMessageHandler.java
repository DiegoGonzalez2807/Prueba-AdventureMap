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
        Personaje p = null;
        try {
            p = ams.getPersonaje(new Tuple(origen));
            ams.moverPersonaje(p, destino);
            System.out.println("Jugadores: " + ams.getJugadores());
            msgt.convertAndSend("/App/jugador/map",ams.getJugadores());
            msgt.convertAndSend("/App/monstruo/map",ams.getMonstruos());
            msgt.convertAndSend("/App/jugador/movimiento."+origen,destino);
        } catch (AdventureMapServicesPersistenceException e) {
            if(e.getMessage() == AdventureMapPersistenceException.ATACAR_EXCEPTION){
                //Tuple con las ubicaciones del personaje a mover y el personaje a atacar
                ArrayList<Tuple> ataques = new ArrayList<Tuple>();
                System.out.println("Origen: "+origen);
                System.out.println("Nuevas: "+ p.getCoordenadas()+"\n");
                ataques.add(new Tuple(origen));
                ataques.add(destino);
                System.out.println(ataques.toString());
                System.out.println("Jugadores: " + ams.getJugadores());
                msgt.convertAndSend("/App/jugador/map",ams.getJugadores());
                msgt.convertAndSend("/App/monstruo/map",ams.getMonstruos());
                msgt.convertAndSend("/App/pelea/",ataques);
                msgt.convertAndSend("/App/jugador/movimiento."+origen,new Tuple(origen));
            }else{
                e.printStackTrace();
            }
        }
    }

    @MessageMapping("/map/pelea.{propio}")
    public void handlePelear(@DestinationVariable String propio, String enemigo){
        Personaje p;
        ArrayList<Tuple> ataques = new ArrayList<Tuple>();
        try {
            System.out.println("Se entra en conflicto");
            ataques.add(new Tuple(propio));
            ataques.add(new Tuple(enemigo));
            p = ams.getPersonaje(new Tuple(propio));
            ams.atacar(p, new Tuple(enemigo));
            msgt.convertAndSend("/App/jugador/map",ams.getJugadores());
            msgt.convertAndSend("/App/monstruo/map",ams.getMonstruos());
            msgt.convertAndSend("/App/pelea/",ataques);//Envia el evento para actualizar las estadisticas
            
        } catch (AdventureMapServicesPersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }






}
