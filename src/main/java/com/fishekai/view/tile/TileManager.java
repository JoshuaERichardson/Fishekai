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
    }

    public void getMapImage(){
        try{
            // Fetching the image based on the location
            mapImage = ImageIO.read(getClass().getResourceAsStream("/sprites/locations/" + gp.fishekai.current_location.getName() + ".png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadCollisionMap() {
        String locationName = gp.fishekai.current_location.getName();
        String fileName = "/sprites/locations/" + locationName + "CollisionMap.txt";  // construct the file name based on the current location
        try (InputStream in = getClass().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
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

//    public void getTileImage(){
//        try{
//            // 0 = grass
//            // 1 = water
//            // 2 = dirt
//            // 3 = dock
//
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/grass.png"));
//
//
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/water.png"));
//            tile[1].collision = true;
//
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/dirt.png"));
//
//            tile[3] = new Tile();
//            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/dock.png"));
//
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//

//    public void loadMap(String tileString){
//        int col = 0;
//        int row = 0;
//        String[] tileStringArray = tileString.split("\n");
//        int i = 0;
//        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
//            while(col < gp.maxScreenCol){
//                String numbers[] = tileStringArray[i].split(" ");
//
//                int num = Integer.parseInt(numbers[col]);
//
//                mapTileNum[col][row] = num;
//                col++;
//            }
//            if(col == gp.maxScreenCol){
//                col = 0;
//                row++;
//            }
//            i++;
//        }
//
//
//    }

//    public void draw(Graphics2D g2) {
//        int col = 0;
//        int row = 0;
//        int x = 0;
//        int y = 0;
//
//
//        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
//
//            int tileNum = mapTileNum[col][row];
//
//            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
//            col++;
//            x += gp.tileSize;
//
//            if(col == gp.maxScreenCol) {
//                col = 0;
//                row++;
//                x = 0;
//                y += gp.tileSize;
//            }
//        }
//    }
public void draw(Graphics2D g2) {
    if (mapImage != null) {
        g2.drawImage(mapImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }
}
}