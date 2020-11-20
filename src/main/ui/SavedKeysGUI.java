package ui;

import model.Key;
import model.WorkSpace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        keyStrings.add("Current key");
        this.workSpace = workSpace;

        keyList = new JComboBox(keyStrings);
        keyList.setOpaque(false);
        keyList.addActionListener(this);
        add(keyList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String selectedKeyName = (String) cb.getSelectedItem();

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
        Key copiedKey = workSpace.getText().getKey().copyKey();
        copiedKey.setName(keyName);
        savedKeysSoFar++;

        workSpace.addKeySetToSaved(copiedKey);

        keyList.addItem(keyName);
        // TODO here the key in text switches
        //keyList.setSelectedItem(keyName);
    }
}
