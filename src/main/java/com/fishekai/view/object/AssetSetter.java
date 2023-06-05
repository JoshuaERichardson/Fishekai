package com.fishekai.view.object;

import com.fishekai.view.GamePanel;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // Max X = 16(columns)
        // Max Y = 12(rows)
        gp.obj[0] = new OBJ_Apple();
        gp.obj[0].worldX = 8 * gp.tileSize; // Columns
        gp.obj[0].worldY = 8 * gp.tileSize; // Row

        gp.obj[1] = new OBJ_Apple();
        gp.obj[1].worldX = 1 * gp.tileSize;
        gp.obj[1].worldY = 1 * gp.tileSize;

        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 15 * gp.tileSize;
        gp.obj[2].worldY = 6 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        ((OBJ_Door) gp.obj[3]).setLocation("beach");
        gp.obj[3].worldX = 8 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;

    }



}