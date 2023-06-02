package com.fishekai.engine;

import com.fishekai.models.*;
import com.fishekai.utilities.AudioManager;
import com.fishekai.utilities.FrameHandler;
import com.fishekai.utilities.Prompter;
import com.fishekai.utilities.SplashApp;
import com.fishekai.view.GamePanel;
import com.fishekai.view.KeyHandler;
import com.fishekai.view.buttons.HelpButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.fishekai.engine.Introduction.formatText;
import static com.fishekai.engine.Mapa.locationCheck;
import static com.fishekai.engine.Mapa.showStaticMap;
import static com.fishekai.utilities.Console.*;

public class Fishekai extends JPanel implements SplashApp, Runnable {
    // constants
    private static final long PAUSE_VALUE = 1_500;
    private static final int LINE_WIDTH = 120;

    // fields
    private boolean isGameOver = false;
    public static int moveCounter;
    private Map<String, Location> locations; // will contain the locations loaded from JSON file
    Player player = new Player("Ethan Rutherford", "Known for expertise in ancient artifacts.");
    Flask flask = new Flask("Hanley's flask");
    private final int drinkCharge = -2; // the value when you drink

    // instances
    private final Introduction intro = new Introduction();
    private final Prompter prompter = new Prompter(new Scanner(System.in));
    private final UserInputParser parser = new UserInputParser();
    private final AudioManager audioManager = new AudioManager();
    VolumeControl volumeControl = new VolumeControl(audioManager);
    FishingMechanic fishingMechanic = new FishingMechanic();
    private final FrameHandler frameHandler = new FrameHandler();
    KeyHandler keyHandler = new KeyHandler();
    HelpPopup helpPopup = new HelpPopup(Display.showHelp());

    // methods
    public void start() {
        // show title here
        Display.showTitle();

        // New JFrame
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Fishekai - Beach");
        window.setLayout(new BorderLayout());

        GamePanel gamePanel = new GamePanel();


        window.add(gamePanel, BorderLayout.CENTER); // <---- The panel is IN the window and therefore is sharing the dispatch thread.
        window.add(helpPopup.getButtonPanel(), BorderLayout.EAST);
        window.setLocationRelativeTo(null);

        // Set the size of the frame, since the preferable size is failing:
        window.setSize(1200, 800);

        window.setVisible(true);
        gamePanel.startGameThread();




        // ask user for input and store it
        String input = prompter.prompt("Would you like to play a new game? [Y]es or [N]o.\n><(((º> ",
                "Yes|yes|Y|y|No|no|N|n",
                "That is not a valid input\n");

        // if New Game, go to begin()
        if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
            // initialize data
            loadData();
            // begin the game
            begin();
        }

        // if Quit, terminates the game
        else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
            gameOver();
        }
    }

    private void begin() {
        // show the intro
        audioManager.playSoundEffect("intro");
        audioManager.playMusic(true);
        intro.showIntro();

        // initialize move counter and set to 0
        moveCounter = 0;

        // set starting point
        Location current_location = locations.get("Beach");

        // starts the game
        while (!isGameOver) {
            // clear screen
            clear();

            // health check
            if (player.getHp() == 0) {
                areYouStillAlive();
                break;
            }

            // check for visited locations, used for showing on the map
            locationCheck(current_location);

            // show display
            Display.showStatus(player, current_location, flask);

            // ask user for input
            String input = prompter.prompt("What would you like to do?\n><(((º> ").trim().strip();

            // give the input to the parser and then save the output of the parser
            String[] words = parser.scan(input);

            // process input
            if (words.length > 0) {
                String verb = words[0].toLowerCase();

                switch (verb) {
                    case "go":
                        current_location = changeLocation(current_location, words[1]);
                        pause(PAUSE_VALUE);
                        break;

                    case "look": // need more testing
                        lookAtItem(current_location, words[1]);
                        pause(PAUSE_VALUE);
                        break;

                    case "drop":
                        dropItem(current_location, words[1]);
                        pause(PAUSE_VALUE);
                        break;

                    case "get":
                        getItem(current_location, words[1]);
                        pause(PAUSE_VALUE);
                        break;

                    case "talk":
                        talkWithNpc(current_location, words[1]);
                        pause(PAUSE_VALUE);
                        break;

                    case "map":
                        clear();
                        audioManager.playSoundEffect("unfold_map");
                        showStaticMap(locations, current_location);
                        intro.askToContinue();
                        break;

                    case "help":
                        clear();
                        audioManager.playSoundEffect("help");
                        String helpMessage = Display.showHelp();
                        JOptionPane.showMessageDialog(null, helpMessage, "Help", JOptionPane.INFORMATION_MESSAGE);
                        break;

                    case "music":
                        musicOnOff(words);
                        pause(PAUSE_VALUE);
                        break;

                    case "volume":
                        volumeAdjustment(words);
                        pause(PAUSE_VALUE);
                        break;

                    case "effect":
                        soundEffectsOnOff(words);
                        pause(PAUSE_VALUE);
                        break;

                    case "show":
                        volumeControl.showWindow();
                        break;

                    case "jump":
                        jumpIntoTheVoid(current_location);
                        pause(PAUSE_VALUE);
                        break;

                    case "quit":
                        audioManager.playSoundEffect("goodbye");
                        gameOver();
                        break;

                    case "drink":
                        rememberToHydrate();
                        pause(PAUSE_VALUE);
                        break;

                    case "eat":
                        timeToEat(words[1]);
                        break;

                    case "build":
                        createFishingPole();
                        pause(PAUSE_VALUE);
                        break;

                    case "fish":
                        fish(player, current_location, audioManager, volumeControl);
                        pause(PAUSE_VALUE);
                        break;

                    case "god":
                        Item rod = new Item("Fishing Pole", "tool", "You hold in your hands an artifact that you have created. Let's hope it catches a fish.");
                        Fish sunfish = locations.get("North Beach").getFishes().get("sunfish");
                        Fish fangfish = locations.get("North Beach").getFishes().get("fangfish");
                        Fish tuna = locations.get("North Beach").getFishes().get("tuna");
                        player.getInventory().put("rod", rod);
                        player.getInventory().put("fangfish", fangfish);
                        player.getInventory().put("tuna", tuna);
                        player.getInventory().put("sunfish", sunfish);
                        break;

                    default:
                        invalidInput();
                        break;
                }
            } else {
                invalidInput();
            }
        }
    }

    private void soundEffectsOnOff(String[] words) {
        if (words.length == 2) {
            String effect = words[1].toLowerCase();
            if (effect.equals("on")) {
                audioManager.setSoundEffectsEnabled(true);
                System.out.println("Sound effects enabled.");
            } else if (effect.equals("off")) {
                audioManager.setSoundEffectsEnabled(false);
                System.out.println("Sound effects disabled.");
            } else {
                System.out.println("Invalid input. Please use [on] or [off].");
            }
        } else {
            System.out.println("Invalid input. Please use effect [on/off]");
        }
    }

    private void volumeAdjustment(String[] words) {
        if (words.length >= 2) {
            String option = words[1].toLowerCase();
            if ("up".equals(option)) {
                audioManager.volumeUp();
            } else if ("down".equals(option)) {
                audioManager.volumeDown();
            } else {
                try {
                    float volume = Float.parseFloat(option);
                    if (volume >= audioManager.getMusicMinVolume() && volume <= audioManager.getMusicMaxVolume()) {
                        audioManager.setMusicVolume(volume);
                    } else {
                        System.out.println("Volume value should be between " +
                                audioManager.getMusicMinVolume() + " and " +
                                audioManager.getMusicMaxVolume());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid volume option. Please use [up, down, 0.0 ~ 1.0]");
                }
            }
        } else {
            System.out.println("You can control the music volume using 'volume up', 'volume down', " +
                    "or by specifying a specific volume value.");
        }
    }

    private void musicOnOff(String[] words) {
        if (words.length >= 2) {
            String setting = words[1].toLowerCase();
            if ("off".equals(setting)) {
                audioManager.stopMusic();
            } else if ("on".equals(setting)) {
                if (!audioManager.isMusicPlaying()) {
                    audioManager.playMusic(true);
                }
            }
        } else {
            System.out.println("You can turn [music on] or [music off]");
        }
    }

    private void timeToEat(String word) {
        String itemToEat = word.toLowerCase();

        if (parser.getFoodList().contains(itemToEat) && (player.getInventory().containsKey(itemToEat))) {
            int nourishment = player.getInventory().get(itemToEat).getModifier();
            player.setHunger(player.getHunger() - nourishment);
//            audioManager.playSoundEffect("eat");
            audioManager.randomEat();
            pause(1_000);
            if (itemToEat.equals("sunfish")) { // winning condition
                formatText(DataLoader.processGameCondition().get("Mystic_Feast"), LINE_WIDTH);
                blankLines(1);
                intro.askToContinue();
                gameOver();
            }
            if (itemToEat.equals("fangfish")){ // losing condition
                formatText(DataLoader.processGameCondition().get("Fanged_Death"), LINE_WIDTH);
                blankLines(1);
                intro.askToContinue();
                gameOver();
            }
            if (itemToEat.equals("banana")) { // return banana to jungle after eating
                System.out.println("You eat the banana.");
                locations.get("Jungle").getItems().put(itemToEat, player.getInventory().get(itemToEat));
            } else if (itemToEat.equals("apple")) { // return apple to jungle after eating
                System.out.println("You eat the apple.");
                locations.get("Mystical Grove").getItems().put(itemToEat, player.getInventory().get(itemToEat));
            }
            player.getInventory().remove(itemToEat);
        } else {
            System.out.printf("You can't eat that %s", itemToEat);
        }
    }

    private void rememberToHydrate() {
        if (player.getInventory().containsKey("flask") && parser.getItemList().contains("flask")) {
            if (player.getInventory().containsKey("flask") && flask.getCharges() > 0) {
                player.setThirst(player.getThirst() + drinkCharge);
                flask.setCharges(flask.getCharges() - 1);
                audioManager.randomDrink();
                System.out.println("You take a drink from the flask.");
            } else {
                System.out.println("Your flask is empty");
            }
        } else {
            System.out.println("You don't have any items to drink from.");
        }
    }

    private void createFishingPole() {
        if (player.getInventory().containsKey("parachute")
                && player.getInventory().containsKey("stick")
                && player.getInventory().containsKey("hook")) {
            audioManager.playSoundEffect("build");
            System.out.println("I have all the items for a fishing pole");
            Item rod = new Item("Fishing Pole", "tool", "You hold in your hands an artifact that you have created. Let's hope it catches a fish.");
            player.getInventory().put("rod", rod);
            player.getInventory().remove("parachute");
            player.getInventory().remove("stick");
            player.getInventory().remove("hook");
        }
    }

    private void fish(Player player, Location current_Location, AudioManager audioManager, VolumeControl volumeControl) {
        if (player.getInventory().containsKey("rod")
                && current_Location.getName().equals("North Beach")) {
            System.out.println("You cast your line to catch a fish.");
            fishingMechanic.startFishing(player, current_Location, audioManager, volumeControl);
        } else if (player.getInventory().containsKey("rod")
                && !current_Location.getName().equals("North Beach")) {
            System.out.println("There aren't any fish here. Try a different area.");
        } else if (!player.getInventory().containsKey("rod")) {
            System.out.println("How can you fish without a fishing rod?");
        }
    }

    private void areYouStillAlive() {
        if (player.getHp() == 0 && player.getThirst() == 10) {
            formatText(DataLoader.processGameCondition().get("Thirst_Toll"), LINE_WIDTH);
            blankLines(1);
            intro.askToContinue();
            gameOver();
        } else if (player.getHp() == 0 && player.getHunger() == 10) {
            formatText(DataLoader.processGameCondition().get("Starvation_Embrace"), LINE_WIDTH);
            blankLines(1);
            intro.askToContinue();
            gameOver();
        }
    }

    private void jumpIntoTheVoid(Location current_location) {
        if (current_location.getName().equals("Volcano")) {
            System.out.println("Despite you better judgement, you jumped into the Volcano's crater...");
            blankLines(1);
            audioManager.playSoundEffect("jump");
            formatText(DataLoader.processGameCondition().get("Volcanic_Plunge"), LINE_WIDTH);
            intro.askToContinue();
            gameOver();
        } else {
            System.out.println("No volcano around here to jump into.");
        }
    }


    private void invalidInput() {
        System.out.println("I don't understand. Type help for a list of commands.");
        pause(PAUSE_VALUE);
    }



    private void talkWithNpc(Location current_location, String word) {
        String npcCharacter = word.toLowerCase();
        if (parser.getNpcList().contains(npcCharacter)) {
            if (current_location.getNpc() != null) {
                NPC targetNPC = current_location.getNpc().get(npcCharacter);
                if (targetNPC != null) {
                    targetNPC.getRandomQuotes();
                    audioManager.randomTalk();
                    pause(PAUSE_VALUE);
                } else {
                    System.out.println("There is no " + npcCharacter + " here.");
                }
            } else {
                System.out.println("There are no NPCs here.");
            }
        } else {
            System.out.println(npcCharacter + " is not an NPC you can talk to.");
        }
    }

    private void getItem(Location current_location, String word) {
        String itemToGet = word.toLowerCase();
        if ((parser.getItemList().contains(itemToGet) || parser.getFoodList().contains(itemToGet))
            && current_location.getItems().containsKey(itemToGet)) {
            if (!player.getInventory().containsKey(itemToGet) && !itemToGet.equals("water")) {
                player.getInventory().put(itemToGet, current_location.getItems().get(itemToGet));
                audioManager.randomGet();
                current_location.getItems().remove(itemToGet);
                System.out.println("You got the " + itemToGet + ".");
            } else if (player.getInventory().containsKey(itemToGet)) {
                System.out.println("You have the " + itemToGet + ".");
            } else if (itemToGet.equals("water")) {
                if (player.getInventory().containsKey("flask")) {
                    flask.setCharges(5);
                    System.out.println("You filled up the flask");
                } else {
                    player.setThirst(drinkCharge);
                    audioManager.randomDrink();
                    System.out.println("You drink a long pull of water. It would be nice to be able to carry some with you.");
                }
            } else {
                System.out.println("There is no " + itemToGet + " here.");
            }
        } else if (parser.getItemList().contains(itemToGet) || parser.getFoodList().contains(itemToGet)) {
            System.out.println("There is no " + itemToGet + " here.");
        } else {
            invalidInput();
        }
    }

    private void dropItem(Location current_location, String word) {
        String itemToDrop = word.toLowerCase();
        if (parser.getItemList().contains(itemToDrop) || parser.getFoodList().contains(itemToDrop)) {
            if (player.getInventory().containsKey(itemToDrop)) {
                if (current_location.getItems() == null) {
                    Map<String, Item> inventoryMap = new HashMap<>();
                    inventoryMap.put(itemToDrop, player.getInventory().get(itemToDrop));
                    player.getInventory().remove(itemToDrop);
                    current_location.setItems(inventoryMap);
                } else {
                    current_location.getItems().put(itemToDrop, player.getInventory().get(itemToDrop));
                    player.getInventory().remove(itemToDrop);
                }
                audioManager.playSoundEffect("drop");
                System.out.println("You dropped the " + itemToDrop + ".");
            } else {
                System.out.println("You don't have a " + itemToDrop + "in your inventory.");
            }
        } else {
            System.out.println("Please specify an item to drop.");
        }
    }

    private void lookAtItem(Location current_location, String word) {
        String itemToLook = word.toLowerCase();
        if (parser.getItemList().contains(itemToLook) || parser.getFoodList().contains(itemToLook)) {
            if (player.getInventory().containsKey(itemToLook)) {
                audioManager.playSoundEffect("look");
                System.out.println("The " + player.getInventory().get(itemToLook).getName() + " looks like " + player.getInventory().get(itemToLook).getDescription());
            } else if (current_location.getItems().containsKey(itemToLook)) {
                audioManager.playSoundEffect("look");
                System.out.println("The " + current_location.getItems().get(itemToLook).getName() + " looks like " + current_location.getItems().get(itemToLook).getDescription());
            } else {
                System.out.println("There is no " + itemToLook + " here.");
            }
        } else {
            // Handle the case when the user didn't specify an item to look at
            System.out.println("Please specify an item to look at.");
        }
    }

    private Location changeLocation(Location current_location, String word) {
        String direction = word.toLowerCase();
        if (parser.getDirectionsList().contains(direction) && current_location.getDirections().containsKey(direction)) {
            current_location.setHasBeenHere(true);
            audioManager.randomGo();
            current_location = locations.get(current_location.getDirections().get(direction));
            // check move counter and apply damage
            player.moveDamage(moveCounter);
            moveCounter += 1;
        } else {
            System.out.println("Please specify a valid direction.");
        }
        return current_location;
    }

    private void gameOver() {
        isGameOver = true;
        clear();
        System.out.println("Thank you for playing!");
        pause(PAUSE_VALUE);

        System.exit(0);
    }

    // load the data
    private void loadData() {
        locations = DataLoader.processLocations(); // load the locations
        DataLoader.processItems(player, locations); // load items and place in locations
        DataLoader.processFishes(locations); // load fishes and place in locations
        DataLoader.processNpc(locations); // load NPCs and place in locations
        parser.loadTextArguments(); // loads text arguments in UserInputParser
        audioManager.loadAudioFiles(); // loads audio files and stores them
    }

    @Override
    public void run() {

    }
}