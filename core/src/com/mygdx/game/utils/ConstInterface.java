package com.mygdx.game.utils;

/**
 * @Date 01.10.2019
 * @Author HaykMuradyan
 */

public interface ConstInterface {
    String TITLE = "2048";

    //paths
    String IMAGES_PATH = "images/";
    String FONTS_PATH = "fonts/";

    String ATLAS = "skinsAtlas.atlas";

    //images
    String ICON = "image2048.png";

    //skins
    String PAINTER = "painter";
    String IMAGE_3x3 = "image3x3";
    String IMAGE_4x4 = "image4x4";
    String IMAGE_5x5 = "image5x5";
    String IMAGE_6x6 = "image6x6";
    String IMAGE_8x8 = "image8x8";
    String ARROW_LEFT = "arrow_left";
    String ARROW_RIGHT = "arrow_right";
    String NUMBER_BG = "number_bg";
    String BTN_BG = "btn_bg";
    String RESTART = "restart";
    String CANCEL = "cancel";
    String WHITE_RECT = "white_rect";

    //fonts
    String CONSOLAS_LARGE_FONT = "Consolas_large.ttf";
    String CONSOLAS_MIDDLE_FONT = "Consolas_middle.ttf";
    String CONSOLAS_SMALL_FONT = "Consolas_small.ttf";

    //strings
    String N_3x3 = "3 " + ConstInterface.MULTIPLE + " 3";
    String N_4x4 = "4 " + ConstInterface.MULTIPLE + " 4";
    String N_5x5 = "5 " + ConstInterface.MULTIPLE + " 5";
    String N_6x6 = "6 " + ConstInterface.MULTIPLE + " 6";
    String N_8x8 = "8 " + ConstInterface.MULTIPLE + " 8";

    //unicode symbols
    String MULTIPLE = "\u00D7";
}
