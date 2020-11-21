package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
This class represents listeners for textArea in textGUI
CITATION: the code in this class has been created with the help of Oracle tutorial on Swing
https://docs.oracle.com/javase/tutorial/uiswing/TOC.html
 */
public class TextListener implements DocumentListener {
    protected WorkSpaceGUI workSpaceGUI;
    protected JTextArea textArea;

    public TextListener(WorkSpaceGUI workSpaceGUI, JTextArea textArea) {
        this.workSpaceGUI = workSpaceGUI;
        this.textArea = textArea;
    }

    @Override

    // MODIFIES: workSpaceGUI, textGUI
    // EFFECTS: updates GUI components on user input (insert) in the textArea in textGUI
    public void insertUpdate(DocumentEvent e) {
        String textInUI = workSpaceGUI.textGUI.returnText();
        workSpaceGUI.workSpace.getText().addText(textInUI);
        updateKey();
        workSpaceGUI.keyTableGUI.updateKeyTableUI(workSpaceGUI.produceKeyVector());
        workSpaceGUI.workSpace.getText().encryptText();

        String ciphertext = workSpaceGUI.workSpace.getText().printCiphertext();
        workSpaceGUI.outputTextUI.ciphertextArea.setText(null);
        workSpaceGUI.outputTextUI.ciphertextArea.insert(ciphertext, 0);
    }

    // MODIFIES: workSpaceGUI
    // EFFECTS: updates Key in Text of WorkSpaceGUI
    private void updateKey() {
        workSpaceGUI.workSpace.getText().makeKeyTemplate();
        workSpaceGUI.workSpace.setKey(workSpaceGUI.workSpace.getText().getKey());
    }

    @Override
    // MODIFIES: workSpaceGUI, textGUI
    // EFFECTS: updates GUI components on user input (remove) in the textArea in textGUI
    public void removeUpdate(DocumentEvent e) {
        String textInUI = workSpaceGUI.textGUI.textArea.getText();
        workSpaceGUI.workSpace.getText().addText(textInUI);
        workSpaceGUI.workSpace.getText().encryptText();

        String ciphertext = workSpaceGUI.workSpace.getText().printCiphertext();
        workSpaceGUI.outputTextUI.ciphertextArea.setText(null);
        workSpaceGUI.outputTextUI.ciphertextArea.insert(ciphertext, 0);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        //Plain text components don't fire these events.
    }
}
