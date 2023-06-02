package com.fishekai.view;

import javax.swing.*;

/**
 * The pixel art for the beach area.
 */
public class Main {

    public static void main(String[] args) {
        // New JFrame
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Fishekai - Beach");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.setLocationRelativeTo(null);

        // Set the size of the frame, since the preferable size is failing:
        window.setSize(768, 576);
        window.setVisible(true);




    }

}