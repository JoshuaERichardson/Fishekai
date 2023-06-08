package com.fishekai.view;

import com.fishekai.engine.Display;
import com.fishekai.engine.HelpPopup;
import com.fishekai.models.Player;
import com.fishekai.utilities.AudioManager;
import com.fishekai.view.object.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class InventoryPanel extends JPanel{
    public int width;
    public int height;
    InventoryItem appleItem, chordItem, stickItem, hookItem, waterItem, bananaItem, flaskItem;
    private final DialogEngine dialogEngine;
    private final AudioManager audioManager;
    private final MainWindow mainWindow;

    public InventoryPanel(Player textPlayer, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        dialogEngine = mainWindow.getGamePanel().getDialog();
        audioManager = mainWindow.getGamePanel().getAudioManager();

        // Resize the panel:

        // Add these to the panel:
        appleItem = new InventoryItem("/sprites/items/bwapple.png", "/sprites/items/apple.png", new OBJ_Apple(), this);
        chordItem = new InventoryItem("/sprites/items/bwparachute.png", "/sprites/items/parachute.png", new OBJ_Parachute(), this);
        stickItem = new InventoryItem("/sprites/items/bwstick.png", "/sprites/items/stick.png", new OBJ_Stick(), this);
        hookItem = new InventoryItem("/sprites/items/bwhook.png", "/sprites/items/hook.png", new OBJ_Hook(), this);
        waterItem = new InventoryItem("/sprites/items/bwwater.png", "/sprites/items/water.png", new OBJ_Water(), this);
        bananaItem = new InventoryItem("/sprites/items/bwbanana.png", "/sprites/items/banana.png", new OBJ_Banana(), this);
        flaskItem = new InventoryItem("/sprites/items/bwflask.png", "/sprites/items/flask.png", new OBJ_Flask(), this);

        // Layout manager:
        setLayout(new GridLayout(3, 2));

        add(appleItem);
        add(chordItem);
        add(stickItem);
        add(hookItem);
        add(waterItem);
        add(bananaItem);
//        add(flaskItem);

        setFocusable(false);

    }

public void updateInventory(List<SuperObject> inventory) {
    for (SuperObject item : inventory) {
        String itemName = item.getName();
        switch (itemName) {
            case "Apple": appleItem.loadImage(); break;
            case "Banana":bananaItem.loadImage();break;
            case "Flask" : flaskItem.loadImage(); break;
            case "Hook" : hookItem.loadImage(); break;
            case "Parachute" : chordItem.loadImage(); break;
            case "Stick" : stickItem.loadImage(); break;
            case "Water" : waterItem.loadImage(); break;

        }

        this.revalidate();
        this.repaint();
    }

}

    public DialogEngine getDialogEngine() {
        return dialogEngine;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }
}