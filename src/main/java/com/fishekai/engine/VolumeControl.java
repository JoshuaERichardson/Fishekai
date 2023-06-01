package com.fishekai.engine;

import com.fishekai.utilities.AudioManager;

import javax.swing.*;
import java.awt.*;


public class VolumeControl {
    private JFrame window;

    public VolumeControl(AudioManager audioManager) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set GridLayout with 2 columns to organize buttons
        GridLayout layout = new GridLayout(0, 2, 20, 10);
        window.setLayout(layout);

        // Music Play/Stop
        JButton musicB = new JButton("Music On/Off");
        musicB.addActionListener(e -> {
            if (audioManager.isMusicPlaying()) {
                audioManager.stopMusic();
            } else {
                audioManager.playMusic(true);
            }
        });
        window.add(musicB);


        // Sound Effects Enable/Disable
        JButton effectB = new JButton("Sound Effects On/Off");
        effectB.addActionListener(e -> {
            if (audioManager.areSoundEffectsEnabled()) {
                audioManager.setSoundEffectsEnabled(false);
            } else {
                audioManager.setSoundEffectsEnabled(true);
            }
        });
        window.add(effectB);

        // Music Up Volume
        JButton volumeUpB = new JButton("Music Volume Up");
        volumeUpB.addActionListener(e -> audioManager.volumeUp());
        window.add(volumeUpB);


        // Sound Effects up Volume
        JButton effectVolUpB = new JButton("Effects Volume Up");
        effectVolUpB.addActionListener(e -> audioManager.increaseSoundEffectsVolume());
        window.add(effectVolUpB);

        // music down volume
        JButton volumeDownB = new JButton("Music Volume Down");
        volumeDownB.addActionListener(e -> audioManager.volumeDown());
        window.add(volumeDownB);

        // Sound Effects down Volume
        JButton effectVolDownB = new JButton("Effects Volume Down");
        effectVolDownB.addActionListener(e -> audioManager.decreaseSoundEffectsVolume());
        window.add(effectVolDownB);

        window.pack();
        window.setVisible(true);
    }

    public void showWindow() {
        window.setVisible(true);
    }

}
