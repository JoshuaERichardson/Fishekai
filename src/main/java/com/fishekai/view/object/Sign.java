package com.fishekai.view.object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Sign{
   private final int col;
   private final int row;
   private final String text;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public Sign(int col, int row, String text){
        this.col = col;
        this.row = row;
        this.text = text;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public String getText() {
        return text;
    }
}