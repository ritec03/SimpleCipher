package gui;


import javafx.scene.control.ComboBox;
import model.Key;
import model.WorkSpace;

import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.KeyName;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SavedKeysGUI extends JPanel implements ActionListener {
    WorkSpace workSpace;
    JComboBox keyList;
    int savedKeysSoFar;

    public SavedKeysGUI(WorkSpace workSpace) {
        savedKeysSoFar = 1;
        Vector<String> keyStrings = new Vector<>();
        this.workSpace = workSpace;

        keyList = new JComboBox(keyStrings);
        keyList.setOpaque(false);
        add(keyList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String selectedKey = (String)cb.getSelectedItem();

    }

    public void saveCurrentKey() {
        Key keyToSave = workSpace.getKey();
        String keyName = "Key " + savedKeysSoFar;
        keyToSave.setName(keyName);
        savedKeysSoFar++;
        workSpace.addKeySetToSaved(workSpace.getKey());

        Vector<String> keys = new Vector<>();
        keys.add(keyName);
        JComboBox newComboBox = new JComboBox(keys);
        ComboBoxModel newModel = newComboBox.getModel();
        keyList.setModel(newModel);

    }
}
