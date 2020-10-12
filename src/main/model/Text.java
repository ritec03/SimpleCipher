package model;

import java.util.List;

/**
 * This class represents the text and it contains the text in its unciphered or ciphered state or both.
 * Text has two forms - the ciphered and unciphered. The key set that is stored is the key used to produce
 * the ciphered/deciphered version that is stored currently.
 */
public class Text {
    Key keySet;
    List<Character> text;
    List<Character> ciphertext;


    //MODIFIES: this
    //EFFECTS: sets the keySet
    public void setKeySet(Key key){

    }

    //MODIFIES: this
    //EFFECTS: adds text and stores it as an List<Character>.
    public void addUncipheredText(String text){

    }

    //MODIFIES: this
    //EFFECTS: produces cipher text as List<Character> by substituting each character in text with
    //another character, that is the value, according to key-value pairs in keymap. Produced ciphertext
    // is stores in ciphertext field.
    public void encryptText(){

    }

    //MODIFIES: this
    //EFFECTS: adds ciphertext and stores it as an List<Character>
    public void addCiphertext(String text){

    }

    //MODIFIES: this
    //EFFECTS: produced text from cipher text, by replacing each value in cipher text with key according
    //to key-value pairs in keymam. If value is missing for a key, then encrypted character is retained.
    //The produced text is stores in text field.
    public void decryptText(){

    }

    //MODIFIES: this
    //EFFECTS: produced text from cipher text, by replacing each value in cipher text with key according
    //to key-value pairs in keymam. If value is missing for a key, then encrypted character is replaced
    // with dashes.
    public void decryptTextDashes(){

    }

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
