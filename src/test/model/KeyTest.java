package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {
    Key testKey;
    HashMap<Character, Character> map;

    @BeforeEach
    public void setup(){
        testKey = new Key();
        map = new HashMap<>();
        map.put('a', 'b');
        map.put('b', 'c');
        map.put('c', 'd');
        map.put('d', 'e');
        map.put('e', 'f');
    }

    @Test
    public void testGetValue(){
        testKey.setWholeKeySet(map);
        assertEquals('b', testKey.getValue('a'));
        assertEquals('d', testKey.getValue('c'));
        assertEquals('f', testKey.getValue('e'));

    }
    @Test
    public void testSetWholeKeySet(){
        assertEquals(0,testKey.getKeyMap().size());
        testKey.setWholeKeySet(map);
        assertEquals(5, testKey.getKeyMap().size());
        assertEquals('b', testKey.getValue('a'));
        assertEquals('c', testKey.getValue('b'));
        assertEquals('d', testKey.getValue('c'));
        assertEquals('e', testKey.getValue('d'));
        assertEquals('f', testKey.getValue('e'));

        HashMap<Character, Character> map1 = new HashMap<>();
        map1.put('x', 'y');
        map1.put('y', 'z');
        map1.put('z', 'x');

        testKey.setWholeKeySet(map1);

        assertEquals(3, testKey.getKeyMap().size());
        assertEquals('y', testKey.getValue('x'));
        assertEquals('z', testKey.getValue('y'));
        assertEquals('x', testKey.getValue('z'));
    }

    @Test
    public void testAddKeyValue() {
        assertEquals(0, testKey.getKeyMap().size());

        testKey.addKeyValue('a', 'b');
        assertEquals(1, testKey.getKeyMap().size());
        assertEquals('b', testKey.getValue('a'));

        testKey.addKeyValue('c', 'd');
        assertEquals(2, testKey.getKeyMap().size());
        assertEquals('d', testKey.getValue('c'));
    }






}