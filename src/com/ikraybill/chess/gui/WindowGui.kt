package com.ikraybill.chess.gui

import com.ikraybill.chess.shared.Reference
import com.ikraybill.chess.shared.Reference.boardSize

import javax.swing.*
import java.awt.*

/**
 * The class that controls the window pane (pun not intended)
 */
class WindowGui : JFrame(Reference.TITLE) {
    private val windowWidth = 600
    private val windowHeight = 640

    init {

        isVisible = true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isResizable = false
        layout = FlowLayout(FlowLayout.CENTER)
        setSize(boardSize + 200, boardSize)
        add(ChessGui())
        add(DebugHud())
        pack()
    }

    @Synchronized
    override fun paint(g: Graphics?) {
        g!!.clearRect(0, 0, windowWidth, windowHeight)
        //System.out.println("Hey!");
    }
}
