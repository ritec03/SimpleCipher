package ui;

import model.Key;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Represents buttons in GUI
CITATION: the code in this class has been created with the help of Oracle tutorial on Swing
https://docs.oracle.com/javase/tutorial/uiswing/TOC.html
 */
public class ButtonGUI extends JPanel implements ActionListener {
    WorkSpaceGUI workSpaceGUI;
    JButton button;

    // EFFECTS: produces new buttons
    public ButtonGUI(String name, WorkSpaceGUI workSpaceGUI, String actionCommand) {
        this.workSpaceGUI = workSpaceGUI;
        button = new JButton(name);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING);
        button.addActionListener(this);
        button.setActionCommand(actionCommand);
        add(button);
    }


    @Override
    // MODIFIES workSpaceGUI
    // EFFECTS responds to user's selection of Buttons in GUI
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

            workSpaceGUI.workSpace.getText().makeKeyTemplate();
            //as number of keys might have been increased
            workSpaceGUI.keyTableGUI.updateKeyTableUI(workSpaceGUI.produceKeyVector());
            workSpaceGUI.workSpace.getText().encryptText();

            String ciphertext = workSpaceGUI.workSpace.getText().printCiphertext();
            workSpaceGUI.outputTextUI.ciphertextArea.setText(null);
            workSpaceGUI.outputTextUI.ciphertextArea.insert(ciphertext, 0);

        }
    }
}
