package com.fishekai.view.object;

import javax.imageio.ImageIO;
import java.io.IOException;

class OBJ_Parachute extends SuperObject {
    public OBJ_Parachute() {
        name = "Parachute";
        description = "the chord could come in handy";
        type = "tool";
        modifier = 0;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/parachute.png"));
        } catch (IOException e) {
            System.out.println("Failed to make the image for apple");
            e.printStackTrace();
        }


    }
}