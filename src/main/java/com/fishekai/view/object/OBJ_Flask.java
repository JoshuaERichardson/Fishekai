package com.fishekai.view.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Flask extends SuperObject{
    public OBJ_Flask() {
        name = "Flask";
        description = "A weather worn, leather wrapped flask caked with sand. It smells of rancid rum, but there is not a drop left. Why is the rum always gone?";
        type = "water";
        modifier = 0;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/flask.png"));
        } catch(IOException e){
            System.out.println("Failed to make the image for apple");
            e.printStackTrace();
        }
    }
}