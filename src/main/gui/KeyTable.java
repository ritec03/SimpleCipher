package gui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class KeyTable extends JPanel {
    protected MainGUI mainGUI;
    JTable table;

    public KeyTable(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        String[] columnNames = {"Key", "Value"};
        Object[][] data = {{"",""}, {"", ""}};
        //table = new JTable(data, columnNames);

        Vector<String> columnNameVector = new Vector<>();
        columnNameVector.add("Key");
        columnNameVector.add("Value");
        Vector<Vector> vector = new Vector<>();

        Vector<Character> keys = new Vector<>();
        keys.add('a');
        keys.add('b');
        Vector<Character> values = new Vector<>();
        values.add('c');
        values.add('d');

        vector.add(keys);
        vector.add(values);

        table = new JTable(vector,columnNameVector);

        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane);
        table.setFillsViewportHeight(false);
    }

}
