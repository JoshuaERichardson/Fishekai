package com.fishekai.view;

import com.fishekai.engine.Fishekai;

import javax.swing.*;
import java.awt.*;

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



    private final InventoryPanel inventoryPanel;

public MainWindow(KeyHandler kh, Fishekai fishekai) {
        this.fishekai = fishekai;

        // Overall display info
        setTitle("Fishekai");
        setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
//        setResizable(false);
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
        sidePanel = new SidePanel(mainPanels, fishekai.textPlayer);

        // Create the inventory panel:
        inventoryPanel = new InventoryPanel(fishekai.textPlayer, this);


        // Register the passed key handler:
        this.kh = kh;
        this.addKeyListener(kh);




        GridBagConstraints c = new GridBagConstraints();
        // Add all the panels to the main window:
        c = GridBagConstraints(c, 0, 0, 4, 5, 1);
        this.add(mainPanels, c);
        c = GridBagConstraints(c, 4,0,3,6, 0.5);
        this.add(sidePanel, c);
        c = GridBagConstraints(c, 0, 5, 3, 1, 0.5);
        this.add(inventoryPanel, c);
        this.revalidate();
        this.repaint();
    }

    private GridBagConstraints GridBagConstraints(GridBagConstraints c, int startX, int startY, int width, int height, double weight) {
        c.gridx = startX;
        c.gridy = startY;
        c.gridwidth = width;
        c.gridheight = height;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = weight;
        c.weighty = weight;
        return c;
    }
    public void nextCard() {
        System.out.println("swapping cards");
        cardLayout.next(mainPanels);

    }


    public void startGameTimer() {
        gamePanel.setupGame();
        gamePanel.startGameTimer();
    }

    public IntroPanel getIntroPanel() {
        return introPanel;
    }

    public InstructionsPanel getInstructionsPanel() {
        return instructionsPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public MainPanel getMainPanels() {
        return mainPanels;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public KeyHandler getKh() {
        return kh;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public Fishekai getFishekai() {
        return fishekai;
    }
    public InventoryPanel getInventoryPanel() {
        return inventoryPanel;
    }
}