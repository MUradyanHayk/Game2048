package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.stages.GameStage;

public class GameScreen implements Screen {
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

    }

    public GameStage getGameStage() {
        return gameStage;
    }
}
