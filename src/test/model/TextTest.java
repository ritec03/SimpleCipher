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

        assertEquals(9, text.textSize());
        assertEquals('a', text.getCharacterInText(0));
        assertEquals(' ', text.getCharacterInText(1));
        assertEquals('b', text.getCharacterInText(2));
        assertEquals('c', text.getCharacterInText(3));
        assertEquals(' ', text.getCharacterInText(4));
        assertEquals('d', text.getCharacterInText(5));
        assertEquals('e', text.getCharacterInText(6));
        assertEquals('f', text.getCharacterInText(7));
        assertEquals('.', text.getCharacterInText(8));
    }

    @Test
    public void testMakeKeyMapTemplateOneSymbol() {
        String s = "aaa a aa";
        text.addText(s);
        
        text.makeKeyTemplate();

        assertEquals(2, text.getKey().keySize());
        assertTrue(text.getKey().containsKey('a'));
        assertTrue(text.getKey().containsKey(' '));
    }

    @Test
    public void testMakeKeyMapTemplateSeveralSymbols() {
        String s = "A bb Daf.";
        text.addText(s);

        text.makeKeyTemplate();

        assertEquals(6, text.getKey().keySize());
        assertTrue(text.getKey().containsKey('a'));
        assertTrue(text.getKey().containsKey('b'));
        assertTrue(text.getKey().containsKey('d'));
        assertTrue(text.getKey().containsKey('f'));
        assertTrue(text.getKey().containsKey('.'));
        assertTrue(text.getKey().containsKey(' '));
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

        assertEquals(7, text.ciphertextSize());
        assertEquals('1', text.getCharacterInCiphertext(0));
        assertEquals('-', text.getCharacterInCiphertext(1));
        assertEquals('2', text.getCharacterInCiphertext(2));
        assertEquals('3', text.getCharacterInCiphertext(3));
        assertEquals('-', text.getCharacterInCiphertext(4));
        assertEquals('4', text.getCharacterInCiphertext(5));
        assertEquals('.', text.getCharacterInCiphertext(6));
    }

    @Test
    public void testAddCipherText(){
        String s = "A bc Def.";
        text.addCiphertext(s);

        assertEquals(9, text.ciphertextSize());
        assertEquals('a', text.getCharacterInCiphertext(0));
        assertEquals(' ', text.getCharacterInCiphertext(1));
        assertEquals('b', text.getCharacterInCiphertext(2));
        assertEquals('c', text.getCharacterInCiphertext(3));
        assertEquals(' ', text.getCharacterInCiphertext(4));
        assertEquals('d', text.getCharacterInCiphertext(5));
        assertEquals('e', text.getCharacterInCiphertext(6));
        assertEquals('f', text.getCharacterInCiphertext(7));
        assertEquals('.', text.getCharacterInCiphertext(8));
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

        assertEquals('1', text.getCharacterInText(0));
        assertEquals('-', text.getCharacterInText(1));
        assertEquals('4', text.getCharacterInText(2));
        assertEquals('1', text.getCharacterInText(3));
        assertEquals('5', text.getCharacterInText(4));
        assertEquals('-', text.getCharacterInText(5));
        assertEquals('2', text.getCharacterInText(6));
        assertEquals('3', text.getCharacterInText(7));
        assertEquals('4', text.getCharacterInText(8));

        assertEquals('a', text.getCharacterInCiphertext(0));
        assertEquals(' ', text.getCharacterInCiphertext(1));
        assertEquals('d', text.getCharacterInCiphertext(2));
        assertEquals('a', text.getCharacterInCiphertext(3));
        assertEquals('e', text.getCharacterInCiphertext(4));
        assertEquals(' ', text.getCharacterInCiphertext(5));
        assertEquals('b', text.getCharacterInCiphertext(6));
        assertEquals('c', text.getCharacterInCiphertext(7));
        assertEquals('d', text.getCharacterInCiphertext(8));
    }

}
