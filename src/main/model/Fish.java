package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;
import java.util.Random;

// This class represents a fish's name, color, species, and their hunger which decreases over time from a max of 100
public class Fish implements Writable {
    // delete or rename this class!
    public static final int DIAMETER = 25;
    public static final Random random = new Random();


    private String name;
    private String color;
    private String species;
    private int hunger;

    private int posX;
    private int posY;
    private int speedX;
    private int speedY;

    // REQUIRES: fishName, fishColor, fishSpecies are non-zero length
    // EFFECTS: name is set to fishName, color is set to fishColor, species is set to fishSpecies,
    //          and hunger is set to 100.
    public Fish(String fishName, String fishColor, String fishSpecies) {
        this.name = fishName;
        this.color = fishColor;
        this.species = fishSpecies;
        this.hunger = 100;

        this.initializePos();
    }

    // EFFECTS: creates modifiable hunger for loading purposes
    public Fish(String fishName, String fishColor, String fishSpecies, int hunger) {
        this.name = fishName;
        this.color = fishColor;
        this.species = fishSpecies;
        this.hunger = hunger;

        this.initializePos();
    }

    private void initializePos() {
        this.posX = random.nextInt(Tank.TANK_WIDTH);
        this.posY = random.nextInt(Tank.TANK_HEIGHT);
        this.speedX = random.nextInt(4);
        this.speedY = random.nextInt(4);
    }

    // MODIFIES: this
    // EFFECTS: resets the fish's hunger back to 100
    public void feed() {
        this.hunger = 100;
    }

    // MODIFIES: this
    // EFFECTS: reduces hunger by 1 when called
    public void decreaseHunger() {
        this.hunger -= 1;
    }

    // EFFECTS: returns a String summarizing the fish's attributes
    public String getSummary() {
        return name + " is a " + color + " " + species + ", with " + getHunger() + " hunger.";
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getSpecies() {
        return species;
    }

    public int getHunger() {
        return hunger;
    }

    @Override
    // EFFECTS: converts the Fish into a JSONObject and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("color", color);
        json.put("species", species);
        json.put("hunger", hunger);
        return json;
    }

    public void move() {
        posX += speedX;
        posY += speedY;

        handleBoundaries();
    }

    private void handleBoundaries() {
        int radius = DIAMETER / 2;
        if (posX - radius < 0) {
            // handle left edge
            posX = radius;
            speedX *= -1;
        } else if (posX + DIAMETER / 2 > Tank.TANK_WIDTH) {
            // handle right edge
            posX = Tank.TANK_WIDTH - radius;
            speedX *= -1;
        } else if (posY - radius < 0) {
            // handle top edge
            posY = radius;
            speedY *= -1;
        } else if (posY + radius > Tank.TANK_HEIGHT) {
            // handle bottom edge
            posY = Tank.TANK_HEIGHT - radius;
            speedY *= -1;
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
