package ui;

/*
Represents output text in GUI
CITATION: the code in this class has been created with the help of Oracle tutorial on Swing
https://docs.oracle.com/javase/tutorial/uiswing/TOC.html
 */
public class OutputTextUI extends TextGUI {

    public OutputTextUI(WorkSpaceGUI gui) {
        super(gui);
    }

    @Override
    // MODIFIES: This
    // EFFECTS: syncs the text area with the Text object in workspace in workSpaceGUI by
    // updating this textarea according to ciphertext in Text.
    public void syncWithText() {
        String ciphertext = workSpaceGUI.workSpace.getText().printCiphertext();
        textArea.setText(null);
        textArea.insert(ciphertext, 0);
    }
}
