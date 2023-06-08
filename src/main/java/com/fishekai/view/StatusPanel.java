package com.fishekai.view;

import com.fishekai.engine.Fishekai;

import javax.swing.*;

class StatusPanel extends JPanel {
    private final Fishekai fishekai;

    public StatusPanel(Fishekai fishekai, MainPanel mainPanel) {
        this.fishekai = fishekai;
        setBackground(java.awt.Color.BLACK);
        int width = mainPanel.getWidth()/2;
        int height = mainPanel.getHeight();
        setSize(width, height);
    }


}