package com.ikraybill.chess.gui

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

    private val pieces = LinkedList<Piece>()
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
            Reference.chessIcons = ImageIO.read(javaClass.getResource("ChessIcons.png"))
        } catch (e: IOException) {
            e.printStackTrace()
        }

        preferredSize = Dimension(if (Reference.DEBUG) Reference.boardSize + 200 else Reference.boardSize , Reference.boardSize)
        this.addMouseListener(this)
        this.addMouseMotionListener(this)

        white = Player(0)
        black = Player(1)

        pieces.add(Rook(0, 0, white))
        pieces.add(Knight(1, 0, white))
        pieces.add(Bishop(2, 0, white))
        pieces.add(Queen(3, 0, white))
        pieces.add(King(4, 0, white))
        pieces.add(Bishop(5, 0, white))
        pieces.add(Knight(6, 0, white))
        pieces.add(Rook(7, 0, white))

        for (i in 0..7) {
            pieces.add(Pawn(i, 1, white))
        }

        for (i in 0..7) {
            pieces.add(Pawn(i, 6, black))
        }

        pieces.add(Rook(0, 7, black))
        pieces.add(Knight(1, 7, black))
        pieces.add(Bishop(2, 7, black))
        pieces.add(Queen(3, 7, black))
        pieces.add(King(4, 7, black))
        pieces.add(Bishop(5, 7, black))
        pieces.add(Knight(6, 7, black))
        pieces.add(Rook(7, 7, black))

        repaint()

        gameLoop = Thread(this)
        gameLoop.start()
    }

    /**
     * updates the game's graphics and logic
     */
    fun gameUpdate() {
        repaint()
    }

    fun drawBoard(g: Graphics?) {
        g!!.color = Color(77, 49, 31)
        run {
            var i = 0
            while (i < 8) {
                var j = 0
                while (j < 8) {
                    g.fillRect(i * Reference.tileSize, j * Reference.tileSize, Reference.tileSize, Reference.tileSize)
                    j += 2
                }
                i += 2
            }
        }
        run {
            var i = 1
            while (i < 9) {
                var j = 1
                while (j < 9) {
                    g.fillRect(i * Reference.tileSize, j * Reference.tileSize, Reference.tileSize, Reference.tileSize)
                    j += 2
                }
                i += 2
            }
        }
        g.color = Color(217, 155, 114)
        run {
            var i = 1
            while (i < 9) {
                var j = 0
                while (j < 8) {
                    g.fillRect(i * Reference.tileSize, j * Reference.tileSize, Reference.tileSize, Reference.tileSize)
                    j += 2
                }
                i += 2
            }
        }
        var i = 0
        while (i < 8) {
            var j = 1
            while (j < 9) {
                g.fillRect(i * Reference.tileSize, j * Reference.tileSize, Reference.tileSize, Reference.tileSize)
                j += 2
            }
            i += 2
        }
    }

    fun drawPieces(g: Graphics?) {
        if(dragging) {
            for (piece in pieces) {
                if (!piece.isDragging) piece.draw(g!!, this)
            }
            for (piece in pieces) {
                if (piece.isDragging) piece.draw(g!!, this)
            }
        } else {
            for (piece in pieces) {
                piece.draw(g!!, this)
            }
        }
    }

    fun drawHud(g: Graphics?) {
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

        drawBoard(g)
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
        for (piece in pieces) {

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
        dragging = false
        for (piece in pieces) {
            if (piece.isDragging) {
                piece.moveOkay = true
                for (otherPiece in pieces) {
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

                }
                debugHud.addLine("Move Okay", piece.moveOkay)
                piece.resetToGrid()
                piece.isDragging = false
                debugHud.removeLine("Dragging")
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

        for (piece in pieces) {

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
