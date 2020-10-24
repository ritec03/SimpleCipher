package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

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
    public void testKeySize(){
        Key k = makeEmptyKey();

        assertEquals(map.size(), testKey.keySize());
    }

    @Test
    public void testGetKeySetEmpty() {
        Key k = makeEmptyKey();
        Set<Character> keySet = k.getKeySet();
        assertEquals(0, keySet.size());
    }

    @Test
    public void testGetKeySetNonEmpty(){
        Set<Character> keySet = testKey.getKeySet();
        assertEquals(map.size(), keySet.size());
        assertTrue(keySet.contains('a'));
        assertTrue(keySet.contains('b'));
        assertTrue(keySet.contains('c'));
        assertTrue(keySet.contains('d'));
        assertTrue(keySet.contains('e'));
    }

    @Test
    // setting whole keymap when keymap is empty
    public void testSetWholeKeyMapEmpty() {
        HashMap<Character,Character> testMap = new HashMap<>();
        testKey.setWholeKeySet(testMap);

        assertEquals(0,testKey.keySize());
        testKey.setWholeKeySet(map);
        assertEquals(map.size(),testKey.keySize());
        checkSetupMapElements();

        HashMap<Character, Character> map1 = new HashMap<>();
        map1.put('x', 'y');
        map1.put('y', 'z');
        map1.put('z', 'x');

        testKey.setWholeKeySet(map1);

        assertEquals(3, testKey.keySize());
        assertEquals('y', testKey.getValue('x'));
        assertEquals('z', testKey.getValue('y'));
        assertEquals('x', testKey.getValue('z'));

        assertEquals('x', testKey.getKey('y'));
        assertEquals('y', testKey.getKey('z'));
        assertEquals('z', testKey.getKey('x'));
    }

    @Test
    // setting whole keymap when keymap is not empty
    public void testSetWholKeyMapNonEmpty() {
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();

        HashMap<Character, Character> map1 = new HashMap<>();
        map1.put('x', 'y');
        map1.put('y', 'z');
        map1.put('z', 'x');

        testKey.setWholeKeySet(map1);

        assertEquals(3, testKey.keySize());
        assertEquals('y', testKey.getValue('x'));
        assertEquals('z', testKey.getValue('y'));
        assertEquals('x', testKey.getValue('z'));

        assertEquals('x', testKey.getKey('y'));
        assertEquals('y', testKey.getKey('z'));
        assertEquals('z', testKey.getKey('x'));
    }

    @Test
    // keymap is empty
    public void testAddKeyValueEmpty() {
        HashMap<Character,Character> testMap = new HashMap<>();
        testKey.setWholeKeySet(testMap);

        assertEquals(0, testKey.keySize());

        assertTrue(testKey.addKeyValue('a', 'b'));
        assertEquals(1, testKey.keySize());

        assertEquals('b', testKey.getValue('a'));
        assertEquals('a', testKey.getKey('b'));

        assertTrue(testKey.addKeyValue('c', 'd'));
        assertEquals(2, testKey.keySize());
        assertEquals('b', testKey.getValue('a'));
        assertEquals('d', testKey.getValue('c'));
    }

    @Test
    // keymap is not empty, key is there
    public void testAddKeyValueNonEmptyNonThere() {
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();

        testKey.addKeyValue('x', 'y');
        assertEquals(6, testKey.keySize());
        assertEquals('y', testKey.getValue('x'));

        checkSetupMapElements();

        assertTrue(testKey.addKeyValue('y', 'z'));
        assertEquals(7, testKey.keySize());
        assertEquals('y', testKey.getValue('x'));
        assertEquals('z', testKey.getValue('y'));

        checkSetupMapElements();
    }


    @Test
    // keymap is not empty, key is not there
    public void testAddKeyValueNonEmptyThere() {
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();

        assertFalse(testKey.addKeyValue('a', 'b'));
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
    }

    @Test
    // keymap is empty
    public void testRemoveKeyValueEmpty() {
        HashMap<Character,Character> testMap = new HashMap<>();
        testKey.setWholeKeySet(testMap);

        assertEquals(0, testKey.keySize());
        assertFalse(testKey.removeKeyValue('a'));
        assertEquals(0, testKey.keySize());
    }

    @Test
    // keymap is not empty, key is there
    public void testRemoveKeyValueNonEmptyThere() {
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();

        assertTrue(testKey.removeKeyValue('a'));
        assertEquals(4, testKey.keySize());
        assertEquals('c', testKey.getValue('b'));
        assertEquals('d', testKey.getValue('c'));
        assertEquals('e', testKey.getValue('d'));
        assertEquals('f', testKey.getValue('e'));

        assertTrue(testKey.removeKeyValue('d'));
        assertEquals(3, testKey.keySize());
        assertEquals('c', testKey.getValue('b'));
        assertEquals('d', testKey.getValue('c'));
        assertEquals('f', testKey.getValue('e'));
    }

    @Test
    // keymap is empty, key is not there
    public void testRemoveKeyValueNonEmptyNonThere(){
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();

        assertFalse(testKey.removeKeyValue('h'));
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
    }

    @Test
    //key is there
    public void testReplaceValueThere(){
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();

        assertTrue(testKey.replaceValue('c', 'x'));
        assertEquals(map.size(), testKey.keySize());
        assertEquals('b', testKey.getValue('a'));
        assertEquals('c', testKey.getValue('b'));

        assertEquals('x', testKey.getValue('c'));

        assertEquals('e', testKey.getValue('d'));
        assertEquals('f', testKey.getValue('e'));
    }

    @Test
    //key is not there
    public void testReplaceValueNotThere(){
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();

        assertFalse(testKey.replaceValue('y', 'x'));
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
    }

    @Test
    // key is not in map
    public void testAddKeyToSureNotThere(){
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();

        assertFalse(testKey.addKeyToSure('x'));
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
    }

    @Test
    // key is in map and not in sureList
    public void testAddKeyToSureThere(){
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();

        assertTrue(testKey.addKeyToSure('b'));
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
    }

    @Test
    // key is in map and in sureList
    public void testAddKeyToSureInList(){
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
        assertTrue(testKey.addKeyToSure('b'));

        assertFalse(testKey.addKeyToSure('b'));
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
    }

    @Test
    // value is in sure list
    public void testRemoveKeyFromSureListThere(){
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
        testKey.addKeyToSure('b');
        testKey.addKeyToSure('d');

        assertTrue(testKey.removeKeyFromSure('d'));
        assertTrue(testKey.removeKeyFromSure('b'));

        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
    }

    @Test
    // value is not in sure list
    public void testRemoveKeyFromSureListNotThere(){
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
        testKey.addKeyToSure('b');
        testKey.addKeyToSure('d');

        assertFalse(testKey.removeKeyFromSure('a'));
        assertFalse(testKey.removeKeyFromSure('e'));

        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
    }

    @Test
    // sureList is empty
    public void testClearAllButSureEmpty(){
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();

        assertFalse(testKey.clearAllButSure());

        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
    }

    @Test
    // sureList is not empty
    public void testClearAllButSureNonEmpty(){
        assertEquals(map.size(), testKey.keySize());
        checkSetupMapElements();
        testKey.addKeyToSure('a');
        testKey.addKeyToSure('e');

        assertTrue(testKey.clearAllButSure());

        assertEquals(2, testKey.keySize());
        assertEquals('b', testKey.getValue('a'));
        assertEquals('f', testKey.getValue('e'));
    }

    @Test
    // key is empty
    public void testContainsKeyEmpty(){
        Key k = makeEmptyKey();

        assertFalse(k.containsKey('g'));
    }

    @Test
    // key is not empty, searched key is there
    public void testContainsKeyThere(){
        assertTrue(testKey.containsKey('a'));
        assertTrue(testKey.containsKey('d'));
    }

    @Test
    // key is not empty, serached key is not there
    public void testContainsKeyNotThere(){
        assertFalse(testKey.containsKey('x'));
    }

    @Test
    // empty
    public void testContainsValueEmpty(){
        Key k = makeEmptyKey();

        assertFalse(k.containsValue('a'));
    }

    @Test
    // not empty, value is there

    public void testContainsValueThere(){
        assertTrue(testKey.containsValue('d'));
        assertTrue(testKey.containsValue('f'));
    }

    @Test
    // not empty, value is not there

    public void testContainsValueNotThere(){
        assertFalse(testKey.containsValue('x'));
    }

    @Test
    public void testClearEmpty(){
        Key k = makeEmptyKey();

        k.clear();

        assertEquals(0, k.keySize());
    }

    @Test
    public void testClearNonEmpty(){
        testKey.clear();
        assertEquals(0, testKey.keySize());
    }

    @Test
    public void testHasKeyForValue(){
        assertTrue(testKey.hasKeyForValue('c'));
        assertTrue(testKey.hasKeyForValue('e'));

        assertFalse(testKey.hasKeyForValue('a'));
        assertFalse(testKey.hasKeyForValue('x'));
    }

    private void checkSetupMapElements() {
        assertEquals('b', testKey.getValue('a'));
        assertEquals('c', testKey.getValue('b'));
        assertEquals('d', testKey.getValue('c'));
        assertEquals('e', testKey.getValue('d'));
        assertEquals('f', testKey.getValue('e'));

        assertEquals('a', testKey.getKey('b'));
        assertEquals('b', testKey.getKey('c'));
        assertEquals('c', testKey.getKey('d'));
        assertEquals('d', testKey.getKey('e'));
        assertEquals('e', testKey.getKey('f'));

    }

    private Key makeEmptyKey() {
        Key k = new Key();
        assertEquals(0, k.keySize());
        return k;
    }
}