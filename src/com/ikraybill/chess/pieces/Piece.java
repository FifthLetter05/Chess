package com.ikraybill.chess.pieces;

import com.ikraybill.chess.game.Player;
import com.ikraybill.chess.shared.Utility;

import java.awt.*;
import java.awt.image.ImageObserver;

import static com.ikraybill.chess.shared.Reference.tileSize;

/**
 * The base piece class that all other pieces inherit from
 */
public abstract class Piece {
    private int boardX, boardY;
    private int posX, posY;
    private int ID;
    private Player player;
    private Image icon;
    private boolean dragging;

    public Piece(int boardX, int boardY, Player player, int ID){
        this.boardX = boardX;
        this.boardY = boardY;
        this.posX = boardX * tileSize;
        this.posY = boardY * tileSize;
        this.player = player;
        this.ID = ID;
        this.icon = Utility.getChessIcon(this.ID, player.getColor());
    }

    public void resetToGrid(){
        if (this.posX != boardX * tileSize) posX = boardX * tileSize;
        if (this.posY != boardY * tileSize) posY = boardY * tileSize;
    }

    public void draw(Graphics g, ImageObserver observer){
        g.drawImage(icon, posX, posY, tileSize, tileSize, observer);
    }

    public int getBoardX(){
        return boardX;
    }

    public void setBoardX(int boardX) {
        this.boardX = boardX;
    }

    public int getBoardY() {
        return boardY;
    }

    public void setBoardY(int boardY) {
        this.boardY = boardY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }
}
