package edu.escuelaing.arsw.model;

/**
 * Clase que simula un personaje en el juego
 */
public abstract class Personaje{

    protected Tuple coordenadas;
    protected int vida;
    protected int dano;
    public static int VIDA = 100;
    public static int DANO = 10;

    public Personaje(Tuple coordenada){
        this.coordenadas = coordenada;
    }

    @Override
    public String toString(){
        return "Coordenadas:"+this.coordenadas+" vida:"+this.vida+" dano: "+this.dano;
    }
  
    //Atacar

    //Moverse





}
