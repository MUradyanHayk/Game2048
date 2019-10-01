package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.utils.ConstInterface;

public class LoadingStage extends Stage {
    private Skin skin;
    private Label loadingLabel;
    private Label titleLabel;
    private Pixmap pixmap;
    private ProgressBar progressBar;
    private AssetManager assetsManager;
    private final int SIZE = Gdx.graphics.getWidth();

    public LoadingStage(final Viewport viewport) {
        super(viewport);
        init();
    }

    private void init() {
        initManagers();
        initLabel();
        initProgressBar();
    }

    private void initManagers() {
        assetsManager = AssetsManager.getInstance().getInternalManager();
    }

    private void initLabel() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = assetsManager.get(ConstInterface.CONSOLAS_FONT);
        style.fontColor = Color.WHITE;
        loadingLabel = new Label("", style);
        loadingLabel.setSize(SIZE * 0.5f, SIZE * 0.06f);
        loadingLabel.setPosition(Gdx.graphics.getWidth() * 0.5f - loadingLabel.getWidth() * 0.5f,
                Gdx.graphics.getHeight() * 0.5f);
        addActor(loadingLabel);
    }

    private void initProgressBar() {
        pixmap = new Pixmap((int) (SIZE * 0.08f), (int) (SIZE * 0.08f), Pixmap.Format.RGBA8888);
        pixmap.setColor(0.2f, 0.2f, 0.2f, 1);
        pixmap.fillRectangle(0, 0, (int) (SIZE * 0.08f), (int) (SIZE * 0.08f));

        Image knob = new Image(new Texture(pixmap));
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle(null, knob.getDrawable());
        progressBarStyle.knobBefore = progressBarStyle.knob;
        progressBar = new ProgressBar(1, 100, 1, false, progressBarStyle);
        progressBar.setAnimateDuration(3);
        progressBar.setSize(SIZE * 0.8f, SIZE * 0.08f);
        progressBar.setPosition(Gdx.graphics.getWidth() * 0.5f - progressBar.getWidth() * 0.5f,
                Gdx.graphics.getHeight() * 0.35f - progressBar.getHeight() * 0.5f);
        addActor(progressBar);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public Label getLoadingLabel() {
        return loadingLabel;
    }

    @Override
    public void dispose() {
        pixmap.dispose();
    }
}
