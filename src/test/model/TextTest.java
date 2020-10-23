package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextTest {
    Text text;
    Key testKey;
    HashMap<Character,Character> map;


    @BeforeEach
    public void setup(){
        text = new Text();
    }

    @Test
    public void testAddText() {
        String s = "A bc Def.";
        text.addText(s);

        assertEquals(9, text.getText().size());
        assertEquals('a', text.getText().get(0));
        assertEquals(' ', text.getText().get(1));
        assertEquals('b', text.getText().get(2));
        assertEquals('c', text.getText().get(3));
        assertEquals(' ', text.getText().get(4));
        assertEquals('d', text.getText().get(5));
        assertEquals('e', text.getText().get(6));
        assertEquals('f', text.getText().get(7));
        assertEquals('.', text.getText().get(8));
    }

    @Test
    public void testMakeKeyMapTemplateOneSymbol() {
        String s = "aaa a aa";
        text.addText(s);
        text.getKey().getKeyMap();

        text.makeKeyTemplate();

        assertEquals(2, text.getKey().getKeyMap().size());
        assertTrue(text.getKey().getKeyMap().containsKey('a'));
        assertTrue(text.getKey().getKeyMap().containsKey(' '));

    }

    @Test
    public void testMakeKeyMapTemplateSeveralSymbols() {
        String s = "A bb Daf.";
        text.addText(s);
        text.getKey().getKeyMap();

        text.makeKeyTemplate();

        assertEquals(6, text.getKey().getKeyMap().size());
        assertTrue(text.getKey().getKeyMap().containsKey('a'));
        assertTrue(text.getKey().getKeyMap().containsKey('b'));
        assertTrue(text.getKey().getKeyMap().containsKey('d'));
        assertTrue(text.getKey().getKeyMap().containsKey('f'));
        assertTrue(text.getKey().getKeyMap().containsKey('.'));
        assertTrue(text.getKey().getKeyMap().containsKey(' '));
    }

    @Test
    public void testEncryptTextNoSpaces() {
        text.addText("A bc d.");

        text.makeKeyTemplate();
        text.getKey().replaceValue('a', '1');
        text.getKey().replaceValue('b', '2');
        text.getKey().replaceValue('c', '3');
        text.getKey().replaceValue('d', '4');
        text.getKey().replaceValue('.', '.');

        text.encryptText();

        assertEquals(7, text.getCiphertext().size());
        assertEquals('1', text.getCiphertext().get(0));
        assertEquals('-', text.getCiphertext().get(1));
        assertEquals('2', text.getCiphertext().get(2));
        assertEquals('3', text.getCiphertext().get(3));
        assertEquals('-', text.getCiphertext().get(4));
        assertEquals('4', text.getCiphertext().get(5));
        assertEquals('.', text.getCiphertext().get(6));
    }

    @Test
    public void testAddCipherText(){
        String s = "A bc Def.";
        text.addCiphertext(s);

        assertEquals(9, text.getCiphertext().size());
        assertEquals('a', text.getCiphertext().get(0));
        assertEquals(' ', text.getCiphertext().get(1));
        assertEquals('b', text.getCiphertext().get(2));
        assertEquals('c', text.getCiphertext().get(3));
        assertEquals(' ', text.getCiphertext().get(4));
        assertEquals('d', text.getCiphertext().get(5));
        assertEquals('e', text.getCiphertext().get(6));
        assertEquals('f', text.getCiphertext().get(7));
        assertEquals('.', text.getCiphertext().get(8));
    }

    @Test
    public void testDecriptCipherext(){
        testKey = new Key();
        map = new HashMap<>();
        map.put('1', 'a');
        map.put('2', 'b');
        map.put('3', 'c');
        map.put('4', 'd');
        map.put('5', 'e');
        testKey.setWholeKeySet(map);
        text.setKeyMap(testKey);

        String s = "A dae bcd";
        text.addCiphertext(s);

        text.decryptCiphertext();

        assertEquals('1', text.getText().get(0));
        assertEquals('-', text.getText().get(1));
        assertEquals('4', text.getText().get(2));
        assertEquals('1', text.getText().get(3));
        assertEquals('5', text.getText().get(4));
        assertEquals('-', text.getText().get(5));
        assertEquals('2', text.getText().get(6));
        assertEquals('3', text.getText().get(7));
        assertEquals('4', text.getText().get(8));

        assertEquals('a', text.getCiphertext().get(0));
        assertEquals(' ', text.getCiphertext().get(1));
        assertEquals('d', text.getCiphertext().get(2));
        assertEquals('a', text.getCiphertext().get(3));
        assertEquals('e', text.getCiphertext().get(4));
        assertEquals(' ', text.getCiphertext().get(5));
        assertEquals('b', text.getCiphertext().get(6));
        assertEquals('c', text.getCiphertext().get(7));
        assertEquals('d', text.getCiphertext().get(8));

    }

}
