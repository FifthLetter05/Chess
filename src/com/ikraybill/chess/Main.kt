package com.ikraybill.chess

import com.ikraybill.chess.gui.ChessGui
import com.ikraybill.chess.gui.WindowGui
import com.ikraybill.chess.shared.Utility

import javax.swing.*

/**
 * The class that starts it all
 */
object Main {
    /**
     * starts the whole thing by creating the ChessGui class
     * @param args  commandline arguments (not used)
     */
    @JvmStatic
    fun main(args: Array<String>) {
        SwingUtilities.invokeLater {
            println("Created GUI on EDT? " + SwingUtilities.isEventDispatchThread())
            WindowGui()
        }
    }
}
