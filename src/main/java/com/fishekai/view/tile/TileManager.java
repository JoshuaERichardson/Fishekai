package com.fishekai.view.tile;

import com.fishekai.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[150];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
    }

    public void getTileImage(){
        try{
            // 0 = grass
            // 1 = water
            // 2 = dirt
            // 3 = dock

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/grass.png"));


            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/water.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/dirt.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/dock.png"));


        } catch (IOException e){
            e.printStackTrace();
        }
    }

//    public void loadMap(String filePath){
//        try{
//            InputStream is = getClass().getResourceAsStream(filePath);
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            int col = 0;
//            int row = 0;
//
//            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
//                String line = br.readLine();
//
//                while(col < gp.maxScreenCol){
//                    String numbers[] = line.split(" ");
//
//                    int num = Integer.parseInt(numbers[col]);
//
//                    mapTileNum[col][row] = num;
//                    col++;
//                }
//                if(col == gp.maxScreenCol){
//                    col = 0;
//                    row++;
//                }
//            }
//            br.close();
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//    }

    public void loadMap(String tileString){
        int col = 0;
        int row = 0;
        String[] tileStringArray = tileString.split("\n");
        int i = 0;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            while(col < gp.maxScreenCol){
                String numbers[] = tileStringArray[i].split(" ");

                int num = Integer.parseInt(numbers[col]);

                mapTileNum[col][row] = num;
                col++;
            }
            if(col == gp.maxScreenCol){
                col = 0;
                row++;
            }
            i++;
        }


    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;


        while(col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol) {
                col = 0;
                row++;
                x = 0;
                y += gp.tileSize;
            }
        }
    }
}