package com.fishekai.view.object;

import javax.imageio.ImageIO;
import java.io.IOException;

class OBJ_Stick extends SuperObject{
    public OBJ_Stick(){
        name = "Stick";
        description = "Smooth and perfectly straight, they would make a great fishing pole";
        type = "tool";
        modifier = 0;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/apple.png"));
        } catch(IOException e){
            System.out.println("Failed to make the image for apple");
            e.printStackTrace();
        }
    }

}