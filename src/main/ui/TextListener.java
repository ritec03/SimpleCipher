package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextListener implements DocumentListener {
    protected WorkSpaceGUI workSpaceGUI;
    protected JTextArea textArea;

    public TextListener(WorkSpaceGUI workSpaceGUI, JTextArea textArea) {
        this.workSpaceGUI = workSpaceGUI;
        this.textArea = textArea;
    }

    @Override
    // EFFECTS: inse
    public void insertUpdate(DocumentEvent e) {
        String textInUI = workSpaceGUI.textUI.returnText();
        workSpaceGUI.workSpace.getText().addText(textInUI);
        updateKey();
        workSpaceGUI.keyTable.updateKeyTableUI(workSpaceGUI.produceKeyVector());
        workSpaceGUI.workSpace.getText().encryptText();

        String ciphertext = workSpaceGUI.workSpace.getText().printCiphertext();
        workSpaceGUI.cipherTextUI.ciphertextArea.setText(null);
        workSpaceGUI.cipherTextUI.ciphertextArea.insert(ciphertext, 0);
    }


    private void updateKey() {
        // TODO solve the problem of unsynchronized Key between workspace and text
        workSpaceGUI.workSpace.getText().makeKeyTemplate();
        workSpaceGUI.workSpace.setKey(workSpaceGUI.workSpace.getText().getKey());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String textInUI = workSpaceGUI.textUI.textArea.getText();
        workSpaceGUI.workSpace.getText().addText(textInUI);
        workSpaceGUI.workSpace.getText().encryptText();

        String ciphertext = workSpaceGUI.workSpace.getText().printCiphertext();
        workSpaceGUI.cipherTextUI.ciphertextArea.setText(null);
        workSpaceGUI.cipherTextUI.ciphertextArea.insert(ciphertext, 0);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        //Plain text components don't fire these events.
    }
}
