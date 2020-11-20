package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * this class represent a key with which a text is encoded/decoded using the substitution cipher.
 * This key class only corresponds to a key for the simple substitution cipher, where each character
 * corresponds only to one value.
 * <p>
 * The key is represented through two maps - keyMap is the map from keys
 * to values, and mapKey which is the map from values to keys. Both maps are maintained to have the same
 * mappings and together constitute a bi-directional mapping with the ability to access keys and values.
 * <p>
 * The key also contains a sureList which represents key-value pairs which the user considers to be correct
 * during decoding, the sure list is represented as a set of keys.
 */
public class Key implements Writable {
    private HashMap<Character, Character> keyMap;
    private HashMap<Character, Character> mapKey;
    private String name;
//    private HashSet<Character> sureList;

    // MODIFIES: this
    // EFFECTS: creates a new Key object
    public Key() {
        keyMap = new HashMap<>();
        mapKey = new HashMap<>();
        // note:since I haven't integrated the functionality related to sureList field into the
        // program, I am commenting it out along with all the methods and tests using it.
//        sureList = new HashSet<>();
    }

    //EFFECTS: returns the value associated with key.
    public Character getValue(Character key) {
        return keyMap.get(key);
    }

    //EFFECTS: returns key that corresponds to value
    public Character getKey(Character value) {
        return mapKey.get(value);
    }

    //EFFECTS: return key name;
    public String getName() {
        return name;
    }

    //EFFECTS: set key name:
    public void setName(String name) {
        this.name = name;
    }

    //EFFECTS: returns number of mappings in key (number of key-value pairs)
    public int keySize() {
        return keyMap.size();
    }

    // EFFECTS: returns set of keys in key
    public Set<Character> getKeySet() {
        return keyMap.keySet();
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
            keyMap.putIfAbsent(key, value);
            mapKey.putIfAbsent(value, key);
            return true;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: removes key value pair from keymap if key is there and returns true, if key is not there,
    // returns false
    // TODO check for correctness
    public boolean removeKeyValue(char key) {
        if (keyMap.containsKey(key)) {
            Character value = keyMap.get(key);
            keyMap.remove(key);
            mapKey.remove(value);
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
//    public boolean addKeyToSure(Character key) {
//        if (keyMap.containsKey(key)) {
//            return sureList.add(key);
//        }
//        return false;
//    }

    //MODIFIES: this
    //EFFECTS: removes value from the sureList if the value is there and returns true, otherwise returns
    //false.
//    public boolean removeKeyFromSure(Character key) {
//        return sureList.remove(key);
//    }

    //MODIFIES: this
    //EFFECTS: removes all key value pairs in keymap, which keys are not in sureList, and returns true,
    // if sureList is empty, returns false.
//    public boolean clearAllButSure() {
//        if (sureList.isEmpty()) {
//            return false;
//        }
//        HashMap<Character, Character> newMap = new HashMap<>();
//        for (Character key : sureList) {
//            if (keyMap.containsKey(key)) {
//                Character value = keyMap.get(key);
//                newMap.put(key, value);
//            }
//        }
//        setWholeKeySet(newMap);
//        return true;
//    }

    // EFFECTS: returns true if keyMap contains the specified key.
    public boolean containsKey(Character key) {
        return keyMap.containsKey(key);
    }

    // EFFECTS: returns true if keyMap contains key-value pair with the given value.
    public boolean containsValue(Character c) {
        return keyMap.containsValue(c);
    }

    // MODIFIES: this
    // EFFECTS: clears keyMap and mapKey of all key-value mappings.
    public void clear() {
        keyMap.clear();
        mapKey.clear();
    }

    //
    public String printKey() {
        String keyString = "";
        Set<Character> keys = keyMap.keySet();
        for (Character c : keys) {
            String kv = c.toString() + "-" + keyMap.get(c).toString() + ", ";
            keyString = keyString + kv;
        }
        return keyString;
    }

    // EFFECTS: returns true if the key mapping contains key for the specified value.
    public boolean hasKeyForValue(Character c) {
        return mapKey.get(c) != null;
    }


    //TODO write specifications
    public Key copyKey() {
        HashMap<Character, Character> savingMap = new HashMap<>();
        Set<Character> keys = getKeySet();
        for (Character c : keys) {
            savingMap.put(c, getValue(c));
        }
        Key savingKey = new Key();
        savingKey.setWholeKeySet(savingMap);
        savingKey.setName(getName());
        return savingKey;
    }

    @Override
    // EFFECTS: converts Key instance to a JSONObject and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("keyMap", keyMapToJson());
        json.put("mapKey", mapKeyToJson());
//        json.put("sureList", sureListToJson());
        return json;
    }

    // EFFECTS: converts keyMap field to JSONObject and returns it
    private JSONObject keyMapToJson() {
        JSONObject object = new JSONObject(keyMap);
        return object;
    }

    // EFFECTS: converts mapKey field to JSONObject and returns it
    private JSONObject mapKeyToJson() {
        JSONObject object = new JSONObject(keyMap);
        return object;
    }

//    private JSONArray sureListToJson() {
//        JSONArray jsonArray = new JSONArray();
//
//        for (Character c: sureList) {
//            JSONObject json = new JSONObject();
//            json.put("c", c);
//            jsonArray.put(json);
//        }
//
//        return jsonArray;
//    }

}
