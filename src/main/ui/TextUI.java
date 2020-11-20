package ui;

import javax.swing.*;


public class TextUI extends JPanel {
    protected JTextArea textArea;
    protected JTextField textField;
    private static final String newline = "\n";
    protected WorkSpaceGUI workSpaceGUI;

    public TextUI(WorkSpaceGUI gui) {
        workSpaceGUI = gui;
        textArea = new JTextArea(20, 10);
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        textArea.getDocument().addDocumentListener(new MyDocumentListener(workSpaceGUI,textArea));
    }

    public String returnText() {
        return textArea.getText();
    }

}
