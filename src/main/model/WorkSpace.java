package model;

import java.util.List;

/**
 * This class
 */
public class WorkSpace {
    private Text text;
    private Text previousText;
    private Key key;
    private Key previousKey;
    private List<Text> savedTexts;
    private List<Key> savedKeys;


    public List<Text> getSavedTexts() {
        return null;
    }

    public List<Key> getSavedKeys() {
        return null;
    }

    public Key getPreviousKey() {
        return null;
    }

    public Key getKey() {
        return null;
    }

    public Text getPreviousText() {
        return null;
    }

    public Text getText() {
        return null;
    }


    //MODIFIES: this
    //EFFECTS: adds key to savedKeys
    public void addKeySetToSaved(Key key){

    }

    //MODIFIES: this
    //EFFECTS: adds text to savedTexts
    public void addTextToSaved(Text text){

    }

    //REQUIRES: keymap is not null
    //MODIFIES: this
    //EFFECTS: sets key as previousKey
    public void setPreviousKey(Key key){

    }

    //MODIFIES: this
    //EFFECTS: adds text to previousText
    public void setPreviousText(Text text){

    }

    //MODIFIES: this
    //EFFECTS: sets text field to text, sets the current text, if there is any, to previousText
    public void setText(Text text){

    }

    //MODIFIES: this
    //EFFECTS: sets keymap with input key, sets the current key, if there is any, to previousKey
    public void setKey(Key key){

    }


}
