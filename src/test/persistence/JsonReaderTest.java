package persistence;

import model.Key;
import model.WorkSpace;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The tests in this class are based on JsonSerializaitonDemo. Note that some fields that are not part
 * of functionality have been commented out along with methods and tests that use them.
 */
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkSpace ws = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // exception expected
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkSpace.json");
        try {
            WorkSpace ws = reader.read();
//            assertEquals(0, ws.getSavedTexts().size());
            assertEquals(0, ws.getSavedKeys().size());
            assertEquals(0, ws.getText().getKey().getKeySet().size());
            assertEquals(0, ws.getText().textSize());
            assertEquals(0, ws.getKey().getKeySet().size());
            assertEquals("", ws.getKey().getName());
//            assertEquals(0, ws.getPreviousText().getKey().getKeySet().size());
//            assertEquals(0, ws.getPreviousText().textSize());
//            assertEquals(0, ws.getPreviousKey().getKeySet().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralWorkSpace() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkSpace.json");
        try {
            WorkSpace ws = reader.read();

            assertEquals("456", ws.getText().printText());
            assertEquals("abc", ws.getText().printCiphertext());
            assertEquals(3, ws.getKey().keySize());
            assertEquals('a', ws.getKey().getValue('4'));
            assertEquals('b', ws.getKey().getValue('5'));
            assertEquals('c', ws.getKey().getValue('6'));
            assertEquals("Key", ws.getKey().getName());

            assertEquals(2, ws.getSavedKeys().size());

            Key firstKey = ws.getSavedKeys().get(0);
            assertEquals(3, firstKey.keySize());
            assertEquals('a', firstKey.getValue('1'));
            assertEquals('b', firstKey.getValue('2'));
            assertEquals('c', firstKey.getValue('3'));
            assertEquals("Key", firstKey.getName());

            Key secondKey = ws.getSavedKeys().get(1);
            assertEquals(3, secondKey.keySize());
            assertEquals('a', secondKey.getValue('4'));
            assertEquals('b', secondKey.getValue('5'));
            assertEquals('c', secondKey.getValue('6'));
            assertEquals("Key", secondKey.getName());

        } catch(IOException e) {

        }
    }

}
