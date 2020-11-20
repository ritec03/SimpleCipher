package ui;

import model.Key;

import javax.swing.*;
import java.util.Vector;

public class SavedKeysGUI extends JPanel {
    private final WorkSpaceGUI workSpaceGUI;
    JComboBox keyList;
    int savedKeysSoFar;

    public SavedKeysGUI(WorkSpaceGUI workSpaceGUI) {
        this.workSpaceGUI = workSpaceGUI;
        savedKeysSoFar = 1;
        Vector<String> keyStrings = new Vector<>();

        keyList = new JComboBox(keyStrings);
        keyList.setOpaque(false);
        add(keyList);
    }

    public void saveCurrentKey() {
        String keyName = "Key " + savedKeysSoFar;
        Key copiedKey = workSpaceGUI.workSpace.getText().getKey().copyKey();
        copiedKey.setName(keyName);
        savedKeysSoFar++;

        workSpaceGUI.workSpace.addKeySetToSaved(copiedKey);

        keyList.addItem(keyName);
        keyList.setSelectedItem(keyName);
    }

    public void clear() {
        while (keyList.getItemCount() != 0) {
            keyList.removeItemAt(0);
        }
        savedKeysSoFar = 1;
    }
}
