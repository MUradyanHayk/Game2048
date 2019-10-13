package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.managers.AssetsManager;
import com.mygdx.game.managers.AudioManager;

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
    private String detection;
    private boolean justPaned1 = true;
    private boolean justPaned2 = true;
    private boolean actionStop = true;
    private BoardGroup context = this;
    private Random random;
    private boolean moving = false;

    public BoardGroup(int level) {
        this.setSize(Gdx.graphics.getWidth() * 0.9f, Gdx.graphics.getWidth() * 0.9f);
        this.level = level;
        random = new Random();
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

    private NumberGroup createNumber(String text) {
        NumberGroup numberGroup = new NumberGroup(text, getWidth() / getSizeByLevel());

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

//        System.out.println("startX = " + x + "  ,  " + "startY = " + y);

        numberGroup.setPosition(x, y);
        numberGroup.addNumber();
        numberGroupsArray.add(numberGroup);
        frontEndGroup.addActor(numberGroup);
        return numberGroup;
    }

    private NumberGroup createNumber(String text, float x, float y) {
        NumberGroup numberGroup = new NumberGroup(text, getWidth() / getSizeByLevel());

        numberGroup.setPosition(x, y);

//        System.out.println("startX = " + x + "  ,  " + "startY = " + y);

        numberGroup.setPosition(x, y);
        numberGroup.addNumber();
        numberGroupsArray.add(numberGroup);
        frontEndGroup.addActor(numberGroup);
        return numberGroup;
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

    private void numbersMove() {
        for (int i = 0; i < numberGroupsArray.size; i++) {
            move(numberGroupsArray.get(i));
        }

        for (int i = 0; i < numberGroupsArray.size; i++) {
            if (!numberGroupsArray.get(i).isActionStop()) {
                return;
            }
        }
        // ???????
        if (!actionStop) {
            moving = false;
            int num = random.nextInt(2);
            String str = num == 1 ? "2" : "4";
            createNumber(str);
            actionStop = true;
        }
        justPaned1 = true;
    }

    private void move(NumberGroup group) {
        if (true) {
            final float[] boundTop = {group.getX(), getHeight() - group.getHeight()};
            final float[] boundBottom = {group.getX(), 0};
            final float[] boundLeft = {0, group.getY()};
            final float[] boundRight = {getWidth() - group.getWidth(), group.getY()};

            float t = Gdx.graphics.getDeltaTime() * Gdx.graphics.getWidth() * 3.2f;

            switch (detection) {
                case ConstInterface.TOP:
                    x = boundTop[0];
                    y = boundTop[1];
                    if (!group.isActionStop()) {
                        if (group.getY() < y - t) {

                            for (int i = 0; i < numberGroupsArray.size; i++) {
                                for (int j = i + 1; j < numberGroupsArray.size; j++) {
                                    if (numberGroupsArray.get(i).isActionStop() && !numberGroupsArray.get(j).isActionStop()) {
                                        if (numberGroupsArray.get(i).getX() == numberGroupsArray.get(j).getX() && numberGroupsArray.get(i).getY() - group.getHeight() == numberGroupsArray.get(j).getY()) {
                                            if (!numberGroupsArray.get(i).getText().equals(numberGroupsArray.get(j).getText())) {
                                                actionStop = true;
                                                numberGroupsArray.get(j).setActionStop(true);
                                                return;
                                            }

                                        }
                                    }
                                }
                            }
//                            for (int i = 0; i < numberGroupsArray.size; i++) {
//                                if (numberGroupsArray.get(i).isActionStop()) {
//                                    if (group.getX() == numberGroupsArray.get(i).getX() && group.getY() == numberGroupsArray.get(i).getY() - group.getHeight()) {
//                                        if (!group.getText().equals(numberGroupsArray.get(i).getText())) {
//                                            actionStop = true;
//                                            numberGroupsArray.get(i).setActionStop(true);
//                                            return;
//                                        }
//                                    }
//                                }
//                            }
                            group.setPosition(x, group.getY() + t);
                            actionStop = false;
                        } else {
                            group.setActionStop(true);
                            group.setPosition(x, getHeight() - group.getHeight());
                        }
                    } else {
                        for (int i = 0; i < numberGroupsArray.size; i++) {
                            if (!numberGroupsArray.get(i).isActionStop()) {
                                if (!group.getText().equals(numberGroupsArray.get(i).getText())) {
                                    if (group.getX() == numberGroupsArray.get(i).getX() && group.getY() - group.getHeight() < numberGroupsArray.get(i).getY() + t) {
                                        numberGroupsArray.get(i).setActionStop(true);
                                        numberGroupsArray.get(i).setPosition(numberGroupsArray.get(i).getX(), group.getY() - group.getHeight());
//                                        actionStop = true;
                                    }
                                } else {
                                    if (group.getX() == numberGroupsArray.get(i).getX() && group.getY() <= numberGroupsArray.get(i).getY() + t) {
                                        int num = Integer.parseInt(group.getText());
                                        float posX = group.getX();
                                        float posY = group.getY();
                                        numberGroupsArray.get(i).setVisible(false);
                                        frontEndGroup.removeActor(numberGroupsArray.get(i));
                                        frontEndGroup.removeActor(group);
                                        numberGroupsArray.removeIndex(i);
                                        numberGroupsArray.removeValue(group, true);

                                        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
                                        createNumber(String.valueOf(2 * num), posX, posY).setActionStop(true);
//                                        actionStop = false;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case ConstInterface.BOTTOM:
                    x = boundBottom[0];
                    y = boundBottom[1];
                    if (!group.isActionStop()) {
                        if (group.getY() > y + t) {
                            for (int i = 0; i < numberGroupsArray.size; i++) {
                                for (int j = i + 1; j < numberGroupsArray.size; j++) {
                                    if (numberGroupsArray.get(i).isActionStop() && !numberGroupsArray.get(j).isActionStop()) {
                                        if (numberGroupsArray.get(i).getX() == numberGroupsArray.get(j).getX() && numberGroupsArray.get(i).getY() + group.getHeight() == numberGroupsArray.get(j).getY()) {
                                            if (!numberGroupsArray.get(i).getText().equals(numberGroupsArray.get(j).getText())) {
                                                actionStop = true;
                                                numberGroupsArray.get(j).setActionStop(true);
                                                return;
                                            }

                                        }
                                    }
                                }
                            }
//                            for (int i = 0; i < numberGroupsArray.size; i++) {
//                                if (numberGroupsArray.get(i).isActionStop()) {
//                                    if (group.getX() == numberGroupsArray.get(i).getX() && group.getY() - group.getHeight() == numberGroupsArray.get(i).getY()) {
//                                        if (!group.getText().equals(numberGroupsArray.get(i).getText())) {
//                                            actionStop = true;
//                                            return;
//                                        }
//                                    }
//                                }
//                            }
                            group.setPosition(x, group.getY() - t);
                            actionStop = false;
                        } else {
                            group.setActionStop(true);
                            group.setPosition(x, 0);
                        }
                    } else {
                        for (int i = 0; i < numberGroupsArray.size; i++) {
                            if (!numberGroupsArray.get(i).isActionStop()) {
                                if (!group.getText().equals(numberGroupsArray.get(i).getText())) {
                                    if (group.getX() == numberGroupsArray.get(i).getX() && group.getY() + group.getHeight() > numberGroupsArray.get(i).getY() - t) {
                                        numberGroupsArray.get(i).setActionStop(true);
                                        numberGroupsArray.get(i).setPosition(numberGroupsArray.get(i).getX(), group.getY() + group.getHeight());
//                                        actionStop = true;
                                    }
                                } else {
                                    if (group.getX() == numberGroupsArray.get(i).getX() && group.getY() >= numberGroupsArray.get(i).getY() - t) {
                                        int num = Integer.parseInt(group.getText());
                                        float posX = group.getX();
                                        float posY = group.getY();
                                        numberGroupsArray.get(i).setVisible(false);
                                        frontEndGroup.removeActor(numberGroupsArray.get(i));
                                        frontEndGroup.removeActor(group);
                                        numberGroupsArray.removeIndex(i);
                                        numberGroupsArray.removeValue(group, true);
                                        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
                                        createNumber(String.valueOf(2 * num), posX, posY).setActionStop(true);
//                                        actionStop = false;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case ConstInterface.LEFT:
                    x = boundLeft[0];
                    y = boundLeft[1];
                    if (!group.isActionStop()) {
                        if (group.getX() > x + t) {
                            for (int i = 0; i < numberGroupsArray.size; i++) {
                                for (int j = i + 1; j < numberGroupsArray.size; j++) {
                                    if (numberGroupsArray.get(i).isActionStop() && !numberGroupsArray.get(j).isActionStop()) {
                                        if (numberGroupsArray.get(i).getX()+group.getWidth() == numberGroupsArray.get(j).getX() && numberGroupsArray.get(i).getY() == numberGroupsArray.get(j).getY()) {
                                            if (!numberGroupsArray.get(i).getText().equals(numberGroupsArray.get(j).getText())) {
                                                actionStop = true;
                                                numberGroupsArray.get(j).setActionStop(true);
                                                return;
                                            }

                                        }
                                    }
                                }
                            }
//                            for (int i = 0; i < numberGroupsArray.size; i++) {
//                                if (numberGroupsArray.get(i).isActionStop()) {
//                                    if (group.getX() - group.getWidth() == numberGroupsArray.get(i).getX() && group.getY() == numberGroupsArray.get(i).getY()) {
//                                        if (!group.getText().equals(numberGroupsArray.get(i).getText())) {
//                                            actionStop = true;
//                                            return;
//                                        }
//                                    }
//                                }
//                            }
                            group.setPosition(group.getX() - t, y);
                            actionStop = false;
                        } else {
                            group.setActionStop(true);
                            group.setPosition(0, y);
                        }
                    } else {
                        for (int i = 0; i < numberGroupsArray.size; i++) {
                            if (!numberGroupsArray.get(i).isActionStop()) {
                                if (!group.getText().equals(numberGroupsArray.get(i).getText())) {
                                    if (group.getX() + group.getWidth() > numberGroupsArray.get(i).getX() - t && group.getY() == numberGroupsArray.get(i).getY()) {
                                        numberGroupsArray.get(i).setActionStop(true);
                                        numberGroupsArray.get(i).setPosition(group.getX() + group.getWidth(), numberGroupsArray.get(i).getY());
//                                        actionStop = true;
                                    }
                                } else {
                                    if (group.getX() >= numberGroupsArray.get(i).getX() - t && group.getY() == numberGroupsArray.get(i).getY()) {
                                        int num = Integer.parseInt(group.getText());
                                        float posX = group.getX();
                                        float posY = group.getY();
                                        numberGroupsArray.get(i).setVisible(false);
                                        frontEndGroup.removeActor(numberGroupsArray.get(i));
                                        frontEndGroup.removeActor(group);
                                        numberGroupsArray.removeIndex(i);
                                        numberGroupsArray.removeValue(group, true);
                                        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
                                        createNumber(String.valueOf(2 * num), posX, posY).setActionStop(true);
//                                        actionStop = false;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case ConstInterface.RIGHT:
                    x = boundRight[0];
                    y = boundRight[1];
                    if (!group.isActionStop()) {
                        if (group.getX() < x - t) {
                            for (int i = 0; i < numberGroupsArray.size; i++) {
                                for (int j = i + 1; j < numberGroupsArray.size; j++) {
                                    if (numberGroupsArray.get(i).isActionStop() && !numberGroupsArray.get(j).isActionStop()) {
                                        if (numberGroupsArray.get(i).getX()-group.getWidth() == numberGroupsArray.get(j).getX() && numberGroupsArray.get(i).getY() == numberGroupsArray.get(j).getY()) {
                                            if (!numberGroupsArray.get(i).getText().equals(numberGroupsArray.get(j).getText())) {
                                                actionStop = true;
                                                numberGroupsArray.get(j).setActionStop(true);
                                                return;
                                            }

                                        }
                                    }
                                }
                            }
//                            for (int i = 0; i < numberGroupsArray.size; i++) {
//                                if (numberGroupsArray.get(i).isActionStop()) {
//                                    if (group.getX() + group.getWidth() == numberGroupsArray.get(i).getX() && group.getY() == numberGroupsArray.get(i).getY()) {
//                                        if (!group.getText().equals(numberGroupsArray.get(i).getText())) {
//                                            actionStop = true;
//                                            return;
//                                        }
//                                    }
//                                }
//                            }
                            group.setPosition(group.getX() + t, y);
                            actionStop = false;
                        } else {
                            group.setActionStop(true);
                            group.setPosition(getWidth() - group.getWidth(), y);
                        }
                    } else {
                        for (int i = 0; i < numberGroupsArray.size; i++) {
                            if (!numberGroupsArray.get(i).isActionStop()) {
                                if (!group.getText().equals(numberGroupsArray.get(i).getText())) {
                                    if (group.getX() - group.getWidth() < numberGroupsArray.get(i).getX() + t && group.getY() == numberGroupsArray.get(i).getY()) {
                                        numberGroupsArray.get(i).setActionStop(true);
                                        numberGroupsArray.get(i).setPosition(group.getX() - group.getWidth(), numberGroupsArray.get(i).getY());
//                                        actionStop = true;
                                    }
                                } else {
                                    if (group.getX() <= numberGroupsArray.get(i).getX() + t && group.getY() == numberGroupsArray.get(i).getY()) {
                                        int num = Integer.parseInt(group.getText());
                                        float posX = group.getX();
                                        float posY = group.getY();
                                        numberGroupsArray.get(i).setVisible(false);
                                        frontEndGroup.removeActor(numberGroupsArray.get(i));
                                        frontEndGroup.removeActor(group);
                                        numberGroupsArray.removeIndex(i);
                                        numberGroupsArray.removeValue(group, true);
                                        System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
                                        createNumber(String.valueOf(2 * num), posX, posY).setActionStop(true);
//                                        actionStop = false;
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
//            System.out.println("action : moveTo");
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) ||
                Gdx.input.isKeyJustPressed(Input.Keys.DOWN) ||
                Gdx.input.isKeyJustPressed(Input.Keys.LEFT) ||
                Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            motion();
//            justPaned1 = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && moving) {
            numbersMove();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && moving) {
            numbersMove();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && moving) {
            numbersMove();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && moving) {
            numbersMove();
        } else if (moving) {
            numbersMove();
        }
    }

    public GestureDetector getMyGestureAdapter() {
        return new GestureDetector(new MyGestureAdapter());
    }

    private class MyGestureAdapter extends GestureDetector.GestureAdapter {
        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            if (justPaned1 && justPaned2) {
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
                        detection = detectDirection(x, y);
                        motion();
                    }
                }
            }
            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            justTouched = true;
            justPaned2 = true;
            System.out.println("panStop");
            return false;
        }
    }

    private void motion() {
        AudioManager.getInstance().getSound(ConstInterface.SOUND_PATH + ConstInterface.MOVE_SOUND).play();
        context.x = x;
        context.y = y;

        for (int i = 0; i < numberGroupsArray.size; i++) {
            numberGroupsArray.get(i).setActionStop(false);
        }

        justPaned1 = false;
        justPaned2 = false;
        moving = true;
        actionStop = false;
        Gdx.app.log("TAG", "paned");
        //justPaned1 = false;
    }
}