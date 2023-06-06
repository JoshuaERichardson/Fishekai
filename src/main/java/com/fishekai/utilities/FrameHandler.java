package com.fishekai.utilities;

import javax.swing.*;
import java.awt.*;

/**
 * The separate window of the game.  Displays the splash screen of the game.
 */
public class FrameHandler {
    // Make a new window
    private JFrame window;
    // Make a new panel
    private JPanel panel;
    // Saving the path to the splash screen
    private final String WELCOME_PATH = "/images/fishekai_welcome.jpg";
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    // Constructor:
    public FrameHandler(){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 600);

    }


    public void showSplashScreen(JFrame window2) {
        // Display the splash screen:
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon image = new ImageIcon(getClass().getResource(WELCOME_PATH));
                // Shrink the image to fit the window
                g.drawImage(image.getImage(), 0, 0, FrameHandler.this.WIDTH, FrameHandler.this.HEIGHT, this);
            }
        };

        window2.add(panel);
        window2.setVisible(true);
    }

    public void hideSplashScreen() {
        // Remove the splash screen:
        window.setVisible(false);
        window.remove(panel);
    }
}