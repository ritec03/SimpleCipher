package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * This class represents a works space that contains current Text and Key that are being worked with.
 * The class also stores previous texts and keys as lists, as well as previous key and previous text
 * in their own fields, when a text of key is replaced.
 */
public class WorkSpace {
    private Text text;
    private Text previousText;
    private Key key;
    private Key previousKey;
    private List<Text> savedTexts;
    private List<Key> savedKeys;


    // MODIFIES: this
    // EFFECTS: creates a new WorkSpace object
    public WorkSpace() {
        savedTexts = new ArrayList<>();
        savedKeys = new ArrayList<>();
    }

    public List<Text> getSavedTexts() {
        return savedTexts;
    }

    public List<Key> getSavedKeys() {
        return savedKeys;
    }

    public Key getPreviousKey() {
        return previousKey;
    }

    public Key getKey() {
        return key;
    }

    public Text getPreviousText() {
        return previousText;
    }

    public Text getText() {
        return text;
    }


    //MODIFIES: this
    //EFFECTS: adds key to savedKeys
    public void addKeySetToSaved(Key key) {
        HashMap<Character,Character> savingMap = new HashMap<>();
        if (text.getKey() != null) {
            Set<Character> keys = text.getKey().getKeySet();
            for (Character c: keys) {
                savingMap.put(c, text.getKey().getValue(c));
            }
            Key savingKey = new Key();
            savingKey.setWholeKeySet(savingMap);
            savedKeys.add(savingKey);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds text to savedTexts
    public void addTextToSaved(Text text) {
        savedTexts.add(text);
    }

    //REQUIRES: keymap is not null
    //MODIFIES: this
    //EFFECTS: sets key as previousKey
    public void setPreviousKey(Key key) {
        previousKey = key;
    }

    //MODIFIES: this
    //EFFECTS: adds text to previousText
    public void setPreviousText(Text text) {
        previousText = text;
    }

    //MODIFIES: this
    //EFFECTS: sets text field to text, sets the current text, if there is any, to previousText
    public void setText(Text text) {
        if (this.text != null) {
            setPreviousText(this.text);
            this.text = text;
        }
        this.text = text;
    }

    //MODIFIES: this
    //EFFECTS: sets key with input key, sets the current key, if there is any, to previousKey
    public void setKey(Key key) {
        if (this.key != null) {
            setPreviousKey(this.key);
            this.key = key;
        }
        this.key = key;
    }



}
