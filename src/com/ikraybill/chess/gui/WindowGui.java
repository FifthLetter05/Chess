package com.ikraybill.chess.gui;

import com.ikraybill.chess.shared.Reference;

import javax.swing.*;
import java.awt.*;

/**
 * The class that controls the window pane (pun not intended)
 */
public class WindowGui extends JFrame{
    private int windowWidth = 600;
    private int windowHeight = 640;

    public WindowGui(){
        super(Reference.TITLE);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(windowWidth,windowHeight);
        add(new ChessGui());
        pack();
    }

    public synchronized void paint(Graphics g){
        g.clearRect(0,0, windowWidth, windowHeight);
        System.out.println("Hey!");
    }
}
