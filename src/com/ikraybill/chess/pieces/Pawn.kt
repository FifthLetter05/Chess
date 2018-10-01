package com.ikraybill.chess.pieces

import com.ikraybill.chess.game.Player
import com.ikraybill.chess.shared.Reference.debugHud

/**
 * Created by iek2d on 4/14/2017.
 */
class Pawn(boardX: Int, boardY: Int, player: Player) : Piece(boardX, boardY, player, 0) {
    override fun checkMove(boardX: Int, boardY: Int): Boolean {
        debugHud.addLine("checked position", boardX.toString() + ", " + boardY)
        if(boardX == this.boardX && boardY == this.boardY + if (this.player.color == 0) 1 else -1){
            return true
        }
        return false
    }
}
