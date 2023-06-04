package com.fishekai.view;

import com.fishekai.engine.Fishekai;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, enterPressed;
    public boolean spaceTyped, enterTyped;
    public boolean enterJustPressed, spaceJustPressed;
    private boolean enterWasPressedLastTick, spaceWasPressedLastTick;
    private List<EnterKeyListener> enterKeyListeners = new ArrayList<>();
    private Fishekai game;

    private static int splashScreenCounter = 0;

    // Constructor:
    public KeyHandler(Fishekai game) {
        this.game = game;
    }

    public void addEnterKeyListener(EnterKeyListener listener) {
        enterKeyListeners.add(listener);
    }
    public interface EnterKeyListener {
        void onEnterKeyPressed();
    }
    private void notifyEnterKeyListeners() {
        for (EnterKeyListener listener : enterKeyListeners) {
            listener.onEnterKeyPressed();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == ' ') {
            spaceTyped = true;
        }
        if (key == '\n') {
            enterTyped = true;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            System.out.println("W pressed");
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            System.out.println("Enter pressed");
            enterPressed = true;
            notifyEnterKeyListeners();
            // If we are not on the last card:
            if (splashScreenCounter == 0) {
                splashScreenCounter++;
                System.out.println(splashScreenCounter);
                game.window.nextCard();
            } else if (splashScreenCounter == 1) {
                splashScreenCounter++;
                game.window.nextCard();
                // Start the game:
                game.window.startGameTimer();
            }



        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }

    public void update() {
        if (enterPressed && !enterWasPressedLastTick) {
            enterJustPressed = true;
        } else {
            enterJustPressed = false;
        }

        if (spacePressed && !spaceWasPressedLastTick) {
            spaceJustPressed = true;
        } else {
            spaceJustPressed = false;
        }

        enterWasPressedLastTick = enterPressed;
        spaceWasPressedLastTick = spacePressed;
    }
}