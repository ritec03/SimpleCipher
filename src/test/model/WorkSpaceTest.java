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
        ws.getText().setKey(key1);
        ws.addKeySetToSaved(key1);
        assertEquals(1, ws.getSavedKeys().size());
        Key k = ws.getSavedKeys().get(0);
        assertEquals(5, k.keySize());
        assertEquals('a', k.getKey('1'));
        assertEquals('b', k.getKey('2'));
        assertEquals('c', k.getKey('3'));
        assertEquals('d', k.getKey('4'));
        assertEquals('e', k.getKey('5'));


        ws.addKeySetToSaved(key2);
        assertEquals(2, ws.getSavedKeys().size());
    }

//    @Test
//    public void testAddTextToSavedEmpty(){
//        assertEquals(0, ws.getSavedTexts().size());
//
//        ws.addTextToSaved(text);
//        assertEquals(1, ws.getSavedTexts().size());
//        assertTrue(ws.getSavedTexts().contains(text));
//    }

//    @Test
//    public void testAddTextToSavedNonEmpty(){
//        Text t1 = new Text();
//        Text t2 = new Text();
//        ws.addTextToSaved(t1);
//        ws.addTextToSaved(t2);
//        assertEquals(2, ws.getSavedTexts().size());
//
//        ws.addTextToSaved(text);
//        assertEquals(3, ws.getSavedTexts().size());
//        assertTrue(ws.getSavedTexts().contains(text));
//    }
//
//    @Test
//    public void testSetPreviousKeyEmpty(){
//        assertEquals(0, ws.getPreviousKey().keySize());
//
//        ws.setPreviousKey(key1);
//        assertEquals(key1, ws.getPreviousKey());
//    }

//    @Test
//    public void testSetPreviousKeyNonEmpty(){
//        ws.setPreviousKey(key1);
//        assertEquals(key1, ws.getPreviousKey());
//
//        ws.setPreviousKey(key2);
//        assertEquals(key2, ws.getPreviousKey());
//    }
//
//    @Test
//    public void testSetPreviousTextEmpty(){
//        assertEquals(0, ws.getPreviousText().textSize());
//
//        ws.setPreviousText(text);
//        assertEquals(text, ws.getPreviousText());
//    }
//
//    @Test
//    public void testSetPreviousTextNonEmpty(){
//        ws.setPreviousText(text);
//        assertEquals(text, ws.getPreviousText());
//
//        Text t1 = new Text();
//        ws.setPreviousText(t1);
//        assertEquals(t1, ws.getPreviousText());
//    }

    @Test
    public void testSetText(){
        assertEquals(0, ws.getText().textSize());
        ws.setText(text);
        assertEquals(text, ws.getText());
//        assertNull(ws.getPreviousText());

        Text newText = new Text();
        ws.setText(newText);
        assertEquals(newText, ws.getText());
//        assertEquals(text, ws.getPreviousText());
    }

    @Test
    public void testSetKey(){
        assertEquals(0, ws.getKey().keySize());
        ws.setKey(key1);
        assertEquals(key1, ws.getKey());

        Key newKey = new Key();
        ws.setKey(newKey);
        assertEquals(newKey, ws.getKey());
//        assertEquals(key1, ws.getPreviousKey());
    }

}
