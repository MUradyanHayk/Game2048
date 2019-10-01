package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.LoadingScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.stages.MenuStage;

/**
 * @Date 01.10.2019
 *
 * @Author HaykMuradyan
 */

public class Game2048 extends Game {
    private static Game2048 instance;

    private LoadingScreen loadingScreen;
    private MenuScreen menuScreen;
    private GameScreen gameScreen;

    private Game2048() {

    }

    public void setScreenById(int id) {
        switch (id) {
            case 1:
                menuScreen = new MenuScreen();
                setScreen(menuScreen);
                Gdx.input.setInputProcessor(menuScreen.getMenuStage());
                loadingScreen.dispose();
                loadingScreen = null;

                break;
            case 2:
                gameScreen = new GameScreen();
                setScreen(gameScreen);
                Gdx.input.setInputProcessor(gameScreen.getGameStage());
                menuScreen.dispose();
                menuScreen = null;
                break;
            case 3:
                break;
        }
    }

    @Override

    public void create() {
        loadingScreen = new LoadingScreen();
        setScreen(loadingScreen);
        Gdx.input.setInputProcessor(loadingScreen.getLoadingStage());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.98f,0.97f,0.95f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        if (loadingScreen != null) {
            loadingScreen.dispose();
        }
        if (menuScreen != null) {
            menuScreen.dispose();
        }
        if (gameScreen != null) {
            gameScreen.dispose();
        }
    }

    public static Game2048 getInstance() {
        if (instance == null) {
            instance = new Game2048();
        }
        return instance;
    }
}
