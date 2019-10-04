package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.managers.DataManager;
import com.mygdx.game.utils.ConstInterface;

/**
 * @Date 01.10.2019
 * @Author HaykMuradyan
 */

public class GameStage extends Stage {
    private DataManager dataManager;
    private AssetsManager assetsManager;
    private Skin skin;
    private Label titleLabel;
    private HorizontalGroup horizontalGroup1;
    private HorizontalGroup horizontalGroup2;
    private Table table;
    private TextButton countTextBtn;
    private TextButton recordTextBtn;
    private Button restartBtn;
    private Button canceltBtn;

    public GameStage(final Viewport viewport) {
        super(viewport);
        init();
    }

    private void init() {
        initManagers();
        initLabels();
        initButtons();
        initHorizontalGroups();
        initTable();
    }

    private void initLabels() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assetsManager.getInternalManager().get(ConstInterface.CONSOLAS_MIDDLE_FONT);
        labelStyle.fontColor = Color.BLACK;
        titleLabel = new Label(ConstInterface.TITLE, labelStyle);
        titleLabel.setSize(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight() * 0.2f);
        titleLabel.setAlignment(Align.center);
    }

    private void initManagers() {
        assetsManager = AssetsManager.getInstance();
        TextureAtlas atlas = assetsManager.getInternalManager().get(ConstInterface.IMAGES_PATH + ConstInterface.ATLAS);
        skin = new Skin();
        skin.addRegions(atlas);
        dataManager = DataManager.getInstance();
    }

    private void initTable() {
        table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.top();
//        table.add(horizontalGroup1).size(horizontalGroup1.getWidth(), horizontalGroup1.getHeight()).expandX();
        table.add(titleLabel).size(titleLabel.getWidth(), titleLabel.getHeight()).left().expandX();
        table.add(countTextBtn).size(countTextBtn.getWidth(), countTextBtn.getHeight()).right();
        table.add(recordTextBtn).size(recordTextBtn.getWidth(), recordTextBtn.getHeight()).right();
        addActor(table);
        table.debugAll();
    }

    private void initHorizontalGroups() {
        horizontalGroup1 = new HorizontalGroup();
        horizontalGroup1.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.15f);
        horizontalGroup1.addActor(new Container<>(titleLabel).size(titleLabel.getWidth(), titleLabel.getHeight()).left());
        horizontalGroup1.addActor(new Container<>(countTextBtn).size(countTextBtn.getWidth(), countTextBtn.getHeight()).right());
        horizontalGroup1.addActor(new Container<>(recordTextBtn).size(recordTextBtn.getWidth(), recordTextBtn.getHeight()).right());

        horizontalGroup2 = new HorizontalGroup();


    }

    private void initButtons() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = assetsManager.getInternalManager().get(ConstInterface.CONSOLAS_SMALL_FONT);
        textButtonStyle.fontColor = Color.BLACK;

        countTextBtn = new TextButton("count", textButtonStyle);
        countTextBtn.setSize(Gdx.graphics.getWidth() * 0.15f, Gdx.graphics.getWidth() * 0.15f);
        countTextBtn.getLabel().setAlignment(Align.top);

        recordTextBtn = new TextButton("record", textButtonStyle);
        recordTextBtn.setSize(Gdx.graphics.getWidth() * 0.15f, Gdx.graphics.getWidth() * 0.15f);
        recordTextBtn.getLabel().setAlignment(Align.top);
    }
}