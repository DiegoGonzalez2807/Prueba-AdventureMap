package edu.escuelaing.arsw.services;

import org.springframework.aop.config.AdviceEntry;

import edu.escuelaing.arsw.model.Monstruo;
import edu.escuelaing.arsw.model.Personaje;
import edu.escuelaing.arsw.model.Tablero;
import edu.escuelaing.arsw.model.Tuple;
import edu.escuelaing.arsw.persistence.AdventureMapPersistenceException;
import edu.escuelaing.arsw.services.persistence.AdventureMapServicesPersistenceException;

/**
 * Clase encargada de la prestacion de los servicios necesarios para el juego.
 */
public class AdventureMapServices {

    private Tablero tablero;

    //IniciarMapa 

    public AdventureMapServices(){
        tablero = Tablero.getTableroJuego();
    }

    public void iniciarMapa() throws AdventureMapServicesPersistenceException{
        for(int i=0;i<5;i++){
            int x = Math.round((float)Math.random()*5);
            int y = Math.round((float)Math.random()*5);
            try{
                Monstruo m = new Monstruo(new Tuple(x,y), tablero);
            }catch(AdventureMapPersistenceException e){
                throw new AdventureMapServicesPersistenceException("No ha sido posible crear el monstruo en la posicion ("+x+","+y+")");
            }
        }
    }

    //MoverPersonaje
    public void moverPersonaje(Personaje p, Tuple destino) throws AdventureMapServicesPersistenceException{
    }

    //Atacar(Jugador)
    public void atacar() throws AdventureMapServicesPersistenceException{}

    //Atacar(Monstruo)


    //AccionEnTerritorioNoVacio(ConJugadores)
    public void accionEnTerritorioNoVacio() throws AdventureMapServicesPersistenceException{
    }

    //AccionEnTerritorioVacio(SinJugadores)
    public void accionEnTerritorioVacio() throws AdventureMapServicesPersistenceException{

    }

    //Comprobar Estado Jugador

    public int comprobarEstadoPersonaje(Personaje p) throws AdventureMapServicesPersistenceException {
        return 0;
    }

    public Tablero getTablero(){
        return this.tablero;
    }
    

}
