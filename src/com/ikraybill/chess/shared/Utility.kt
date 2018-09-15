package com.ikraybill.chess.shared

import java.awt.*

import com.ikraybill.chess.shared.Reference.chessIcons

/**
 * Created by iek2d on 4/14/2017.
 */
object Utility {
    /**
     * retrieves the specific chess piece icon from ChessIcons.png for a specific chess piece
     * defaults to white pawn if parameters exceed bounds of the icon image
     * @param ID        the ID of the piece
     * @param color     the color of the piece
     * @return          the icon corresponding to the chess piece
     */
    @Deprecated("functionality resides in Piece class")
    fun getChessIcon(ID: Int, color: Int): Image? {
        if (ID > 5 || ID < 0 || color > 1 || color < 0) {
            println("Not a valid icon!")
            return null
        }
        return chessIcons?.getSubimage(Math.abs(5 - ID) * Reference.CHESS_ICON_W, color * Reference.CHESS_ICON_H, Reference.CHESS_ICON_W, Reference.CHESS_ICON_H)
    }
}
