package com.fishekai.view.tile;

import com.fishekai.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public BufferedImage mapImage;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[4];
        getMapImage();
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        loadCollisionMap();
    }

    public void getMapImage(){
        try{
            // Fetching the image based on the location
            String locationName = gp.fishekai.current_location.getName();
//            if (gp.fishekai.current_location.getName().equals("North Beach")) locationName = "northbeach";
//            if (locationName.equals("Jungle")) locationName = "forest";
//            if (locationName.equals("Mystical Grove")){
//                locationName = "mysticalgrove";
//            }
            String filePath = "/sprites/locations/" + locationName + ".png";

            InputStream in = getClass().getResourceAsStream(filePath);
            if (in == null) {
                System.out.println("InputStream is null for file: " + filePath);
                return;
            }
            mapImage = ImageIO.read(in);
        } catch (IOException e){
            e.printStackTrace();
        }
        loadCollisionMap();
    }

    public void loadCollisionMap() {
        String locationName = gp.fishekai.current_location.getName();
        String fileName = "/sprites/locations/" + locationName + "CollisionMap.txt";  // construct the file name based on the current location

        if (mapTileNum == null) {
            System.out.println("mapTileNum is null");
            return;
        }

        try {
            InputStream in = getClass().getResourceAsStream(fileName);
            if (in == null) {
                System.out.println("InputStream is null for file: " + fileName);
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split(" ");
                for (int col = 0; col < values.length; col++) {
                    mapTileNum[col][row] = Integer.parseInt(values[col]);
                }
                row++;
            }
        } catch (IOException e) {
            System.out.println("Error loading map file: " + e);
        }
    }

public void draw(Graphics2D g2) {
    if (mapImage != null) {
        g2.drawImage(mapImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
        }
    }
}