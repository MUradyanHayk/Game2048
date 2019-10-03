package com.mygdx.game.utils;

public class GameData {
    private int currentLevel;

    public GameData(){
       this(1);
    }

    public GameData(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
