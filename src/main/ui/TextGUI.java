package ui;

import javax.swing.*;

/*
Represents TextArea in GUI
CITATION: the code in this class has been created with the help of Oracle tutorial on Swing
https://docs.oracle.com/javase/tutorial/uiswing/TOC.html
 */

public abstract class TextGUI extends JPanel {
    protected JTextArea textArea;
    protected WorkSpaceGUI workSpaceGUI;

    public TextGUI(WorkSpaceGUI gui) {
        workSpaceGUI = gui;
        textArea = new JTextArea(20, 10);
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
    }

    // MODIFIES: this, Text
    // EFFECTS: syncs the text area with the Text object in workspace in workSpaceGUI
    public abstract void syncWithText();
}
