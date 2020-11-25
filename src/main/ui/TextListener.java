package ui;

import model.Key;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
This class represents listeners for textArea in inputTextGUI
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

    // MODIFIES: workSpaceGUI, inputTextGUI
    // EFFECTS: updates GUI components on user input (insert) in the textArea in inputTextGUI
    public void insertUpdate(DocumentEvent e) {
        String textInUI = workSpaceGUI.inputTextGUI.returnText();
        workSpaceGUI.workSpace.getText().addText(textInUI);
        updateKey();
        workSpaceGUI.keyTableGUI.updateKeyTableUI(workSpaceGUI.produceKeyVector());
        workSpaceGUI.workSpace.getText().encryptText();
        workSpaceGUI.outputTextUI.syncWithText();
    }

    // MODIFIES: workSpaceGUI
    // EFFECTS: updates Key in Text of WorkSpaceGUI
    private void updateKey() {
        workSpaceGUI.workSpace.getText().makeKeyTemplate();
        workSpaceGUI.workSpace.setKey(workSpaceGUI.workSpace.getText().getKey());
    }

    @Override
    // MODIFIES: workSpaceGUI, inputTextGUI
    // EFFECTS: updates GUI components on user input (remove) in the textArea in inputTextGUI
    public void removeUpdate(DocumentEvent e) {
        workSpaceGUI.inputTextGUI.syncWithText();
        workSpaceGUI.outputTextUI.syncWithText();
    }


    @Override
    public void changedUpdate(DocumentEvent e) {
        //Plain text components don't fire these events.
    }
}
