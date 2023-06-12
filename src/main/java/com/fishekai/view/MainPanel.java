package com.fishekai.view;

import com.fishekai.models.Player;
import com.fishekai.view.object.SuperObject;

import javax.swing.*;
import java.awt.*;

class MainPanel extends JPanel {
    // Screen Size:
    // SCREEN SETTINGS

    final static int originalTileSize = 16; // original tile size
    final static int scale = 3; // scale of the game
    public final static int tileSize = originalTileSize * scale; // tile size
    public final static int maxScreenCol = 12; // max screen columns
    public final static int maxScreenRow = 12; // max screen rows
    public final static int screenWidth = tileSize * maxScreenCol;
    public final static int screenHeight = tileSize * maxScreenRow;
    final int FPS = 60; // screen frames per second
    final static Dimension MAIN_PANEL_SIZE = new Dimension(screenWidth, screenHeight);


    public MainPanel() {
//        setSize(MAIN_PANEL_SIZE);
//        setMaximumSize(MAIN_PANEL_SIZE);
//        setMinimumSize(MAIN_PANEL_SIZE);
        setPreferredSize(MAIN_PANEL_SIZE);
        setVisible(true);
//        setBackground(Color.GRAY);

    }

    public MainPanel(LayoutManager layoutManager) {
        this();
        setLayout(layoutManager);

    }



}