package com.fishekai.view;

import com.fishekai.engine.Display;
import com.fishekai.engine.HelpPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;

class SidePanel extends JPanel {
    // Size:
    public static final int WIDTH = 400;
    public static final int HEIGHT = 1000;
    private HelpPopup helpPopup;

    public SidePanel() {
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setBackground(java.awt.Color.GREEN);

        // Make the Help Button:
        JButton helpButton = new JButton("Help");

        // Add the listener to the Help:
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getParent(), Display.showHelp());
            }
        });

        // Add the button to the panel:
        this.add(helpButton);


    }



}