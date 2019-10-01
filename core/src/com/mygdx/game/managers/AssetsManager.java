package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.mygdx.game.utils.ConstInterface;

public class AssetsManager {
    private static AssetsManager instance;
    private AssetManager internalManager;
    private AssetManager externalManager;

    private AssetsManager() {
        init();
        load();
    }

    private void load() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        internalManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        internalManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter parameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parameter.fontFileName = ConstInterface.FONTS_PATH + ConstInterface.CONSOLAS_FONT;
        parameter.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.1f);
        parameter.fontParameters.color = Color.WHITE;

        internalManager.load( ConstInterface.CONSOLAS_FONT, BitmapFont.class, parameter);

        internalManager.finishLoading();
        internalManager.load(ConstInterface.IMAGES_PATH + ConstInterface.ATLAS, TextureAtlas.class);
    }

    private void init() {
        internalManager = new AssetManager(new InternalFileHandleResolver());
        externalManager = new AssetManager(new ExternalFileHandleResolver());
    }


    public AssetManager getInternalManager() {
        return internalManager;
    }

    public AssetManager getExternalManager() {
        return externalManager;
    }

    public static AssetsManager getInstance() {
        if (instance == null) {
            instance = new AssetsManager();
        }
        return instance;
    }
}
