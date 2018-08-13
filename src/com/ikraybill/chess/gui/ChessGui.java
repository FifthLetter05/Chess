package com.ikraybill.chess.gui;

import com.ikraybill.chess.shared.Reference;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The Main Chess GUI class
 */
public class ChessGui extends JPanel implements Runnable, MouseMotionListener {

    public BufferedImage chessIcons;
    public Thread gameLoop;
    //private Graphics2D g2D;

    private int boardSize = 600;
    private int tileSize = boardSize /8;
    private int currentFrame = 0;
    private int totalFrames = 0;
    private int frameCount = 0;
    private int frameDelay = 10;

    private int mouseX;
    private int mouseY;

    /**
     * creates the Chess GUI JPanel on the main window
     */
    public ChessGui(){
        super(true);

        try {
            chessIcons = ImageIO.read(getClass().getResource("ChessIcons.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(boardSize, boardSize));

        gameLoop = new Thread(this);
        gameLoop.start();
    }

    /**
     * updates the game's graphics and logic
     */
    public void gameUpdate(){
        repaint();
    }

    /**
     * draws the stuff on the screen
     * @param g the JPanel graphics
     */
    public synchronized void paint(Graphics g){
        super.paint(g);
        g.setColor(new Color(77, 49, 31));
        for (int i = 0; i < 8; i+=2) {
            for (int j = 0; j < 8; j+=2) {
                g.fillRect(i* tileSize,j*(tileSize), tileSize, tileSize);
            }
        }
        for (int i = 1; i < 9; i+=2) {
            for (int j = 1; j < 9; j+=2) {
                g.fillRect(i* tileSize,j*(tileSize), tileSize, tileSize);
            }
        }
        g.setColor(new Color(217, 155, 114));
        for (int i = 1; i < 9; i+=2) {
            for (int j = 0; j < 8; j+=2) {
                g.fillRect(i* tileSize,j*(tileSize), tileSize, tileSize);
            }
        }
        for (int i = 0; i < 8; i+=2) {
            for (int j = 1; j < 9; j+=2) {
                g.fillRect(i* tileSize,j*(tileSize), tileSize, tileSize);
            }
        }
    }

    /**
     * retrieves the specific chess piece icon from ChessIcons.png for a specific chess piece
     * defaults to white pawn if parameters exceed bounds of the icon image
     * @param ID        the ID of the piece
     * @param color     the color of the piece
     * @return          the icon corresponding to the chess piece
     */
    private Image getChessIcon(int ID, int color){
        if ((ID>5 || ID<0) || (color>1 || color < 0)) {
            System.out.println("Not a valid icon!");
            ID = 0;
            color = 0;
        }
        return chessIcons.getSubimage(Math.abs(5-ID)*(Reference.CHESS_ICON_W),color * (Reference.CHESS_ICON_H),Reference.CHESS_ICON_W,Reference.CHESS_ICON_H);
    }

    /**
     * starts the main game loop thread
     */
    public void run() {
        Thread t = Thread.currentThread();
        while (t == gameLoop){

            gameUpdate();

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
