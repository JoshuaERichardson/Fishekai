package com.fishekai.view;

import com.fishekai.engine.Fishekai;
import com.fishekai.view.object.SuperObject;

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
        public GamePanel gamePanel;
        MainPanel mainPanels;
        private CardLayout cardLayout;
        private KeyHandler kh;
        private SidePanel sidePanel;
        private Fishekai fishekai;

public MainWindow(KeyHandler kh, Fishekai fishekai) {
        this.fishekai = fishekai;

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
        gamePanel = new GamePanel(kh, fishekai);

        cardLayout = new CardLayout();
        mainPanels = new MainPanel(cardLayout);
        mainPanels.add(introPanel);
        mainPanels.add(instructionsPanel);
        mainPanels.add(gamePanel);

        // Create the side panel:
        sidePanel = new SidePanel();


        // Register the passed key handler:
        this.kh = kh;
        this.addKeyListener(kh);


        // Add all the panels to the main window:
        this.add(mainPanels);
        this.add(sidePanel, BorderLayout.EAST);
        this.revalidate();
        this.repaint();
    }

    public void nextCard(){
        System.out.println("swapping cards");
        cardLayout.next(mainPanels);

    }


        public void startGameTimer() {
            gamePanel.setupGame();
            gamePanel.startGameTimer();
        }
}