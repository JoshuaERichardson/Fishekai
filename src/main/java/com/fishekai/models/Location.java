package com.fishekai.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {

    // fields
    private final String name;
    private Map<String, String> directions;
    private boolean hasBeenHere = false; // default value is false
    private Map<String, String> descriptions;
    private Map<String, Fish> fishes = new HashMap<>();
    private Map<String, NPC> npc = new HashMap<>();
    private String tiles;
    private List<Map<String, Map<String, Integer>>> items; // itemMap has items, locations, and directions(if door)
    private Map<String, Item> itemsInText;
    private List<Map<String, String>> signs;





    // constructors
    public Location(String name, Map<String, String> directions, Map<String, String> descriptions, String tileMap, List<Map<String, Map<String, Integer>>> items, List<Map<String, String>> signs) {
        this.name = name;
        this.directions = directions;
        this.descriptions = descriptions;
        this.tiles = tileMap;
        this.items = items;
        this.signs = signs;
    }

    // accessors
    public String getName() {
        return name;
    }

    public Map<String, String> getDirections() {
        return directions;
    }

    public Map<String, String> getDescriptions() {
        return descriptions;
    }

    public boolean isHasBeenHere() {
        return hasBeenHere;
    }

    public void setHasBeenHere(boolean hasBeenHere) {
        this.hasBeenHere = hasBeenHere;
    }

    public Map<String, Fish> getFishes() {
        return fishes;
    }

    public void setFishes(Map<String, Fish> fishes) {
        this.fishes = fishes;
    }

    public void addFishable() {
        Map<String, Map<String, Integer>> fishMap = new HashMap<>();
        fishMap.put("fish", null);
        items.add(fishMap);

        Map<String, Map<String, Integer>> doorMap = new HashMap<>();
        Map<String, Integer> info = new HashMap<>();
        info.put("column", 1);
        info.put("row", 4);
        info.put("direction", 3);
        doorMap.put("door", info);
        items.add(doorMap);




    }



    public Map<String, NPC> getNpc() {
        return npc;
    }

    public void setNpc(Map<String, NPC> npc) {
        this.npc = npc;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", directions=" + directions +
                ", description='" + descriptions + '\'' +
                ", items=" + items +
                '}';
    }

    public String getTiles() {
        return tiles;
    }

    public List<Map<String, Map<String, Integer>>> getItems() {
        return items;
    }

    public Map<String, Item> getItemsInText() {
        return itemsInText;
    }
    public void setItemsInText(Map<String, Item> itemMap) {
        itemsInText = itemMap;
    }

    public List<Map<String, String>> getSigns() {
        return signs;
    }


    // for internal testing
//    public static void main(String[] args) {
//        Location north_beach = new Location("North Beach");
//
//        HashMap<String, Location> direction = new HashMap<>();
//        direction.put("north", north_beach);
//
//        HashMap<String, String> description = new HashMap<>();
//        description.put("before", "Description when first entered.");
//        description.put("after", "Description for subsequent entries.");
//
//        Location beach = new Location("Beach", direction, description);
//
//        Location current_location = beach;
//
//        System.out.println(current_location.getName());
//
//        System.out.println(current_location.getDirections().keySet());
//    }
}

