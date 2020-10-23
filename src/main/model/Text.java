package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the text and it contains the text in its unciphered or ciphered
 * state or both. Text has two forms - the ciphered and unciphered. The key set that is
 * stored is the key used to produce the ciphered/deciphered version that is stored currently.
 */
public class Text {
    Key key;
    ArrayList<Character> text;
    ArrayList<Character> ciphertext;

    // TODO write specification
    public Text() {
        key = new Key();
        text = new ArrayList<Character>();
        ciphertext = new ArrayList<Character>();
    }

    public Key getKey() {
        return key;
    }

    public List<Character> getText() {
        return text;
    }

    public List<Character> getCiphertext() {
        return ciphertext;
    }

    //MODIFIES: this
    //EFFECTS: sets the key
    public void setKeyMap(Key key) {
        this.key = key;
    }

    //MODIFIES: this
    //EFFECTS: adds text and stores it as an List<Character>, blank spaces are included,
    // punctuation marks are included, all capital letters are converted to lower-case letters.
    public void addText(String textAsString) {
        char[] arrayText = textAsString.toCharArray();
        for (int n = 0; n < arrayText.length; n++) {
            // the following line uses the method that I found in the following URL:
            // https://www.geeksforgeeks.org/array-get-method-in-java/
            Character c = (Character) Array.get(arrayText, n);
            c = Character.toLowerCase(c);
            text.add(c);
        }
    }

    //MODIFIES: this
    //EFFECTS: based on the text, create a KeyMap with key for every distinct symbol in text that
    // is not space, and an empty value.
    public void makeKeyTemplate() {
        for (Character c : text) {
            if (!key.containsKey(c)) {
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
        ArrayList<Character> encodedText = new ArrayList<>();
        for (Character c : text) {
            if (!key.containsKey(c)) {
                encodedText.add('-');
            } else if (key.getValue(c) == null) {
                encodedText.add('-');
            } else {
                Character v = key.getValue(c);
                if (Character.isLetter(v)) {
                    // the following method was taken from
                    // URL: https://www.tutorialspoint.com/java/lang/character_isletter.htm
                    v = Character.toLowerCase(v);
                    encodedText.add(v);
                } else {
                    encodedText.add(v);
                }
            }
        }
        ciphertext = encodedText;
    }

    //MODIFIES: this
    //EFFECTS: adds ciphertext and stores it as a List<Character>. This method overwrites ciphertext
    // field. Also, all capital letters are converted to lower-case letters.
    public void addCiphertext(String textAsString) {
        char[] arrayText = textAsString.toCharArray();
        for (int n = 0; n < arrayText.length; n++) {
            // the following line uses the method that I found in the following URL:
            // https://www.geeksforgeeks.org/array-get-method-in-java/
            Character c = (Character) Array.get(arrayText, n);
            c = Character.toLowerCase(c);
            ciphertext.add(c);
        }
    }

    //MODIFIES: this
    //EFFECTS: produced text from cipher text, by replacing each value in cipher text with key
    // according to key-value pairs in key. If value is missing for a key, then encrypted
    // character is replaced with dashes. The result is stored in text field.
    public void decryptCiphertext() {
        ArrayList<Character> decodedText = new ArrayList<>();
        for (Character c : ciphertext) {
            if (!key.containsValue(c)) {
                decodedText.add('-');
            } else if (key.getKey(c) == null) {
                decodedText.add('-');
            } else {
                Character v = key.getKey(c);
                if (Character.isLetter(v)) {
                    // the following method was taken from
                    // URL: https://www.tutorialspoint.com/java/lang/character_isletter.htm
                    v = Character.toLowerCase(v);
                    decodedText.add(v);
                } else {
                    decodedText.add(v);
                }
            }
        }
        text = decodedText;
    }


    // TODO  write tests
    //EFFECTS: converts ciphertext into a String and returns this String.
    public String produceEncryptedText() {
        return " ";
    }

    public String produceDecryptedText() {
        return " ";
    }


//    //REQUIRES:
//    //MODIFIES:
//    //EFFECTS:
//    public void doFrequencyAnalysis(int windowLength) {
//
//    }

}
