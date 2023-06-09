package com.fishekai.view.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Water extends SuperObject{
    public OBJ_Water() {
        name = "Water";
        description = "refreshing and improves health";
        type = "water";
        modifier = 2;


        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/water.png"));
        } catch (IOException e) {
            System.out.println("Failed to make the image for apple");
            e.printStackTrace();
        }
    }
}