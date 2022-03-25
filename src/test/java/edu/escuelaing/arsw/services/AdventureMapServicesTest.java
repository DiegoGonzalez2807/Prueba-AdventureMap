package edu.escuelaing.arsw.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;

import edu.escuelaing.arsw.model.Jugador;
import edu.escuelaing.arsw.model.Monstruo;
import edu.escuelaing.arsw.model.Personaje;
import edu.escuelaing.arsw.model.Tablero;
import edu.escuelaing.arsw.model.Tuple;
import edu.escuelaing.arsw.persistence.AdventureMapNotFoundException;
import edu.escuelaing.arsw.persistence.AdventureMapPersistenceException;
import edu.escuelaing.arsw.services.persistence.AdventureMapServicesPersistenceException;

public class AdventureMapServicesTest {
    
    AdventureMapServices ams;
    Tablero t;
    Jugador player1,player2;

    @Before
    public void before(){
        ams = new AdventureMapServices();
        t = ams.getTablero();
        try{
            Jugador player1 = new Jugador(new Tuple(12,24), "Jugador1", t);
            Jugador player2 = new Jugador(new Tuple(15,78),"Jugador2",t);
        }catch(AdventureMapPersistenceException e){
            e.printStackTrace();
        }
    }

    // @Test
    // public void deberiaCrearMapaAleatorio(){
    //     try{
    //         ams.iniciarMapa();
    //         ConcurrentHashMap<String,Personaje> tablero = t.getTablero();
    //         assertEquals(tablero.size(), 7);//Se valida que se hayan creado 5 personajes
    //         Collection<Personaje> l = tablero.values();
    //         for(Personaje p:l){
    //             if(!p.getCoordenadas().equals(new Tuple(12,24)) || !p.getCoordenadas().equals(new Tuple(15, 78))){
    //                 assertEquals(p.getClass(), Monstruo.class);
    //             }
    //         }
    //     }catch(AdventureMapServicesPersistenceException e){
    //         fail("No deberia lanzar error");
    //     }
        
    // }

    @Test
    public void deberiaIngresarJugadores(){
        try{
            assertEquals(t.getPersonaje(new Tuple(12,24)).getCoordenadas().toString(),new Tuple(12,24).toString());
            assertEquals(t.getPersonaje(new Tuple(15,78)).getCoordenadas().toString(), new Tuple(15,78).toString());
            Jugador player3 = new Jugador(new Tuple(12,24),"Jugador3",t);

        }catch(AdventureMapPersistenceException e){
            try{
                assertNotEquals(((Jugador)t.getPersonaje(new Tuple(12,24))).getNombre(),"Jugador3");//Se evalua que no permita ingresar jugadores en la misma posicion    
            }catch(AdventureMapNotFoundException exc){
                fail("No deberia lanzar error");
            }catch(AdventureMapPersistenceException excp){
                fail("No deberia lanzar error");
            }
        }catch(AdventureMapNotFoundException ex){
            fail("No deberia lanzar errores");
        }
    }

    @Test
    public void deberiaMoverPersonaje(){
        // try{
        //     ams.moverPersonaje(player1,new Tuple(13,25));
        //     assertEquals(player1.getCoordenadas(), new Tuple(13,25));
        //     ams.moverPersonaje(player1, new Tuple(15,78));
        // }catch(AdventureMapServicesPersistenceException e){
        //     assertEquals(e.getMessage(), "No se puede mover el personaje a la posicion (15,78)");
        // }
    }

    @Test
    public void deberiaAtacarPersonaje(){
        // ams.
    }

}
