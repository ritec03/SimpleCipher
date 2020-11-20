package ui;

import javax.swing.*;


public class TextUI extends JPanel {
    protected JTextArea textArea;
    protected WorkSpaceGUI workSpaceGUI;

    public TextUI(WorkSpaceGUI gui) {
        workSpaceGUI = gui;
        textArea = new JTextArea(20, 10);
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        textArea.getDocument().addDocumentListener(new TextListener(workSpaceGUI,textArea));
    }

    public String returnText() {
        return textArea.getText();
    }

}
