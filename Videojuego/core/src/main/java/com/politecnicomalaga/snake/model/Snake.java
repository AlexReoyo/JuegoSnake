package com.politecnicomalaga.snake.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    public int getNum_cuadrados() {
        return num_cuadrados;
    }

    public Sentido getDireccion() {
        return direccion;
    }

    public Cuadrado[] getCuerpo() {
        return cuerpo;
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    //setters

    public void setNum_cuadrados(int num_cuadrados) {
        this.num_cuadrados = num_cuadrados;
    }

    public void setDireccion(Sentido direccion) {
        this.direccion = direccion;
    }

    public void setCuerpo(Cuadrado[] cuerpo) {
        this.cuerpo = cuerpo;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    //otros métodos
    public void crecer (Texture cabezaArriba, Texture cabezaAbajo, Texture cabezaDerecha, Texture cabezaIzquierda){
        Cuadrado nuevaCabeza = new Cuadrado(cuerpo[0].getX(),
            cuerpo[0].getY(),
            cuerpo[0].getLado(),
            cuerpo[0].getImagen());
        switch (direccion){
            case ABA:
                nuevaCabeza.setImagen(cabezaAbajo);
                break;
            case IZQ:
                nuevaCabeza.setImagen(cabezaIzquierda);
                break;
            case DER:
                nuevaCabeza.setImagen(cabezaDerecha);
                break;
            case ARR:
                nuevaCabeza.setImagen(cabezaArriba);
                break;
        }
        nuevaCabeza.mover(direccion);
        for (int i = num_cuadrados-1;i>=0;i--){
            cuerpo[i+1] = cuerpo[i];
        }
        cuerpo[0] = nuevaCabeza;
        cuerpo[1].setImagen(cuerpo[num_cuadrados-1].getImagen());
        num_cuadrados++;

    }


    public void pintar (SpriteBatch batch){
        for (int i = 0;i<num_cuadrados;i++){
            cuerpo[i].pintar(batch);
        }
    }


    public void mover (Texture cabezaArriba, Texture cabezaAbajo, Texture cabezaDerecha, Texture cabezaIzquierda){
        Cuadrado cabeza = cuerpo [0];
        for (int i = num_cuadrados-1;i>0;i--){
            cuerpo[i].setX(cuerpo[i-1].getX());
            cuerpo[i].setY(cuerpo[i-1].getY());
        }
        switch (direccion){
            case ABA:
                cabeza.setImagen(cabezaAbajo);
                cabeza.mover(Sentido.ABA);
                break;
            case IZQ:
                cabeza.setImagen(cabezaIzquierda);

                cabeza.mover(Sentido.IZQ);
                break;
            case DER:
                cabeza.setImagen(cabezaDerecha);

                cabeza.mover(Sentido.DER);
                break;
            case ARR:
                cabeza.setImagen(cabezaArriba);

                cabeza.mover(Sentido.ARR);
                break;
        }

        cuerpo[0]=cabeza;
    }

    public void cambiaDireccion(int x, int y){
        if (direccion==Sentido.ABA || direccion==Sentido.ARR){
            if (x<cuerpo[0].getX()){
                this.direccion=Sentido.IZQ;
            } else{
                this.direccion=Sentido.DER;
            }
        } else {
            if (y<cuerpo[0].getY()){
                this.direccion=Sentido.ABA;
            } else {
                this.direccion=Sentido.ARR;
            }
        }
    }

    public boolean estaMuerta(){
        if (colisiono()){
            return true;
        }else if (salir()){
            return true;
        } else{
            return false;
        }
    }

    public boolean colisiono(){
        if (num_cuadrados<5){
            return false;
        } else {
            for (int i = 4;i<num_cuadrados;i++){
                if (cuerpo[0].estaEncima(cuerpo[i])) {
                    return true;
                }
            }
            return  false;
        }
    }

    
}
