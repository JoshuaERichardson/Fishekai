package com.fishekai.view.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Fish extends SuperObject{
    public OBJ_Fish() {
        name ="Fish";
        description ="A fish";
        location = "North Beach";
        type ="misc";
        modifier =0;
        worldX = 0;
        worldY = 4;





        try{
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/fish1.png"));
        } catch (IOException e){
            System.out.println("Failed to make the image for fish");
            e.printStackTrace();
        }

    }



}