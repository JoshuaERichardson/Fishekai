package com.fishekai.view;

import com.fishekai.engine.Introduction;
import com.fishekai.view.entity.Player;
import com.fishekai.view.splashscreen.FullScreenScroll;
import com.fishekai.view.splashscreen.SplashPaths;
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
    Player player;
    TileManager tileM = new TileManager(this);
    KeyHandler kh;

    public GamePanel(KeyHandler kh) {
        this.kh = kh;
        player = new Player(this, kh);
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




//    public void startGameThread(){
//        // The thread is managed by Swing
//        gameThread = new Thread(this);
//        gameThread.start();
//    }
//    @Override
//    public void run() {
//        while(gameThread != null) {
//
//            // Need to revamp run --- should not be messing with the thread.
//            // Able to add the runnable "later" queue
//            // We can make a timer thread for recurrent tasks.  <--- needs to be in the event listener <--- timer class!
//
//            double drawInterval = 1000000000 / FPS;
//            double nextDrawTime = System.nanoTime() + drawInterval;
//
//            long currentTime = System.nanoTime();  // 1mil nano = 1 sec
//
//
//                // 1 UPDATE: update game state
//                update();
//                // 2 DRAW: draw game state to screen
//                repaint();
//
//
//                // Forces the 60 FPS max
//                try {
//                    double remainingTime = nextDrawTime - System.nanoTime();
//                    remainingTime = remainingTime / 1000000; // convert to milliseconds
//
//                    // If remaining time is negative, we need to draw immediately
//                    if(remainingTime < 0) {
//                        remainingTime = 0;
//                    }
//                    Thread.sleep((long) remainingTime);
//
//                    nextDrawTime += drawInterval;
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }

    /**
     * Control the game state with the order variable
     */
    public void update() {
                player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            tileM.draw(g2); // Note that tile is before player!!! Otherwise player will be drawn over the tile

            player.draw(g2);


            g2.dispose();

        }

}