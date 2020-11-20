package ui;

import model.Key;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JPanel implements ActionListener {
    WorkSpaceGUI workSpaceGUI;
    JButton button;

    public Button(String name, WorkSpaceGUI workSpaceGUI, String actionCommand) {
        this.workSpaceGUI = workSpaceGUI;
        button = new JButton(name);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        button.addActionListener(this);
        button.setActionCommand(actionCommand);
        add(button);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            workSpaceGUI.savedKeysGUI.saveCurrentKey();
        } else if (e.getActionCommand().equals("clear")) {
            workSpaceGUI.clear();
        } else if (e.getActionCommand().equals("choose")) {
            String selectedKeyName = (String) workSpaceGUI.savedKeysGUI.keyList.getSelectedItem();
            for (Key k : workSpaceGUI.workSpace.getSavedKeys()) {
                if (selectedKeyName.equals(k.getName())) {
                    workSpaceGUI.workSpace.getText().setKeyMap(k);
                }
            }

            workSpaceGUI.workSpace.getText().makeKeyTemplate(); //as number of keys might have been increased
            workSpaceGUI.keyTable.updateKeyTableUI(workSpaceGUI.produceKeyVector());
            workSpaceGUI.workSpace.getText().encryptText();

            String ciphertext = workSpaceGUI.workSpace.getText().printCiphertext();
            workSpaceGUI.cipherTextUI.ciphertextArea.setText(null);
            workSpaceGUI.cipherTextUI.ciphertextArea.insert(ciphertext, 0);

        }
    }
}
