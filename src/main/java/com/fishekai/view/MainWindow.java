package com.fishekai.view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
        // Screen Size:
        public static final int SCREEN_WIDTH = 1200;
        public static final int SCREEN_HEIGHT = 1000;

public MainWindow() {
        setTitle("Fishekai");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);

        IntroPanel introPanel = new IntroPanel();
        introPanel.setLayout(new BorderLayout());
        add(introPanel, BorderLayout.CENTER);
    }


}