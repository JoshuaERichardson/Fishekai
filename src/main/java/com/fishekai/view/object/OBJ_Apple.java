package com.fishekai.view.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Apple extends SuperObject {
    public OBJ_Apple() {

        name = "Apple";
        description = "an apple a day keeps the doctor away";
        type =  "food";
        modifier = 2;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/apple.png"));
        } catch(IOException e){
            System.out.println("Failed to make the image for apple");
            e.printStackTrace();
        }
    }
}