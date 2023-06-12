package com.fishekai.view.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Hook extends SuperObject{
    public OBJ_Hook(){
        name = "Hook";
        description = "The hook carries the weight of stories untold, promising a connection to the abundant waters that surround this deserted island";
        type = "tool";
        modifier = 0;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/hook.png"));
        } catch(IOException e){
            System.out.println("Failed to make the image for apple");
            e.printStackTrace();
        }
    }
}