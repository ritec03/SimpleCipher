package ui;

import model.WorkSpace;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.Vector;

public class WorkSpaceGUI extends JFrame {
    WorkSpace workSpace;
    TextUI textUI;
    CipherTextUI cipherTextUI;
    KeyTable keyTable;
    SavedKeysGUI savedKeysGUI;

    public WorkSpaceGUI() {
        super("myFrame");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        createWorkspace();

        JMenu menu = new JMenu("A Menu");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        JMenuItem save = new JMenuItem("Save");
        menu.add(save);
        JMenuItem loadPrevious = new JMenuItem("Load Previous");
        menu.add(loadPrevious);
        setJMenuBar(menuBar);

        savedKeysGUI = new SavedKeysGUI(workSpace, this);
        SaveKeyButton newSaveKeyButton2 = new SaveKeyButton("Save current key", this);
        JLabel savedKeyLabel = new JLabel("Select Key");

        RadioButtons radioButtons = new RadioButtons(this);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
        topPanel.add(radioButtons);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(savedKeyLabel);
        topPanel.add(savedKeysGUI);
        topPanel.add(newSaveKeyButton2);

        add(topPanel, BorderLayout.PAGE_START);

        // centre panel
        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel,BoxLayout.X_AXIS));

        //centre panel left part
        JPanel centrePanelLeft = new JPanel();
        centrePanelLeft.setLayout(new BoxLayout(centrePanelLeft, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("Decoded Text");
        textUI = new TextUI(this);
        centrePanelLeft.add(label1);
        centrePanelLeft.add(textUI);
        centrePanel.add(centrePanelLeft);

        //centre panel right part
        JPanel centrePanelRight = new JPanel();
        centrePanelRight.setLayout(new BoxLayout(centrePanelRight, BoxLayout.Y_AXIS));
        JLabel label2 = new JLabel("Encoded Text");
        cipherTextUI = new CipherTextUI(this);
        centrePanelRight.add(label2);
        centrePanelRight.add(cipherTextUI);
        centrePanel.add(centrePanelRight);

        add(centrePanel);

        keyTable = new KeyTable(this);

        add(keyTable, BorderLayout.LINE_END);

        pack();

        setVisible(true);
    }

    private void createWorkspace() {
        workSpace = new WorkSpace();
    }

    public static void main(String[] args) {
        new WorkSpaceGUI();
    }

    public Vector<Vector> produceKeyVector() {
        Set<Character> keySet = workSpace.getText().getKey().getKeySet();
        Vector<Vector> vector = new Vector<>();
        for (Character c: keySet) {
            Vector<Character> row = new Vector<>();
            row.add(c);
            Character value = workSpace.getText().getKey().getValue(c);
            row.add(value);
            vector.add(row);
        }
        return vector;
    }

    // TODO implement
    public void setEncodeMode() {

    }

    // TODO implement
    public void setDecodeMode() {

    }
}