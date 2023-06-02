package com.fishekai.view.buttons;

import javax.swing.*;

public class HelpButton extends Button{
    public int x, y, width, height;
    public boolean hover, clicked, visible;

    public HelpButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    public void update(){
        if(visible){
            if(hover){
                if(clicked){
                    clicked = false;
                    // Create a pop up:
                    JOptionPane.showMessageDialog(null, "This is a pop up!");
                }
            }
        }
    }
}