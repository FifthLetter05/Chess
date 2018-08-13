package com.ikraybill.chess.gui;

import com.ikraybill.chess.game.Player;
import com.ikraybill.chess.pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.LinkedList;

import static com.ikraybill.chess.shared.Reference.boardSize;
import static com.ikraybill.chess.shared.Reference.chessIcons;
import static com.ikraybill.chess.shared.Reference.tileSize;

/**
 * The Main Chess GUI class
 */
public class ChessGui extends JPanel implements Runnable, MouseListener, MouseMotionListener {

    public Thread gameLoop;
    //private Graphics2D g2D;

    private int currentFrame = 0;
    private int totalFrames = 0;
    private int frameCount = 0;
    private int frameDelay = 10;

    private int mouseX;
    private int mouseY;

    private LinkedList<Piece> pieces = new LinkedList<>();
    private Player white;
    private Player black;

    private DebugHud debugHud = new DebugHud();

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
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        white = new Player(0);
        black = new Player(1);

        pieces.add(new Rook(0,0, white));
        pieces.add(new Knight(1,0, white));
        pieces.add(new Bishop(2,0, white));
        pieces.add(new Queen(3,0, white));
        pieces.add(new King(4,0, white));
        pieces.add(new Bishop(5,0, white));
        pieces.add(new Knight(6,0, white));
        pieces.add(new Rook(7,0, white));

        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(i,1, white));
        }

        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(i,6, black));
        }

        pieces.add(new Rook(0,7, black));
        pieces.add(new Knight(1,7, black));
        pieces.add(new Bishop(2,7, black));
        pieces.add(new Queen(3,7, black));
        pieces.add(new King(4,7, black));
        pieces.add(new Bishop(5,7, black));
        pieces.add(new Knight(6,7, black));
        pieces.add(new Rook(7,7, black));

        repaint();

        gameLoop = new Thread(this);
        gameLoop.start();
    }

    /**
     * updates the game's graphics and logic
     */
    public void gameUpdate(){
        repaint();
    }

    public void drawBoard(Graphics g){
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

    public void drawPieces(Graphics g){
        for (Piece piece: pieces) {
            piece.draw(g, this);
        }
    }

    public void drawHud(Graphics g){
        g.setColor(new Color(70, 73, 255));
        debugHud.draw(g);
    }

    /**
     * draws the stuff on the screen
     * @param g the JPanel graphics
     */
    public synchronized void paint(Graphics g){
        super.paint(g);
        //g.clearRect(0,0,boardSize,boardSize);

        drawBoard(g);
        drawPieces(g);
        drawHud(g);

    }

    /**
     * starts the main game loop thread
     */
    public void run() {
        Thread t = Thread.currentThread();
        //repaint();
        while (t == gameLoop){

            gameUpdate();

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int offsetX, offsetY;
    private boolean dragging = false;

    @Override
    public void mousePressed(MouseEvent e) {
        for (Piece piece : pieces) {

            if ((piece.getPosX() < mouseX && mouseX < piece.getPosX() + tileSize) && (piece.getPosY() < mouseY && mouseY < piece.getPosY() + tileSize)){
                piece.setDragging(true);
                dragging = true;
                offsetX = mouseX - piece.getPosX();
                offsetY = mouseY - piece.getPosY();
                piece.setPosX(mouseX - offsetX);
                piece.setPosY(mouseY - offsetY);
                //System.out.println("dragging");
                debugHud.addLine("Dragging", dragging);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        boolean moveOkay = true;
        for (Piece piece : pieces) {
            if (piece.isDragging()) {
                for (Piece otherPiece : pieces) {
                    if (((mouseX / tileSize == otherPiece.getBoardX()) && (mouseY / tileSize == otherPiece.getBoardY()))){
                        moveOkay = false;
                    }
                }
                if (moveOkay) {
                    piece.setBoardX(mouseX / tileSize);
                    piece.setBoardY(mouseY / tileSize);

                }
                debugHud.addLine("Move Okay", moveOkay);
                piece.resetToGrid();
                piece.setDragging(false);
                debugHud.removeLine("Dragging");
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        //System.out.println("Position: " + mouseX + ", " + mouseY);
        //repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        for (Piece piece : pieces) {

            if (piece.isDragging()){
                piece.setPosX(mouseX - offsetX);
                piece.setPosY(mouseY - offsetY);
                //System.out.println("dragging");
            }
            if (piece.isDragging()) dragging = true;
        }

        //System.out.println("Dragged: " + mouseX + ", " + mouseY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
