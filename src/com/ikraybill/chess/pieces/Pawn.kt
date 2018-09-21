package com.ikraybill.chess.pieces

import com.ikraybill.chess.game.Player

/**
 * Created by iek2d on 4/14/2017.
 */
class Pawn(posX: Int, posY: Int, player: Player) : Piece(posX, posY, player, 0) {
    override fun checkMove(posX: Int, posY: Int): Boolean {
        if(posX == this.posX && posY == this.posY - 1){
            return true
        }
        return false
    }
}
