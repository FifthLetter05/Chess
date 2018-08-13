package com.ikraybill.chess.game;

/**
 * Created by iek2d on 4/14/2017.
 */
public class Player {
    private int color;

    public Player(int color){
        this.color = color;
    }

    public int getColor(){
        return color;
    }

    public void setColor(int color){
        this.color = color;
    }
}
