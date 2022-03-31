package edu.escuelaing.arsw.model;

import java.awt.*;

import edu.escuelaing.arsw.persistence.AdventureMapPersistenceException;
import javafx.scene.image.Image;

public class Monstruo extends Personaje{

    private Image imagen;

    public Monstruo(Tuple coordenada, Tablero tablero) throws AdventureMapPersistenceException{
        super(coordenada, tablero);
    }

    public Monstruo(){

    }

    @Override
    public String toString(){
        return "Monstruo{"+super.toString()+"}";
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
}
