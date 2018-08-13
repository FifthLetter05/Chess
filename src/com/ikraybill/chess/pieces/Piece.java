package com.ikraybill.chess.pieces;

import com.ikraybill.chess.game.Player;

/**
 * The base piece class that all other pieces inherit from
 */
public abstract class Piece {
    public int x,y;
    public Player player;

    public Piece(int x, int y, Player player){
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public abstract void draw();
}
