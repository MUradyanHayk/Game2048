package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
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
    private boolean justTouched = true;
    private float startX;
    private float startY;
    private float x;
    private float y;
    private float dx;
    private float dy;
    private boolean justPaned = true;
    private BoardGroup context = this;

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
        createNumber("2");

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
//                if (posArray.get(i).getX() == startX && posArray.get(i).getY() == startY) {
//                    posArray.removeIndex(i);
//
//                    System.out.println("==========================");
//                    System.out.println("if");
//                    System.out.println("==========================");
//
//                    break;
//                } else {
//                    startX = random.nextInt(getSizeByLevel())* numberGroup.getWidth();
//                    startY = random.nextInt(getSizeByLevel())* numberGroup.getHeight();;
//
//                    System.out.println("==========================");
//                    System.out.println("else");
//                    System.out.println("==========================");
//                    i = 0;
//                }
//            }


        System.out.println("startX = " + x + "  ,  " + "startY = " + y);

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

    private float getAngle(float x, float y) {
////        float dx = this.startX - x;
////        float dy = this.startY - y;
        dx = x - startX;
        dy = startY - y;
        float a = 0;
        if (dx == 0) {
            a = dy > 0 ? 90 : 270;
            return a;
        }
        if (dy == 0) {
            a = dx > 0 ? 0 : 180;

            return a;
        }
        //tan(a) == abs(dy) / abs(dx) therefore
        a = (float) Math.toDegrees(Math.atan(Math.abs(dy / dx)));
        if (dx > 0 && dy > 0) {
            // do nothing, 'a' is correct
        } else if (dx > 0 && dy < 0) {
            a = 360 - a;
        } else if (dx < 0 && dy > 0) {
            a = 180 - a;
        } else {
            a = 180 + a;
        }
        return a;
    }

    private String detectDirection(float x, float y) {
        //System.out.println(getAngle(x, y));
        float angle = getAngle(x, y);
        if (angle >= 0 && angle <= 45 || angle >= 315 && angle < 360) {
            return ConstInterface.RIGHT;
        } else if (angle > 45 && angle <= 135) {
            return ConstInterface.TOP;
        } else if (angle > 135 && angle <= 225) {
            return ConstInterface.LEFT;
        } else if (angle > 225 && angle <= 315) {
            return ConstInterface.BOTTOM;
        }
        return "null";
    }

    private void numbersMove(String direction) {
        Action moveTo;
        switch (direction) {
            case ConstInterface.TOP:
                for (int i = 0; i < numberGroupsArray.size; i++) {
                    move(numberGroupsArray.get(i), numberGroupsArray.get(i).getX(), getY() + getHeight());
                }
                break;
            case ConstInterface.BOTTOM:
                for (int i = 0; i < numberGroupsArray.size; i++) {
                    move(numberGroupsArray.get(i), numberGroupsArray.get(i).getX(), getY());
                }
                break;
            case ConstInterface.LEFT:
                for (int i = 0; i < numberGroupsArray.size; i++) {
                    move(numberGroupsArray.get(i), getX(), numberGroupsArray.get(i).getY());
                }
                break;
            case ConstInterface.RIGHT:
                for (int i = 0; i < numberGroupsArray.size; i++) {
                    move(numberGroupsArray.get(i), getX() + getWidth(), numberGroupsArray.get(i).getY());
//                    MoveToAction moveToAction = new MoveToAction();
//                    moveToAction.setPosition(getX() + getWidth(), numberGroupsArray.get(i).getY());
//                    moveToAction.setDuration(2);
//                    numberGroupsArray.get(i).addAction(moveToAction);
                }
                break;
        }
    }

    private void move(NumberGroup group, float x, float y) {
        Action moveTo = new Action() {
            @Override
            public boolean act(float delta) {
                group.moveBy(x, y);
                return false;
            }
        };
        group.addAction(moveTo);
    }

    public GestureDetector getMyGestureAdapter() {
        return new GestureDetector(new MyGestureAdapter());
    }

    private class MyGestureAdapter extends GestureDetector.GestureAdapter {
        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            if (justTouched) {
                startX = x;
                startY = y;
                justTouched = false;
            }
            if (startX >= getX() + Gdx.graphics.getWidth() * 0.008f &&
                    startX <= getX() + context.getWidth() - Gdx.graphics.getWidth() * 0.008f &&
                    startY >= getY() + Gdx.graphics.getHeight() * 0.008f &&
                    startY <= getY() + getHeight() - Gdx.graphics.getHeight() * 0.008f) {
                dx = x - startX;
                dy = startY - y;
                if (Math.abs(dx) > Gdx.graphics.getWidth() * 0.008f || Math.abs(dy) > Gdx.graphics.getHeight() * 0.008) {
                    if (justPaned) {
                        String d = detectDirection(x, y);
                        numbersMove(d);
//
                        Gdx.app.log("TAG", d);
                        justPaned = false;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            justTouched = true;
            justPaned = true;
            return false;
        }
    }
}
