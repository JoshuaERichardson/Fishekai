package com.fishekai.view;

import com.fishekai.engine.Display;
import com.fishekai.engine.HelpPopup;
import com.fishekai.models.Player;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    // Size:
    public static final int WIDTH = 400;
    public static final int HEIGHT = 1000;
    private final JPanel mainPanel;
    private HelpPopup helpPopup;

    public SidePanel(MainPanel mainPanel, Player textPlayer) {
        this.mainPanel = mainPanel;
        setSize(new Dimension(HEIGHT, WIDTH));
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

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public HelpPopup getHelpPopup() {
        return helpPopup;
    }
}