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

    public Tuple(String coordenada){
        String[] l = coordenada.split(",");
        this.x = Integer.parseInt(l[0].replace("(",""));
        this.y = Integer.parseInt(l[1].replace(")",""));
    }

    public Tuple(){

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

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    
}
