package com.fishekai.view;

import com.fishekai.engine.Display;
import com.fishekai.engine.Fishekai;
import com.fishekai.engine.HelpPopup;
import com.fishekai.engine.VolumeControl;
import com.fishekai.models.Location;
import com.fishekai.models.Player;
import com.fishekai.utilities.AudioManager;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    // Size:
    public static final int WIDTH = 400;
    public static final int HEIGHT = 1000;
    private final JPanel mainPanel;
    private HelpPopup helpPopup;
    private final AudioManager audioManager;
    private Fishekai fishekai;

    public SidePanel(MainPanel mainPanel, Player textPlayer, Fishekai fishekai) {
        this.mainPanel = mainPanel;
        // setSize(new Dimension(HEIGHT, WIDTH));
        setVisible(true);
        setBackground(java.awt.Color.GREEN);
        audioManager = fishekai.getAudioManager();
        this.fishekai = fishekai;

        // Make the Help Button:
        JButton helpButton = new JButton("Help");
        helpButton.setRequestFocusEnabled(false);

        // Add the listener to the Help:
        helpButton.addActionListener(e -> {
            new HelpPopup(Display.showHelp()).createHelpDialog().setVisible(true);
            audioManager.playSoundEffect("help");
        });

        this.add(helpButton);

// Fishing scene start in North Beach

        JButton startFishingButton = new JButton("Start Fishing");
        startFishingButton.setRequestFocusEnabled(false);

        startFishingButton.addActionListener(e -> {

            Player player = fishekai.getTextPlayer();
            Location location = fishekai.getCurrent_location();
            fishekai.getFishingMechanic().startFishing(player, location, audioManager);

            FishingFrame fishingFrame = new FishingFrame(fishekai.getFishingMechanic());
            fishingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fishingFrame.setVisible(true);
            audioManager.playSoundEffect("fishing");
        });

        this.add(startFishingButton);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public HelpPopup getHelpPopup() {
        return helpPopup;
    }
}