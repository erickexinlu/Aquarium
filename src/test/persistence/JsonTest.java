package persistence;

import model.Fish;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    // SOURCE: JsonSerialization

    // EFFECTS: provides a condensed avenue of checking that a fish has the given attributes
    protected void checkFish(String name, String color, String species, int hunger, Fish fish) {
        assertEquals(name, fish.getName());
        assertEquals(color, fish.getColor());
        assertEquals(species, fish.getSpecies());
        assertEquals(hunger, fish.getHunger());
    }
}
