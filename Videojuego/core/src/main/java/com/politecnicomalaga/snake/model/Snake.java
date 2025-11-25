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

    //constructor
    public Snake(int x, int y, int lado, int ancho, int alto){
        //le paso la posición x de la cabeza y el lado
        //tenemos siempre al principio dos cuadrados
        int max_cuadrados = (ancho/lado)*(alto/lado);
        cuerpo = new Cuadrado[max_cuadrados]; //creación del espacio para el cuerpo
        num_cuadrados=3;
        direccion= Sentido.DER;
        cuerpo[0] = new Cuadrado(x, y, lado);
        cuerpo[1] = new Cuadrado(x-lado,y,lado);
        cuerpo[2] = new Cuadrado(x-(lado*2),y,lado);
    }
    //getters

    //setters

    //otros métodos
}
