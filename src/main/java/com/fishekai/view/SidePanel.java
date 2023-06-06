package com.fishekai.view;

import com.fishekai.engine.Display;
import com.fishekai.engine.HelpPopup;

import javax.swing.*;

class SidePanel extends JPanel {
    // Size:
    public static final int WIDTH = 400;
    public static final int HEIGHT = 1000;
    private final JPanel mainPanel;
    private HelpPopup helpPopup;

    public SidePanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setBackground(java.awt.Color.GREEN);

        // Make the Help Button:
        JButton helpButton = new JButton("Help");
        helpButton.setRequestFocusEnabled(false);

        // Add the listener to the Help:
        helpButton.addActionListener(e -> new HelpPopup(Display.showHelp()).createHelpDialog().setVisible(true));

        // Add the button to the panel:
        this.add(helpButton);


    }



}