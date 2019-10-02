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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.utils.ConstInterface;

/**
 * @Date 01.10.2019
 * @Author HaykMuradyan
 */


public class LoadingStage extends Stage {
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
        Label.LabelStyle styleLarge = new Label.LabelStyle();
        styleLarge.font = assetsManager.get(ConstInterface.CONSOLAS_LARGE_FONT);
        styleLarge.fontColor = new Color(0.2f, 0.2f, 0.2f, 1);
        titleLabel = new Label(ConstInterface.TITLE, styleLarge);
        titleLabel.setAlignment(Align.center);
        titleLabel.setSize(SIZE * 0.6f, SIZE * 0.3f);
        titleLabel.setPosition(Gdx.graphics.getWidth() * 0.5f - titleLabel.getWidth() * 0.5f,
                Gdx.graphics.getHeight() - titleLabel.getHeight() * 3f);
        addActor(titleLabel);
    }

    private void initProgressBar() {
        pixmap = new Pixmap((int) (SIZE * 0.08f), (int) (SIZE * 0.08f), Pixmap.Format.RGBA8888);
        pixmap.setColor(0.2f, 0.2f, 0.2f, 1);
        pixmap.fillRectangle(0, 0, (int) (SIZE * 0.08f), (int) (SIZE * 0.08f));

        Image knob = new Image(new Texture(pixmap));
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle(null, knob.getDrawable());
        progressBarStyle.knobBefore = progressBarStyle.knob;
        progressBar = new ProgressBar(1, 100, 1, false, progressBarStyle);
        //progressBar.setAnimateDuration(3.5f);
        progressBar.setSize(SIZE * 0.85f, SIZE * 0.08f);
        progressBar.setPosition(Gdx.graphics.getWidth() * 0.5f - progressBar.getWidth() * 0.5f,
                Gdx.graphics.getHeight() * 0.1f - progressBar.getHeight() * 0.5f);
        addActor(progressBar);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void dispose() {
        pixmap.dispose();
    }
}
