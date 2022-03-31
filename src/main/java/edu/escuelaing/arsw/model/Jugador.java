package edu.escuelaing.arsw.model;

import edu.escuelaing.arsw.persistence.AdventureMapPersistenceException;

public class Jugador extends Personaje{

    private String nombre;
    private int bajaJugadores;
    private int bajaMonstruos;


    public Jugador(){
        super();
    }
    public Jugador(Tuple coordenada, String nombre, Tablero tablero) throws AdventureMapPersistenceException{
        super(coordenada, tablero);
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return "Jugador{Nombre: "+nombre+", "+super.toString()+"}";
    }


    /**
     * Simula la accion segun la decision del personaje
     * Si en la accion se decide pelear, se procede a entrar a la pelea
     * Si en la accion se decide huir, se procede a moverse a una casilla vacia al rededor
     * @param accion
     */ 
    public void decidir(String accion) throws AdventureMapPersistenceException{
        switch (accion){
            case "Pelear":
                //Pelear
            case "Huir":
                //Huir
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getBajaJugadores() {
        return bajaJugadores;
    }

    public int getBajaMonstruos() {
        return bajaMonstruos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBajaJugadores(int bajaJugadores) {
        this.bajaJugadores = bajaJugadores;
    }

    public void setBajaMonstruos(int bajaMonstruos) {
        this.bajaMonstruos = bajaMonstruos;
    }
    

}
