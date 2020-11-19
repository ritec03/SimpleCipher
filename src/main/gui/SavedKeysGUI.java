package gui;


import javafx.scene.control.ComboBox;
import model.Key;
import model.WorkSpace;

import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.KeyName;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SavedKeysGUI extends JPanel implements ActionListener {
    private final WorkSpaceGUI workSpaceGUI;
    WorkSpace workSpace;
    JComboBox keyList;
    int savedKeysSoFar;

    public SavedKeysGUI(WorkSpace workSpace, WorkSpaceGUI workSpaceGUI) {
        this.workSpaceGUI = workSpaceGUI;
        savedKeysSoFar = 1;
        Vector<String> keyStrings = new Vector<>();
        this.workSpace = workSpace;

        keyList = new JComboBox(keyStrings);
        keyList.setOpaque(false);
        keyList.addActionListener(this);
        add(keyList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String selectedKeyName = (String)cb.getSelectedItem();
        for (Key k : workSpace.getSavedKeys()) {
            if (selectedKeyName.equals(k.getName())) {
                workSpace.getText().setKeyMap(k);
            }
        }

        workSpaceGUI.keyTable.updateKeyTableUI(workSpaceGUI.produceKeyVector());
        workSpaceGUI.workSpace.getText().encryptText();

        String ciphertext = workSpaceGUI.workSpace.getText().printCiphertext();
        workSpaceGUI.cipherTextUI.ciphertextArea.setText(null);
        workSpaceGUI.cipherTextUI.ciphertextArea.insert(ciphertext, 0);
    }

    public void saveCurrentKey() {
        String keyName = "Key " + savedKeysSoFar;
        workSpace.getText().getKey().setName(keyName);
        savedKeysSoFar++;

        Vector<String> keys = new Vector<>();
        workSpace.addKeySetToSaved(workSpace.getText().getKey());
        List<Key> savedKeys = workSpace.getSavedKeys();
        for (Key k : savedKeys) {
            keys.add(k.getName());
        }

        JComboBox newComboBox = new JComboBox(keys);
        ComboBoxModel newModel = newComboBox.getModel();
        keyList.setModel(newModel);
        keyList.addActionListener(this);
    }
}
