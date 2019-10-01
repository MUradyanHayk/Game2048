package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.screens.LoadingScreen;

public class Game2048 extends Game {
    private static Game2048 instance;

    private LoadingScreen loadingScreen;

    private Game2048() {

    }

    public void setScreenByFlag(int flag) {

    }

    @Override

    public void create() {
        loadingScreen = new LoadingScreen();
        setScreen(loadingScreen);
        Gdx.input.setInputProcessor(loadingScreen.getLoadingStage());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.85f, 0.85f, 0.75f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
    }

    public static Game2048 getInstance() {
        if (instance == null) {
            instance = new Game2048();
        }
        return instance;
    }
}
