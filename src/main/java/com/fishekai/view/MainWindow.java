package com.fishekai.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
        // Screen Size:
        public static final int SCREEN_WIDTH = 1200;
        public static final int SCREEN_HEIGHT = 1000;

        // The cards for the layout:
        private IntroPanel introPanel;
        private InstructionsPanel instructionsPanel;
        private GamePanel gamePanel;
        MainPanel mainPanels;
        private CardLayout cardLayout;
        private KeyHandler kh;

public MainWindow(KeyHandler kh) {
        // Overall display info
        setTitle("Fishekai");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);

        // Create the list of panels for the main screen:
        introPanel = new IntroPanel();
        instructionsPanel = new InstructionsPanel();
        gamePanel = new GamePanel(kh);

        cardLayout = new CardLayout();
        mainPanels = new MainPanel(cardLayout);
        mainPanels.add(introPanel);
        mainPanels.add(instructionsPanel);
        mainPanels.add(gamePanel);

        // Register the passed key handler:
        this.kh = kh;
        this.addKeyListener(kh);

        // Add the main panel to the frame:
        this.add(mainPanels);
        this.revalidate();
        this.repaint();


    }

    public void nextCard(){
        System.out.println("swapping cards");
        cardLayout.next(mainPanels);

    }


        public void startGameTimer() {
            gamePanel.startGameTimer();
        }
}