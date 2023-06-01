package com.fishekai.view;

import com.fishekai.utilities.FrameHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class StartGame {
    private final String WELCOME_PATH = "/images/fishekai_welcome.jpg";
    GamePanel gamePanel;
    KeyHandler keyHandler;
    BufferedImage splashImage;

    // Constructor
    public StartGame(GamePanel gp, KeyHandler kh) {
        gamePanel = gp;
        keyHandler = kh;
        splashImage = showSplashScreen();

    }

    public BufferedImage showSplashScreen() {
        // Display the splash screen:
        BufferedImage splashImage = null;
        try {
            splashImage = ImageIO.read(getClass().getResourceAsStream("/images/fishekai_welcome.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return splashImage;
    }

    public void draw(Graphics graphics){
        // Draw the splash screen but fit it to the screen size:
        graphics.drawImage(splashImage, 0, 0, gamePanel.screenWidth, gamePanel.screenHeight, null);
    }
    public void update(){
        if(keyHandler.enterPressed || keyHandler.spacePressed){
            // Remove the image from the screen:
            splashImage = null;
        }
    }
}
