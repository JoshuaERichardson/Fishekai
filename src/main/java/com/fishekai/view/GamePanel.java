package com.fishekai.view;

import com.fishekai.view.entity.Player;
import com.fishekai.view.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // original tile size
    final int scale = 3; // scale of the game
    public final int tileSize = originalTileSize * scale; // tile size
    public final int maxScreenCol = 16; // max screen columns
    public final int maxScreenRow = 12; // max screen rows
    public final int screenWidth = tileSize * maxScreenCol; // screen width (768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // screen height (576 pixels)
    final int FPS = 60; // screen frames per second

    KeyHandler keyH = new KeyHandler();

    Thread gameThread;

    // Note: Player from entity package NOT from models
    Player player = new Player(this, keyH);
    TileManager tileM = new TileManager(this);

    StartGame startGame = new StartGame(this, keyH);




    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        System.out.println(screenHeight + " " + screenWidth);
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while(gameThread != null) {

            double drawInterval = 1000000000 / FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;

            long currentTime = System.nanoTime();  // 1mil nano = 1 sec


                // 1 UPDATE: update game state
                update();
                // 2 DRAW: draw game state to screen
                repaint();


                // Forces the 60 FPS max
                try {
                    double remainingTime = nextDrawTime - System.nanoTime();
                    remainingTime = remainingTime / 1000000; // convert to milliseconds

                    // If remaining time is negative, we need to draw immediately
                    if(remainingTime < 0) {
                        remainingTime = 0;
                    }
                    Thread.sleep((long) remainingTime);

                    nextDrawTime += drawInterval;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    public void update() {
//        player.update();
        startGame.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

//        Graphics2D g2 = (Graphics2D) g;
//
//        tileM.draw(g2); // Note that tile is before player!!! Otherwise player will be drawn over the tile
//
//        player.draw(g2);
//
//
//        g2.dispose();

        startGame.draw(g);





    }


}