package com.fishekai.view;

import javax.imageio.ImageIO;
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
            g2.drawImage(dialogBubble, 200, 200, 600, 300, null);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            g2.drawString(input, 250, 250);


        }
    }
}