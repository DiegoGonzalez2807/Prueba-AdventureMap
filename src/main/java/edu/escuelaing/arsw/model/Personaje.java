package edu.escuelaing.arsw.model;

import edu.escuelaing.arsw.persistence.AdventureMapNotFoundException;
import edu.escuelaing.arsw.persistence.AdventureMapPersistenceException;

/**
 * Clase que simula un personaje en el juego
 */
public abstract class Personaje{

    protected Tuple coordenadas;
    protected int vida;
    protected int dano;
    protected Tablero tablero;
    public static int VIDA = 100;
    public static int DANO = 10;

    public Personaje(Tuple coordenada, Tablero tablero){
        this.coordenadas = coordenada;
        this.tablero = tablero;
    }

    @Override
    public String toString(){
        return "Coordenadas:"+this.coordenadas+" vida:"+this.vida+" dano: "+this.dano;
    }

    /**
    * Retorna lavida del personaje
    */
    public int consultarEstado(){
        return this.vida;
    }
  
    /**
     * Accion que simula el ataque de un personaje a otro
     * @param enemigo Posicion en el mapa del enemigo a atacar.
     */
    public void atacar(Tuple enemigo) throws AdventureMapNotFoundException,AdventureMapPersistenceException{

    }
    //Atacar

    /**
     * Accion que simula el movimiento de un personaje en el tablero de juego
     */
    public void mover(Tuple destino) throws AdventureMapPersistenceException{

    }
    //Moverse





}
