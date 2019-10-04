package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.managers.AssetsManager;

public class NumberGroup extends Group {
    private Skin skin;
    private Image numberImg;
    private Label numberLabel;
    private float size;

    private String text;

    public NumberGroup(float size) {
        this("", 0, 0, size);
    }

    public NumberGroup(String text, float x, float y, float size) {
        this.text = text;
        this.size = size;
        this.setPosition(x, y);
        init();
    }

    private void init() {
        skin = new Skin();
        skin.addRegions(AssetsManager.getInstance().getInternalManager().get(ConstInterface.IMAGES_PATH + ConstInterface.ATLAS));

        setSize(size, size);
        numberImg = new Image(skin.getDrawable(ConstInterface.NUMBER_BG));
        numberImg.setSize(getWidth() * 0.9f, getHeight() * 0.9f);
        numberImg.setPosition(getWidth() * 0.5f - numberImg.getWidth() * 0.5f,
                getHeight() * 0.5f - numberImg.getHeight() * 0.5f);
        numberImg.addAction(Actions.color(new Color(0.84f, 0.81f, 0.77f, 1)));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = AssetsManager.getInstance().getInternalManager().get(ConstInterface.CONSOLAS_MIDDLE_FONT);
        labelStyle.fontColor = Color.BLACK;
        numberLabel = new Label(text, labelStyle);
    }

    public void addNumber() {
        addActor(numberImg);
    }

    public Image getNumberImg() {
        return numberImg;
    }

    public void setText(String text) {
        this.text = text;
    }
}
