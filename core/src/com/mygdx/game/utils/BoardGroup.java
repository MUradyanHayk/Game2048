package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntIntMap;
import com.mygdx.game.managers.AssetsManager;

import java.util.Random;

/**
 * @Date 01.10.2019
 * @Author HaykMuradyan
 */

public class BoardGroup extends Group {
    private int level;
    private Skin skin;
    private Image boardImg;
    private NumberGroup[][] numberGroups_bg;
    private Array<NumberGroup> numberGroupsArray;
    private Array<Pos> posArray;
    private Group backEndGroup;
    private Group frontEndGroup;

    public BoardGroup(int level) {
        this.setSize(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getWidth() * 0.9f);
        this.level = level;
        init();
    }

    private void init() {
        initSkin();
        initGroups();
        initNumberGroups();
    }

    private void initGroups() {
        backEndGroup = new Group();
        backEndGroup.setSize(getWidth(), getHeight());
        boardImg = new Image(skin.getDrawable(ConstInterface.WHITE_RECT));
        boardImg.setSize(getWidth(), getHeight());
        boardImg.addAction(Actions.color(new Color(0.74f, 0.67f, 0.64f, 1)));
        backEndGroup.addActor(boardImg);
        addActor(backEndGroup);

        frontEndGroup = new Group();
        frontEndGroup.setSize(getWidth(), getHeight());
        addActor(frontEndGroup);
    }

    private void initNumberGroups() {
        posArray = new Array<>();
        numberGroups_bg = new NumberGroup[getSizeByLevel()][getSizeByLevel()];
        for (int i = 0; i < numberGroups_bg.length; i++) {
            for (int j = 0; j < numberGroups_bg[i].length; j++) {
                numberGroups_bg[i][j] = new NumberGroup(getWidth() / getSizeByLevel());
                numberGroups_bg[i][j].setPosition(i * numberGroups_bg[i][j].getWidth(), j * numberGroups_bg[i][j].getHeight());
                numberGroups_bg[i][j].addNumber();
                posArray.add(new Pos(numberGroups_bg[i][j].getX(), numberGroups_bg[i][j].getY()));
                backEndGroup.addActor(numberGroups_bg[i][j]);
            }
        }
        numberGroupsArray = new Array<>();

        createNumber("2");
        createNumber("4");
        createNumber("8");
        createNumber("16");
        createNumber("32");
        createNumber("64");
        createNumber("128");
        createNumber("256");
        createNumber("512");

    }


    private void initSkin() {
        skin = new Skin();
        skin.addRegions(AssetsManager.getInstance().getInternalManager().get(ConstInterface.IMAGES_PATH + ConstInterface.ATLAS));
    }

    private void createNumber(String text) {
        NumberGroup numberGroup = new NumberGroup(text, getWidth() / getSizeByLevel());
        Random random = new Random();
        float x = random.nextInt(getSizeByLevel()) * numberGroup.getWidth();
        float y = random.nextInt(getSizeByLevel()) * numberGroup.getHeight();
        numberGroup.setPosition(x, y);

        for (int i = 0; i < numberGroupsArray.size; i++) {
            if (getSizeByLevel() * getSizeByLevel() <= numberGroupsArray.size) {
                break;
            }
            if (x == numberGroupsArray.get(i).getX() && y == numberGroupsArray.get(i).getY()) {
                i = -1;
                x = random.nextInt(getSizeByLevel()) * numberGroup.getWidth();
                y = random.nextInt(getSizeByLevel()) * numberGroup.getHeight();
            }
        }


//            for (int i = 0; i < posArray.size; i++) {
//                if (posArray.get(i).getX() == x && posArray.get(i).getY() == y) {
//                    posArray.removeIndex(i);
//
//                    System.out.println("==========================");
//                    System.out.println("if");
//                    System.out.println("==========================");
//
//                    break;
//                } else {
//                    x = random.nextInt(getSizeByLevel())* numberGroup.getWidth();
//                    y = random.nextInt(getSizeByLevel())* numberGroup.getHeight();;
//
//                    System.out.println("==========================");
//                    System.out.println("else");
//                    System.out.println("==========================");
//                    i = 0;
//                }
//            }


        System.out.println("x = " + x + "  ,  " + "y = " + y);

        numberGroup.setPosition(x, y);
        numberGroup.addNumber();
        numberGroupsArray.add(numberGroup);
        frontEndGroup.addActor(numberGroup);
    }

    private int getSizeByLevel() {
        switch (level) {
            case 0:
                return 3;
            case 1:
                return 4;
            case 2:
                return 5;
            case 3:
                return 6;
            case 4:
                return 8;
        }
        return -1;
    }

    public GestureDetector getMyGestureAdapter() {
        return new GestureDetector(new MyGestureAdapter());
    }

    private class MyGestureAdapter extends GestureDetector.GestureAdapter {

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            Gdx.app.log("TAG", "pan");
            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }
    }
}
