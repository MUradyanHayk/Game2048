package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.utils.ConstInterface;

public class LoadingStage extends Stage {
    private Skin skin;
    private Label label;
    private ProgressBar progressBar;
    private AssetManager assetsManager;

    public LoadingStage(final Viewport viewport) {
        super(viewport);
        init();
    }

    private void init() {
        initManagers();
        initLabel();
    }

    private void initManagers() {
        assetsManager = AssetsManager.getInstance().getInternalManager();
    }

    private void initLabel() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = assetsManager.get(ConstInterface.CONSOLAS_FONT);
        style.fontColor = Color.WHITE;
        label = new Label("", style);
        label.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
        addActor(label);
    }
}
