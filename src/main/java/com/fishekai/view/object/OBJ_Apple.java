package com.fishekai.view.object;

import javax.imageio.ImageIO;
import java.io.IOException;

class OBJ_Apple extends SuperObject {
    public OBJ_Apple() {

        name = "Apple";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/apple.png"));
        } catch(IOException e){
            System.out.println("Failed to make the image for apple");
            e.printStackTrace();
        }
    }


}