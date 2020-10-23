package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;



/**
 * this class represent a key with which a text is encoded/decoded using the substitution cipher.
 * This key class only corresponds to a key for the simple substitution cipher, where each character
 * corresponds only to one value.
 */
public class Key {
    private HashMap<Character, Character> keyMap;
    private HashMap<Character, Character> mapKey;
    private HashSet<Character> sureList;

    public Key() {
        keyMap = new HashMap<>();
        mapKey = new HashMap<>();
        sureList = new HashSet<>();
    }

    //EFFECTS: returns keymap.
    public HashMap<Character, Character> getKeyMap() {
        return keyMap;
    }

    //EFFECTS: returns the value associated with key.
    public Character getValue(Character key) {
        return keyMap.get(key);
    }

    //EFFECTS: returns key that corresponds to value
    public Character getKey(Character value) {
        return mapKey.get(value);
    }

    //REQUIRES: passed keymap is not empty
    //MODIFIES: this
    //EFFECTS: sets the this keymap to input keymap
    public void setWholeKeySet(HashMap<Character, Character> keyMap) {
        this.keyMap = keyMap;
        Set<Character> keys = keyMap.keySet();
        for (Character k : keys) {
            Character value = keyMap.get(k);
            mapKey.put(value, k);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds key value pair to keymap, if the key is not already there and returns true, otherwise
    // returns false
    public boolean addKeyValue(char key, Character value) {
        if (keyMap.putIfAbsent(key, value) == null) {
            keyMap.putIfAbsent(key,value);
            mapKey.putIfAbsent(key, value);
            return true;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: removes key value pair from keymap if key is there and returns true, if key is not there,
    // returns false
    public boolean removeKeyValue(char key) {
        if (keyMap.containsKey(key)) {
            keyMap.remove(key);
            mapKey.remove(key);
            return true;
        }
        return false;
    }

    //REQUIRES: keymap is not empty
    //MODIFIES: this
    //EFFECTS: replaces value of the key with input newValue and returns true, if the key is not in
    // keymap, returns false
    public boolean replaceValue(char key, Character newValue) {
        if (!keyMap.containsKey(key)) {
            return false;
        }
        Character value = keyMap.get(key);
        mapKey.remove(value);
        mapKey.put(newValue, key);
        keyMap.replace(key, newValue);
        return true;
    }

    //MODIFIES: this
    //EFFECTS: adds the key to the sureList if the key is not already there and if the key is in keymap
    // and returns true, otherwise returns false.
    public boolean addKeyToSure(Character key) {
        if (keyMap.containsKey(key)) {
            return sureList.add(key);
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: removes value from the sureList if the value is there and returns true, otherwise returns
    //false.
    public boolean removeKeyFromSure(Character key) {
        return sureList.remove(key);
    }

    //MODIFIES: this
    //EFFECTS: removes all key value pairs in keymap, which keys are not in sureList, and returns true,
    // if sureList is empty, returns false.
    public boolean clearAllButSure() {
        if (sureList.isEmpty()) {
            return false;
        }
        HashMap<Character, Character> newMap = new HashMap<>();
        for (Character key : sureList) {
            if (keyMap.containsKey(key)) {
                Character value = keyMap.get(key);
                newMap.put(key, value);
            }
        }
        setWholeKeySet(newMap);
        return true;
    }

    // TODO specify and make tests
    public boolean containsKey(Character key) {
        return keyMap.containsKey(key);
    }

    // TODO specify and make tests
    public boolean containsValue(Character c) {
        return keyMap.containsValue(c);
    }

//    //MODIFIES: this
//    //EFFECTS: replaces the values of keys that correspond to the characters in string keys with
//    //characters that correspond to
//    public void addTuplesToKey(String keys, String values){
//
//    }
//
//    public void clearTuples(String keys){
//
//    }











}
