package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.stages.LoadingStage;

public class LoadingScreen implements Screen {
    private LoadingStage loadingStage;
    private Viewport viewport;

    @Override
    public void show() {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        loadingStage = new LoadingStage(viewport);
    }

    @Override
    public void render(float delta) {
        if (AssetsManager.getInstance().getInternalManager().update()) {
            update(delta);
        }
    }

    private void update(float delta) {
        loadingStage.act(delta);
        loadingStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        loadingStage.getViewport().update(width, height);

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

    public LoadingStage getLoadingStage() {
        return loadingStage;
    }
}
