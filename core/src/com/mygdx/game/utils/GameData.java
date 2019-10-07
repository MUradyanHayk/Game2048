package com.mygdx.game.utils;
/**
 * @Date 01.10.2019
 * @Author HaykMuradyan
 */
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
