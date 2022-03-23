package edu.escuelaing.arsw.persistence;

/**
 * Excepcion que informa que un elemento no fue encontrado en el mapa
 */
public class AdventureMapNotFoundException extends Exception{
    
    public AdventureMapNotFoundException(String message){super(message);}

    public AdventureMapNotFoundException(String message, Throwable cause){super(message, cause);}
    
}
