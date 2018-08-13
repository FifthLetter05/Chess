package com.ikraybill.chess.shared;

import java.awt.*;

import static com.ikraybill.chess.shared.Reference.chessIcons;

/**
 * Created by iek2d on 4/14/2017.
 */
public class Utility {
    /**
     * retrieves the specific chess piece icon from ChessIcons.png for a specific chess piece
     * defaults to white pawn if parameters exceed bounds of the icon image
     * @param ID        the ID of the piece
     * @param color     the color of the piece
     * @return          the icon corresponding to the chess piece
     */
    public static Image getChessIcon(int ID, int color){
        if ((ID>5 || ID<0) || (color>1 || color < 0)) {
            System.out.println("Not a valid icon!");
            ID = 0;
            color = 0;
        }
        return chessIcons.getSubimage(Math.abs(5-ID)*(Reference.CHESS_ICON_W),color * (Reference.CHESS_ICON_H),Reference.CHESS_ICON_W,Reference.CHESS_ICON_H);
    }
}
