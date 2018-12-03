package com.ikraybill.chess.gui

import com.ikraybill.chess.game.ChessBoard
import com.ikraybill.chess.game.Player
import com.ikraybill.chess.pieces.*
import com.ikraybill.chess.shared.Reference

import javax.imageio.ImageIO
import javax.swing.*
import java.awt.*
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.io.IOException
import java.util.LinkedList

import com.ikraybill.chess.shared.Reference.boardSize
import com.ikraybill.chess.shared.Reference.chessIcons
import com.ikraybill.chess.shared.Reference.debugHud
import com.ikraybill.chess.shared.Reference.tileSize

/**
 * The Main Chess GUI class
 */
class ChessGui : JPanel(true), Runnable, MouseListener, MouseMotionListener {

    var gameLoop: Thread
    //private Graphics2D g2D;

    private val currentFrame = 0
    private val totalFrames = 0
    private val frameCount = 0
    private val frameDelay = 10

    private var mouseX: Int = 0
    private var mouseY: Int = 0
    private var mouseBoardX: Int = 0
    private var mouseBoardY: Int = 0

    @Deprecated("use chessBoard.pieces")
    private var pieces = LinkedList<Piece>()
    private val chessBoard: ChessBoard
    private val white: Player
    private val black: Player

    private var offsetX: Int = 0
    private var offsetY: Int = 0
    private var dragging = false

    /**
     * creates the Chess GUI JPanel on the main window
     */
    init {

        try {
            Reference.chessIcons = ImageIO.read(System::class.java.getResource("/ChessIcons.png"))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        chessBoard = ChessBoard()

        preferredSize = chessBoard.size

        //pieces = chessBoard.pieces

        this.addMouseListener(this)
        this.addMouseMotionListener(this)

        white = Player.WHITE
        black = Player.BLACK

        repaint()

        gameLoop = Thread(this)
        gameLoop.start()
    }

    /**
     * updates the game's graphics and logic
     */
    private fun gameUpdate() {
        repaint()
    }

    private fun drawPieces(g: Graphics?) {
        if(dragging) {
            for (piece in chessBoard.pieces) {
                if (!piece.isDragging) piece.draw(g!!, this)
            }
            for (piece in chessBoard.pieces) {
                if (piece.isDragging) piece.draw(g!!, this)
            }
        } else {
            for (piece in chessBoard.pieces) {
                piece.draw(g!!, this)
            }
        }
    }

    private fun drawHud(g: Graphics?) {
        g!!.color = Color(70, 73, 255)
        debugHud.draw(g)
    }

    /**
     * draws the stuff on the screen
     * @param g the JPanel graphics
     */
    @Synchronized
    override fun paint(g: Graphics?) {
        super.paint(g)
        //g.clearRect(0,0,boardSize,boardSize);

        chessBoard.draw(g)
        drawPieces(g)
        if (Reference.DEBUG) {
            drawHud(g)
        }

    }

    /**
     * starts the main game loop thread
     */
    override fun run() {
        val t = Thread.currentThread()
        //repaint();
        while (t === gameLoop) {

            gameUpdate()

            try {
                Thread.sleep(5)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
    }

    override fun mousePressed(e: MouseEvent) {
        for (piece in chessBoard.pieces) {

            if (piece.posX < mouseX && mouseX < piece.posX + Reference.tileSize && piece.posY < mouseY && mouseY < piece.posY + Reference.tileSize) {
                piece.isDragging = true
                dragging = true

                //determines offset for position of piece relative to mouse while dragging
                offsetX = mouseX - piece.posX
                offsetY = mouseY - piece.posY
                piece.posX = mouseX - offsetX
                piece.posY = mouseY - offsetY
                //System.out.println("dragging");
                debugHud.addLine("Dragging", dragging)
            }
        }
    }

    override fun mouseReleased(e: MouseEvent) {
        for (piece in chessBoard.pieces) {
            if (piece.isDragging) {
                piece.moveOkay = true
                for (otherPiece in chessBoard.pieces) {
                    if (mouseBoardX == otherPiece.boardX && mouseBoardY == otherPiece.boardY) {
                        piece.moveOkay = false
                    }
                }
                if (!piece.checkMove(mouseBoardX, mouseBoardY)) {
                    piece.moveOkay = false
                }
                if (piece.moveOkay) {
                    piece.boardX = mouseBoardX
                    piece.boardY = mouseBoardY
                    Reference.turn++
                    debugHud.addLine("Turn", Reference.turn)

                }
                debugHud.addLine("Move Okay", piece.moveOkay)
                piece.resetToGrid()
                piece.isDragging = false
                dragging = false
                debugHud.addLine("Dragging", dragging)
            }
        }
    }

    override fun mouseMoved(e: MouseEvent) {
        mouseX = e.x
        mouseY = e.y
        mouseBoardX = mouseX / Reference.tileSize
        mouseBoardY = mouseY / Reference.tileSize
        //println("Position: $mouseX, $mouseY")
    }

    override fun mouseDragged(e: MouseEvent) {
        mouseX = e.x
        mouseY = e.y
        mouseBoardX = mouseX / Reference.tileSize
        mouseBoardY = mouseY / Reference.tileSize

        for (piece in chessBoard.pieces) {

            if (piece.isDragging) {
                piece.posX = mouseX - offsetX
                piece.posY = mouseY - offsetY
                //System.out.println("dragging");
            }
            if (piece.isDragging) dragging = true
        }

        //System.out.println("Dragged: " + mouseX + ", " + mouseY);
    }

    override fun mouseClicked(e: MouseEvent) {

    }

    override fun mouseEntered(e: MouseEvent) {

    }

    override fun mouseExited(e: MouseEvent) {

    }
}
