package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Game2048;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.stages.LoadingStage;

public class LoadingScreen implements Screen {
    public static final int ID = 1;
    private static final String LOADING = "LOADING ...  ";
    private LoadingStage loadingStage;
    private Viewport viewport;
    private AssetsManager assetsManager;
    private String loadingText;


    @Override
    public void show() {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        loadingStage = new LoadingStage(viewport);
        assetsManager = AssetsManager.getInstance();
    }

    @Override
    public void render(float delta) {
        update(delta);
        if (!assetsManager.getInternalManager().update()) {
            int progress = (int) (assetsManager.getExternalManager().getProgress() * 100);
            loadingStage.getLoadingLabel().setText(LOADING + progress + " %");
            loadingStage.getProgressBar().setValue(progress);
        } else {
            Game2048.getInstance().setScreenById(ID);
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
