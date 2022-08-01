package model;

import org.json.JSONObject;
import persistence.Writable;

// This class represents a fish's name, color, species, and their hunger which decreases over time from a max of 100
public class Fish implements Writable {
    // delete or rename this class!
    private String name;
    private String color;
    private String species;
    private int hunger;

    // REQUIRES: fishName, fishColor, fishSpecies are non-zero length
    // EFFECTS: name is set to fishName, color is set to fishColor, species is set to fishSpecies,
    //          and hunger is set to 100.
    public Fish(String fishName, String fishColor, String fishSpecies) {
        this.name = fishName;
        this.color = fishColor;
        this.species = fishSpecies;
        this.hunger = 100;
    }

    // EFFECTS: creates modifiable hunger for loading purposes
    public Fish(String fishName, String fishColor, String fishSpecies, int hunger) {
        this.name = fishName;
        this.color = fishColor;
        this.species = fishSpecies;
        this.hunger = hunger;
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
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("color", color);
        json.put("species", species);
        json.put("hunger", hunger);
        return json;
    }
}
