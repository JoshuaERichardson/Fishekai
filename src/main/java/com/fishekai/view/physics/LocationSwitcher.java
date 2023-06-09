package com.fishekai.view.physics;

import com.fishekai.engine.Fishekai;
import com.fishekai.models.Location;
import com.fishekai.models.Player;
import com.fishekai.utilities.AudioManager;
import com.fishekai.view.FishingFrame;
import com.fishekai.view.GamePanel;
import com.fishekai.view.object.OBJ_Door;
import com.fishekai.view.tile.TileManager;

import javax.swing.*;

public class LocationSwitcher {
    static int moves = 0;

    public static void moveScenes(Fishekai fishekai, String locationName){


        if (locationName == null){ // Fishing starts!
            Player player = fishekai.getTextPlayer();
            Location location = fishekai.getCurrent_location();
            AudioManager audioManager = fishekai.getAudioManager();
            fishekai.getFishingMechanic().startFishing(player, location, audioManager);

            FishingFrame fishingFrame = new FishingFrame(fishekai.getFishingMechanic(), fishekai.getWindow().getGamePanel());
            // Pause the game while fishing:
            GamePanel gamePanel = fishekai.getWindow().getGamePanel();
            gamePanel.setPaused(true);
            // Change the status panel from the build-a-pole to the caught fish:
            fishekai.getWindow().getStatusPanel().setFishing(true);
            audioManager.playSoundEffect("fishing");
            fishingFrame.setVisible(true);
            fishingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fishekai.getKeyHandler().leftPressed = false;
            fishekai.window.gamePanel.player.worldX = 2 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 4 * fishekai.window.gamePanel.tileSize;
            return;
        }
        moves++;
        fishekai.getTextPlayer().moveDamage(moves);
        System.out.println(fishekai.getTextPlayer().getThirst() + " " + fishekai.getTextPlayer().getHunger() + " " + fishekai.getTextPlayer().getHp());
        // Repaint the statusPanel with updated thirst and hunger:
        fishekai.window.getStatusPanel().update();


        // Save the prevLocation direction:
        String prevLocation = fishekai.current_location.getName();

        // First set the player in the new location
        fishekai.current_location = fishekai.locations.get(locationName);

        // Get the door on the opposite side of the prevLocation


        // Next redraw the tiles
        fishekai.window.gamePanel.tileM.getMapImage();

        // Then redraw the objects
        fishekai.window.gamePanel.assetSetter.setObject();

        // Then redraw the player::: TODO: Need to simplify this!
        // If NorthBeach from Beach:
        if (locationName.equals("North Beach") && prevLocation.equals("Beach")) {
            fishekai.window.gamePanel.player.worldX = 7 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 10 * fishekai.window.gamePanel.tileSize;
        }
        // If Beach from NorthBeach:
        else if (locationName.equals("Beach") && prevLocation.equals("North Beach")) {
            fishekai.window.gamePanel.player.worldX = 7 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 1 * fishekai.window.gamePanel.tileSize;
        }
        // If Beach to Jungle:
        else if (locationName.equals("Jungle") && prevLocation.equals("Beach")) {
            fishekai.window.gamePanel.player.worldX = 1 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 5 * fishekai.window.gamePanel.tileSize;
        }
        // If Jungle to Beach:
        else if (locationName.equals("Beach") && prevLocation.equals("Jungle")) {
            fishekai.window.gamePanel.player.worldX = 10 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 5 * fishekai.window.gamePanel.tileSize;
        }
        // If Jungle to Waterfall:
        else if (locationName.equals("Waterfall") && prevLocation.equals("Jungle")) {
            fishekai.window.gamePanel.player.worldX = 1 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 5 * fishekai.window.gamePanel.tileSize;
        }
        // If Waterfall to Jungle:
        else if (locationName.equals("Jungle") && prevLocation.equals("Waterfall")) {
            fishekai.window.gamePanel.player.worldX = 10 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 5 * fishekai.window.gamePanel.tileSize;
        }
        // If Waterfall to Mystical Grove:
        else if (locationName.equals("Mystical Grove") && prevLocation.equals("Waterfall")) {
            fishekai.window.gamePanel.player.worldX = 1 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 5 * fishekai.window.gamePanel.tileSize;
        }
        // If Mystical Grove to Waterfall:
        else if (locationName.equals("Waterfall") && prevLocation.equals("Mystical Grove")) {
            fishekai.window.gamePanel.player.worldX = 10 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 5 * fishekai.window.gamePanel.tileSize;
        }
        // If Mystical Grove to Volcano:
        else if (locationName.equals("Volcano") && prevLocation.equals("Mystical Grove")) {
            fishekai.window.gamePanel.player.worldX = 5 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 10 * fishekai.window.gamePanel.tileSize;
        }
        // If Volcano to Mystical Grove:
        else if (locationName.equals("Mystical Grove") && prevLocation.equals("Volcano")) {
            fishekai.window.gamePanel.player.worldX = 5 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 1 * fishekai.window.gamePanel.tileSize;
        }
        // If North Beach to Mountain:
        else if (locationName.equals("Mountain") && prevLocation.equals("North Beach")) {
            fishekai.window.gamePanel.player.worldX = 1 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 9 * fishekai.window.gamePanel.tileSize;
        }
        // If Mountain to North Beach:
        else if (locationName.equals("North Beach") && prevLocation.equals("Mountain")) {
            fishekai.window.gamePanel.player.worldX = 10 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 9 * fishekai.window.gamePanel.tileSize;
        }
        // If mountain to Cave:
        else if (locationName.equals("Cave") && prevLocation.equals("Mountain")) {
            fishekai.window.gamePanel.player.worldX = 5 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 3 * fishekai.window.gamePanel.tileSize;
        }
        // If cave to mountain:
        else if (locationName.equals("Mountain") && prevLocation.equals("Cave")) {
            fishekai.window.gamePanel.player.worldX = 8 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 4 * fishekai.window.gamePanel.tileSize;
        }
        // If mountain to Forest:
        else if (locationName.equals("Jungle") && prevLocation.equals("Mountain")) {
            fishekai.window.gamePanel.player.worldX = 5 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 1 * fishekai.window.gamePanel.tileSize;
        }
        // If Forest to Mountain:
        else if (locationName.equals("Mountain") && prevLocation.equals("Jungle")) {
            fishekai.window.gamePanel.player.worldX = 4 * fishekai.window.gamePanel.tileSize;
            fishekai.window.gamePanel.player.worldY = 10 * fishekai.window.gamePanel.tileSize;
        }

        // Play sound effect
        fishekai.getAudioManager().randomGo();












    }


}