package edu.escuelaing.arsw.persistence;

/**
 * Excepcion que informa que hubo un error desconocido en el programa
 */
public class AdventureMapPersistenceException extends Exception{
    
    public AdventureMapPersistenceException(String message){super(message);}

    public AdventureMapPersistenceException(String message, Throwable cause){super(message, cause);}
}