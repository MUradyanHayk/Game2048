package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Game2048;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.managers.DataManager;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.ConstInterface;
import com.mygdx.game.utils.GameData;

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
    private DataManager dataManager;
    private int count = 0;
    private GameData data;

    public MenuStage(final Viewport viewport) {
        super(viewport);
        init();
    }

    private void init() {
        initManagers();
        initTextLabel();
        initImages();
        initButtons();
        initTable();
    }

    private void initManagers() {
        assetManager = AssetsManager.getInstance().getInternalManager();
        skin = new Skin();
        skin.addRegions(assetManager.get(ConstInterface.IMAGES_PATH + ConstInterface.ATLAS));
        dataManager = DataManager.getInstance();
        data = new GameData();
    }

    private void initTable() {
        HorizontalGroup horizontalGroup = new HorizontalGroup();
        horizontalGroup.setSize(Gdx.graphics.getWidth() * 0.65f, Gdx.graphics.getWidth() * 0.08f);
        horizontalGroup.addActor(new Container<>(arrowLeftImg).size(horizontalGroup.getHeight()*1.1f,horizontalGroup.getHeight()*1.1f));
        horizontalGroup.addActor(new Container<>(textLabel).size(Gdx.graphics.getWidth() * 0.18f));
        horizontalGroup.addActor(new Container<>(arrowRightImg).size(horizontalGroup.getHeight()*1.1f,horizontalGroup.getHeight()*1.1f));
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
        style.font = assetManager.get(ConstInterface.CONSOLAS_MIDDLE_FONT);
        textLabel = new Label(ConstInterface.N_3x3, style);
        textLabel.setAlignment(Align.center);
    }

    private void initImages() {
        painterImg = new Image(skin.getDrawable(ConstInterface.PAINTER));
        boardImg = new Image(skin.getDrawable(ConstInterface.IMAGE_3x3));
        setLevel(dataManager.getData().getLevel());

        arrowLeftImg = new Image(skin.getDrawable(ConstInterface.ARROW_LEFT));
        arrowRightImg = new Image(skin.getDrawable(ConstInterface.ARROW_RIGHT));

        arrowRightImg.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setLevel(1);
                System.out.println(textLabel.getText());
            }
        });

        arrowLeftImg.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setLevel(-1);
                System.out.println(textLabel.getText());
            }
        });
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
        playBtnStyle.font = assetManager.get(ConstInterface.CONSOLAS_MIDDLE_FONT);
        playBtn = new TextButton(PLAY, playBtnStyle);
        playBtn.getLabel().setAlignment(Align.center);
        playBtn.setSize(Gdx.graphics.getWidth() * 0.65f, Gdx.graphics.getHeight() * 0.08f);
        playBtn.addActorAt(0, playImg);
        playImg.setSize(playBtn.getWidth(), playBtn.getHeight());
        playImg.setOrigin(playImg.getWidth() * 0.5f, playImg.getHeight() * 0.5f);
        playBtn.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playImg.addAction(Actions.sequence(Actions.scaleTo(1.07f, 1.07f, 0.12f)
                        , Actions.sequence(Actions.scaleTo(1f, 1f, 0.12f))));
                Gdx.app.log("TAG", "clicked");
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Game2048.getInstance().setScreenById(GameScreen.ID);
            }
        });

        TextButton.TextButtonStyle recordsBtnStyle = new TextButton.TextButtonStyle();
        recordsBtnStyle.fontColor = Color.WHITE;
        recordsBtnStyle.font = assetManager.get(ConstInterface.CONSOLAS_SMALL_FONT);
        recordsBtn = new TextButton(LEADER_BOARD, playBtnStyle);
        recordsBtn.getLabel().setAlignment(Align.center);
        recordsBtn.setSize(Gdx.graphics.getWidth() * 0.65f, Gdx.graphics.getHeight() * 0.08f);
        recordsBtn.addActorAt(0, recordsImg);
        recordsImg.setSize(recordsBtn.getWidth(), recordsBtn.getHeight());
        recordsImg.setOrigin(recordsImg.getWidth() * 0.5f, recordsImg.getHeight() * 0.5f);
        recordsBtn.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                recordsImg.addAction(Actions.sequence(Actions.scaleTo(1.07f, 1.07f, 0.12f)
                        , Actions.sequence(Actions.scaleTo(1f, 1f, 0.12f))));
                Gdx.app.log("TAG", "clicked");
            }
        });
    }

    private void setLevel(int level) {
        count += level;
        count = count > 4 ? 0 : count;
        count = count < 0 ? 4 : count;
        switch (count) {
            case 0:
                boardImg.setDrawable(skin.getDrawable(ConstInterface.IMAGE_3x3));
                textLabel.setText(ConstInterface.N_3x3);
                break;
            case 1:
                boardImg.setDrawable(skin.getDrawable(ConstInterface.IMAGE_4x4));
                textLabel.setText(ConstInterface.N_4x4);
                break;
            case 2:
                boardImg.setDrawable(skin.getDrawable(ConstInterface.IMAGE_5x5));
                textLabel.setText(ConstInterface.N_5x5);
                break;
            case 3:
                boardImg.setDrawable(skin.getDrawable(ConstInterface.IMAGE_6x6));
                textLabel.setText(ConstInterface.N_6x6);
                break;
            case 4:
                boardImg.setDrawable(skin.getDrawable(ConstInterface.IMAGE_8x8));
                textLabel.setText(ConstInterface.N_8x8);
                break;
        }
        data.setLevel(count);
        dataManager.setData(data);
    }
}