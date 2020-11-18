package gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyDocumentListener implements DocumentListener {
    protected MainGUI mainGUI;
    protected JTextArea textArea;

    public MyDocumentListener(MainGUI mainGUI, JTextArea textArea) {
        this.mainGUI = mainGUI;
        this.textArea = textArea;
    }

    @Override
    // EFFECTS: inse
    public void insertUpdate(DocumentEvent e) {
        String textInUI = mainGUI.textUI.textArea.getText();
        mainGUI.workSpace.getText().addText(textInUI);
        updateKey();
        mainGUI.keyTable.updateKeyTableUI(mainGUI.produceKeyVector());
        mainGUI.workSpace.getText().encryptText();

        String ciphertext = mainGUI.workSpace.getText().printCiphertext();
        mainGUI.cipherTextUI.ciphertextArea.setText(null);
        mainGUI.cipherTextUI.ciphertextArea.insert(ciphertext, 0);
    }


    private void updateKey() {
        // TODO solve the problem of unsynchronized Key between workspace and text
        mainGUI.workSpace.getText().makeKeyTemplate();
        mainGUI.workSpace.setKey(mainGUI.workSpace.getText().getKey());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String textInUI = mainGUI.textUI.textArea.getText();
        mainGUI.workSpace.getText().addText(textInUI);
        mainGUI.workSpace.getText().encryptText();

        String ciphertext = mainGUI.workSpace.getText().printCiphertext();
        mainGUI.cipherTextUI.ciphertextArea.setText(null);
        mainGUI.cipherTextUI.ciphertextArea.insert(ciphertext, 0);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        //Plain text components don't fire these events.
    }
}
