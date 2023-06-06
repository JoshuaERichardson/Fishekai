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

        // Get the door on the opposite side of the prevLocation


        // Next redraw the tiles
        fishekai.window.gamePanel.tileM.loadMap(fishekai.current_location.getTiles());

        // Then redraw the objects
        fishekai.window.gamePanel.assetSetter.setObject();

        // Then redraw the player::: TODO: Need to simplify this!
        // If NorthBeach from Beach:
        if (locationName.equals("North Beach") && prevLocation.equals("Beach")) {
            fishekai.window.gamePanel.player.worldX = 8 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 10 * fishekai.window.gamePanel.tileSize;
        }
        // If Beach from NorthBeach:
        else if (locationName.equals("Beach") && prevLocation.equals("North Beach")) {
            fishekai.window.gamePanel.player.worldX = 7 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 1 * fishekai.window.gamePanel.tileSize;
        }










    }


}