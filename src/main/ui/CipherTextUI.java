package ui;

import javax.swing.*;

public class CipherTextUI extends JPanel {
    protected JTextArea ciphertextArea;
    protected WorkSpaceGUI workSpaceGUI;

    public CipherTextUI(WorkSpaceGUI gui) {
        workSpaceGUI = gui;
        ciphertextArea = new JTextArea(20, 10);
        ciphertextArea.setEditable(true);
        ciphertextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(ciphertextArea);
        add(scrollPane);
    }
}
