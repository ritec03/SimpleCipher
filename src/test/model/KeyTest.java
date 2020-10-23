package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {
    Key testKey;
    HashMap<Character, Character> map;

    @BeforeEach
    public void setup() {
        testKey = new Key();
        map = new HashMap<>();
        map.put('a', 'b');
        map.put('b', 'c');
        map.put('c', 'd');
        map.put('d', 'e');
        map.put('e', 'f');
        testKey.setWholeKeySet(map);
    }

    @Test
    public void testGetValue() {
        assertEquals('b', testKey.getValue('a'));
        assertEquals('d', testKey.getValue('c'));
        assertEquals('f', testKey.getValue('e'));
    }

    @Test
    public void testGetKey() {
        assertEquals('a', testKey.getKey('b'));
        assertEquals('c', testKey.getKey('d'));
        assertEquals('e', testKey.getKey('f'));
    }
    @Test
    // setting whole keymap when keymap is empty
    public void testSetWholeKeyMapEmpty() {
        HashMap<Character,Character> testMap = new HashMap<>();
        testKey.setWholeKeySet(testMap);

        assertEquals(0,testKey.getKeyMap().size());
        testKey.setWholeKeySet(map);
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();

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
    // setting whole keymap when keymap is not empty
    public void testSetWholKeyMapNonEmpty() {
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();

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
    // keymap is empty
    public void testAddKeyValueEmpty() {
        HashMap<Character,Character> testMap = new HashMap<>();
        testKey.setWholeKeySet(testMap);

        assertEquals(0, testKey.getKeyMap().size());

        assertTrue(testKey.addKeyValue('a', 'b'));
        assertEquals(1, testKey.getKeyMap().size());
        assertEquals('b', testKey.getValue('a'));


        assertTrue(testKey.addKeyValue('c', 'd'));
        assertEquals(2, testKey.getKeyMap().size());
        assertEquals('b', testKey.getValue('a'));
        assertEquals('d', testKey.getValue('c'));
    }

    @Test
    // keymap is not empty, key is there
    public void testAddKeyValueNonEmptyNonThere() {
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();

        testKey.addKeyValue('x', 'y');
        assertEquals(6, testKey.getKeyMap().size());
        assertEquals('y', testKey.getValue('x'));

        checkSetupMapElements();

        assertTrue(testKey.addKeyValue('y', 'z'));
        assertEquals(7, testKey.getKeyMap().size());
        assertEquals('y', testKey.getValue('x'));
        assertEquals('z', testKey.getValue('y'));

        checkSetupMapElements();
    }


    @Test
    // keymap is not empty, key is not there
    public void testAddKeyValueNonEmptyThere() {
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();

        assertFalse(testKey.addKeyValue('a', 'b'));
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
    }

    @Test
    // keymap is empty
    public void testRemoveKeyValueEmpty() {
        HashMap<Character,Character> testMap = new HashMap<>();
        testKey.setWholeKeySet(testMap);

        assertEquals(0, testKey.getKeyMap().size());
        assertFalse(testKey.removeKeyValue('a'));
        assertEquals(0, testKey.getKeyMap().size());
    }

    @Test
    // keymap is not empty, key is there
    public void testRemoveKeyValueNonEmptyThere() {
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();

        assertTrue(testKey.removeKeyValue('a'));
        assertEquals(4, testKey.getKeyMap().size());
        assertEquals('c', testKey.getValue('b'));
        assertEquals('d', testKey.getValue('c'));
        assertEquals('e', testKey.getValue('d'));
        assertEquals('f', testKey.getValue('e'));

        assertTrue(testKey.removeKeyValue('d'));
        assertEquals(3, testKey.getKeyMap().size());
        assertEquals('c', testKey.getValue('b'));
        assertEquals('d', testKey.getValue('c'));
        assertEquals('f', testKey.getValue('e'));
    }

    @Test
    // keymap is empty, key is not there
    public void testRemoveKeyValueNonEmptyNonThere(){
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();

        assertFalse(testKey.removeKeyValue('h'));
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
    }

    @Test
    //key is there
    public void testReplaceValueThere(){
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();

        assertTrue(testKey.replaceValue('c', 'x'));
        assertEquals(5, testKey.getKeyMap().size());
        assertEquals('b', testKey.getValue('a'));
        assertEquals('c', testKey.getValue('b'));

        assertEquals('x', testKey.getValue('c'));

        assertEquals('e', testKey.getValue('d'));
        assertEquals('f', testKey.getValue('e'));
    }

    @Test
    //key is not there
    public void testReplaceValueNotThere(){
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();

        assertFalse(testKey.replaceValue('y', 'x'));
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
    }

    @Test
    // TODO: think if i need to get more access to sureList field
    // TODO: think to add tests for when sure list is empty and non-empty
    // key is not in map
    public void testAddKeyToSureNotThere(){
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();

        assertFalse(testKey.addKeyToSure('x'));
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
    }

    @Test
    // key is in map and not in sureList
    public void testAddKeyToSureThere(){
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();

        assertTrue(testKey.addKeyToSure('b'));
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
    }

    @Test
    // key is in map and in sureList
    public void testAddKeyToSureInList(){
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
        assertTrue(testKey.addKeyToSure('b'));

        assertFalse(testKey.addKeyToSure('b'));
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
    }

    @Test
    // value is in sure list
    public void testRemoveKeyFromSureListThere(){
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
        testKey.addKeyToSure('b');
        testKey.addKeyToSure('d');

        assertTrue(testKey.removeKeyFromSure('d'));
        assertTrue(testKey.removeKeyFromSure('b'));

        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
    }

    @Test
    // value is not in sure list
    public void testRemoveKeyFromSureListNotThere(){
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
        testKey.addKeyToSure('b');
        testKey.addKeyToSure('d');

        assertFalse(testKey.removeKeyFromSure('a'));
        assertFalse(testKey.removeKeyFromSure('e'));

        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
    }

    @Test
    // sureList is empty
    public void testClearAllButSureEmpty(){
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();

        assertFalse(testKey.clearAllButSure());

        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
    }

    @Test
    // sureList is not empty
    public void testClearAllButSureNonEmpty(){
        assertEquals(5, testKey.getKeyMap().size());
        checkSetupMapElements();
        testKey.addKeyToSure('a');
        testKey.addKeyToSure('e');

        assertTrue(testKey.clearAllButSure());

        assertEquals(2, testKey.getKeyMap().size());
        assertEquals('b', testKey.getValue('a'));
        assertEquals('f', testKey.getValue('e'));
    }

    private void checkSetupMapElements() {
        assertEquals('b', testKey.getValue('a'));
        assertEquals('c', testKey.getValue('b'));
        assertEquals('d', testKey.getValue('c'));
        assertEquals('e', testKey.getValue('d'));
        assertEquals('f', testKey.getValue('e'));
    }
}