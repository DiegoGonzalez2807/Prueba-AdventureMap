package edu.escuelaing.arsw.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import org.springframework.aop.config.AdviceEntry;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
@Service
@Component
public class AdventureMapServices {

    private Tablero tablero = Tablero.getTableroJuego();
    private static final int tTablero = 390;
    private ArrayList<Tuple> monstruos;
    private ArrayList<Tuple> jugadores;


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
            // if(e.getMessage() == AdventureMapPersistenceException.ATACAR_EXCEPTION){
            //     //accionEnTerritorioNoVacio(p, destino);
            // }
            //e.printStackTrace();
            throw new AdventureMapServicesPersistenceException("No se puede mover el personaje a la posicion ("+destino.getX()+","+destino.getY()+")");
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
    public void accionEnTerritorioNoVacio(Personaje p, Tuple enemigo) throws AdventureMapServicesPersistenceException{
        System.out.println(p+ " Esta a punto de atacar a " + enemigo);
        System.out.println("Que desea hacer\n 1.Atacar\n2.Huir");
        Scanner sc = new Scanner(System.in);
        int accion = sc.nextInt();
        
        switch (accion) {
            case 1:
                System.out.println("Atacar jugador");
                break;
            case 2:
                System.out.println("Huir");
                break;
        }
        try {
            System.out.println(tablero.getPersonaje(enemigo));
        } catch (AdventureMapNotFoundException | AdventureMapPersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        atacar(p, enemigo);
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
        monstruos = new ArrayList<Tuple>();
        jugadores = new ArrayList<Tuple>();
        for(Personaje p:personajes){
            if(p instanceof Monstruo){
                monstruos.add(p.getCoordenadas());
            }else if(p instanceof Jugador){
                jugadores.add(p.getCoordenadas());
            }
        }
    }

    public ArrayList<Tuple> getMonstruos() throws AdventureMapServicesPersistenceException{
        reloadPersonajes();
        return this.monstruos;
    }

    public ArrayList<Tuple> getJugadores() throws AdventureMapServicesPersistenceException{
        reloadPersonajes();
        return this.jugadores;
    }
    
    public Personaje getPersonaje(Tuple personaje) throws AdventureMapServicesPersistenceException{
        Personaje p = null;
        try {
            p = tablero.getPersonaje(personaje);
        } catch (AdventureMapNotFoundException | AdventureMapPersistenceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return p;
    }

}
