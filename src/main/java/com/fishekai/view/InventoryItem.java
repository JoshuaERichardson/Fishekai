package com.fishekai.view;

import com.fishekai.engine.HelpPopup;
import com.fishekai.utilities.AudioManager;
import com.fishekai.view.entity.Player;
import com.fishekai.view.object.OBJ_Caught_Fish;
import com.fishekai.view.object.SuperObject;

import javax.swing.*;
import java.awt.*;

class InventoryItem extends JComponent {
    private String missingItemPath;
    private String haveItemPath;
    private Image image;
    JPanel backCard, frontCard;
    boolean itemPickedUp;
    private final DialogEngine dialogEngine;
    SuperObject item;
    private final AudioManager audioManager;
    private final Player player;
    private StatusPanel statusPanel;
    private JLabel label;



    public InventoryItem(OBJ_Caught_Fish item, String haveItemPath, StatusPanel statusPanel){
        this.statusPanel = statusPanel;
        this.dialogEngine = statusPanel.getFishekai().window.getGamePanel().getDialog();
        this.audioManager = statusPanel.getFishekai().window.getGamePanel().getAudioManager();
        this.player = statusPanel.getFishekai().window.getGamePanel().getPlayer();
        this.item = item;
        this.haveItemPath = haveItemPath;

        setLayout(new CardLayout());
        backCard = new JPanel();
        frontCard = new JPanel();


        label = new JLabel();
        label.setText("Go Fish!");
        frontCard.add(label);
        // Grow the size of the label:
//        label.setPreferredSize(new Dimension(100, 100));
//        label.setFont(new Font("Arial", Font.BOLD, 20));
//        label.setForeground(Color.WHITE);
//        label.setHorizontalAlignment(JLabel.CENTER);
//        label.setVerticalAlignment(JLabel.CENTER);
//        label.setOpaque(true);
//        label.setBackground(Color.BLACK);
        missingItemPath = "/sprites/items/fishblue.png";



        JButton useButton = new JButton("Eat");
        JButton flipButton = new JButton("Cancel");

        useButton.addActionListener(e -> {
            // Use the item
                if (item.getName().equals("Fangfish")) {
                    statusPanel.getFishekai().window.getGamePanel().gameOver(1);
                }
                if (item.getName().equals("Sunfish")) {
                    statusPanel.getFishekai().window.getGamePanel().gameOver(3);
                } else {
                    dialogEngine.update("You ate the " + item.getName() + ".");
                    audioManager.randomEat();
                    itemPickedUp = false;
                    player.getFishekai().timeToEat(3);
                    statusPanel.update();
                    usedImage();
                    flipCard();
                    player.consumeObject(item.getName());
                }
        });
        flipButton.addActionListener(e -> {
            // Flip the card back over
            flipCard();
        });
        useButton.setFocusable(false);
        flipButton.setFocusable(false);
        backCard.add(useButton);
        backCard.add(flipButton);

        // Add an action listener for a mouse click:
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (itemPickedUp) {
                    flipCard();
                }
            }
        });
        setBackground(Color.blue);

        add(frontCard, "front");
        add(backCard, "back");

        ImageIcon icon = new ImageIcon(getClass().getResource("/sprites/items/clear.png"));
        image = icon.getImage();
        // Stretch the image to fit the panel
        icon = new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));




        // Front card is the image of the item
        frontCard.add(new JLabel(icon));



    }
    public InventoryItem(String missingItemPath, String haveItemPath, SuperObject item, InventoryPanel inventoryPanel) {
        this.missingItemPath = missingItemPath;
        this.dialogEngine = inventoryPanel.getMainWindow().gamePanel.getDialog();
        this.haveItemPath = haveItemPath;
        this.item = item;
        this.audioManager = inventoryPanel.getMainWindow().gamePanel.getAudioManager();
        this.player = inventoryPanel.getMainWindow().gamePanel.getPlayer();
        this.statusPanel = inventoryPanel.getMainWindow().getStatusPanel();



        setLayout(new CardLayout());
        backCard = new JPanel();
        frontCard = new JPanel();


        ImageIcon icon = new ImageIcon(getClass().getResource(missingItemPath));
        image = icon.getImage();
        // Stretch the image to fit the panel
        icon = new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));




        // Front card is the image of the item
        frontCard.add(new JLabel(icon));


        JButton lookButton = new JButton("Look");
        JButton useButton = new JButton("Use");
        JButton flipButton = new JButton("Cancel");
        lookButton.addActionListener(e -> {
            audioManager.playSoundEffect("look");
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
                    statusPanel.update();
                    break;
                case "water":
                    int charges = player.getFishekai().getFlask().getCharges();
                    player.getFishekai().rememberToHydrate();
                    audioManager.randomDrink();
                    if (charges <= 0) player.consumeObject(item.getName());
                    statusPanel.update();
                    break;
                case "tool":
                    if (item.getName().equals("Stick")){
                        statusPanel.setHaveStick(true);
                        dialogEngine.update("You used the stick... That should help out making a fishing pole.");
                        player.consumeObject(item.getName());
                    }
                    if (item.getName().equals("Parachute")){
                        statusPanel.setHaveRope(true);
                        dialogEngine.update("Hmmm... I guess I'll try to use this to make the line for a fishing pole.");
                        player.consumeObject(item.getName());
                    }
                    if (item.getName().equals("Hook")){
                        statusPanel.setHaveHook(true);
                        dialogEngine.update("This hook should be perfect for fishing.  Let's try it out");
                        player.consumeObject(item.getName());
                    }
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
        icon = new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        image = icon.getImage();
        frontCard.removeAll();
        if (item.getType().equals("misc")) {
            label.setText(item.getName());
            frontCard.add(label);
        }
        frontCard.add(new JLabel(icon));
        itemPickedUp = true;
        revalidate();
        repaint();
    }
    void loadFish(){
        label.setText(item.getName());
        itemPickedUp = true;
        revalidate();
        repaint();
    }

    void caughtFish(){
        loadImage();
    }
    void usedImage(){
        ImageIcon icon = new ImageIcon(getClass().getResource(missingItemPath));
        icon = new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
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
//        if(image != null){
//            g.drawImage(image, 0, 0, getParent().getWidth() / 7, getParent().getHeight(), null);
//        }
    }
}