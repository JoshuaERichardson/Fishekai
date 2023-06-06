package com.fishekai.view.entity;

import com.fishekai.engine.Fishekai;
import com.fishekai.view.GamePanel;
import com.fishekai.view.KeyHandler;
import com.fishekai.view.physics.LocationSwitcher;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasApple = 0;
    private final Fishekai fishekai;
    private final LocationSwitcher locationSwitcher;

    public Player(GamePanel gp, KeyHandler keyH, Fishekai fishekai){

        this.gp = gp;
        this.keyH = keyH;
        this.fishekai = fishekai;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        // Collision box
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        setDefaultValues();
        getPlayerImage();

        locationSwitcher = new LocationSwitcher();
    }

    public void setDefaultValues() {
        worldX = 400;
        worldY = 400;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/FACEDOWN1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/FACEDOWN2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/FACEUP1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/FACEUP2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/FACELEFT1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/FACELEFT2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/FACERIGHT1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/player/FACERIGHT2.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.spacePressed == true){
            if (keyH.upPressed) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressed == true) {
                direction = "right";
            } else if (keyH.spacePressed ==  true) {
                int objIndex = gp.collisionChecker.checkObject(this, true);
                pickUpObject(objIndex);
            }

            // Check Tile Collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            // If Collision is false, player can move
            if (collisionOn == false) {
                switch (direction) {
                    case "up"   :    worldY -= speed;   break;
                    case "down" :    worldY += speed;   break;
                    case "left" :    worldX -= speed;   break;
                    case "right":    worldX += speed;   break;
                }
            }

            // Check door Collision
            int objIndex = gp.collisionChecker.checkObject(this, true);
            walkThroughDoor(objIndex);




            spriteCounter++;
            if (spriteCounter > 12){
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    private void pickUpObject(int i) {
        if(i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Apple":
                    if (keyH.spacePressed) {
                        hasApple++;
                        gp.obj[i] = null;
                        System.out.println("Apples: " + hasApple);
                    }
                    break;
            }
        }
    }

    public void walkThroughDoor(int i) {
        if(i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
//                case "Apple":
//                    if (keyH.spacePressed){
//                        hasApple++;
//                        gp.obj[i] = null;
//                        System.out.println("Apples: " + hasApple);
//                    }
//                    break;
                case "Door":
                    String destination = gp.obj[i].getLocation();
                    gp.obj[i].getLocation();
                    locationSwitcher.moveScenes(fishekai,destination);
            }

        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction) {
            case "down":
                if (spriteNum ==1) image = down1;
                else image = down2;
                break;
            case "up":
                if (spriteNum == 1) image = up1;
                else image = up2;
                break;
            case "left":
                if (spriteNum == 1) image = left1;
                else image = left2;
                break;
            case "right":
                if(spriteNum == 1) image = right1;
                else image = right2;
                break;
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}