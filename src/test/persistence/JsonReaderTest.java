package persistence;

import model.Fish;
import model.Tank;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderTest extends JsonTest {
    // SOURCE: JsonSerializationDemo

    @Test
    void testReaderFileDoesNotExist() {
        JsonReader reader = new JsonReader("./data/imaginaryFile.json");
        try {
            Tank testTank = reader.read();
            fail("Should have thrown IOException");
        } catch (IOException e) {
            // nice!
        }
    }

    @Test
    void testReaderEmptyTank() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTank.json");
        try {
            Tank t = reader.read();
            assertEquals(0, t.size());
        } catch (IOException e) {
            fail("Should not be here!");
        }
    }

    @Test
    void testReaderFullTank() {
        JsonReader reader = new JsonReader("./data/testReaderFullTank.json");
        try {
            Tank t = reader.read();
            List<Fish> fishes = t.getTank();
            assertEquals(2, fishes.size());
            checkFish("Gilbert", "green", "betta", 95, fishes.get(0));
            checkFish("Ricky", "blue", "shark", 30, fishes.get(1));
        } catch (IOException e) {
            fail("Should not be here!");
        }
    }




}
