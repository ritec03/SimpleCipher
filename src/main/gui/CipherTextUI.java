package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CipherTextUI extends JPanel {
    protected JTextArea ciphertextArea;
    private static final String newline = "\n";
    protected MainGUI mainGUI;

    public CipherTextUI(MainGUI gui) {
        mainGUI = gui;
        ciphertextArea = new JTextArea(20, 10);
        ciphertextArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(ciphertextArea);
        add(scrollPane);
    }
}
