package edu.escuelaing.arsw.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.aop.config.AdviceEntry;

import edu.escuelaing.arsw.model.Jugador;
import edu.escuelaing.arsw.model.Monstruo;
import edu.escuelaing.arsw.model.Personaje;
import edu.escuelaing.arsw.model.Tablero;
import edu.escuelaing.arsw.model.Tuple;
import edu.escuelaing.arsw.persistence.AdventureMapNotFoundException;
import edu.escuelaing.arsw.persistence.AdventureMapPersistenceException;
import edu.escuelaing.arsw.services.persistence.AdventureMapServicesPersistenceException;

/**
 * Clase encargada de la prestacion de los servicios necesarios para el juego.
 */
public class AdventureMapServices {

    private Tablero tablero;
    private static final int tTablero = 390;
    private ArrayList<Monstruo> monstruos;
    private ArrayList<Jugador> jugadores;


    public AdventureMapServices(){
        tablero = Tablero.getTableroJuego();
        monstruos = new ArrayList<>();
        jugadores = new ArrayList<>();
    }

    public void iniciarMapa() throws AdventureMapServicesPersistenceException{
        for(int i=0;i<5;i++){
            int x = Math.round((float)Math.random()*tTablero);
            int y = Math.round((float)Math.random()*tTablero);
            Tuple newPosicion = new Tuple(x,y);
            try{
                while(tablero.getPersonaje(newPosicion)!=null){
                    x = Math.round((float)Math.random()*tTablero);
                    y = Math.round((float)Math.random()*tTablero);
                    newPosicion = new Tuple(x, y);
                }
                    Monstruo m = new Monstruo(newPosicion, tablero);

            }catch(AdventureMapPersistenceException e){
                throw new AdventureMapServicesPersistenceException("No ha sido posible crear el monstruo en la posicion ("+x+","+y+")");
            }catch(AdventureMapNotFoundException ex){
                ex.printStackTrace();
                throw new AdventureMapServicesPersistenceException("No ha sido posible crear al monstruo en la posicion ("+x+","+y+")");
            }
        }
    }

    //MoverPersonaje
    public void moverPersonaje(Personaje p, Tuple destino) throws AdventureMapServicesPersistenceException{
        try{
            p.mover(destino);
        }catch(AdventureMapPersistenceException e){
            e.printStackTrace();
            throw new AdventureMapServicesPersistenceException("No se puede mover el personaje a la posicicion ("+destino.getX()+","+destino.getY()+")");
        }
    }

    //Atacar(Jugador)
    public void atacar(Personaje atacante,Tuple victima) throws AdventureMapServicesPersistenceException{
        try{
            atacante.atacar(victima);
        }catch(AdventureMapNotFoundException e){
            throw new AdventureMapServicesPersistenceException("El enemigo no fue encontrado",e.getCause());
        }catch(AdventureMapPersistenceException ex){
            throw new AdventureMapServicesPersistenceException(ex.getMessage(),ex.getCause());
        }
    }

    //Atacar(Monstruo)


    //AccionEnTerritorioNoVacio(ConJugadores)
    public void accionEnTerritorioNoVacio() throws AdventureMapServicesPersistenceException{
    }

    //AccionEnTerritorioVacio(SinJugadores)
    public void accionEnTerritorioVacio() throws AdventureMapServicesPersistenceException{

    }

    //Comprobar Estado Jugador

    public int comprobarEstadoPersonaje(Personaje p){
        return p.getVida();
    }

    public Tablero getTablero(){
        return this.tablero;
    }

    public void reloadPersonajes(){
        Collection<Personaje> personajes =  tablero.getTablero().values();
        monstruos = new ArrayList<Monstruo>();
        jugadores = new ArrayList<Jugador>();
        for(Personaje p:personajes){
            if(p instanceof Monstruo){
                monstruos.add((Monstruo)p);
            }else if(p instanceof Jugador){
                jugadores.add((Jugador)p);
            }
        }
    }

    public ArrayList<Monstruo> getMonstruos(){
        reloadPersonajes();
        return this.monstruos;
    }

    public ArrayList<Jugador> getJugadores(){
        reloadPersonajes();
        return this.jugadores;
    }
    

}
