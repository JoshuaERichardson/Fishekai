package com.fishekai.view.buttons;

/**
 * Buttons for JFrames
 */
class Button extends javax.swing.JButton {
    public int x, y;
    public int width, height;
    public String text;
    public boolean hover = false;
    public boolean clicked = false;
    public boolean visible = true;

    public Button(int x, int y, int width, int height, String text){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public void update(){
        if(visible){
            if(hover){
                if(clicked){
                    clicked = false;
                }
            }
        }
    }
}