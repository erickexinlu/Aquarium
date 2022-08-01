package persistence;

import model.Fish;
import model.Tank;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// A reader that takes JSON files from the data and loads them as tank states
public class JsonReader {
    //SOURCE: JsonDemo
    private String sourceFile;

    // EFFECTS: constructs a jsonReader to read from sourceFile
    public JsonReader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    // EFFECTS: reads Tank on file and returns it,
    // throws IOException if an error occurs at any point
    public Tank read() throws IOException {
        String jsonData = readFile(sourceFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTank(jsonObject);
    }

    // EFFECTS: turns sourceFile into string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder converter = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> converter.append(s));
        }

        return converter.toString();
    }

    // EFFECTS: converts the JSON file into a usable tank Object and returns it
    private Tank parseTank(JSONObject jsonObject) {
        Tank t = new Tank();
        addFishes(t, jsonObject);
        return t;
    }

    // MODIFIES: t
    // EFFECTS: converts the fish from JSON object and adds them to the tank
    private void addFishes(Tank t, JSONObject jsonObject) {
        JSONArray jsonTank = jsonObject.getJSONArray("tank");
        for (Object jsonFish : jsonTank) {
            JSONObject nextFish = (JSONObject) jsonFish;
            addFish(t, nextFish);
        }
    }

    // MODIFIES: tank
    // EFFECTS: creates single Fish from the JSON and adds to the tank
    private void addFish(Tank t, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String color = jsonObject.getString("color");
        String species = jsonObject.getString("species");
        int hunger = jsonObject.getInt("hunger");
        Fish fish = new Fish(name, color, species, hunger);
        t.addFish(fish);
    }
}
