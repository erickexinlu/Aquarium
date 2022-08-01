package persistence;

import org.json.JSONObject;

// Class that requires those that implement it to be able to be converted into JSONObject form
public interface Writable {
    // EFFECTS: returns this class as JSON object
    // SOURCE: JsonSerializationDemo

    JSONObject toJson();
}
