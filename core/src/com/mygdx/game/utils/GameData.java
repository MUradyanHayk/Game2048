package com.mygdx.game.utils;

public class GameData {
    private int level;

    public GameData(){
       this(1);
    }

    public GameData(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
