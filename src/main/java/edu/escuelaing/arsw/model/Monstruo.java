package edu.escuelaing.arsw.model;

import java.awt.*;

import edu.escuelaing.arsw.persistence.AdventureMapPersistenceException;

public class Monstruo extends Personaje{

    private Image imagen;

    public Monstruo(Tuple coordenada, Tablero tablero) throws AdventureMapPersistenceException{
        super(coordenada, tablero);
    }

    @Override
    public String toString(){
        return "Monstruo{"+super.toString()+"}";
    }

}
