package ui;

/*
Represents InputTextArea in GUI
CITATION: the code in this class has been created with the help of Oracle tutorial on Swing
https://docs.oracle.com/javase/tutorial/uiswing/TOC.html
 */
public class InputTextGUI extends TextGUI {

    public InputTextGUI(WorkSpaceGUI gui) {
        super(gui);
        textArea.getDocument().addDocumentListener(new TextListener(workSpaceGUI,textArea));
    }

    public String returnText() {
        return textArea.getText();
    }

    @Override
    // MODIFIES: Text
    // EFFECTS: syncs the text area with the Text object in workspace in workSpaceGUI by
    // updating the text in Text is updated to the text in this text area.
    public void syncWithText() {
        String textInUI = returnText();
        workSpaceGUI.workSpace.getText().addText(textInUI);
        workSpaceGUI.workSpace.getText().encryptText();
    }
}
