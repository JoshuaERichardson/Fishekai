package com.fishekai.view;

import com.fishekai.engine.DataLoader;
import com.fishekai.engine.Display;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class InstructionsPanel extends MainPanel {
    // public final String PATH = "/images/waves.png";
    // private Image image;
    private JLabel label;
    private Map<String, String> instructions;
    private String story;
    private String objective;
    private String playerInfo;
    private String winningCondition;
    private JTextPane textPane;
    private final String openHTML = "<html><p><font size=\"20\">";
    private final String closeHTML = "</p></html>";

    public InstructionsPanel() {
        super();
        setLayout(new BorderLayout());
        // loadImage(PATH);
        instructions = DataLoader.processGameInfo();

        winningCondition = instructions.get("winning_condition");
        playerInfo       = instructions.get("player_info");
        objective        = instructions.get("objective");
        story            = instructions.get("story");

        textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(openHTML + story + "<br><br>" + objective + "<br><br>" + playerInfo+ "<br><br>" + winningCondition + "<br><br>PRESS ENTER TO CONTINUE"+ closeHTML);
        textPane.setForeground(Color.WHITE);
        textPane.setEditable(false);
        textPane.setOpaque(false);
        textPane.setFocusable(false);
        textPane.setCaretColor(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);

        setPreferredSize(MainPanel.MAIN_PANEL_SIZE);
    }

    // void loadImage(String path){
    //    ImageIcon icon = new ImageIcon(getClass().getResource(PATH));
    //    image = icon.getImage();
    //}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background color
//        g.setColor(Color.decode("#728F9D"));
//        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Draw the image on the panel
        // if(image != null){
        //    g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        // }
    }
}