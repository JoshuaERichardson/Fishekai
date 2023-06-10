package com.fishekai.view;

import com.fishekai.engine.Fishekai;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    // Screen Size:
    public static final int SCREEN_WIDTH = 1159;
    public static final int SCREEN_HEIGHT = 1002;

        // The cards for the layout:
        private IntroPanel introPanel;
        private InstructionsPanel instructionsPanel;
        public GamePanel gamePanel;
        MainPanel mainPanels;
        private CardLayout cardLayout;
        private KeyHandler kh;
        private SidePanel sidePanel;
        private Fishekai fishekai;
        private StatusPanel statusPanel;




    private final InventoryPanel inventoryPanel;

public MainWindow(KeyHandler kh, Fishekai fishekai) {
        this.fishekai = fishekai;

        // Overall display info
        setTitle("Fishekai");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
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
        mainPanels.setPreferredSize(MainPanel.MAIN_PANEL_SIZE);

        // Create the status panel:
        statusPanel = new StatusPanel(fishekai, mainPanels);

        // Create the side panel:
        sidePanel = new SidePanel(mainPanels, fishekai.textPlayer, fishekai);

        // Create the inventory panel:
        inventoryPanel = new InventoryPanel(fishekai.textPlayer, this);


        // Register the passed key handler:
        this.kh = kh;
        this.addKeyListener(kh);

        // Add all the panels to the main window:
        this.add(mainPanels, customGridBag(0, 0, 3, 2, 1, true, true));
        this.add(statusPanel, customGridBag(3, 0, 2, 3, 0, true, true));
        this.add(sidePanel, customGridBag(5, 0, 2, 3, 0, true, true));
        this.add(inventoryPanel, customGridBag(0, 2, 3, 1, 0, true, true));
        this.revalidate();
        this.repaint();
        System.out.println("Main Window: " + getWidth() + " " + getHeight());



}



    private GridBagConstraints customGridBag(int startX, int startY, int width, int height, double weight, boolean fillVert, boolean fillHoriz) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = startX;
        c.gridy = startY;
        c.gridwidth = width;
        c.gridheight = height;
        c.weightx = weight;
        c.weighty = weight;
        if (fillHoriz && fillVert) c.fill = GridBagConstraints.BOTH;
        else if (fillHoriz) c.fill = GridBagConstraints.HORIZONTAL;
        else if (fillVert) c.fill = GridBagConstraints.VERTICAL;
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

    public StatusPanel getStatusPanel() {
        return statusPanel;
    }
}