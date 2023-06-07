package com.fishekai.view;

import com.fishekai.engine.HelpPopup;
import com.fishekai.utilities.AudioManager;
import com.fishekai.view.entity.Player;
import com.fishekai.view.object.SuperObject;

import javax.swing.*;
import java.awt.*;

class InventoryItem extends JPanel {
    private String missingItemPath;
    private String haveItemPath;
    private Image image;
    JPanel backCard, frontCard;
    boolean itemPickedUp;
    private final DialogEngine dialogEngine;
    SuperObject item;
    private final AudioManager audioManager;
    private final Player player;

    public InventoryItem(String missingItemPath, String haveItemPath, SuperObject item, InventoryPanel inventoryPanel) {
        this.missingItemPath = missingItemPath;
        this.dialogEngine = inventoryPanel.getMainWindow().gamePanel.getDialog();
        this.haveItemPath = haveItemPath;
        this.item = item;
        this.audioManager = inventoryPanel.getMainWindow().gamePanel.getAudioManager();
        this.player = inventoryPanel.getMainWindow().gamePanel.getPlayer();



        setLayout(new CardLayout());
        backCard = new JPanel();
        frontCard = new JPanel();


        ImageIcon icon = new ImageIcon(getClass().getResource(missingItemPath));
        image = icon.getImage();

        // Front card is the image of the item
        frontCard.add(new JLabel(icon));


        JButton lookButton = new JButton("Look");
        JButton useButton = new JButton("Use");
        JButton flipButton = new JButton("Cancel");
        lookButton.addActionListener(e -> {
            dialogEngine.update(item.description);
        });
        useButton.addActionListener(e -> {
            // Use the item
            switch(item.getType()) {
                case "food":
                    dialogEngine.update("You ate the " + item.getName() + ".");
                    audioManager.randomEat();
                    itemPickedUp = false;
                    player.getFishekai().timeToEat(item.getModifier());
                    usedImage();
                    flipCard();
                    player.consumeObject(item.getName());

                    break;
                case "water":
                    dialogEngine.update("You drank some water from the flask. YOU HAVE CHARGES?");
                    audioManager.randomDrink();
                    break;
            }
        });
        flipButton.addActionListener(e -> {
            // Flip the card back over
            flipCard();
        });

        lookButton.setFocusable(false);
        useButton.setFocusable(false);
        flipButton.setFocusable(false);
        backCard.add(lookButton);
        backCard.add(useButton);
        backCard.add(flipButton);

        // Add an action listener for a mouse click:
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (itemPickedUp) flipCard();
            }
        });

        add(frontCard, "front");
        add(backCard, "back");
    }

    void loadImage(){
        ImageIcon icon = new ImageIcon(getClass().getResource(haveItemPath));
        image = icon.getImage();
        frontCard.removeAll();
        frontCard.add(new JLabel(icon));
        itemPickedUp = true;
        revalidate();
        repaint();
    }
    void usedImage(){
        ImageIcon icon = new ImageIcon(getClass().getResource(missingItemPath));
        image = icon.getImage();
        frontCard.removeAll();
        frontCard.add(new JLabel(icon));
        itemPickedUp = false;
        revalidate();
        repaint();
    }

    void changeImage(String path){
        loadImage();
    }

    public void flipCard(){
        CardLayout cl = (CardLayout)(this.getLayout());
        cl.next(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the image on the panel
        if(image != null){
            g.drawImage(image, 0, 0, getParent().getWidth() / 7, getParent().getHeight(), null);
            System.out.println("height: " + getParent().getHeight());
        }
    }
}