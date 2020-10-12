package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * this class represent a key with which a text is encoded/decoded using the substitution cipher.
 * This key class only corresponds to a key for the simple substitution cipher, where each character
 * corresponds only to one value.
 */
public class Key {
    HashMap<Character, Character> keyMap;
    HashSet<Character> sureList;

    //EFFECTS: returns keymap.
    public HashMap<Character, Character> getKeyMap() {
        return null;
    }

    //EFFECTS: returns the value associated with key.
    public Character getValue(Character key) {
        return null;
    }

    //EFFECTS: returns key that corresponds to value
    public Character getKey(Character value) {
        return null;
    }

    //REQUIRES: passed keymap is not empty
    //MODIFIES: this
    //EFFECTS: sets the this keymap to input keymap
    public void setWholeKeySet(HashMap<Character, Character> keymap) {

    }

    //MODIFIES: this
    //EFFECTS: adds key value pair to keymap, if the key is not already there and returns true, otherwise
    // returns false
    public boolean addKeyValue(char key, Character value) {
        return false;
    }

    //MODIFIES: this
    //EFFECTS: removes key value pair from keymap if key is there and returns true, if key is not there,
    // returns false
    public boolean removeKeyValue(char key) {
        return false;
    }

    //REQUIRES: keymap is not empty
    //MODIFIES: this
    //EFFECTS: replaces value of the key with input newValue and returns true, if the key is not in
    // keymap, returns false
    public boolean replaceValue(char key, Character newValue) {
        return false;
    }

    //MODIFIES: this
    //EFFECTS: adds the key to the sureList if the key is not already there and if the key is in keymap
    // and returns true, otherwise returns false.
    public boolean addKeyToSure(Character key) {
        return false;
    }

    //MODIFIES: this
    //EFFECTS: removes value from the sureList if the value is there and returns true, otherwise returns
    //false.
    public boolean removeKeyFromSure(Character key) {
        return false;
    }

    //MODIFIES: this
    //EFFECTS: removes all key value pairs in keymap, which keys are not in sureList, and returns true,
    // if sureList is empty, returns false.
    public boolean clearAllButSure() {
        return false;
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
