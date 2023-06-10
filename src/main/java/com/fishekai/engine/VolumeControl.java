package com.fishekai.engine;

import com.fishekai.utilities.AudioManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


public class VolumeControl {
    private JFrame window;
    private talkingChad talkingChad;

    public VolumeControl(AudioManager audioManager) {
        window = new JFrame();

        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(window,
                        "Are You Sure to Close this window(it can't be opened again)?",
                        "Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    window.dispose();
                }
            }
        });

        // Set GridLayout with 2 columns to organize buttons
        GridBagLayout layout = new GridBagLayout();
        window.setLayout(layout);
        window.setMaximumSize(new Dimension(800, 600));
        GridBagConstraints c = new GridBagConstraints();

        // Music Play/Stop
        JButton musicB = new JButton("Music On/Off");
        musicB.addActionListener(e -> {
            if (audioManager.isMusicPlaying()) {
                audioManager.stopMusic();
            } else {
                audioManager.playMusic(true);
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        window.add(musicB, c);


        // Sound Effects Enable/Disable
        JButton effectB = new JButton("Sound Effects On/Off");
        effectB.addActionListener(e -> {
            if (audioManager.areSoundEffectsEnabled()) {
                audioManager.setSoundEffectsEnabled(false);
            } else {
                audioManager.setSoundEffectsEnabled(true);
            }
        });
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        window.add(effectB, c);

        // Music Up Volume
        JButton volumeUpB = new JButton("Music Volume Up");
        volumeUpB.addActionListener(e -> audioManager.volumeUp());
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        window.add(volumeUpB, c);


        // Sound Effects up Volume
        JButton effectVolUpB = new JButton("Effects Volume Up");
        effectVolUpB.addActionListener(e -> audioManager.increaseSoundEffectsVolume());
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        window.add(effectVolUpB, c);

        // music down volume
        JButton volumeDownB = new JButton("Music Volume Down");
        volumeDownB.addActionListener(e -> audioManager.volumeDown());
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        window.add(volumeDownB, c);

        // Sound Effects down Volume
        JButton effectVolDownB = new JButton("Effects Volume Down");
        effectVolDownB.addActionListener(e -> audioManager.decreaseSoundEffectsVolume());
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        window.add(effectVolDownB, c);


        talkingChad = new talkingChad();

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        window.add(talkingChad, c);


        window.pack();
        window.setVisible(true);
        window.toFront();




    }
    public void changeImage() {
        talkingChad.changeImage();
    }

    public void showWindow() {
        window.setVisible(true);
        changeImage();
    }


    private class talkingChad extends JPanel {
        ImageIcon image1, image2, image3, image4;
        JLabel label1, label2, label3, label4;
        Image bufferedImage1, bufferedImage2, bufferedImage3, bufferedImage4;
        JPanel panel1, panel2, panel3, panel4;
        public talkingChad() {
            // load photo of Chad:
            image1 = new ImageIcon(getClass().getResource("/images/fireplace2.png"));
            image2 = new ImageIcon(getClass().getResource("/images/fireplace3.png"));
            image3 = new ImageIcon(getClass().getResource("/images/fireplace4.png"));
            image4 = new ImageIcon(getClass().getResource("/images/fireplace1.png"));

            bufferedImage1 = image1.getImage();
            bufferedImage2 = image2.getImage();
            bufferedImage3 = image3.getImage();
            bufferedImage4 = image4.getImage();


            label1 = new JLabel(image1);
            label2 = new JLabel(image2);
            label3 = new JLabel(image3);
            label4 = new JLabel(image4);


            // Card Layout:
            setLayout(new CardLayout());

            // Add just the first label to the window
            panel1 = new JPanel();
            panel2 = new JPanel();
            panel3 = new JPanel();
            panel4 = new JPanel();

            panel1.add(label1);
            panel2.add(label2);
            panel3.add(label3);
            panel4.add(label4);

            add(panel1);
            add(panel2);
            add(panel3);
            add(panel4);

            // Add just the first label to the window
        }
        public void changeImage() {
            // Choose one of the images randomly:
            CardLayout cl = (CardLayout) (this.getLayout());
            cl.next(this);
            // Refresh the window
            revalidate();
            repaint();
        }

    }


    public JFrame getWindow() {
        return window;
    }

    public VolumeControl.talkingChad getTalkingChad() {
        return talkingChad;
    }
}
