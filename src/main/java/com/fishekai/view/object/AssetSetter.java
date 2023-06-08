package com.fishekai.view.object;

import com.fishekai.models.Location;
import com.fishekai.view.GamePanel;

import java.util.List;
import java.util.Map;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // Max X = 12(columns)
        // Max Y = 12(rows)
        Location current_location = gp.fishekai.current_location;

        List<Map<String, Map<String, Integer>>> itemList = current_location.getItems();
        int i = 0;
        // Wipe the map of all items first:
        for (int j = 0; j < gp.obj.length; j++) {
            gp.obj[j] = null;
        }
        for (Map<String, Map<String, Integer>> item : itemList) {
            if (item.containsKey("apple")) {
                Map<String, Integer> apple = item.get("apple");
                gp.obj[i] = new OBJ_Apple();
                gp.obj[i].worldX = apple.get("column") * gp.tileSize;
                gp.obj[i].worldY = apple.get("row") * gp.tileSize;
                i++;
            } else if (item.containsKey("banana")){
                Map<String, Integer> banana = item.get("banana");
                gp.obj[i] = new OBJ_Banana();
                gp.obj[i].worldX = banana.get("column") * gp.tileSize;
                gp.obj[i].worldY = banana.get("row") * gp.tileSize;
                i++;
            } else if (item.containsKey("parachute")) {
                Map<String, Integer> parachute = item.get("parachute");
                gp.obj[i] = new OBJ_Parachute();
                gp.obj[i].worldX = parachute.get("column") * gp.tileSize;
                gp.obj[i].worldY = parachute.get("row") * gp.tileSize;
                i++;
            } else if (item.containsKey("flask")) {
                Map<String, Integer> flask = item.get("flask");
                gp.obj[i] = new OBJ_Flask();
                gp.obj[i].worldX = flask.get("column") * gp.tileSize;
                gp.obj[i].worldY = flask.get("row") * gp.tileSize;
                i++;
            } else if (item.containsKey("hook")) {
                Map<String, Integer> hook = item.get("hook");
                gp.obj[i] = new OBJ_Hook();
                gp.obj[i].worldX = hook.get("column") * gp.tileSize;
                gp.obj[i].worldY = hook.get("row") * gp.tileSize;
                i++;
            } else if (item.containsKey("parachute")) {
                Map<String, Integer> parachute = item.get("parachute");
                gp.obj[i] = new OBJ_Parachute();
                gp.obj[i].worldX = parachute.get("column") * gp.tileSize;
                gp.obj[i].worldY = parachute.get("row") * gp.tileSize;
                i++;
            } else if (item.containsKey("stick")) {
                Map<String, Integer> stick = item.get("stick");
                gp.obj[i] = new OBJ_Stick();
                gp.obj[i].worldX = stick.get("column") * gp.tileSize;
                gp.obj[i].worldY = stick.get("row") * gp.tileSize;
                i++;
            } else if (item.containsKey("water")) {
                Map<String, Integer> water = item.get("water");
                gp.obj[i] = new OBJ_Water();
                gp.obj[i].worldX = water.get("column") * gp.tileSize;
                gp.obj[i].worldY = water.get("row") * gp.tileSize;
                i++;
            } else if (item.containsKey("fish")){
                gp.obj[i] = new OBJ_Fish();
                gp.obj[i].worldY = gp.obj[i].worldY * gp.tileSize;
                gp.obj[i].worldX = gp.obj[i].worldX * gp.tileSize;
            } else if (item.containsKey("door")) {
                Map<String, Integer> door = item.get("door");
                gp.obj[i] = new OBJ_Door();
                gp.obj[i].worldX = door.get("column") * gp.tileSize;
                gp.obj[i].worldY = door.get("row") * gp.tileSize;
                // 0 is north, 1 east, 2 south, 3 west
                String nextLocation = switchDirection(door.get("direction"));
                String prevDirection = switchDirection((door.get("direction")+2) % 4);
                // Now find out what location the door leads to
                String doorGoesTo = current_location.getDirections().get(nextLocation);
                String doorComesFrom = gp.fishekai.locations.get(doorGoesTo).getDirections().get(prevDirection);
                ((OBJ_Door) gp.obj[i]).setLocation(doorGoesTo);
                ((OBJ_Door) gp.obj[i]).setFromLocation(doorComesFrom);
                i++;
                }

            }
        // Load the signs:
        List<Map<String, String>> signs = current_location.getSigns();
        // Wipe the map of all signs first:
        for (int j = 0; j < gp.sign.length; j++) {
            gp.sign[j] = null;
        }
        i = 0;
        for (Map<String, String> sign : signs){
            int row = Integer.parseInt(sign.get("row")) * gp.tileSize;
            int col = Integer.parseInt(sign.get("column")) * gp.tileSize;
            String text = sign.get("text");
            Sign s = new Sign(col, row, text);
            gp.sign[i] = s;
            i++;
        }



    }

    private String switchDirection(int direction) {
        String location;
        switch(direction) {
            case 0: location = "north"; break;
            case 1: location = "east";  break;
            case 2: location = "south"; break;
            case 3: location = "west";  break;
            default:
                location = "north";
        }
        return location;
    }



}