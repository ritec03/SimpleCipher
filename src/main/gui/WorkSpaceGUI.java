package gui;

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

        savedKeysGUI = new SavedKeysGUI(workSpace, this);
        Button newButton2 = new Button("Save current key", this);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
        topPanel.add(savedKeysGUI);
        topPanel.add(newButton2);

        add(topPanel, BorderLayout.PAGE_START);

        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel,BoxLayout.X_AXIS));

        textUI = new TextUI(this);
        centrePanel.add(textUI);

        cipherTextUI = new CipherTextUI(this);
        centrePanel.add(cipherTextUI);

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

}
