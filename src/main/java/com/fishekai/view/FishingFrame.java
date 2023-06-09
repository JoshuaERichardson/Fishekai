package com.fishekai.view;

import com.fishekai.engine.FishingMechanic;
import com.fishekai.models.Fish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class FishingFrame extends JFrame {
    private JLabel fishUpdateLabel;
    private JLabel lineTightLabel;
    private JLabel fishDistanceLabel;
    private JButton pullButton;
    private JButton releaseButton;
    private FishingMechanic fishingMechanic;
    private GamePanel gp;

    public FishingFrame(FishingMechanic fishingMechanic, GamePanel gp) {
        this.fishingMechanic = fishingMechanic;
        this.gp = gp;

       


        setTitle("Fishing Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create the main content panel with vertical BoxLayout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Create components
        fishUpdateLabel = new JLabel();
        lineTightLabel = new JLabel();
        fishDistanceLabel = new JLabel();
        pullButton = new JButton("Pull");
        releaseButton = new JButton("Release");

        // Add components to the content panel
        contentPanel.add(fishUpdateLabel);
        contentPanel.add(lineTightLabel);
        contentPanel.add(fishDistanceLabel);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(pullButton);
        buttonPanel.add(releaseButton);

        // Add the content panel and button panel to the frame
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set initial text for the labels
        fishUpdateLabel.setText("You cast your line into the water and wait patiently...");
        lineTightLabel.setText("Is the line tight? " + fishingMechanic.isLineTight());
        fishDistanceLabel.setText("Fish distance: " + fishingMechanic.updateFishDistance());


        // Attach action listeners to the buttons
        pullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = fishingMechanic.pullLine();
                fishUpdateLabel.setText(message);
                lineTightLabel.setText("Is the line tight? " + fishingMechanic.isLineTight());
                fishDistanceLabel.setText("Fish distance: " + fishingMechanic.updateFishDistance());

                // Check if fish is caught
                if (fishingMechanic.isFishCaught()) {
                    handleFishCaught();
                } else if (fishingMechanic.getPullCount() <= -3) {
                    handleFishEscaped();
                }
            }
        });

        releaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = fishingMechanic.releaseLine();
                fishUpdateLabel.setText(message);
                lineTightLabel.setText("Is the line tight? " + fishingMechanic.isLineTight());
                fishDistanceLabel.setText("Fish distance: " + fishingMechanic.updateFishDistance());
            }
        });

        // Set the size and make the frame visible
        setSize(500, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void handleFishCaught() {
        // TODO: Implement the logic for when the player successfully catches the fish
        Fish fish = fishingMechanic.getCaughtFish();
        if (fish != null) {
            JOptionPane.showMessageDialog(this, "Congratulations! You caught the " + fish.getName() + "!", "Fish Caught", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Congratulations! You caught a fish!", "Fish Caught", JOptionPane.INFORMATION_MESSAGE);
        }
        dispose();
    }

    private void handleFishEscaped() {

        JOptionPane.showMessageDialog(this, "The fish escaped. Better luck next time!", "Fish Escaped", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            // Move the character off the dock:

            gp.setPaused(false);
        }
    }
}

