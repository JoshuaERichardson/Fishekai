package com.fishekai.view;

import javax.swing.*;
import java.awt.*;

class MainPanel extends JPanel {
    // Screen Size:
    // SCREEN SETTINGS
    final int originalTileSize = 16; // original tile size
    final int scale = 3; // scale of the game
    public final int tileSize = originalTileSize * scale; // tile size
    public final int maxScreenCol = 16; // max screen columns
    public final int maxScreenRow = 12; // max screen rows
    public final int screenWidth = tileSize * maxScreenCol; // screen width (768 pixels)
    public final int screenHeight = tileSize * maxScreenRow; // screen height (576 pixels)
    final int FPS = 60; // screen frames per second

    public MainPanel() {
        setMaximumSize(new Dimension(screenWidth, screenHeight));
        setLayout(null);
        setVisible(true);
        setBackground(Color.BLACK);
    }

}