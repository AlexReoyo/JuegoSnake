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
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
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
