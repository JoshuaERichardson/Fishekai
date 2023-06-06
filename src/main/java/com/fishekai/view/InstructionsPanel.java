package com.fishekai.view;

import com.fishekai.engine.DataLoader;
import com.fishekai.engine.Display;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class InstructionsPanel extends MainPanel{
    public final String PATH = "/images/instructions.jpg";
    private Image image;
    private JLabel label;
    private Map<String, String> instructions;
    private String story;
    private String objective;
    private String playerInfo;
    private String winningCondition;
    private JTextArea textArea;
    private final String openHTML = "<html><p>";
    private final String closeHTML = "</p></html>";


    public InstructionsPanel() {
        super();
        loadImage(PATH);
        instructions = DataLoader.processGameInfo();


        winningCondition = instructions.get("winning_condition");
        playerInfo       = instructions.get("player_info");
        objective        = instructions.get("objective");
        story            = instructions.get("story");

        textArea = new JTextArea(story+objective+playerInfo+winningCondition);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize((int)MAIN_PANEL_SIZE.getWidth(), (int)MAIN_PANEL_SIZE.getHeight());
        add(scrollPane);
    }
    void loadImage(String path){
        ImageIcon icon = new ImageIcon(getClass().getResource(PATH));
        image = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the image on the panel
        if(image != null){
            g.drawImage(image, 0, 0, MAIN_PANEL_SIZE.width, MAIN_PANEL_SIZE.height, null);
        }
    }




}