package com.ikraybill.chess.shared

import java.awt.image.BufferedImage

/**
 * Created by iek2d on 4/14/2017.
 */
object Reference {
    const val TITLE = "Chess"
    const val CHESS_ICON_IMG_W = 1440
    const val CHESS_ICON_IMG_H = 580
    const val CHESS_ICON_IMG_COLS = 6
    const val CHESS_ICON_IMG_ROWS = 2
    val CHESS_ICON_W = CHESS_ICON_IMG_W / CHESS_ICON_IMG_COLS
    val CHESS_ICON_H = CHESS_ICON_IMG_H / CHESS_ICON_IMG_ROWS
    var chessIcons: BufferedImage? = null
    var boardSize = 600
    var tileSize = boardSize / 8
}
