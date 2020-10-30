package persistence;

import model.WorkSpace;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Represents a writer that records objects in JSON representation onto a file.
 *
 * CITATION: The code in this class has been based on CPSC210-Project-Demo and
 * JsonSerializationDemo projects.
 */
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination; // pathname

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFound Exception if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON WorkSpace representation to file
    public void write(WorkSpace ws) {
        JSONObject json = ws.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    private void saveToFile(String json) {
        writer.print(json);
    }
}
