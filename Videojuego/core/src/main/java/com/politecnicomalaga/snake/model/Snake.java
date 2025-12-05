package com.politecnicomalaga.snake.model;

import com.badlogic.gdx.graphics.Texture;

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
    private int alto,ancho;

    //constructor
    public Snake(int x, int y, int lado, int ancho, int alto, Texture cabezaDerecha, Texture cuerpoHorizontal){
        //le paso la posición x de la cabeza y el lado
        //tenemos siempre al principio dos cuadrados
        int max_cuadrados = (ancho/lado)*(alto/lado);
        cuerpo = new Cuadrado[max_cuadrados]; //creación del espacio para el cuerpo
        num_cuadrados=3;
        direccion= Sentido.DER;
        cuerpo[0] = new Cuadrado(x, y, lado, cabezaDerecha);
        cuerpo[1] = new Cuadrado(x-lado,y,lado, cuerpoHorizontal);
        cuerpo[2] = new Cuadrado(x-(lado*2),y,lado, cuerpoHorizontal);
        this.alto=alto;
        this.ancho=ancho;
    }
    //getters

    //setters

    //otros métodos
}
