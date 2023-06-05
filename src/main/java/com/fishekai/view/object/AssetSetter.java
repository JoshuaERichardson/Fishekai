package com.fishekai.view.object;

import com.fishekai.view.GamePanel;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Apple();
        gp.obj[0].worldX = 8 * gp.tileSize; // Columns
        gp.obj[0].worldY = 8 * gp.tileSize; // Row

        gp.obj[1] = new OBJ_Apple();
        gp.obj[1].worldX = 1 * gp.tileSize;
        gp.obj[1].worldY = 1 * gp.tileSize;
    }



}