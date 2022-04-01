package edu.escuelaing.arsw.Controllers;

import edu.escuelaing.arsw.model.Jugador;
import edu.escuelaing.arsw.model.Monstruo;
import edu.escuelaing.arsw.model.Personaje;
import edu.escuelaing.arsw.model.Tuple;
import edu.escuelaing.arsw.services.AdventureMapServices;
import edu.escuelaing.arsw.services.persistence.AdventureMapServicesPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@Service
@RestController
public class appAPIController {
    @Autowired
    AdventureMapServices services;


    @RequestMapping(value = "/AdventureMap/monstruos", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorgetMonstruos() {
        ArrayList<Tuple> monstruos = null;
        ResponseEntity<?> mensaje = null;
        monstruos = services.getMonstruos();
        mensaje = new ResponseEntity<>(monstruos, HttpStatus.ACCEPTED);
        return mensaje;
    }

        @RequestMapping(value = "/AdventureMap/jugadores", method = RequestMethod.GET)
        public ResponseEntity<?> manejadorgetJugadores() {
            ArrayList<Tuple> jugadores = null;
            ResponseEntity<?> mensaje = null;
            System.out.println("Se hace la solicitud de Jugadores");
            jugadores = services.getJugadores();
            System.out.println("Lista de jugadores: "+jugadores.toString());
            mensaje = new ResponseEntity<>(jugadores, HttpStatus.ACCEPTED);
            return mensaje;
        }

    @RequestMapping(value = "/AdventureMap/initMap", method = RequestMethod.GET)
    public ResponseEntity<?> manejadorgetMapa(){
        ResponseEntity<?> mensaje = null;
        try{
            services.iniciarMapa();
        } catch (AdventureMapServicesPersistenceException e) {
            e.printStackTrace();
            mensaje = new ResponseEntity<>("No se pudo iniciar el mapa con los monstruos", HttpStatus.NOT_FOUND);
        }
        return mensaje;
    }

    @RequestMapping(value="/AdventureMap/personajes/{personaje}", method=RequestMethod.GET)
    public ResponseEntity<?> manejadorGetJugadorTupla(@PathVariable String personaje) {
        ResponseEntity<?> mensaje = null;
            Tuple p = services.getPersonaje(new Tuple(personaje),true);
            mensaje= new ResponseEntity<>(p,HttpStatus.ACCEPTED);
        return mensaje;
    }

    @RequestMapping(value = "/AdventureMap/jugadores/{jugador}", method=RequestMethod.GET)
    public ResponseEntity<?> manejadorGetJugadorNombre(@PathVariable String jugador){
        ResponseEntity<?> mensaje = null;
        Tuple p;
        try {
            p = services.getPersonaje(jugador);
            System.out.println(services.getPersonaje(jugador));
            mensaje = new ResponseEntity<>(p,HttpStatus.ACCEPTED);
        } catch (AdventureMapServicesPersistenceException e) {
            // TODO Auto-generated catch block
            mensaje = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return mensaje;
    }

    /**
     * Metodo creado para retornar las estadisticas del personaje indicado
     * @param personaje Personaje a retornar estadisticas
     * @return Estadisticas del jugador representadas como una tupla x: vida, y:dano
     */
    @RequestMapping(value="/AdventureMap/personajes/estadisticas/{personaje}", method=RequestMethod.GET)
    public ResponseEntity<?> manejadorGetEstadisticasJugador(@PathVariable String personaje) {
        ResponseEntity<?> mensaje = null;
            Personaje p;
            try {
                
                p = services.getPersonaje(new Tuple(personaje));
                //Creacion de la tupla por la cual se representan las estadisticas del jugador
                Tuple q = new Tuple(p.getVida(),p.getDano());
                mensaje= new ResponseEntity<>(q,HttpStatus.ACCEPTED);
            } catch (AdventureMapServicesPersistenceException e) {
                // TODO Auto-generated catch block
                mensaje = new ResponseEntity<>("Personaje no encontrado", HttpStatus.NOT_FOUND);
                e.printStackTrace();
            }
        return mensaje;
    }
    
    
}
