package com.politecnicomalaga.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.politecnicomalaga.snake.model.Snake;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture cabezaDerecha;
    private Texture cabezaArriba;
    private Texture cabezaAbajo;
    private Texture cabezaIzquierda;
    private Texture cuerpoHorizontal;
    private int contadorFrames;
    private int contadorVeces;
    private Snake serpiente;
    private int anchoPantalla;
    private int altoPantalla;
    private boolean jugando;
    private Texture gameOver;


    @Override
    public void create() {
        batch = new SpriteBatch();
        cabezaDerecha = new Texture("cabezaPro_Derecha.png");
        cabezaIzquierda = new Texture("cabezaPro_Izquierda.png");
        cabezaAbajo = new Texture("cabezaPro_Abajo.png");
        cabezaArriba = new Texture("cabezaPro_Arriba.png");

        cuerpoHorizontal = new Texture("cuerpoPro_horizontal.png");

        anchoPantalla= Gdx.graphics.getWidth();
        altoPantalla = Gdx.graphics.getHeight();
        jugando=true;
        serpiente = new Snake(300 , 300, 40, anchoPantalla,altoPantalla,cabezaDerecha,cuerpoHorizontal);
        gameOver= new Texture("gameover.png");
    }

    @Override
    public void render() {
        //control estado
        //pullig de la pantalla
        if (Gdx.input.justTouched()){
            if (jugando) {
                int x = Gdx.input.getX();
                int y = Gdx.graphics.getHeight() - Gdx.input.getY();
                serpiente.cambiaDireccion(x, y);
            } else {
                serpiente = new Snake(300 , 300, 40, anchoPantalla,altoPantalla,cabezaDerecha,cuerpoHorizontal);
                jugando=true;
            }
        }

        //hemos muerto?
        //muerte por colision o por slair de los límites
        if (serpiente.estaMuerta()){
            jugando =false;
        }

        //actualización mundo virtual
        if (jugando) {
            contadorFrames++;
            if (contadorFrames == 30) {
                contadorVeces++;
                contadorFrames = 0;
                if (contadorVeces == 3) {
                    contadorVeces = 0;
                    serpiente.crecer(cabezaArriba,cabezaAbajo,cabezaDerecha,cabezaIzquierda);
                } else {
                    serpiente.mover(cabezaArriba,cabezaAbajo,cabezaDerecha,cabezaIzquierda);
                }
            }
        }

        //pintar
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        if (jugando) {
            serpiente.pintar(batch);
        } else {
            batch.draw(gameOver,(anchoPantalla/2)/2, (altoPantalla/2)/2);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        cabezaIzquierda.dispose();
        cabezaDerecha.dispose();
        cabezaAbajo.dispose();
        cabezaArriba.dispose();
        cuerpoHorizontal.dispose();
        gameOver.dispose();
    }
}
