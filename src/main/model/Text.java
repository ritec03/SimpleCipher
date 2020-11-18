package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class represents the text and it contains the text in its unciphered or ciphered
 * state or both. Text has two forms - the ciphered and unciphered. The key set that is
 * stored is the key used to produce the ciphered/deciphered version that is stored currently.
 */
public class Text implements Writable {
    private Key key;
    private ArrayList<Character> text;
    private ArrayList<Character> ciphertext;

    // MODIFIES: this
    // EFFECTS: creates a new Text object
    public Text() {
        key = new Key();
        text = new ArrayList<Character>();
        ciphertext = new ArrayList<Character>();
    }

    public Key getKey() {
        return key;
    }

    // EFFECTS: returns the character in text corresponding to index
    public Character getCharacterInText(int index) {
        return text.get(index);
    }

    // EFFECTS: returns the character in ciphertext corresponding to index
    public Character getCharacterInCiphertext(int index) {
        return ciphertext.get(index);
    }

    // EFFECTS: returns the size of the text
    public int textSize() {
        return text.size();
    }

    // EFFECTS: returns the size of the ciphertext
    public int ciphertextSize() {
        return ciphertext.size();
    }

    //MODIFIES: this
    //EFFECTS: sets the key
    public void setKeyMap(Key key) {
        this.key = key;
    }

    //MODIFIES: this
    //EFFECTS: stores textAsString in an ArrayList<Character> (text or cipher text) with blank spaces and
    // punctuation marks  included, all capital letters are converted to lower-case letters. This method
    // overwrites ArrayList<Character> field on input.
    private ArrayList<Character> storeStringAsArray(String textAsString) {
        ArrayList<Character> list = new ArrayList<>();
        char[] arrayText = textAsString.toCharArray();
        for (int n = 0; n < arrayText.length; n++) {
            // the following line uses the method that I found in the following URL:
            // https://www.geeksforgeeks.org/array-get-method-in-java/
            Character c = (Character) Array.get(arrayText, n);
            c = Character.toLowerCase(c);
            list.add(c);
        }
        return list;
    }

    //MODIFIES: this
    //EFFECTS: stores text as ArrayList<Character> in text field.
    public void addText(String textAsString) {
        text = storeStringAsArray(textAsString);
    }

    // todo
    //MODIFIES: this
    //EFFECTS: stores array list of characters in the text field
    public void addText(ArrayList<Character> c) {
        this.text = c;
    }

    //MODIFIES: this
    //EFFECTS: stores ciphertext as ArrayList<Character> in ciphertext field.
    public void addCiphertext(String textAsString) {
        ciphertext = storeStringAsArray(textAsString);
    }

    // todo
    //MODIFIES: this
    //EFFECTS: stores array list of characters in the text field
    public void addCiphertext(ArrayList<Character> c) {
        this.ciphertext = c;
    }

    //MODIFIES: this
    //EFFECTS: based on the text, create a KeyMap with key for every distinct symbol in text that
    // is not space, and an empty value. Does not overwrite current key
    public void makeKeyTemplate() {
        for (Character c : text) {
            if (!key.containsKey(c)) {
                // TODO check if change from null to '' is good
                key.addKeyValue(c, null);
            }
        }
    }


    //REQUIRES: keymap should cover
    //MODIFIES: this
    //EFFECTS: produces ciphertext by substituting each character in text with another character,
    // that is the value, according to key-value pairs in keymap. If key does not contain key-value
    // pair to encrypt a symbol, the symbol is substituted with '-'. The method overwrites the
    // current ciphertext field.
    public void encryptText() {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : text) {
            if (key.getValue(c) == null) {
                list.add('-');
            } else {
                Character v = key.getValue(c);
                list.add(v);
            }
        }
        ciphertext = list;
    }

    //MODIFIES: this
    //EFFECTS: produced text from cipher text, by replacing each value in cipher text with key
    // according to key-value pairs in key. If value is missing for a key, then encrypted
    // character is replaced with dashes. The result is stored in text field.
    public void decryptCiphertext() {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : ciphertext) {
            if (!key.containsValue(c)) {
                list.add('-');
            } else {
                Character v = key.getKey(c);
                list.add(v);
            }
        }
        text = list;
    }

    // EFFECTS: produce a String from a list (text or ciphertext) by converting to String and
    // concatenating all the elements in the list.
    private String getStringFromList(ArrayList<Character> list) {
        String stringToPrint = "";
        for (Character c : list) {
            String character = c.toString();
            stringToPrint = stringToPrint + character;
        }
        return stringToPrint;
    }

    //EFFECTS: converts text into a String and returns this String.
    public String printText() {
        return getStringFromList(this.text);
    }

    public String printCiphertext() {
        return getStringFromList(this.ciphertext);
    }

    @Override
    // EFFECTS: converts Text instance to a JSONObject and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("key", key.toJson());
        json.put("text", text);
        json.put("ciphertext", ciphertext);
        return json;
    }
}
