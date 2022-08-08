package persistence;

import model.Tank;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// SOURCE: JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// A class that saves JSON representations of the Tank states to file
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destinationPath;

    // EFFECTS: creates a writer with the given destination file path
    public JsonWriter(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    // MODIFIES: this
    // EFFECTS: opens up the writer
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destinationPath));
    }

    // MODIFIES: this
    // EFFECTS: handles delegation of writing tasks
    public void write(Tank t) {
        JSONObject jsonTank = t.toJson();
        saveToFile(jsonTank.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to the file at filePath
    private void saveToFile(String json) {
        writer.print(json);
    }
}
