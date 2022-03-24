package edu.escuelaing.arsw.model;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLongArray;

import edu.escuelaing.arsw.persistence.AdventureMapNotFoundException;
import edu.escuelaing.arsw.persistence.AdventureMapPersistenceException;


/**
 * Clase que simula el tablero de juego, en este se guardan lo personajes ya sea monstruo o jugador de la partida.
 * Se maneja el tablero por medio de un hashMap.
 * @author CristianCastellanos ,DiegoGonzalez,EduardoOspina
 */
public class Tablero {

    //Tablero del juego
    private ConcurrentHashMap<Tuple,Personaje> tablero;

    public static Tablero getTablero(){
        return new Tablero();
    }

    public Tablero(){
        tablero = new ConcurrentHashMap<>();
    }

    /**
     * Simula el movimiento de un personaje en el tablero
     * @param begin Punto de partida del personaje
     * @param end Punto de llegada del personaje
     */
    public void moverPersonaje(Tuple begin, Tuple end) throws AdventureMapPersistenceException{
        try{
            Personaje personaje = tablero.remove(begin);
            tablero.put(end, personaje);
        }catch(Exception e){
            throw new AdventureMapPersistenceException(e.getMessage(), e.getCause());
        }

    }

    /**
     * Ingresa al tablero de juego un personaje
     * @param pos Posicion donde sera ingresado el personaje
     * @param p Personaje a ingresar
     * @return Indica si se ingreso el personaje
     * @throws AdventureMapPersistenceException
     */
    public boolean ingresarPersonaje(Tuple pos, Personaje p) throws AdventureMapPersistenceException{
        boolean isOk = false;//Informa si se inserto el personaje
        try{
            tablero.put(pos,p);
            isOk = true;
        }catch(Exception e){
            throw new AdventureMapPersistenceException(e.getMessage(), e.getCause());
        }
        return isOk;

    }

    /**
     * Retorna el personaje en la posicion indicada
     * @param pos Posicion del personaje a retornar
     * @return
     */
    public Personaje getPersonaje(Tuple pos) throws AdventureMapNotFoundException, AdventureMapPersistenceException{
        Personaje p;
        try{
            p = tablero.get(pos);
            // En caso que no se encuentre un elemento en el tablero en la posicion.
            if(p == null){
                throw new AdventureMapNotFoundException("Personaje no encontrado en la posicion:("+pos.x+","+pos.y+")");
            }
        }catch(Exception e){
            throw new AdventureMapPersistenceException(e.getMessage(), e.getCause());
        }
        return p;
    }

    /**
     * Indica si en la posicion se encuentra un Personaje
     * @param pos
     * @return
     */
    public boolean isPlayer(Tuple pos) throws AdventureMapPersistenceException{
        boolean isPlayer = false;
        try{
            Personaje p =tablero.get(pos);    
            if(p != null){
                isPlayer = true;
            }
        }catch(Exception e){
            throw new AdventureMapPersistenceException(e.getMessage(), e.getCause()); 
        }
        return isPlayer;
    }

    

}
