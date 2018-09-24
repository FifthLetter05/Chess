package com.ikraybill.chess.shared

import com.ikraybill.chess.gui.DebugHud
import java.awt.image.BufferedImage

/**
 * Created by iek2d on 4/14/2017.
 */
object Reference {
    const val TITLE = "Chess"
    const val CHESS_ICON_IMG_W = 1140
    const val CHESS_ICON_IMG_H = 580
    const val CHESS_ICON_IMG_COLS = 6
    const val CHESS_ICON_IMG_ROWS = 2
    val CHESS_ICON_W = 191
    val CHESS_ICON_H = 200
    val debugHud = DebugHud()
    var chessIcons: BufferedImage? = null
    var boardSize = 1000
    var tileSize = boardSize / 8
    var round = 1
}
