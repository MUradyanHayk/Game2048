package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.managers.AssetsManager;

public class NumberGroup extends Group {
    private Skin skin;
    private Image numberImg;
    private Label numberLabel;
    private float size;
    private Label.LabelStyle labelStyle;

    private String text;

    public NumberGroup(float size) {
        this("", size);
    }

    public NumberGroup(String text, float size) {
        this.text = text;
        this.size = size;
        init();
        setBgColor();
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

        labelStyle = new Label.LabelStyle();
        labelStyle.font = AssetsManager.getInstance().getInternalManager().get(ConstInterface.CONSOLAS_MIDDLE_FONT);
        labelStyle.fontColor = Color.BLACK;
        numberLabel = new Label(text, labelStyle);
        numberLabel.setAlignment(Align.center);
        numberLabel.setSize(getWidth(), getHeight());
        numberLabel.setPosition(getWidth() * 0.5f - numberLabel.getWidth() * 0.5f, getHeight() * 0.5f - numberLabel.getHeight() * 0.5f);
    }

    public void addNumber() {
        addActor(numberImg);
        addActor(numberLabel);
    }

    private void setBgColor() {
        switch (text) {
            case "":
                break;
            case "2":
                numberImg.addAction(Actions.color(new Color(0.93f, 0.89f, 0.86f, 1)));
                labelStyle.fontColor = new Color(0.46f, 0.42f, 0.38f, 1);
                break;
        }
    }

    public Image getNumberImg() {
        return numberImg;
    }

    public void setText(String text) {
        this.text = text;
    }
}
