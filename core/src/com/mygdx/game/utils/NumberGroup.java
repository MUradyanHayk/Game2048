package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.managers.AssetsManager;

public class NumberGroup extends Group {
    private Skin skin;
    private Image numberImg;

    public NumberGroup() {
        init();
    }

    private void init() {
        skin = new Skin();
        skin.addRegions(AssetsManager.getInstance().getInternalManager().get(ConstInterface.ATLAS));

        numberImg = new Image(skin.getDrawable(ConstInterface.NUMBER_BG));
        numberImg.setSize(getWidth() * 0.9f, getHeight() * 0.9f);
        numberImg.addAction(Actions.color(new Color(0.84f, 0.81f, 0.77f, 1)));
    }

    public Image getNumberImg() {
        return numberImg;
    }
}
