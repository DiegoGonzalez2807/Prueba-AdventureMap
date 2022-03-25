package edu.escuelaing.arsw.model;

/**
 * Clase que simula una tupla conformado por una coordenada x e y
 */
public class Tuple {

    private int x;
    private int y;

    public Tuple(int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString(){
        return "Tuple{x: "+x+",y: "+y+"}";
    }
    
    @Override
    public boolean equals(Object obj){
        return this.equals((Tuple)obj);
    }

    public boolean equals(Tuple other){
        boolean isEquals  =false;
        if(other.getX() == this.x && other.getY()==this.y){
            isEquals = true;
        }
        return isEquals;
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}
    
}
