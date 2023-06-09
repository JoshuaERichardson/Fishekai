package com.fishekai.view.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Banana extends SuperObject{
    public OBJ_Banana() {
        name = "Banana";
        description = "it's bananas, B.A.N.A.N.A.S";
        type =  "food";
        modifier = 5;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/banana.png"));
        } catch(
        IOException e){
            System.out.println("Failed to make the image for apple");
            e.printStackTrace();
    }
    }
}
