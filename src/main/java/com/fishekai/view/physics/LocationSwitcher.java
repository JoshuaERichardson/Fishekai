package com.fishekai.view.physics;

import com.fishekai.engine.Fishekai;
import com.fishekai.models.Location;
import com.fishekai.view.GamePanel;
import com.fishekai.view.object.OBJ_Door;
import com.fishekai.view.tile.TileManager;

public class LocationSwitcher {

    public static void moveScenes(Fishekai fishekai, String locationName){


        // Save the prevLocation direction:
        String prevLocation = fishekai.current_location.getName();

        // First set the player in the new location
        fishekai.current_location = fishekai.locations.get(locationName);

        // Next redraw the tiles
        fishekai.window.gamePanel.tileM.loadMap(fishekai.current_location.getTiles());

        // Then redraw the objects
        fishekai.window.gamePanel.assetSetter.setObject();

        // Then draw the player at the correct x and y.

        // Find out if it came from north, east, south, or west:










    }


}