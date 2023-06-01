package com.fishekai.view.splashscreen;

import com.fishekai.view.GamePanel;
import com.fishekai.view.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FullScreenSplash {
    private String path = "/images/fishekai_welcome.jpg";
    GamePanel gamePanel;
    KeyHandler keyHandler;
    BufferedImage splashImage;

    // Constructor
    public FullScreenSplash(GamePanel gp, KeyHandler kh) {
        gamePanel = gp;
        keyHandler = kh;
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
    public void update(String path){
        if(keyHandler.enterPressed || keyHandler.spacePressed){
            // Remove the image from the screen:
            setSplashImage(path);
            gamePanel.incrementOrder();
            // Pause:
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}