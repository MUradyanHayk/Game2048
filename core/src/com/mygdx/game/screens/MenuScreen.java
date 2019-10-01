package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.stages.MenuStage;

/**
 * @Date 01.10.2019
 *
 * @Author HaykMuradyan
 */

public class MenuScreen implements Screen {
    private MenuStage menuStage;
    private Viewport viewport;

    @Override
    public void show() {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        menuStage = new MenuStage(viewport);
    }

    @Override
    public void render(float delta) {
        update(delta);
    }

    private void update(float delta) {
        menuStage.act(delta);
        menuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        menuStage.getViewport().update(width, height);

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

    public MenuStage getMenuStage() {
        return menuStage;
    }
}
