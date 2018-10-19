package com.ikraybill.chess.gui

import com.ikraybill.chess.shared.Reference
import com.ikraybill.chess.shared.Reference.boardSize

import javax.swing.*
import java.awt.*

/**
 * The class that controls the window pane (pun not intended)
 */
class WindowGui : JFrame(Reference.TITLE) {

    init {
        isVisible = true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isResizable = false
        layout = FlowLayout(FlowLayout.CENTER)
        setSize(boardSize, boardSize)
        add(ChessGui())
        pack()
    }
}
