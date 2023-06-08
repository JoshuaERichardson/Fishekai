package com.fishekai.view.object;

import com.fishekai.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sign{
   private final int col;
   private final int row;
   private final String text;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public BufferedImage image;


    public Sign(int col, int row, String text){
        this.col = col;
        this.row = row;
        this.text = text;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/fish2.png"));
        } catch (IOException e){
            System.out.println("Failed to make the image for sign");
            e.printStackTrace();
        }
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
    public void draw(Graphics2D g2, GamePanel gp) {
        g2.drawImage(image, col, row, gp.tileSize, gp.tileSize, null);
    }
}