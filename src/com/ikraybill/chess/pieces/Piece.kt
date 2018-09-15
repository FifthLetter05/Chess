package com.ikraybill.chess.pieces

import com.ikraybill.chess.game.Player
import com.ikraybill.chess.shared.Reference
import com.ikraybill.chess.shared.Utility

import java.awt.*
import java.awt.image.ImageObserver

import com.ikraybill.chess.shared.Reference.tileSize

/**
 * The base piece class that all other pieces inherit from
 */
abstract class Piece(var boardX: Int, var boardY: Int, player: Player, ID: Int) {
    var posX: Int = boardX * tileSize
    var posY: Int = boardY * tileSize
    private val icon: Image? = Reference.chessIcons?.getSubimage(Math.abs(5 - ID) * Reference.CHESS_ICON_W, player.color * Reference.CHESS_ICON_H, Reference.CHESS_ICON_W, Reference.CHESS_ICON_H)
    var isDragging: Boolean = false

    fun resetToGrid() {
        if (this.posX != boardX * tileSize) posX = boardX * tileSize
        if (this.posY != boardY * tileSize) posY = boardY * tileSize
    }

    fun draw(g: Graphics, observer: ImageObserver) {
        g.drawImage(icon, posX, posY, tileSize, tileSize, observer)
    }
}
