package edu.escuelaing.arsw.model;

import java.awt.*;

public class Monstruo extends Personaje{

    private Image imagen;

    public Monstruo(Tuple coordenada){
        super(coordenada);
    }

    @Override
    public String toString(){
        return "Monstruo{"+super.toString()+"}";
    }

}
