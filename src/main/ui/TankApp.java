package ui;

import model.Fish;

import java.util.ArrayList;
import java.util.List;

public class TankApp {
    public static final int MAX_FISH = 5;
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
    private boolean addFish(Fish fish) {
        return true;
    }

    // MODIFIES: this
    // EFFECTS: attempts to remove the Fish with the corresponding given fishName, if succeeds returns true, else false
    private boolean removeFish(String fishName) {
        return true;
    }

    // REQUIRES: tank is non-empty
    // MODIFIES: this
    // EFFECTS: feeds all the fish inside the tank ArrayList
    private void feedAllFish() {

    }

    // EFFECTS: returns a string of all the Fish in the tank with their respective attributes
    private String listFish() {
        return "";
    }
}
