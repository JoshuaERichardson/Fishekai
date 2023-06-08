package com.fishekai.engine;

import com.fishekai.models.Fish;
import com.fishekai.models.Location;
import com.fishekai.models.Player;
import com.fishekai.utilities.AudioManager;

import java.util.*;

/**
 * This class is responsible for the fishing mechanic.
 * It displays the fishing mechanic and handles the logic.
 */
public class FishingMechanic {
    private static final int PAUSE_VALUE = 1_500;
    private boolean isLineTight = false;
    private int pullCount = 0;
    private AudioManager audioManager;
    private Fish fish;
    private List<String> fishDistance = new ArrayList<>();
    private String message = "";
    private boolean fishCaught = false;

    public void startFishing(Player player, Location current_location, AudioManager audioManager) {
        this.audioManager = audioManager;

        // reset display
        resetFishDistance();
        isLineTight= false;
        pullCount= 0;
        fishCaught = false;

        // randomly select a fish
        Map<String, Fish> fishMap = current_location.getFishes();
        Random random = new Random();
        String randomFishName = fishMap.keySet().stream()
                .skip(random.nextInt(fishMap.size()))
                .findFirst()
                .orElse(null);
        this.fish = current_location.getFishes().get(randomFishName);

        this.message = "You cast your line into the water at " + current_location.getName() + " and wait patiently... A " + fish.getName() + " has bitten your bait! It's time to reel it in.";
    }

        public boolean pullLine() {
            if (isLineTight) {
                this.message = "The line is tight! You pull anyway and lose some progress.";
                pullCount -= 3;
                fishDistance.remove(fishDistance.size() - 1);
                fishDistance.remove(fishDistance.size() - 1);
                fishDistance.remove(fishDistance.size() - 1);
                fishDistance.add(1, " ");
                fishDistance.add(1, " ");
                fishDistance.add(1, " ");
                audioManager.randomPull();
            } else {
                Random random = new Random();
                int success = random.nextInt(5);
                audioManager.randomPull();
                if (success >= 1) {
                    this.message = "You pull the line and feel a strong resistance. You're making progress!";
                    pullCount++;
                    fishDistance.remove(1);
                    fishDistance.add(" ");
                    if (isFishCaught()) {
                        this.message = "After a few more strong pulls, you successfully catch the " + fish.getName() + "!";
                        displayFishImage();
                        return true; // Fish is caught
                    }
                } else {
                    this.message = "You pull the line, but the fish slips away. Keep trying!";
                    pullCount--;
                    fishDistance.remove(fishDistance.size() - 1);
                    fishDistance.add(1, " ");
                }
                // Randomly determine if the line becomes tight
                isLineTight = random.nextBoolean();
            }
            return false; // Fish is not caught
        }

    private void displayFishImage() {
    }

    public void releaseLine() {
        this.audioManager = audioManager;
        if (isLineTight) {
            this.message = "You release the line, giving the fish some slack. The line is still tight...";
            isLineTight = false;
//            audioManager.randomReel();
        } else {
//            audioManager.randomReel();
            this.message = "You release the line, giving the fish some slack.";
            isLineTight = true;
        }
    }

    private void resetFishDistance() {
        fishDistance.clear();
        fishDistance.add("o/");
        fishDistance.add(" ");
        fishDistance.add(" ");
        fishDistance.add(" ");
        fishDistance.add("><(((º>");
        fishDistance.add(" ");
        fishDistance.add(" ");
        fishDistance.add(" ");
        fishDistance.add(" ");
    }

    public String getMessage() {
        return message;
    }

    public List<String> getFishDistance() {
        return fishDistance;
    }
    public boolean isLineTight() {
        return isLineTight;
    }
    public boolean isFishCaught() {
        return fishCaught;
    }
}