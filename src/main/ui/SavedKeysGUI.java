package ui;

import model.Key;

import javax.swing.*;
import java.util.Vector;


/*
This class represents SavedKeysGUI combo box in main GUI
CITATION: the code in this class has been created with the help of Oracle tutorial on Swing
https://docs.oracle.com/javase/tutorial/uiswing/TOC.html
 */
public class SavedKeysGUI extends JPanel {
    private final WorkSpaceGUI workSpaceGUI;
    JComboBox keyListComboBox;
    int savedKeysSoFar;

    //MODIFIES this
    //EFFECTS produces a new SavedKeysGUI
    public SavedKeysGUI(WorkSpaceGUI workSpaceGUI) {
        this.workSpaceGUI = workSpaceGUI;
        savedKeysSoFar = 1;
        Vector<String> keyStrings = new Vector<>();

        keyListComboBox = new JComboBox(keyStrings);
        keyListComboBox.setOpaque(false);
        add(keyListComboBox);
    }

    //MODIFIES this, workSpaceGUI
    //EFFECTS saves current key in workspace of WorkSpaceGUI and adds item to this
    public void saveCurrentKey() {
        String keyName = "Key " + savedKeysSoFar;
        Key copiedKey = workSpaceGUI.workSpace.getText().getKey().copyKey();
        copiedKey.setName(keyName);
        savedKeysSoFar++;

        workSpaceGUI.workSpace.addKeySetToSaved(copiedKey);

        keyListComboBox.addItem(keyName);
    }

    //MODIFIES this
    //EFFECTS clears all the items in this
    public void clear() {
        while (keyListComboBox.getItemCount() != 0) {
            keyListComboBox.removeItemAt(0);
        }
        savedKeysSoFar = 1;
    }
}
