package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Game2048;
import com.mygdx.game.stages.GameStage;

/**
 * @Date 01.10.2019
 * @Author HaykMuradyan
 */

public class GameScreen implements Screen {
    public static final int ID = 2;
    private Viewport viewport;
    private GameStage gameStage;

    @Override
    public void show() {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameStage = new GameStage(viewport);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.input.setCatchBackKey(true);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            Game2048.getInstance().setScreenById(MenuScreen.ID);
            Gdx.input.setCatchBackKey(false);
        }
    }

    private void update(float delta) {
        gameStage.act(delta);
        gameStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameStage.dispose();
    }

    public GameStage getGameStage() {
        return gameStage;
    }
}
