package com.ikraybill.chess;

import com.ikraybill.chess.gui.ChessGui;
import com.ikraybill.chess.gui.WindowGui;

import javax.swing.*;

/**
 * The class that starts it all
 */
public class Main {
    /**
     * starts the whole thing by creating the ChessGui class
     * @param args  commandline arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Created GUI on EDT? "+ SwingUtilities.isEventDispatchThread());
            new WindowGui();
        });
    }
}
