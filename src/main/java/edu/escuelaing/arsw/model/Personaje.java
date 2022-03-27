package edu.escuelaing.arsw.model;

import edu.escuelaing.arsw.persistence.AdventureMapNotFoundException;
import edu.escuelaing.arsw.persistence.AdventureMapPersistenceException;

/**
 * Clase que simula un personaje en el juego
 */
public abstract class Personaje extends Thread{

    protected Tuple coordenadas;
    protected int vida;
    protected int dano;
    protected Tablero tablero;
    public static int VIDA = 100;
    public static int DANO = 10;
    private long time = System.currentTimeMillis();

    public Personaje(Tuple coordenada, Tablero tablero) throws AdventureMapPersistenceException{
        this.coordenadas = coordenada;
        this.tablero = tablero;
        try{
            tablero.ingresarPersonaje(coordenada,this);
        }catch(AdventureMapPersistenceException e){
            throw e;
        }
        this.dano = DANO;
        this.vida = VIDA;
    }
    @Override
    public void run(){
        while(vida >0){   
            long actual = System.currentTimeMillis();
            if(actual - time > 5000){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
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
        Personaje p = tablero.getPersonaje(enemigo);
        try{
            System.out.println(this.coordenadas + " ataca a "+enemigo);
            p.sufrirAtaque(dano);
        }
        catch(AdventureMapPersistenceException e){
            throw e;
        }

    }
    //Atacar

    /**
     * Accion que simula recibir un ataque
     * @param dano Daño sufrido al personaje
     */
    public void sufrirAtaque(int dano) throws AdventureMapPersistenceException{
        this.vida -= dano;
        System.out.println("Vida Restante de "+this.coordenadas +": " +this.vida);
        if(this.vida<=0){
            throw new AdventureMapPersistenceException(AdventureMapPersistenceException.EXCEPCTION_MUERTEJUGADOR);
        }
    }

    /**
     * Accion que simula el movimiento de un personaje en el tablero de juego
     */
    public void mover(Tuple destino) throws AdventureMapPersistenceException{
        try{
            tablero.moverPersonaje(coordenadas, destino);
            this.coordenadas = destino;
        }catch(AdventureMapPersistenceException e){
            e.printStackTrace();
            throw e;
        }
    }
    //Moverse

    public Tuple getCoordenadas() {
        return coordenadas;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }





}
