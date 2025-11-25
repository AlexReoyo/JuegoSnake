package com.politecnicomalaga.snake.model;

public class Cuadrado {

    //atributos - almacenan el estado
    //por defecto son privados
    private int x,y; //esquina superior izquierda
    private int lado;

    //métodos - implementan el comportamiento con algoritmos
    //todos los métodos por defecto son public

    //crearse
    //Al menos un método de creación, se llaman constructores
    public Cuadrado(int x, int y, int lado){
        this.x= x; //this es el propio objeto que se está creando
        this.y= y;
        this.lado = lado;
    }

    //getter
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }
    public int getLado() {
        return lado;
    }

    //setter
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setLado(int lado) {
        this.lado = lado;
    }

    //más comportamientos
    public void mover(Snake.Sentido direccion){
        switch (direccion){
            case ARR: y-=lado;
                break;
            case ABA: y+=lado;
                break;
            case DER: x+=lado;
                break;
            case IZQ: x-=lado;
                break;
        }
    }
}
