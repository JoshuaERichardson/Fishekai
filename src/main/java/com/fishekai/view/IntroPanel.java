package com.fishekai.view;

import javax.swing.*;
import java.awt.*;

public class IntroPanel extends MainPanel{
    public String PATH = "/images/fishekai_welcome.jpg";
    private Image image;

    public IntroPanel() {
        super();

        loadImage(PATH);

        // Force the photo to be the same size as the panel

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