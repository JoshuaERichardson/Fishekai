package com.fishekai.view;

import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends MainPanel{
    public final String PATH = "/images/instructions.jpg";
    private Image image;

    public InstructionsPanel() {
        super();
        loadImage(PATH);
    }
    void loadImage(String path){
        ImageIcon icon = new ImageIcon(getClass().getResource(PATH));
        image = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the image on the panel
        if(image != null){
            g.drawImage(image, 0, 0, MAIN_PANEL_SIZE.width, MAIN_PANEL_SIZE.height, null);
        }
    }




}