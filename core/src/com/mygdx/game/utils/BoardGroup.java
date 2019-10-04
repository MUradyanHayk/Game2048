package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.managers.AssetsManager;

public class BoardGroup extends Group {
    private int level;
    private Skin skin;
    private NumberGroup[][] numberGroups;

    public BoardGroup(int level) {
        this.level = level;
        init();
        this.setSize(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getWidth() * 0.9f);
        Image boardImg = new Image(skin.getDrawable(ConstInterface.WHITE_RECT));
        boardImg.setSize(this.getWidth(), this.getHeight());
        Image image = new Image(skin.getDrawable(ConstInterface.NUMBER_BG));
        image.setSize(60, 60);
        boardImg.addAction(Actions.color(new Color(0.74f, 0.67f, 0.64f, 1)));
        addActor(boardImg);
        addActor(image);
    }

    private void init() {
        initSkin();
    }

    private void initNumberGroups(){
        numberGroups = new NumberGroup[level][level];

    }

    private void initSkin() {
        skin = new Skin();
        skin.addRegions(AssetsManager.getInstance().getInternalManager().get(ConstInterface.IMAGES_PATH + ConstInterface.ATLAS));
    }
}
