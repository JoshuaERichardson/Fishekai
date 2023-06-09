package com.fishekai.view;

import com.fishekai.engine.FishingMechanic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FishingFrame extends JFrame {
    private JLabel messageLabel;
    private JLabel fishDistanceLabel;
    private JLabel lineTightLabel;
    private JPanel blocksPanel;
    private JLabel fishImageLabel;
    private JButton pullButton;
    private JButton releaseButton;
    private FishingMechanic fishingMechanic;

    public FishingFrame(FishingMechanic fishingMechanic) {
        this.fishingMechanic = fishingMechanic;

        ImageIcon fishIcon = new ImageIcon("sprites/items/fish1.png");
        Image originalImage = fishIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon scaledFishIcon = new ImageIcon(scaledImage);
        fishImageLabel = new JLabel(scaledFishIcon);
        fishImageLabel.setVisible(false);

        setTitle("Fishing Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1));
        messageLabel = new JLabel("Cast your line and wait...");
        lineTightLabel = new JLabel("Line tight: ");
        fishDistanceLabel = new JLabel("Fish distance: ");
        infoPanel.add(messageLabel);
        infoPanel.add(lineTightLabel);
        infoPanel.add(fishDistanceLabel);

        blocksPanel = new JPanel();
        blocksPanel.setLayout(new GridLayout(1, 7));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(infoPanel, BorderLayout.NORTH);
        contentPanel.add(blocksPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        pullButton = new JButton("Pull");
        releaseButton = new JButton("Release");
        buttonPanel.add(pullButton);
        buttonPanel.add(releaseButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);

        pullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean fishCaught = fishingMechanic.pullLine();
                updateLabels();
                updateBlocks();
                if (fishCaught) {
                    displayFishImage();
                }
            }
        });

        releaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fishingMechanic.releaseLine();
                updateLabels();
                updateBlocks();
            }
        });

        setSize(800,500);
        setLocationRelativeTo(null);
    }

    private void updateLabels() {
        messageLabel.setText(fishingMechanic.getMessage());
        fishDistanceLabel.setText("Fish distance: " + String.join("", fishingMechanic.getFishDistance()));
        lineTightLabel.setText("Line tight: " + (fishingMechanic.isLineTight() ? "Yes" : "No"));
    }

    private void updateBlocks() {
        blocksPanel.removeAll();

        for (String block : fishingMechanic.getFishDistance()) {
            JPanel blockPanel = new JPanel();
            blockPanel.setPreferredSize(new Dimension(30, 30));
            blockPanel.setBackground(Color.BLUE);
            if (block.equals("o/")) {
                blockPanel.setBackground(new Color(139, 69, 19));
            }
            blocksPanel.add(blockPanel);
        }

        blocksPanel.revalidate();
        blocksPanel.repaint();
    }

    private void displayFishImage() {
        fishImageLabel.setVisible(true);
        getContentPane().add(fishImageLabel, BorderLayout.NORTH);
        pack();
        revalidate();
    }
}