package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.utils.ConstInterface;

/**
 * @Date 01.10.2019
 * @Author HaykMuradyan
 */

public class MenuStage extends Stage {
    private Image arrowLeftImg;
    private Image arrowRightImg;
    private Image painterImg;
    private Image boardImg;
    private Label textLabel;
    private Skin skin;
    private Table table;
    private AssetManager assetManager;

    public MenuStage(final Viewport viewport) {
        super(viewport);
        init();
    }

    private void init() {
        initManagers();
        initImages();
        initTextLabel();
        initTable();
    }

    private void initManagers() {
        assetManager = AssetsManager.getInstance().getInternalManager();
        skin = new Skin();
        skin.addRegions(assetManager.get(ConstInterface.IMAGES_PATH + ConstInterface.ATLAS));
    }

    private void initTable() {
        table = new Table();
        table.top();
        table.setPosition(0, 0);
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(painterImg).size(Gdx.graphics.getWidth() * 0.1f).right().pad(Gdx.graphics.getWidth() * 0.02f);
        table.row();
        table.add(boardImg).size(Gdx.graphics.getWidth() * 0.65f).top();
        table.row();
        table.add(arrowLeftImg).size(Gdx.graphics.getWidth() * 0.05f);
        table.add(textLabel).size(Gdx.graphics.getWidth()*0.05f);
        table.add(arrowRightImg).size(Gdx.graphics.getWidth() * 0.05f);
        table.row();
        addActor(table);
        table.debugAll();
    }

    private void initTextLabel() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = Color.BLACK;
        style.font = assetManager.get(ConstInterface.CONSOLAS_SMALL_FONT);
        textLabel = new Label("3x3", style);
    }

    private void initImages() {
        painterImg = new Image(skin.getDrawable(ConstInterface.PAINTER));
        boardImg = new Image(skin.getDrawable(ConstInterface.IMAGE_3x3));

        arrowLeftImg = new Image(skin.getDrawable(ConstInterface.ARROW_LEFT));
        arrowRightImg = new Image(skin.getDrawable(ConstInterface.ARROW_RIGHT));
    }

}
