package com.ikraybill.chess.gui;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DebugHud {
    private String renderedText;
    private Map<String, Object> content;
    private int fontSize;

    public DebugHud(){
        content = new HashMap<>();
    }

    public void addLine(String description, Object data){
        content.put(description, data);
        renderedText = "";
        for (Map.Entry<String, Object> me: content.entrySet()) {
            renderedText += me.getKey() + ": " + me.getValue().toString() + "\n";
        }

    }

    public void removeLine(String description){
        content.remove(description);
        renderedText = "";
        for (Map.Entry<String, Object> me: content.entrySet()) {
            renderedText += me.getKey() + ": " + me.getValue().toString() + "\n";
        }
    }

    //TODO
    public void draw(Graphics g){
        int i = 1;
        System.out.println(i);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        for (Map.Entry<String, Object> me: content.entrySet()) {
            i++;
            g.drawString(me.getKey() + ": " + me.getValue().toString(), 10, fontSize * i);
        }
    }
}
