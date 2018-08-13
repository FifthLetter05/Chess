package com.ikraybill.chess.pieces;

import com.ikraybill.chess.game.Player;

/**
 * Created by iek2d on 4/14/2017.
 */
public class Pawn extends Piece{

    int texX = 1667;

    public Pawn(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public void draw() {
        //chessIcons
    }
}
