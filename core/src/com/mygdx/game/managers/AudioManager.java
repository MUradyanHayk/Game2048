package com.mygdx.game.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
    private static AudioManager instance;
    private AssetManager assetManager;

    private AudioManager() {
        assetManager = AssetsManager.getInstance().getInternalManager();
    }

    public Music getMusic(String name) {
        return assetManager.get(name);
    }

    public Sound getSound(String name) {
        return assetManager.get(name);
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }
}
