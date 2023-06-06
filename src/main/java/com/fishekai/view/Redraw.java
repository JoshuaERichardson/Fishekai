package com.fishekai.view;

public class Redraw {
    public static void redraw(MainWindow window) {
        // Redraw the window:
        window.revalidate();
        window.repaint();
    }

}