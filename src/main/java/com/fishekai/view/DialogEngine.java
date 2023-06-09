package com.fishekai.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DialogEngine {
    BufferedImage dialogBubble;
    private final GamePanel gp;
    private final KeyHandler kh;
    String input;
    boolean display = false;

    public DialogEngine(GamePanel gp){
        try {
            dialogBubble = ImageIO.read(getClass().getResourceAsStream("/images/dialogue.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.gp = gp;
        this.kh = gp.getKh();
    }

    public void update(String input){
        display = true;
        this.input = input;
        try {
            dialogBubble = ImageIO.read(getClass().getResourceAsStream("/images/dialogue.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(){
        if(kh.enterPressed){
            // Remove the dialogue bubble
            dialogBubble = null;
            input = null;
            display = false;
        }

    }

    public void draw(Graphics2D g2){
        if(display){
            // Display the dialogue bubble
            g2.drawImage(dialogBubble, 50, 200, 600, 300, null);

            g2.setFont(new Font("Arial", Font.BOLD, 18));
//            g2.drawString(input, 250, 250);
            drawWrappedText(g2, input, 100, 250, 500,300);
        }
    }

    static void write(Graphics2D g2, String input) {
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        //            g2.drawString(input, 250, 250);
        drawWrappedText(g2, input, 10, 10, 90, 90);
    }

    static void drawWrappedText(Graphics g, String text, int x, int y, int w, int h) {
        JTextArea ta = new JTextArea(text);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setBounds(0, 0, w, h);
        ta.setOpaque(false);
        ta.setFont(g.getFont());
        ta.setForeground(Color.WHITE);
        Graphics g2 = g.create(x, y, w, h); // Use new graphics to leave original graphics state unchanged
        ta.paint(g2);
    }
}