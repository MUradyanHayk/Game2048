package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.managers.AssetsManager;

/**
 * @Date 01.10.2019
 * @Author HaykMuradyan
 */

public class NumberGroup extends Group {
    private Skin skin;
    private Image numberImg;
    private Label numberLabel;
    private float size;
    private Label.LabelStyle labelStyle;
    private String text;
    private boolean actionStop = true;

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
        skin.addRegions((TextureAtlas) AssetsManager.getInstance().getInternalManager().get(ConstInterface.IMAGES_PATH + ConstInterface.ATLAS));

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
        if (!text.equals("")) {
            ScaleToAction scaleToAction1 = new ScaleToAction();
            scaleToAction1.setScale(0.2f);
            ScaleToAction scaleToAction2 = new ScaleToAction();
            scaleToAction2.setScale(1);
            scaleToAction2.setDuration(0.15f);
            setOrigin(getWidth() / 2, getHeight() / 2);
            addAction(Actions.sequence(scaleToAction1, scaleToAction2));
        }
    }

    private void setBgColor() {
//        if (!text.trim().equals("")) {
//            switch (Integer.valueOf(text.trim())) {
//                case 3:
//                    AssetsManager.getInstance().getParamLarge().fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.04f);
//                    break;
//                case 4:
//                    AssetsManager.getInstance().getParamLarge().fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.08f);
//                    break;
//                case 5:
//                    AssetsManager.getInstance().getParamLarge().fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.3216f);
//                    break;
//                case 6:
//                    AssetsManager.getInstance().getParamLarge().fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.32f);
//                    break;
//                case 8:
//                    AssetsManager.getInstance().getParamLarge().fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.36f);
//                    break;
//            }
//        }
        switch (text) {
            case "":
                break;
            case "2":
                numberImg.addAction(Actions.color(new Color(0.93f, 0.89f, 0.86f, 1)));
                labelStyle.fontColor = new Color(0.46f, 0.42f, 0.38f, 1);
                break;
            case "4":
                numberImg.addAction(Actions.color(new Color(0.94f, 0.88f, 0.78f, 1)));
                labelStyle.fontColor = new Color(0.47f, 0.44f, 0.40f, 1);
                break;
            case "8":
                numberImg.addAction(Actions.color(new Color(0.94f, 0.69f, 0.48f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "16":
                numberImg.addAction(Actions.color(new Color(1f, 0.60f, 0.38f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "32":
                numberImg.addAction(Actions.color(new Color(1f, 0.53f, 0.37f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "64":
                numberImg.addAction(Actions.color(new Color(1f, 0.42f, 0.23f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "128":
                numberImg.addAction(Actions.color(new Color(0.95f, 0.8f, 0.44f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "256":
                numberImg.addAction(Actions.color(new Color(0.95f, 0.78f, 0.36f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "512":
                numberImg.addAction(Actions.color(new Color(0.95f, 0.76f, 0.31f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "1024":
                numberImg.addAction(Actions.color(new Color(0.95f, 0.75f, 0.23f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "2048":
                numberImg.addAction(Actions.color(new Color(0.96f, 0.74f, 0.16f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "4096":
                numberImg.addAction(Actions.color(new Color(0.99f, 0.45f, 0.42f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "8192":
                numberImg.addAction(Actions.color(new Color(0.98f, 0.36f, 0.35f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "16384":
                numberImg.addAction(Actions.color(new Color(0.94f, 0.32f, 0.21f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "32768":
                numberImg.addAction(Actions.color(new Color(0.42f, 0.68f, 0.85f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "65536":
                numberImg.addAction(Actions.color(new Color(0.35f, 0.61f, 0.9f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
            case "131072":
                numberImg.addAction(Actions.color(new Color(0, 0.45f, 0.77f, 1)));
                labelStyle.fontColor = new Color(1f, 0.95f, 0.91f, 1);
                break;
        }
    }

    public Image getNumberImg() {
        return numberImg;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isActionStop() {
        return actionStop;
    }

    public void setActionStop(boolean actionStop) {
        this.actionStop = actionStop;
    }
}

