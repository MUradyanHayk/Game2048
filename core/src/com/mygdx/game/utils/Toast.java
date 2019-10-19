package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.managers.AssetsManager;

public class Toast extends Group {
    private Label label;
    private Image bgImg;
    private static Group context;
    private static String text;
    private static float duration;

    static {

    }

    private Toast() {
        setSize(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getWidth() * 0.1f);
        setPosition(200, Gdx.graphics.getHeight() - getHeight() * 2);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = Color.WHITE;
        labelStyle.font = AssetsManager.getInstance().getInternalManager().get(ConstInterface.CONSOLAS_SMALL_FONT);
        label = new Label("", labelStyle);
        label.setAlignment(Align.center);


        Skin skin = new Skin();
        skin.addRegions(AssetsManager.getInstance().getInternalManager().get(ConstInterface.IMAGES_PATH+ConstInterface.ATLAS));
        bgImg = new Image(skin.getDrawable(ConstInterface.NUMBER_BG));
        bgImg.setSize(getWidth(), getHeight());
        bgImg.addAction(Actions.color(new Color(0.4f, 40.f, 0.4f, 1)));
    }

    public static Toast makeText(Group context, String text, float duration) {
        Toast.duration = duration;
        Toast.context = context;
        Toast.text = text;
        return new Toast();
    }

    public void show() {
        addActor(bgImg);
        addActor(label);
        label.setText(text);

        Color color = getColor();
        addAction(Actions.color(new Color(color.r, color.g, color.b, 0)));

        ColorAction colorAction1 = new ColorAction();
        colorAction1.setDuration(duration * 0.5f);
        colorAction1.setColor(color);

        ColorAction colorAction2 = new ColorAction();
        colorAction2.setDuration(duration * 0.5f);
        colorAction2.setColor(new Color(color.r, color.g, color.b, 0));

        addAction(Actions.sequence(colorAction1, colorAction2));
        context.addActor(this);
    }
}
