package com.fishekai.view;

import com.fishekai.engine.Fishekai;

import javax.swing.*;

public class StatusPanel extends JPanel {
    private final Fishekai fishekai;
    private int hunger;
    private int thirst;
    private int health;
    private JPanel healthPanel;
    private JPanel hungerPanel;
    private JPanel thirstPanel;

    private JLabel hungerLabel;
    private JLabel thirstLabel;
    private JLabel healthLabel;
    private boolean haveStick = false;


    public StatusPanel(Fishekai fishekai, MainPanel mainPanel) {
        this.fishekai = fishekai;
        setBackground(java.awt.Color.BLACK);
        int width = mainPanel.getWidth()/2;
        int height = mainPanel.getHeight();
        setVisible(true);

        // We need space for Health, Hunger, Thirst
        // We need space for the build-a-pole
        // Split the panel into 2 Rows:
        // Row 1 is a JPanel with 3 columns
        // Row 2 is a JPanel with 1 column
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
        // Print the health to the health panel:
        healthPanel = new JPanel();
        healthLabel = new JLabel("Health: " + fishekai.textPlayer.getHp());
        healthPanel.add(healthLabel);
        row1.add(healthPanel);
        // Print the hunger to the hunger panel:
        hungerPanel = new JPanel();
        hungerLabel = new JLabel("Hunger: " + fishekai.textPlayer.getHunger());
        hungerPanel.add(hungerLabel);
        row1.add(hungerPanel);
        // Print the thirst to the thirst panel:
        thirstPanel = new JPanel();
        thirstLabel = new JLabel("Thirst: " + fishekai.textPlayer.getThirst());
        thirstPanel.add(thirstLabel);
        row1.add(thirstPanel);
        add(row1);

        // Row 2 is a JPanel with 1 column
        JPanel row2 = new JPanel();
        JLabel buildLabel = new JLabel("Build a pole");
        row2.add(buildLabel);
        add(row2);





    }

    public int updateThirst(){
        thirst = fishekai.textPlayer.getThirst();
        return thirst;
    }
    public int updateHunger(){
        hunger = fishekai.textPlayer.getHunger();
        return hunger;
    }
    public int updateHealth(){
        health = fishekai.textPlayer.getHp();
        return health;
    }

    public void setHaveStick(boolean haveStick) {
        this.haveStick = haveStick;
    }

    public void update() {
        thirstLabel.setText("Thirst: " + updateThirst());
        hungerLabel.setText("Hunger: " + updateHunger());
        healthLabel.setText("Health: " + updateHealth());
        this.repaint();
    }
}