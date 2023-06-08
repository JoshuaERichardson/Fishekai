package com.fishekai.view;

import com.fishekai.engine.Fishekai;
import com.fishekai.models.Item;
import com.fishekai.models.Location;
import com.fishekai.view.object.OBJ_Fish;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class StatusPanel extends JPanel {
    private final Fishekai fishekai;
    private int hunger;
    private int thirst;
    private int health;
    private JPanel healthPanel, hungerPanel, thirstPanel;
    private JLabel hungerLabel, thirstLabel, healthLabel;
    private boolean haveStick, haveRope, haveHook, havePole;
    private Image stickImage, ropeImage, hookImage, poleImage;
    private JPanel healthStatus, buildAPole;

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
        healthStatus = new JPanel();
        healthStatus.setLayout(new BoxLayout(healthStatus, BoxLayout.X_AXIS));
        // Print the health to the health panel:
        healthPanel = new JPanel();
        healthLabel = new JLabel("Health: " + fishekai.textPlayer.getHp());
        healthPanel.add(healthLabel);
        healthStatus.add(healthPanel);
        // Print the hunger to the hunger panel:
        hungerPanel = new JPanel();
        hungerLabel = new JLabel("Hunger: " + fishekai.textPlayer.getHunger());
        hungerPanel.add(hungerLabel);
        healthStatus.add(hungerPanel);
        // Print the thirst to the thirst panel:
        thirstPanel = new JPanel();
        thirstLabel = new JLabel("Thirst: " + fishekai.textPlayer.getThirst());
        thirstPanel.add(thirstLabel);
        healthStatus.add(thirstPanel);
        add(healthStatus);

        // Row 2 is a JPanel with 1 column
        buildAPole = new JPanel();
        JLabel buildLabel = new JLabel("Build a pole");
        buildAPole.add(buildLabel);
        add(buildAPole);

        // Set the stick image:
        ImageIcon stickIcon = new ImageIcon(getClass().getResource("/images/stick.png"));
        stickImage = stickIcon.getImage();
        // Set the hook image:
        ImageIcon hookIcon = new ImageIcon(getClass().getResource("/images/hook.png"));
        hookImage = hookIcon.getImage();
        // Set the rope image:
        ImageIcon ropeIcon = new ImageIcon(getClass().getResource("/images/rope.png"));
        ropeImage = ropeIcon.getImage();
        // Set the pole image:
        ImageIcon poleIcon = new ImageIcon(getClass().getResource("/images/pole.png"));
        poleImage = poleIcon.getImage();


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
        didWeBuildAPole();
    }
    public void setHaveHook(boolean haveHook) {
        this.haveHook = haveHook;
        didWeBuildAPole();
    }
    public void setHaveRope(boolean haveRope) {
        this.haveRope = haveRope;
        didWeBuildAPole();
    }
    public void didWeBuildAPole(){
        if(haveStick && haveHook && haveRope){
            havePole = true;
            // Wipe the build-a-pole panel and add the pole image:
            buildAPole.removeAll();
            JLabel poleLabel = new JLabel("You built a pole!");
            buildAPole.add(poleLabel);
            buildAPole.repaint();

            fishekai.getAudioManager().playSoundEffect("build");
            populateNorthBeachFish();


        }
    }

    private void populateNorthBeachFish() {
        Location northBeach = fishekai.getLocations().get("North Beach");
        northBeach.addFish();


    }

    public void update() {
        thirstLabel.setText("Thirst: " + updateThirst());
        hungerLabel.setText("Hunger: " + updateHunger());
        healthLabel.setText("Health: " + updateHealth());
        healthStatus.repaint();
    }
    public void draw(Graphics2D g2){
        if (havePole) buildAPole.getGraphics().drawImage(poleImage, 0, 20, buildAPole.getWidth(), (int)(buildAPole.getHeight()*.75), null);
        else if(haveStick) buildAPole.getGraphics().drawImage(stickImage, 0, 20, buildAPole.getWidth(), (int)(buildAPole.getHeight()*.75), null);
        else if(haveHook) buildAPole.getGraphics().drawImage(hookImage, 0, 20, buildAPole.getWidth(), (int)(buildAPole.getHeight()*.75), null);
        else if(haveRope) buildAPole.getGraphics().drawImage(ropeImage, 0, 20, buildAPole.getWidth(), (int)(buildAPole.getHeight()*.75), null);
    }
}