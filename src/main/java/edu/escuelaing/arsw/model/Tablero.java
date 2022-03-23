package edu.escuelaing.arsw.model;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLongArray;


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
     * @param p Personaje a mover
     */
    public void moverPersonaje(Tuple begin, Tuple end, Personaje p){
    }

    /**
     * Ingresa al tablero de juego un personaje
     * @param pos Posicion donde sera ingresado el personaje
     * @param p Personaje a ingresar
     * @return Indica si se ingreso el personaje
     */
    public boolean ingresarPersonaje(Tuple pos, Personaje p){
        return false;
    }

    /**
     * Retorna el personaje en la posicion indicada
     * @param pos Posicion del personaje a retornar
     * @return
     */
    public Personaje getPersonaje(Tuple pos){
        return null;
    }

    /**
     * Indica si en la posicion se encuentra un Personaje
     * @param pos
     * @return
     */
    public boolean isPlayer(Tuple pos){
        return false;
    }

    

}
