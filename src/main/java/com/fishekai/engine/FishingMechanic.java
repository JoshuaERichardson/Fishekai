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
    private List<String> fishDistance = new ArrayList<>();
    private boolean islineTight;
    private int pullCount;
    private AudioManager audioManager;
    private Fish fish;
    private boolean fishCaught;

    public FishingMechanic(AudioManager audioManager) {
        this.audioManager = audioManager;
        this.islineTight = false;
        this.pullCount = 0;
        this.fishCaught = false;
    }

    public void startFishing(Player player, Location current_location, AudioManager audioManager) {
        // Reset display
        resetFishDistance();

        // Randomly select a fish
        Map<String, Fish> fishMap = current_location.getFishes();
        Random random = new Random();
        String randomFishName = fishMap.keySet().stream()
                .skip(random.nextInt(fishMap.size()))
                .findFirst()
                .orElse(null);
        fish = current_location.getFishes().get(randomFishName);

        System.out.println("You cast your line into the water at " + current_location.getName() + " and wait patiently...");
        System.out.println("A " + fish.getName() + " has bitten your bait! It's time to reel it in.");

        islineTight = false;
        pullCount = 0;
        fishCaught = false;
    }

    public String pullLine() {
        String message = "";
        Random random = new Random();

        if (islineTight) {
            message = "The line is tight! You pull anyway and lose some progress.";
            pullCount--;
            audioManager.randomPull();

        } else {
            int success = random.nextInt(10); // Random number will handle success rate of pulling a fish
            audioManager.randomPull();

            if (success >= 1 && pullCount <3) {
                message = "You pull the line and feel a strong resistance. You're making progress!";
                pullCount++;

                if (pullCount >= 3) {
                    message = "After a few more strong pulls, you successfully catch the " + fish.getName() + "!";
                    fishCaught = true;
                }
            } else {
                message = "You pull the line, but the fish slips away. Keep trying!";
                pullCount--;
                audioManager.randomPull();

                if (pullCount <= -3) {
                    message = "The fish escapes. Better luck next time!";
                }
            }
            islineTight = random.nextBoolean();
        }
        return message;
    }

    public String releaseLine() {
        Random random = new Random();
        boolean badLuckProtection = random.nextBoolean();
        if (badLuckProtection) {
            String message = "The fish dipped when it should have dived.  You gained some progress!";
            pullCount++;
            audioManager.randomReel();
            islineTight = false;
            return message;
        }
        String message = "You release the line, giving the fish some slack.";
        islineTight = false;
        pullCount--;
        audioManager.randomReel();

        if (pullCount <= -3) {
            message = "The fish escapes. Better luck next time!";
            fishCaught = false;
        }
        return message;
    }

    public boolean isLineTight() {
        return islineTight;
    }

    public int getPullCount() {
        return pullCount;
    }

    public boolean isFishCaught() {
        return fishCaught;
    }

    public Fish getCaughtFish() {
        return fishCaught ? fish : null;
    }

    private void resetFishDistance() {
        fishDistance.clear();
        StringBuilder fishLineBuilder = new StringBuilder();

        for (int i = -3; i <= 3; i++) {
            if (i == 0) {
                fishLineBuilder.append("<º(((><");
            } else {
                fishLineBuilder.append("===");
            }
        }

        fishDistance.add(fishLineBuilder.toString());
    }

    public String updateFishDistance() {
        StringBuilder distanceBuilder = new StringBuilder();

        int fishPosition = Math.max(-3, Math.min(3, pullCount));

        distanceBuilder.append("===".repeat(Math.max(0, 3 - fishPosition)));
        distanceBuilder.append("<º(((><");
        distanceBuilder.append("===".repeat(Math.max(0, fishPosition + 3)));

        return distanceBuilder.toString();
    }
}
