package com.fishekai.view.splashscreen;

import com.fishekai.engine.Introduction;
import com.fishekai.view.GamePanel;
import com.fishekai.view.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class FullScreenScroll extends JScrollPane{
    GamePanel gamePanel;
    KeyHandler keyHandler;
    JTextArea textArea = new JTextArea();
    Introduction intro = new Introduction();

    // Constructor
    public FullScreenScroll(GamePanel gp, KeyHandler kh) {
        gamePanel = gp;
        keyHandler = kh;
    }



    // Update method:
    public void update() {
        // Update the scroll pane:
        textArea.setText("Help" + intro.showIntro());
        textArea.setFont(new Font("Arial", Font.BOLD, 15));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Make the JScrollPane


        this.setPreferredSize(new Dimension(500, 500));
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);




        this.setViewportView(textArea);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
        gamePanel.add(this);


    }


}