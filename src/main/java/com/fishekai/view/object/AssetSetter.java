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
        System.out.println(gp.fishekai);
        Location current_location = gp.fishekai.current_location;

        List<Map<String, Map<String, Integer>>> itemList = current_location.getItems();
        int i = 0;

        for (Map<String, Map<String, Integer>> item : itemList) {
            if (item.containsKey("apple")) {
                Map<String, Integer> apple = item.get("apple");
                gp.obj[i] = new OBJ_Apple();
                gp.obj[i].worldX = apple.get("column") * gp.tileSize;
                gp.obj[i].worldY = apple.get("row") * gp.tileSize;
                i++;
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