package ui;

import model.Fish;

import java.util.ArrayList;
import java.util.List;

public class TankApp {
    public static final int MAX_FISH = 2;
    public static final int DECAY_RATE_PER_SECOND = 5;

    private ArrayList<Fish> tank;

    public TankApp() {
        tank = new ArrayList<Fish>();
        startTank();
    }

    // MODIFIES: this
    // EFFECTS: opens the Scanner, initializes the tank ArrayList, and handles input until program is terminated
    private void startTank() {

    }

    // MODIFIES: this
    // EFFECTS: attempts to add the given Fish to the tank, if there's space, returns true, else false
    public boolean addFish(Fish fish) {
        return true;
    }

    // MODIFIES: this
    // EFFECTS: attempts to remove the Fish with the corresponding given fishName, if succeeds returns true, else false
    public boolean removeFish(String fishName) {
        return true;
    }

    // EFFECTS: returns the Fish object with the given name, if not found, returns null
    public Fish getFishFromName(String fishName) {
        return null;
    }

    // REQUIRES: index is non-negative
    // EFFECTS: returns the fish in the given index of the list
    public Fish getFish(int fishIndex) {
        return null;
    }

    // REQUIRES: tank is non-empty
    // MODIFIES: this
    // EFFECTS: feeds all the fish inside the tank ArrayList
    public void feedAllFish() {

    }

    // REQUIRES: tank is non-empty
    // MODIFIES: this
    // EFFECTS: reduces the hunger values of all fish in the tank by 1
    public void hungerAllFish() {

    }

    // EFFECTS: returns a string of all the Fish in the tank with their respective attributes
    public String listFish() {
        return "";
    }

    // EFFECTS: returns the number of fish in the tank
    public int size() {
        return tank.size();
    }

    // EFFECTS: returns true if the tank has no fish, else false
    public boolean isEmpty() {
        return (tank.size() == 0);
    }

    // EFFECTS: returns true if the tank size is equal to MAX_FISH, else false
    public boolean isFull() {
        return (tank.size() == MAX_FISH);
    }
}
