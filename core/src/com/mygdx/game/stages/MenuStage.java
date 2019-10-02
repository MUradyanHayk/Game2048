package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.utils.ConstInterface;

/**
 * @Date 01.10.2019
 * @Author HaykMuradyan
 */

public class MenuStage extends Stage {
    private static final String PLAY = "Play";
    private static final String LEADER_BOARD = "Leader Board";
    private Image arrowLeftImg;
    private Image arrowRightImg;
    private Image painterImg;
    private Image boardImg;
    private TextButton playBtn;
    private TextButton recordsBtn;
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
        initButtons();
        initTable();
    }

    private void initManagers() {
        assetManager = AssetsManager.getInstance().getInternalManager();
        skin = new Skin();
        skin.addRegions(assetManager.get(ConstInterface.IMAGES_PATH + ConstInterface.ATLAS));
    }

    private void initTable() {
        HorizontalGroup horizontalGroup = new HorizontalGroup();
        horizontalGroup.setSize(Gdx.graphics.getWidth() * 0.65f, Gdx.graphics.getWidth() * 0.08f);
        horizontalGroup.addActor(new Container<>(arrowLeftImg).size(horizontalGroup.getHeight()));
        horizontalGroup.addActor(new Container<>(textLabel).size(Gdx.graphics.getWidth() * 0.18f));
        horizontalGroup.addActor(new Container<>(arrowRightImg).size(horizontalGroup.getHeight()));
        horizontalGroup.space(horizontalGroup.getWidth() * 0.18f);
        horizontalGroup.center();
        table = new Table();
        table.top();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.add(painterImg).size(Gdx.graphics.getWidth() * 0.09f).right().pad(table.getWidth() * 0.025f).expandX();
        table.row();
        table.add(boardImg).size(horizontalGroup.getWidth());
        table.row();
        table.add(horizontalGroup).size(horizontalGroup.getWidth(), horizontalGroup.getHeight()).spaceTop(horizontalGroup.getHeight() * 1.5f).spaceBottom(horizontalGroup.getHeight() * 1.5f);
        table.row();
        table.add(playBtn).size(playBtn.getWidth(), playBtn.getHeight()).spaceTop(playBtn.getHeight() * 0.8f);
        table.row();
        table.add(recordsBtn).size(recordsBtn.getWidth(), recordsBtn.getHeight()).spaceTop(recordsBtn.getHeight() * 0.5f);

        addActor(table);
    }

    private void initTextLabel() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = Color.BLACK;
        style.font = assetManager.get(ConstInterface.CONSOLAS_SMALL_FONT);
        textLabel = new Label("3x3", style);
        textLabel.setAlignment(Align.center);
    }

    private void initImages() {
        painterImg = new Image(skin.getDrawable(ConstInterface.PAINTER));
        boardImg = new Image(skin.getDrawable(ConstInterface.IMAGE_3x3));

        arrowLeftImg = new Image(skin.getDrawable(ConstInterface.ARROW_LEFT));
        arrowRightImg = new Image(skin.getDrawable(ConstInterface.ARROW_RIGHT));
    }

    private void initButtons() {
        Image playImg = new Image(skin.getDrawable(ConstInterface.BTN_BG));
        playImg.setPosition(0, 0);
        playImg.addAction(Actions.color(new Color(0.96f, 0.52f, 0.38f, 1)));

        Image recordsImg = new Image(skin.getDrawable(ConstInterface.BTN_BG));
        recordsImg.setPosition(0, 0);
        recordsImg.addAction(Actions.color(new Color(0.56f, 0.48f, 0.39f, 1)));

        TextButton.TextButtonStyle playBtnStyle = new TextButton.TextButtonStyle();
        playBtnStyle.fontColor = Color.WHITE;
        playBtnStyle.font = assetManager.get(ConstInterface.CONSOLAS_SMALL_FONT);
        playBtn = new TextButton(PLAY, playBtnStyle);
        playBtn.getLabel().setAlignment(Align.center);
        playBtn.setSize(Gdx.graphics.getWidth() * 0.65f, Gdx.graphics.getHeight() * 0.08f);
        playBtn.addActorAt(0, playImg);
        playImg.setSize(playBtn.getWidth(), playBtn.getHeight());

        TextButton.TextButtonStyle recordsBtnStyle = new TextButton.TextButtonStyle();
        recordsBtnStyle.fontColor = Color.WHITE;
        recordsBtnStyle.font = assetManager.get(ConstInterface.CONSOLAS_SMALL_FONT);
        recordsBtn = new TextButton(LEADER_BOARD, playBtnStyle);
        recordsBtn.getLabel().setAlignment(Align.center);
        recordsBtn.setSize(Gdx.graphics.getWidth() * 0.65f, Gdx.graphics.getHeight() * 0.08f);
        recordsBtn.addActorAt(0, recordsImg);
        recordsImg.setSize(recordsBtn.getWidth(), recordsBtn.getHeight());
    }
}
