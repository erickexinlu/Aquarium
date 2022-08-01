package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this class as JSON object
    // SOURCE: JsonSerializationDemo

    JSONObject toJson();
}
