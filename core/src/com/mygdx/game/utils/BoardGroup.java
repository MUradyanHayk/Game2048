package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.managers.AssetsManager;

public class BoardGroup extends Group {
    private int level;
    private Skin skin;
    private Image boardImg;
    private NumberGroup[][] numberGroups_bg;

    public BoardGroup(int level) {
        this.setSize(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getWidth() * 0.9f);
        this.level = level;
        init();
    }

    private void init() {
        initSkin();
        initImages();
        initNumberGroups_bg();
    }

    private void initNumberGroups_bg() {
        numberGroups_bg = new NumberGroup[getSizeByLevel()][getSizeByLevel()];
        for (int i = 0; i < numberGroups_bg.length; i++) {
            for (int j = 0; j < numberGroups_bg[i].length; j++) {
                numberGroups_bg[i][j] = new NumberGroup(getWidth() / getSizeByLevel());
                numberGroups_bg[i][j].setPosition(i * numberGroups_bg[i][j].getWidth(), j * numberGroups_bg[i][j].getHeight());
                numberGroups_bg[i][j].addNumber();
                addActor(numberGroups_bg[i][j]);
            }
        }
    }

    private int getSizeByLevel() {
        switch (level) {
            case 0:
                return 3;
            case 1:
                return 4;
            case 2:
                return 5;
            case 3:
                return 6;
            case 4:
                return 8;
        }
        return -1;
    }

    private void initSkin() {
        skin = new Skin();
        skin.addRegions(AssetsManager.getInstance().getInternalManager().get(ConstInterface.IMAGES_PATH + ConstInterface.ATLAS));
    }

    private void initImages() {
        boardImg = new Image(skin.getDrawable(ConstInterface.WHITE_RECT));
        boardImg.setSize(this.getWidth(), this.getHeight());
        boardImg.addAction(Actions.color(new Color(0.74f, 0.67f, 0.64f, 1)));
        addActor(boardImg);

    }

    public GestureDetector getMyGestureAdapter() {
        return new GestureDetector(new MyGestureAdapter());
    }

    private class MyGestureAdapter extends GestureDetector.GestureAdapter {

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            Gdx.app.log("TAG", "pan");
            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

    }
}
