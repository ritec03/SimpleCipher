package ui;

import model.Key;

import javax.swing.*;
import java.util.Vector;


/*
This class represents SavedKeysGUI combo box in main GUI
 */
public class SavedKeysGUI extends JPanel {
    private final WorkSpaceGUI workSpaceGUI;
    JComboBox keyList;
    int savedKeysSoFar;

    //MODIFIES this
    //EFFECTS produces a new SavedKeysGUI
    public SavedKeysGUI(WorkSpaceGUI workSpaceGUI) {
        this.workSpaceGUI = workSpaceGUI;
        savedKeysSoFar = 1;
        Vector<String> keyStrings = new Vector<>();

        keyList = new JComboBox(keyStrings);
        keyList.setOpaque(false);
        add(keyList);
    }

    //MODIFIES this, workSpaceGUI
    //EFFECTS saves current key in workspace of WorkSpaceGUI and adds item to this
    public void saveCurrentKey() {
        String keyName = "Key " + savedKeysSoFar;
        Key copiedKey = workSpaceGUI.workSpace.getText().getKey().copyKey();
        copiedKey.setName(keyName);
        savedKeysSoFar++;

        workSpaceGUI.workSpace.addKeySetToSaved(copiedKey);

        keyList.addItem(keyName);
    }

    //MODIFIES this
    //EFFECTS clears all the items in this
    public void clear() {
        while (keyList.getItemCount() != 0) {
            keyList.removeItemAt(0);
        }
        savedKeysSoFar = 1;
    }
}
