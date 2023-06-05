package com.fishekai.view.object;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject{
    public String location;

    public OBJ_Door(){
        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/rock.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setLocation(String location) {
        location = location;
    }
    public String getLocation(){
        return location;
    }

}