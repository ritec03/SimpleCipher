package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class WorkSpaceTest {
    WorkSpace ws;
    Text text;
    Key key1;
    Key key2;
    Key key3;
    HashMap<Character, Character> map;

    @BeforeEach
    public void setup(){
        ws = new WorkSpace();

        key1 = new Key();
        map = new HashMap<>();
        map.put('a', '1');
        map.put('b', '2');
        map.put('c', '3');
        map.put('d', '4');
        map.put('e', '5');
        key1.setWholeKeySet(map);

        key2 = new Key();
        map = new HashMap<>();
        map.put('a', '5');
        map.put('b', '6');
        map.put('c', '7');
        map.put('d', '8');
        map.put('e', '9');
        key2.setWholeKeySet(map);

        key3 = new Key();
        map = new HashMap<>();
        map.put('a', 'f');
        map.put('b', 'g');
        map.put('c', 'h');
        map.put('d', 'i');
        map.put('e', 'k');
        key3.setWholeKeySet(map);

        text = new Text();
    }

    @Test
    // when savedKeys is empty
    public void testAddKeyToSavedEmpty(){
        ws.setText(text);
        assertEquals(0,ws.getSavedKeys().size());

        ws.addKeySetToSaved(key1);

        assertEquals(1, ws.getSavedKeys().size());
    }

    @Test
    // when savedKeys is NOT empty
    public void testAddKeyToSavedNonEmpty(){
        ws.setText(text);
        ws.addKeySetToSaved(key1);
        assertEquals(1, ws.getSavedKeys().size());

        ws.addKeySetToSaved(key2);
        assertEquals(2, ws.getSavedKeys().size());
    }

    @Test
    public void testAddTextToSavedEmpty(){
        assertEquals(0, ws.getSavedTexts().size());

        ws.addTextToSaved(text);
        assertEquals(1, ws.getSavedTexts().size());
        assertTrue(ws.getSavedTexts().contains(text));
    }

    @Test
    public void testAddTextToSavedNonEmpty(){
        Text t1 = new Text();
        Text t2 = new Text();
        ws.addTextToSaved(t1);
        ws.addTextToSaved(t2);
        assertEquals(2, ws.getSavedTexts().size());

        ws.addTextToSaved(text);
        assertEquals(3, ws.getSavedTexts().size());
        assertTrue(ws.getSavedTexts().contains(text));
    }

    @Test
    public void testSetPreviousKeyEmpty(){
        assertNull(ws.getPreviousKey());

        ws.setPreviousKey(key1);
        assertEquals(key1, ws.getPreviousKey());
    }

    @Test
    public void testSetPreviousKeyNonEmpty(){
        ws.setPreviousKey(key1);
        assertEquals(key1, ws.getPreviousKey());

        ws.setPreviousKey(key2);
        assertEquals(key2, ws.getPreviousKey());
    }

    @Test
    public void testSetPreviousTextEmpty(){
        assertNull(ws.getPreviousText());

        ws.setPreviousText(text);
        assertEquals(text, ws.getPreviousText());
    }

    @Test
    public void testSetPreviousTextNonEmpty(){
        ws.setPreviousText(text);
        assertEquals(text, ws.getPreviousText());

        Text t1 = new Text();
        ws.setPreviousText(t1);
        assertEquals(t1, ws.getPreviousText());
    }

    @Test
    public void testSetText(){
        assertNull(ws.getText());
        ws.setText(text);
        assertEquals(text, ws.getText());
    }

    @Test
    public void testSetKey(){
        assertNull(ws.getKey());
        ws.setKey(key1);
        assertEquals(key1, ws.getKey());
    }

}
