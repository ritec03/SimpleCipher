package persistence;

import model.Key;
import model.Text;
import model.WorkSpace;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * The tests in this class are based on JsonSerializaitonDemo. Note that some fields that are not part
 * of functionality have been commented out along with methods and tests that use them.
 */

public class JsonWriterTest {
    Key key1;
    Key key2;
    Key key3;
    HashMap<Character,Character> map;
    Text text;
    Text previousText;


    @Test
    void testWriterInvalidFile() {
        try {
            WorkSpace wr = new WorkSpace();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // exception expected
        }
    }

    @Test
    void testWriterEmptyWorkSpace() {
        try {
            WorkSpace ws = new WorkSpace();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkSpace.json");
            writer.open();
            writer.write(ws);
            writer.close();
        } catch(IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testWriterGeneralWorkSpace() {
        try {
            key1 = new Key();
            map = new HashMap<>();
            map.put('a', '1');
            map.put('b', '2');
            map.put('c', '3');
            map.put('d', '4');
            map.put('e', '5');
            key1.setWholeKeySet(map);
            key1.setName("Key");

            key2 = new Key();
            map = new HashMap<>();
            map.put('a', '5');
            map.put('b', '6');
            map.put('c', '7');
            map.put('d', '8');
            map.put('e', '9');
            key2.setWholeKeySet(map);
            key2.setName("Key");

            key3 = new Key();
            map = new HashMap<>();
            map.put('a', 'f');
            map.put('b', 'g');
            map.put('c', 'h');
            map.put('d', 'i');
            map.put('e', 'k');
            key3.setWholeKeySet(map);
            key3.setName("Key");

            text = new Text();
            String s = "A bc Def.";
            text.addText(s);
            text.setKeyMap(key3);
            text.encryptText();

            previousText = new Text();
            String ps = "abcde";
            previousText.addText(ps);
            previousText.setKeyMap(key2);
            previousText.encryptText();

            WorkSpace ws = new WorkSpace();
//            ws.setPreviousText(previousText);
//            ws.addTextToSaved(previousText);
            ws.setText(text);
            ws.setKey(key3);
//            ws.setPreviousKey(key2);
            ws.addKeySetToSaved(key1);
            ws.addKeySetToSaved(key2);
            ws.addKeySetToSaved(key3);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkSpace.json");

            writer.open();
            writer.write(ws);
            writer.close();
        } catch(IOException e) {
            // exception not expected
        }
    }
 }
