package com.fishekai.view.object;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject{
    public String location;
    public String fromLocation;
    public boolean doorToFishingMiniGame;
    public OBJ_Door(){
        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/clear.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setLocation(String location) {
        this.location = location;

    }
    public String getLocation(){
        return location;
    }
    public String setFromLocation(String fromLocation){
        return fromLocation;
    }
    public String getFromLocation(){
        return fromLocation;
    }
    public void setDoorToFishingMiniGame(){
        this.doorToFishingMiniGame = true;
    }

}