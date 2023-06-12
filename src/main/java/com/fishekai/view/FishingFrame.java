package com.fishekai.view;

import com.fishekai.engine.FishingMechanic;
import com.fishekai.models.Fish;
import com.fishekai.view.object.OBJ_Caught_Fish;

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
    private StatusPanel statusPanel;

    public FishingFrame(FishingMechanic fishingMechanic, GamePanel gp) {
        this.fishingMechanic = fishingMechanic;
        this.gp = gp;
        this.statusPanel = gp.getFishekai().window.getStatusPanel();

        setTitle("Fishing Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create the main content panel with vertical BoxLayout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Create components
        fishUpdateLabel = new JLabel("", SwingConstants.CENTER);
        lineTightLabel = new JLabel("", SwingConstants.CENTER);
        fishDistanceLabel = new JLabel("", SwingConstants.CENTER);
        pullButton = new JButton("Pull");
        releaseButton = new JButton("Release");

        // Set the font for the labels and buttons
        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        fishUpdateLabel.setFont(labelFont);
        lineTightLabel.setFont(labelFont);
        fishDistanceLabel.setFont(labelFont);
        pullButton.setFont(labelFont);
        releaseButton.setFont(labelFont);

        // Set the color for the labels and buttons
        Color bgColor = Color.decode("#00827f");
        Color fgColor = Color.WHITE;
        fishUpdateLabel.setForeground(fgColor);
        lineTightLabel.setForeground(fgColor);
        fishDistanceLabel.setForeground(fgColor);
        pullButton.setForeground(fgColor);
        releaseButton.setForeground(fgColor);
        contentPanel.setBackground(bgColor);
        pullButton.setBackground(bgColor);
        releaseButton.setBackground(bgColor);

        // Add components to the content panel
        contentPanel.add(fishUpdateLabel);
        contentPanel.add(new JLabel(" "));
        contentPanel.add(lineTightLabel);
        contentPanel.add(new JLabel(" "));
        contentPanel.add(new JLabel(" "));
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
        String isLineTight = fishingMechanic.isLineTight() ? "Yes" : "No";
        lineTightLabel.setText("Is the line tight? " + isLineTight);
        fishDistanceLabel.setText("Fish distance: " + fishingMechanic.updateFishDistance());

        // Attach action listeners to the buttons
        pullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = fishingMechanic.pullLine();
                fishUpdateLabel.setText(message);
                String isLineTight = fishingMechanic.isLineTight() ? "Yes" : "No";
                lineTightLabel.setText("Is the line tight? " + isLineTight);
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
                String isLineTight = fishingMechanic.isLineTight() ? "Yes" : "No";
                lineTightLabel.setText("Is the line tight? " + isLineTight);
                fishDistanceLabel.setText("Fish distance: " + fishingMechanic.updateFishDistance());
            }
        });

        // Set the size and make the frame visible
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleFishCaught() {
        String fishType = fishingMechanic.getCaughtFish().getName();
        OBJ_Caught_Fish fish = new OBJ_Caught_Fish(fishType);
        gp.getPlayer().getInventory().add(fish);
        statusPanel.updateStatusPanelFish();

        if (fish != null) {
            JOptionPane.showMessageDialog(this, "Congratulations! You caught the " + fish.getName() + "!", "Fish Caught", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Congratulations! You caught a fish!", "Fish Caught", JOptionPane.INFORMATION_MESSAGE);
        }
        gp.setPaused(false);
        dispose();
    }

    private void handleFishEscaped() {
        JOptionPane.showMessageDialog(this, "The fish escaped. Better luck next time!", "Fish Escaped", JOptionPane.INFORMATION_MESSAGE);
        gp.setPaused(false);
        dispose();
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            gp.setPaused(false);
        }
    }
}
