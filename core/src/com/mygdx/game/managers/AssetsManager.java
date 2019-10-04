package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.I18NBundle;
import com.mygdx.game.utils.ConstInterface;

import javax.print.DocFlavor;

/**
 * @Date 01.10.2019
 * @Author HaykMuradyan
 */


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

        FreetypeFontLoader.FreeTypeFontLoaderParameter paramLarge = new FreetypeFontLoader.FreeTypeFontLoaderParameter();

        paramLarge.fontFileName = ConstInterface.FONTS_PATH + ConstInterface.CONSOLAS_LARGE_FONT;
        paramLarge.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.32f);
        paramLarge.fontParameters.color = Color.WHITE;

        internalManager.load(ConstInterface.CONSOLAS_LARGE_FONT, BitmapFont.class, paramLarge);

        internalManager.finishLoading();

        FreetypeFontLoader.FreeTypeFontLoaderParameter paramSmall = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        paramSmall.fontFileName = ConstInterface.FONTS_PATH + ConstInterface.CONSOLAS_SMALL_FONT;
        paramSmall.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.035f);
        paramSmall.fontParameters.color = Color.WHITE;

        internalManager.load(ConstInterface.CONSOLAS_SMALL_FONT, BitmapFont.class, paramSmall);

        FreetypeFontLoader.FreeTypeFontLoaderParameter paramMiddle = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        paramMiddle.fontFileName = ConstInterface.FONTS_PATH + ConstInterface.CONSOLAS_MIDDLE_FONT;
        paramMiddle.fontParameters.size = (int) (Gdx.graphics.getWidth() * 0.06f);
        paramMiddle.fontParameters.color = Color.WHITE;
        paramMiddle.fontParameters.characters += ConstInterface.MULTIPLE;

        internalManager.load(ConstInterface.CONSOLAS_MIDDLE_FONT, BitmapFont.class, paramMiddle);

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
