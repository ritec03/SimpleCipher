package gui;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.Vector;

public class KeyTable extends JPanel {
    protected MainGUI mainGUI;
    JTable table;
    Vector<String> columnNameVector;

    public KeyTable(MainGUI mainGUI) {
        this.mainGUI = mainGUI;

        columnNameVector = new Vector<>();
        columnNameVector.add("Key");
        columnNameVector.add("Value");

        Vector<Vector> vector = mainGUI.produceKeyVector();

        table = new JTable(vector,columnNameVector);

        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane);
        table.setFillsViewportHeight(false);

        table.getModel().addTableModelListener(new KeyTableListener(mainGUI));
    }

    public void updateKeyTableUI(Vector<Vector> vector) {
        JTable newTable = new JTable(vector, columnNameVector);
        TableModel model = newTable.getModel();
        table.setModel(model);
    }
}
