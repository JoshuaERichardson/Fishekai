package com.fishekai.view;

import com.fishekai.engine.Display;
import com.fishekai.engine.HelpPopup;
import com.fishekai.models.Player;
import com.fishekai.view.object.SuperObject;

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

    public InventoryPanel(Player textPlayer, MainWindow mainWindow) {
        // Overall display info
        int windowHeight = mainWindow.getHeight();
        int gpHeight = mainWindow.gamePanel.getScreenHeight();
        int gpWidth = mainWindow.gamePanel.getScreenWidth();
        width = gpWidth;
        height = windowHeight - gpHeight;
        System.out.println(height + " " + windowHeight + " " + gpHeight);
        //Layout:
        setLayout(new GridLayout(0, 7));
        // Resize the panel:
        setSize(new Dimension(width, height));

        // Add these to the panel:
        appleItem = new InventoryItem("/sprites/items/bwapple.png", "/sprites/items/apple.png");
        chordItem = new InventoryItem("/sprites/items/bwapple.png", "/sprites/items/apple.png");
        stickItem = new InventoryItem("/sprites/items/bwapple.png", "/sprites/items/apple.png");
        hookItem = new InventoryItem("/sprites/items/bwapple.png", "/sprites/items/apple.png");
        waterItem = new InventoryItem("/sprites/items/bwapple.png", "/sprites/items/apple.png");
        bananaItem = new InventoryItem("/sprites/items/bwapple.png", "/sprites/items/apple.png");
        flaskItem = new InventoryItem("/sprites/items/bwapple.png", "/sprites/items/apple.png");

        add(appleItem);
        add(chordItem);
        add(stickItem);
        add(hookItem);
        add(waterItem);
        add(bananaItem);
        add(flaskItem);

    }

public void updateInventory(List<SuperObject> inventory) {
    for (SuperObject item : inventory) {
        String itemName = item.getName();
        switch (itemName) {
            case "Apple":
                appleItem.loadImage();
                break;
        }
        this.revalidate();
        this.repaint();
    }


}
}