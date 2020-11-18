package gui;

import javax.swing.*;


public class TextUI extends JPanel {
    protected JTextArea textArea;
    protected JTextField textField;
    private static final String newline = "\n";
    protected MainGUI mainGUI;

    public TextUI(MainGUI gui) {
        mainGUI = gui;
        textArea = new JTextArea(20, 10);
        textArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        textArea.getDocument().addDocumentListener(new MyDocumentListener(mainGUI,textArea));
    }

    public String returnText() {
        return textArea.getText();
    }

}
