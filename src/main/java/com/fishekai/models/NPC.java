package com.fishekai.models;

import com.google.gson.annotations.Expose;

import java.util.Random;

public class NPC extends Character {
    @Expose
    private String location;
    @Expose
    private String type;
    @Expose
    private String[] randomQuotes;

    // constructors
    public NPC(String name) {
        super(name);
    }

    public NPC(String name, String description, String location, String type, String[] randomQuotes) {
        super(name, description);
        this.location = location;
        this.type = type;
        this.randomQuotes = randomQuotes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void getRandomQuotes() {
        Random random = new Random();
        int randomIndex = random.nextInt(randomQuotes.length);
        String randomQuote = randomQuotes[randomIndex];
        System.out.printf("The %s says: '%s'\n\n", getType(), randomQuote);
    }

    // for internal testing
//    public static void main(String[] args) {
//        NPC ghost = new NPC("Hanley Druthers", "Your standard ghost haunting the place of his death and  just enjoying the Mystic Grove for its beauty.", location);
//
//        System.out.println(ghost.getName());
//        System.out.println(ghost.getDescription());
//
//        System.out.println(ghost.getHp());
//        ghost.setHp(ghost.getHp() - 3);
//        System.out.println(ghost.getHp()); // you can kill a ghost!!!
//
//        System.out.println(ghost.quotes());
//    }
}
