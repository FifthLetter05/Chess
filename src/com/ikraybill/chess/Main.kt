package com.ikraybill.chess

import com.ikraybill.chess.gui.WindowGui
import javax.swing.SwingUtilities

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
