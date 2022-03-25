package edu.escuelaing.arsw.services.persistence;

/**
 * Excepcion que informa que hubo un error desconocido en el programa
 */
public class AdventureMapServicesPersistenceException extends Exception{

    public static final String EXCEPCTION_MUERTEJUGADOR = "Ha muerto";
    
    public AdventureMapServicesPersistenceException(String message){super(message);}

    public AdventureMapServicesPersistenceException(String message, Throwable cause){super(message, cause);}
}