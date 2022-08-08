package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;
import java.util.Random;


// SOURCE: JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// SOURCE: PaddleBallFrame from lab3 (cloned from VCS)
// https://github.students.cs.ubc.ca/CPSC210-2022S-T2/lab3_m6g6q/commit/8b37f9b6e072a28e6e962ec68cd148c6e767ec12

// SOURCE: SpaceInvadersStarter (download from edX)
// https://edge.edx.org/assets/courseware/v1/13595daba554c85e2fe669e686cbff91/
// asset-v1:UBC+CPSC210+all+type@asset+block/SpaceInvadersStarter.zip


// This class represents a fish's name, color, species, position, and hunger
public class Fish implements Writable {
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

    // MODIFIES: this
    // EFFECTS: initializes the position and speed for the fish randomly
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

    // MODIFIES: this
    // EFFECTS: advances the position of the fish, and checks to see if it's out of bounds
    public void move() {
        posX += speedX;
        posY += speedY;

        handleBoundaries();
    }

    // MODIFIES: this
    // EFFECTS: if the fish is out of bounds, it returns it to the right position and changes speed
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
