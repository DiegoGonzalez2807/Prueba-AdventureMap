package edu.escuelaing.arsw.services;

import edu.escuelaing.arsw.model.Jugador;
import edu.escuelaing.arsw.model.Tuple;
import edu.escuelaing.arsw.persistence.AdventureMapNotFoundException;
import edu.escuelaing.arsw.persistence.AdventureMapPersistenceException;
import edu.escuelaing.arsw.services.persistence.AdventureMapServicesPersistenceException;

public class AdventureMapMain {
    
    public static void main(String[] args) {
        AdventureMapServices ams = new AdventureMapServices();
        // try {
        //     ams.iniciarMapa();
        //     Jugador j1 = new Jugador(new Tuple(12,78), "Jugador1", ams.getTablero());
        //     Jugador j2 = new Jugador(new Tuple(13,78),"Jugador2",ams.getTablero());
        //     Jugador j3 = new Jugador(new Tuple(14,78),"Jugador3",ams.getTablero());
            String h = "(12,56)";
            String[] l = h.split(",");
            //l[0].replace("(", "");
            System.out.println(Integer.parseInt(l[0].replace("(","")));
            System.out.println(Integer.parseInt(l[1].replace(")","")));

            
            // j1.start();
            // j2.start();
            // j3.start();
            // ams.atacar(j1, j2.getCoordenadas());
            // j1.atacar(j2.getCoordenadas());
            // j3.atacar(j2.getCoordenadas());
            // ams.moverPersonaje(j1, j2.getCoordenadas());
            // ams.moverPersonaje(j3, j2.getCoordenadas());
            // ams.moverPersonaje(j2, j3.getCoordenadas());
            // ams.moverPersonaje(j3, j1.getCoordenadas());
            // ams.atacar(j2, j3.getCoordenadas());
            // ams.atacar(j1, j3.getCoordenadas());
            // ams.atacar(j1,j2.getCoordenadas());
            // ams.atacar(j2,j1.getCoordenadas());
            // ams.atacar(j3,j1.getCoordenadas());

        // } catch (AdventureMapServicesPersistenceException e) {
        //     TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (AdventureMapPersistenceException e) {
        //     TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (AdventureMapNotFoundException e) {
        //     TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

    }
}