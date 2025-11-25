package com.politecnicomalaga.snake.model;

public class Snake {
    public enum Sentido { //forma de definir una variable con tus propios valores y no se puedan utilizar otros
        ARR,
        ABA,
        IZQ,
        DER;
    }
    private int num_cuadrados;
    private Sentido direccion;
    private Cuadrado[] cuerpo;
    //la cabeza será inicialmente el cuadrado del cuerpo [0]
}
