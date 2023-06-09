package com.fishekai.view;

import com.fishekai.engine.Fishekai;
import com.fishekai.utilities.AudioManager;
import com.fishekai.view.entity.Player;
import com.fishekai.view.object.AssetSetter;
import com.fishekai.view.object.Sign;
import com.fishekai.view.object.SuperObject;
import com.fishekai.view.physics.CollisionChecker;
import com.fishekai.view.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends MainPanel{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // original tile size
    final int scale = 4; // scale of the game
    public final int tileSize = originalTileSize * scale; // tile size
    public static final int MAX_SCREEN_COL = 12; // max screen columns
    public static final int MAX_SCREEN_ROW = 12; // max screen rows
    public final int screenWidth = tileSize * MAX_SCREEN_COL;
    public final int screenHeight = tileSize * MAX_SCREEN_ROW;
    final int FPS = 60; // screen frames per second
    private int order = 0;
    private Timer gameTimer;

    // Note: Player from entity package NOT from models
    public Player player;
    public TileManager tileM;
    KeyHandler kh;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[20]; // 20 slots for objects but we can adjust
    public Sign sign[] = new Sign[4];
    public Fishekai fishekai;
//    private final SidePanel sidePanel;



    private DialogEngine dialog;


    public GamePanel(KeyHandler kh, Fishekai fishekai) {
        this.kh = kh;
        player = new Player(this, kh, fishekai);
        this.fishekai = fishekai;
        tileM = new TileManager(this);
        dialog = new DialogEngine(this);
        setPreferredSize(MainPanel.MAIN_PANEL_SIZE);

    }
    public void setupGame() {
        assetSetter.setObject();
    }

    public void startGameTimer(){
        int delay = 1000 / FPS; // Delay in milliseconds between each frame
        gameTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update game state
//                System.out.println("Main Window Height: " + fishekai.window.getHeight() + "\nMain Window Width: " + fishekai.window.getWidth());
                update();
                // Draw game state to screen
                repaint();
            }
        });
        gameTimer.start();
    }

    public void stopGameTimer(){
        if (gameTimer != null && gameTimer.isRunning()){
            gameTimer.stop();
            gameTimer = null;
        }
    }


    /**
     * Control the game state with the order variable
     */
    public void update() {
                player.update();
                dialog.update();
//                fishekai.window.getStatusPanel().update(); // Update the status panel

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Tile
        tileM.draw(g2); // Note that tile is before player!!! Otherwise player will be drawn over the tile

        // Object
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // Sign
        for(int i = 0; i < sign.length; i++) {
            if(sign[i] != null) {
                sign[i].draw(g2, this);
            }
        }

        // Player
        player.draw(g2);

        // Dialog
        dialog.draw(g2);

        // Status Panel
        fishekai.window.getStatusPanel().draw(g2);


        g2.dispose();

        }

    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public int getScale() {
        return scale;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return MAX_SCREEN_COL;
    }

    public int getMaxScreenRow() {
        return MAX_SCREEN_ROW;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getFPS() {
        return FPS;
    }

    public int getOrder() {
        return order;
    }

    public Timer getGameTimer() {
        return gameTimer;
    }

    public Player getPlayer() {
        return player;
    }

    public TileManager getTileM() {
        return tileM;
    }

    public KeyHandler getKh() {
        return kh;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public AssetSetter getAssetSetter() {
        return assetSetter;
    }

    public SuperObject[] getObj() {
        return obj;
    }

    public Fishekai getFishekai() {
        return fishekai;
    }
    public DialogEngine getDialog() {
        return dialog;
    }

    public AudioManager getAudioManager() {
        return fishekai.getAudioManager();
    }

    public void setPaused(boolean b) {
        if (b) {
            stopGameTimer();
        } else {
            startGameTimer();
        }
    }
}