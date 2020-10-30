package persistence;

import org.json.JSONObject;

/**
 * Represents an interface for classes whose objects can be turned into a JSON object.
 */
public interface Writable {
    // returns this as JSON object
    JSONObject toJson();
}
