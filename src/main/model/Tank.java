package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// This class is composed of a tank, and manages all GUI interactions
// SOURCE: JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// SOURCE: PaddleBallFrame from lab3 (cloned from VCS)
// https://github.students.cs.ubc.ca/CPSC210-2022S-T2/lab3_m6g6q/commit/8b37f9b6e072a28e6e962ec68cd148c6e767ec12

// SOURCE: SpaceInvadersStarter (download from edX)
// https://edge.edx.org/assets/courseware/v1/13595daba554c85e2fe669e686cbff91/
// asset-v1:UBC+CPSC210+all+type@asset+block/SpaceInvadersStarter.zip


// This class represents the tank in which all the fish are contained, as an ArrayList
public class Tank implements Writable {
    public static final int MAX_FISH = 2;
    public static final int TANK_WIDTH = 600;
    public static final int TANK_HEIGHT = 400;
    public static final int TICKS_PER_HUNGER = 80;

    private static int ticksSoFar;

    private ArrayList<Fish> tank;

    // EFFECTS: initializes the ArrayList as an empty list
    public Tank() {
        tank = new ArrayList<>();
        ticksSoFar = 0;
    }

    // MODIFIES: this
    // EFFECTS: attempts to add the given Fish to the tank, if there's space, returns true, else false
    public boolean addFish(Fish fish) {
        if (isFull()) {
            return false;
        } else {
            tank.add(fish);
            EventLog.getInstance().logEvent(
                    new Event("A fish named " + fish.getName() + " was added to the tank.")
            );
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: advances all the fish in the tank and decrements their hunger
    public void update() {
        moveFishes();
        ticksSoFar++;
        if (ticksSoFar >= TICKS_PER_HUNGER) {
            hungerAllFish();
            ticksSoFar = 0;
        }

    }

    // MODIFIES: this
    // EFFECTS: moves each fish in the tank
    private void moveFishes() {
        for (Fish f : tank) {
            f.move();
        }
    }


    // MODIFIES: this
    // EFFECTS: attempts to remove the Fish with the corresponding given fishName, if succeeds returns true, else false
    public boolean removeFish(String fishName) {
        for (Fish fish : tank) {
            if (fishName.equalsIgnoreCase(fish.getName())) {
                tank.remove(fish);
                EventLog.getInstance().logEvent(
                        new Event("The fish " + fishName + " was removed from the tank.")
                );
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

    // EFFECTS: returns the list of fish in the tank
    public List<Fish> getTank() {
        return Collections.unmodifiableList(tank);
    }

    // REQUIRES: tank is non-empty
    // MODIFIES: this
    // EFFECTS: feeds all the fish inside the tank ArrayList
    public void feedAllFish() {
        for (Fish fish : tank) {
            fish.feed();
        }
    }

    // MODIFIES: this
    // EFFECTS: after enough ticks are reached, each fish's hunger is reduced by 1
    public void hungerAllFish() {
        for (Fish fish : tank) {
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


    // EFFECTS: converts the tank into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tank", fishesToJson());
        return json;
    }

    // EFFECTS: converts all the fish in the tank list into a JSONArray and returns it
    private JSONArray fishesToJson() {
        // SOURCE: JsonDemo
        JSONArray jsonFishes = new JSONArray();
        for (Fish f : tank) {
            jsonFishes.put(f.toJson());
        }
        return jsonFishes;
    }
}
