package gui;

import model.Key;
import model.WorkSpace;

import javax.crypto.Cipher;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;

public class MainGUI extends JFrame {
    WorkSpace workSpace;
    TextUI textUI;
    CipherTextUI cipherTextUI;
    KeyTable keyTable;

    public MainGUI() {
        super("myFrame");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        createWorkspace();

        String[] keyStrings = {"A", "B", "C"};
        JComboBox keyList = new JComboBox(keyStrings);
        keyList.setOpaque(false);
        add(keyList, BorderLayout.PAGE_START);

        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel,BoxLayout.X_AXIS));

        textUI = new TextUI(this);
        centrePanel.add(textUI);

        cipherTextUI = new CipherTextUI(this);
        centrePanel.add(cipherTextUI);

        Button newButton3 = new Button("PAGE_END");
        newButton3.setOpaque(true);
        add(newButton3, BorderLayout.PAGE_END);

        add(centrePanel);

        keyTable = new KeyTable(this);

        add(keyTable, BorderLayout.LINE_END);
//        String[] columnNames = {"Key", "Value"};
//        Object[][] data = {{"A", "B"}, {"C","D"}};
//        JTable table = new JTable(data, columnNames);
//        JScrollPane tableScrollPane = new JScrollPane(table);
//        table.setFillsViewportHeight(true);
//
//        add(tableScrollPane, BorderLayout.LINE_END);

        pack();

        setVisible(true);

    }

    private void createWorkspace() {
        workSpace = new WorkSpace();
        Key testKey = new Key();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('a', 'b');
        map.put('b', 'c');
        map.put('c', 'd');
        map.put('d', 'e');
        map.put('e', 'f');
        testKey.setWholeKeySet(map);
        //TODO make sure workspace.setkey changes key in workspace.gettext();
        workSpace.getText().setKeyMap(testKey);
    }

    public static void main(String[] args) {
        new MainGUI();
    }

    public void updateKeyTable() {

    }
}
