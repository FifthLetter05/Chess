package com.ikraybill.chess.shared;

import java.awt.image.BufferedImage;

/**
 * Created by iek2d on 4/14/2017.
 */
public class Reference {
    public static final String TITLE = "Chess";
    public static final int CHESS_ICON_IMG_W = 2000;
    public static final int CHESS_ICON_IMG_H = 667;
    public static final int CHESS_ICON_IMG_COLS = 6;
    public static final int CHESS_ICON_IMG_ROWS = 2;
    public static final int CHESS_ICON_W = CHESS_ICON_IMG_W/CHESS_ICON_IMG_COLS;
    public static final int CHESS_ICON_H = CHESS_ICON_IMG_H/CHESS_ICON_IMG_ROWS;
    public static BufferedImage chessIcons;
    public static int boardSize = 600;
    public static int tileSize = boardSize /8;
}
