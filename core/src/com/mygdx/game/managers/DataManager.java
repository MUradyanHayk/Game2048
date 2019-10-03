package com.mygdx.game.managers;

import com.mygdx.game.utils.GameData;

public class DataManager {
    private static DataManager instance;
    private GameData data;

    private DataManager() {
        data = new GameData();
    }

    public GameData getData() {
        return data;
    }

    public void setData(GameData data) {
        this.data = data;
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
}