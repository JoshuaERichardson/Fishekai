package com.fishekai.view;

import javax.swing.*;
import java.awt.*;

public class IntroPanel extends MainPanel{
    public String PATH = "/images/fishekaiTitleTeam3.png";
    private Image image;

    public IntroPanel() {
        loadImage(PATH);
        setPreferredSize(MainPanel.MAIN_PANEL_SIZE);
        // Force the photo to be the same size as the panel

    }
    void loadImage(String path){
        ImageIcon icon = new ImageIcon(getClass().getResource(PATH));
        image = icon.getImage();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);

        // Draw the image on the panel
        if(image != null){
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            // Draw the text
            g.setColor(Color.WHITE);  // Set the text color
            g.setFont(new Font("Arial", Font.BOLD, 20));  // Set the font
            g.drawString("PRESS ENTER TO CONTINUE           ", getWidth() - 320, getHeight() - 20);
        }
    }




}