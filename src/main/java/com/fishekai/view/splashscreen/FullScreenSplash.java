package com.fishekai.view.splashscreen;

import com.fishekai.engine.Introduction;
import com.fishekai.view.GamePanel;
import com.fishekai.view.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FullScreenSplash {
    private String path = "/images/fishekai_welcome.jpg";
    GamePanel gamePanel;
    KeyHandler keyHandler;
    BufferedImage splashImage;
    FullScreenScroll fullScreenScroll;

    // Constructor
    public FullScreenSplash(GamePanel gp, KeyHandler kh) {
        gamePanel = gp;
        keyHandler = kh;
        fullScreenScroll = new FullScreenScroll(gamePanel, keyHandler);
        try {
            splashImage = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        path = null;
    }

    public void setSplashImage(String path) {
        // Display the splash screen:
        try {
            splashImage = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        path = null;
    }

    public void draw(Graphics graphics){

        // Draw the splash screen but fit it to the screen size:
        graphics.drawImage(splashImage, 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);
    }
    public void draw(Graphics graphics, String text){

        // Draw the splash screen and overlay the scrollPane:
        graphics.drawImage(splashImage, 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);



    }
    public void update(String path){
        if(keyHandler.enterJustPressed || keyHandler.spaceJustPressed){
            // Remove the image from the screen:
            setSplashImage(path);
            fullScreenScroll.update();
            gamePanel.incrementOrder();
        }
    }

    /**
     * Add text to the splash screen.
     */
    public void addText(String s, int i, int i1) {
        Graphics2D g2 = (Graphics2D) splashImage.getGraphics();
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 15));



        g2.drawString(s, i, i1);
        g2.dispose();

    }

    public void remove() {
        gamePanel.remove(fullScreenScroll);
    }
}