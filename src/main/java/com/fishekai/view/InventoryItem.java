package com.fishekai.view;

import com.fishekai.engine.HelpPopup;

import javax.swing.*;
import java.awt.*;

class InventoryItem extends JPanel {
    private String missingItemPath;
    private String haveItemPath;
    private Image image;
    JPanel backCard, frontCard;
    boolean itemPickedUp;

    public InventoryItem(String missingItemPath, String haveItemPath) {
        this.missingItemPath = missingItemPath;
        this.haveItemPath = haveItemPath;



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
            // Pop up a window with the description of the item
            HelpPopup helpPopup = new HelpPopup("This is a ");
        });
        useButton.addActionListener(e -> {
            // Use the item
            HelpPopup helpPopup = new HelpPopup("You used the ");
        });
        flipButton.addActionListener(e -> {
            // Flip the card back over
            flipCard();
        });

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