package com.fishekai.view;

import com.fishekai.engine.Fishekai;
import com.fishekai.engine.Introduction;
import com.fishekai.view.entity.Player;
import com.fishekai.view.object.AssetSetter;
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
    final int scale = 3; // scale of the game
    public final int tileSize = originalTileSize * scale; // tile size
    public final int maxScreenCol = 16; // max screen columns
    public final int maxScreenRow = 12; // max screen rows
    public final int screenWidth = tileSize * maxScreenCol; // screen width (768 pixels)
    public final int screenHeight = tileSize * maxScreenRow; // screen height (576 pixels)
    final int FPS = 60; // screen frames per second
    private int order = 0;
    private Timer gameTimer;

    // Note: Player from entity package NOT from models
    public Player player;
    public TileManager tileM = new TileManager(this);
    KeyHandler kh;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[10]; // 10 slots for objects but we can adjust
    public Fishekai fishekai;


    public GamePanel(KeyHandler kh, Fishekai fishekai) {
        this.kh = kh;
        player = new Player(this, kh, fishekai);
        this.fishekai = fishekai;
        tileM.loadMap(fishekai.current_location.getTiles());
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

        // Player
        player.draw(g2);


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
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
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
}