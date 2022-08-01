package persistence;

import model.Fish;
import model.Tank;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterImpossiblePath() {
        try {
            Tank t = new Tank();
            JsonWriter writer = new JsonWriter(".data/my\0illegal.json");
            writer.open();
            fail("We should have thrown an exception");
        } catch (IOException e) {
            // nice!
        }
    }

    @Test
    void testWriterEmptyTank() {
        try {
            Tank t = new Tank();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTank.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTank.json");
            t = reader.read();
            assertEquals(0, t.size());
        } catch (IOException e) {
            fail("not here");
        }
    }

    @Test
    void testWriterFullTank() {
        try {
            Tank t = new Tank();
            t.addFish(new Fish("Gilbert", "orange", "guppy", 33));
            t.addFish(new Fish("Ricky", "green", "frog", 85));
            JsonWriter writer = new JsonWriter("./data/testWriterFullTank.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFullTank.json");
            t = reader.read();
            assertEquals(2, t.size());
            checkFish("Gilbert", "orange", "guppy", 33, t.getFish(0));
            checkFish("Ricky", "green", "frog", 85, t.getFish(1));
        } catch (IOException e) {
            fail("not here");
        }
    }
}
