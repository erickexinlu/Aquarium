package persistence;

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
}
