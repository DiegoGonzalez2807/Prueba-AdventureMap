package edu.escuelaing.arsw.model;

public class Jugador extends Personaje{

    private String nombre;
    private int bajaJugadores;
    private int bajaMonstruos;


    public Jugador(Tuple coordenada, String nombre) {
        super(coordenada);
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return "Jugador{Nombre: "+nombre+super.toString()+"}";
    }

    //COnsultar estado jugador

    //Decidir

    

}
