package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.managers.DataManager;
import com.mygdx.game.utils.BoardGroup;
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
    private Table table;
    private TextButton countTextBtn;
    private TextButton recordTextBtn;
    private Button restartBtn;
    private Button cancelBan;
    private BoardGroup boardGroup;

    public GameStage(final Viewport viewport) {
        super(viewport);
        init();
    }

    private void init() {
        initManagers();
        initLabels();
        initButtons();
        initBoardGroup();
        initTable();
    }

    private void initBoardGroup() {
        boardGroup = new BoardGroup();
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
        table.add(titleLabel).size(titleLabel.getWidth(), titleLabel.getHeight()).expandX().left();
        table.add(countTextBtn).size(countTextBtn.getWidth(), countTextBtn.getHeight()).right();
        table.add(recordTextBtn).size(recordTextBtn.getWidth(), recordTextBtn.getHeight()).right().padLeft(recordTextBtn.getWidth() * 0.2f).padRight(recordTextBtn.getWidth() * 0.2f);
        table.row().right();
        table.add().left();
        table.add(cancelBan).size(cancelBan.getWidth(), cancelBan.getHeight()).right();
        table.add(restartBtn).size(restartBtn.getWidth(), restartBtn.getHeight()).right().padLeft(recordTextBtn.getWidth() * 0.2f).padRight(recordTextBtn.getWidth() * 0.2f);
        table.row();
        table.add(boardGroup).size(boardGroup.getWidth(), boardGroup.getHeight()).colspan(3).spaceTop(boardGroup.getWidth() * 0.02f);
        addActor(table);
        //table.debugAll();
    }

    private void initButtons() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = assetsManager.getInternalManager().get(ConstInterface.CONSOLAS_SMALL_FONT);
        textButtonStyle.fontColor = Color.BLACK;

        countTextBtn = new TextButton("score", textButtonStyle);
        countTextBtn.setSize(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getWidth() * 0.2f);
        countTextBtn.getLabel().setAlignment(Align.top);

        recordTextBtn = new TextButton("record", textButtonStyle);
        recordTextBtn.setSize(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getWidth() * 0.2f);
        recordTextBtn.getLabel().setAlignment(Align.top);

        cancelBan = new Button(new Button.ButtonStyle());
        cancelBan.setSize(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getWidth() * 0.2f);
        Image cancelImg = new Image(skin.getDrawable(ConstInterface.CANCEL));
        cancelImg.setSize(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getWidth() * 0.05f);
        cancelBan.add(cancelImg).size(cancelImg.getWidth(), cancelImg.getHeight());

        restartBtn = new Button(new Button.ButtonStyle());
        restartBtn.setSize(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getWidth() * 0.2f);
        Image restartImg = new Image(skin.getDrawable(ConstInterface.RESTART));
        restartImg.setSize(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getWidth() * 0.05f);
        restartBtn.add(restartImg).size(restartImg.getWidth(), restartImg.getHeight());
    }
}