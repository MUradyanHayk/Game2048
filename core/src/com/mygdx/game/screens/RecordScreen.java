package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.stages.RecordStage;

public class RecordScreen implements Screen {
    private Viewport viewport;
    private RecordStage recordStage;

    @Override
    public void show() {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        recordStage = new RecordStage(viewport);
    }

    @Override
    public void render(float delta) {
        update(delta);
    }

    private void update(float delta) {
        recordStage.act(delta);
        recordStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        recordStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        recordStage.dispose();
    }
}
