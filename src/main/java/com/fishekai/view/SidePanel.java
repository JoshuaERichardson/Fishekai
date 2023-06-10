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
//    private VolumeControl volumeControl; // Instance variable declaration

    private Fishekai fishekai;

    public SidePanel(MainPanel mainPanel, Player textPlayer, Fishekai fishekai) {
        this.mainPanel = mainPanel;
        setVisible(true);
        audioManager = fishekai.getAudioManager();
        this.fishekai = fishekai;

        // Initializing the instance variable instead of declaring a new one
//        this.volumeControl = new VolumeControl(audioManager);

        // Set the layout of the JPanel to BoxLayout (Y_AXIS)
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Color buttonColor = Color.decode("#00827f");

//        // Volume button:
//        JButton volumeButton = new JButton("Volume");
//        volumeButton.setRequestFocusEnabled(false);
//        volumeButton.setBackground(buttonColor);
//        volumeButton.setForeground(Color.WHITE);
//
//        volumeButton.addActionListener(e -> {
//            volumeControl.showWindow();
//            audioManager.playSoundEffect("volume");
//        });
//
//        this.add(volumeButton);
//        this.add(Box.createVerticalStrut(20));

        // Make the Help Button:
        JButton helpButton = new JButton("   Help   ");
        helpButton.setRequestFocusEnabled(false);
        helpButton.setBackground(buttonColor); // Sets the background color of the button
        helpButton.setForeground(Color.WHITE);
        // Add the listener to the Help:
        helpButton.addActionListener(e -> {
            new HelpPopup(Display.showHelp()).createHelpDialog().setVisible(true);
            audioManager.playSoundEffect("help");
        });

        this.add(helpButton);

        // Add space
        this.add(Box.createVerticalStrut(20));  // add 20 pixel space

        JButton mapButton = new JButton("   Map   ");
        mapButton.setRequestFocusEnabled(false);
        mapButton.setBackground(buttonColor); // Sets the background color of the button
        mapButton.setForeground(Color.WHITE);
        mapButton.addActionListener(e -> {
            // Load the image
            String imagePath = "/images/map.png";
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
            Image image = imageIcon.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
            JOptionPane.showMessageDialog(null, "", "Map", JOptionPane.INFORMATION_MESSAGE, imageIcon);
            audioManager.playSoundEffect("map");
        });

        this.add(mapButton);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public HelpPopup getHelpPopup() {
        return helpPopup;
    }
}