package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Game2048;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.utils.ConstInterface;

import java.nio.file.spi.FileTypeDetector;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 320;
        config.height = 600;
        config.title = ConstInterface.TITLE;
        config.addIcon(ConstInterface.IMAGES_PATH + ConstInterface.ICON, Files.FileType.Internal);
        new LwjglApplication(Game2048.getInstance(), config);
    }
}
