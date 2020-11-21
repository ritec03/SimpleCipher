package ui;

import javax.swing.*;

/*
Represents output text in GUI
CITATION: the code in this class has been created with the help of Oracle tutorial on Swing
https://docs.oracle.com/javase/tutorial/uiswing/TOC.html
 */
public class OutputTextUI extends JPanel {
    protected JTextArea ciphertextArea;
    protected WorkSpaceGUI workSpaceGUI;

    public OutputTextUI(WorkSpaceGUI gui) {
        workSpaceGUI = gui;
        ciphertextArea = new JTextArea(20, 10);
        ciphertextArea.setEditable(true);
        ciphertextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(ciphertextArea);
        add(scrollPane);
    }
}
