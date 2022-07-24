package model;

import java.util.ArrayList;

// This class represents the tank in which all the fish are contained, as an ArrayList
public class Tank {
    public static final int MAX_FISH = 2;

    private ArrayList<Fish> tank;

    // EFFECTS: initializes the ArrayList as an empty list
    public Tank() {
        tank = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: attempts to add the given Fish to the tank, if there's space, returns true, else false
    public boolean addFish(Fish fish) {
        if (isFull()) {
            return false;
        } else {
            tank.add(fish);
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: attempts to remove the Fish with the corresponding given fishName, if succeeds returns true, else false
    public boolean removeFish(String fishName) {
        for (Fish fish: tank) {
            if (fishName.equals(fish.getName())) {
                tank.remove(fish);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: index is non-negative and does not exceed the size of the list
    // EFFECTS: returns the fish in the given index of the list
    public Fish getFish(int fishIndex) {
        if (fishIndex >= size()) {
            return null;
        } else {
            return tank.get(fishIndex);
        }
    }

    // REQUIRES: tank is non-empty
    // MODIFIES: this
    // EFFECTS: feeds all the fish inside the tank ArrayList
    public void feedAllFish() {
        for (Fish fish: tank) {
            fish.feed();
        }
    }

    // REQUIRES: tank is non-empty
    // MODIFIES: this
    // EFFECTS: reduces the hunger values of all fish in the tank by 1
    public void hungerAllFish() {
        for (Fish fish: tank) {
            fish.decreaseHunger();
        }
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