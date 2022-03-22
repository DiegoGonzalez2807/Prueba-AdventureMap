package edu.escuelaing.arsw.model;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLongArray;

import javafx.css.PseudoClass;

public class Tablero {

    private ConcurrentHashMap<Integer,ArrayList<Personaje>> tablero;

    public static Tablero getTablero(){
        return new Tablero();
    }

    /**
     * Simula el movimiento de un personaje en el tablero
     * @param begin Punto de partida del personaje
     * @param end Punto de llegada del personaje
     * @param p Personaje a mover
     */
    public void moverPosicion(Tuple begin, Tuple end, Personaje p){
    }


    public void setTablero(){};

}
