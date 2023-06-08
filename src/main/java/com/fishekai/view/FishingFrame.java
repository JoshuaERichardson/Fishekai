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
        fishImageLabel = new JLabel(fishIcon);
        fishImageLabel.setVisible(false);

        setTitle("Fishing Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        messageLabel = new JLabel("Cast your line and wait...");
        fishDistanceLabel = new JLabel("Fish distance: ");
        lineTightLabel = new JLabel("Line tight: ");
        infoPanel.add(messageLabel);
        infoPanel.add(fishDistanceLabel);

        blocksPanel = new JPanel();
        blocksPanel.setLayout(new GridLayout(1, 7));
        updateBlocks();

        JPanel buttonPanel = new JPanel();
        pullButton = new JButton("Pull");
        releaseButton = new JButton("Release");
        buttonPanel.add(pullButton);
        buttonPanel.add(releaseButton);

        mainPanel.add(infoPanel, BorderLayout.CENTER);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(blocksPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.SOUTH);

        add(mainPanel);

        pullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fishingMechanic.pullLine();
                updateLabels();
                updateBlocks();
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

        pack();
        setLocationRelativeTo(null);
    }

    private void updateLabels() {
        messageLabel.setText(fishingMechanic.getMessage());
        fishDistanceLabel.setText(String.join("", fishingMechanic.getFishDistance()));

        lineTightLabel.setText("Line tight: " + (fishingMechanic.isLineTight() ? "Yes" : "No"));
    }

    private void updateBlocks() {
        blocksPanel.removeAll();


        for (String block : fishingMechanic.getFishDistance()) {
            JPanel blockPanel = new JPanel();
            blockPanel.setPreferredSize(new Dimension(30, 30));
            blockPanel.setBackground(Color.BLUE);
            if (block.equals("o/")) {
                blockPanel.setBackground(Color.RED);
            }
            blocksPanel.add(blockPanel);
        }

        blocksPanel.revalidate();
        blocksPanel.repaint();
    }
}