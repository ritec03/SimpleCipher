package ui;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.lang.reflect.Array;
import java.util.Vector;

/*
Represents KeyTable with keys and values in main GUI
 */
public class KeyTableGUI extends JPanel implements TableModelListener {
    protected WorkSpaceGUI workSpaceGUI;
    JTable table;
    Vector<String> columnNameVector;

    // EFFECTS: produces new KeyTableGUI
    public KeyTableGUI(WorkSpaceGUI workSpaceGUI) {
        this.workSpaceGUI = workSpaceGUI;

        columnNameVector = new Vector<>();
        columnNameVector.add("Input symbol");
        columnNameVector.add("Output symbol");

        Vector<Vector> vector = workSpaceGUI.produceKeyVector();

        table = new JTable(vector,columnNameVector);

        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane);
        table.setFillsViewportHeight(false);
        table.getModel().addTableModelListener(this);

    }

    // MODIFIES this
    // EFFECTS updates this with new key-value pairs in Vector<Vector>
    public void updateKeyTableUI(Vector<Vector> vector) {
        JTable newTable = new JTable(vector, columnNameVector);
        TableModel model = newTable.getModel();
        table.setModel(model);
        table.getModel().addTableModelListener(this);
    }

    @Override
    // MODIFIES this, WorkSpaceGUI
    // EFFECTS responds to changes in KeyTableGUI by changing corresponding key-value pairs in Key
    // in WorkSpace of WorkSpaceGUI
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        if (columnName.equals("Output symbol")) {
            String dataString = (String) data;
            char[] dataStringArray = dataString.toCharArray();
            Character value = (Character) Array.get(dataStringArray, 0);

            Object keyData = model.getValueAt(row, 0);
            Character key = (Character) keyData;
            workSpaceGUI.workSpace.getText().getKey().replaceValue(key,value);

            workSpaceGUI.workSpace.getText().encryptText();
            String ciphertext = workSpaceGUI.workSpace.getText().printCiphertext();
            workSpaceGUI.outputTextUI.ciphertextArea.setText(null);
            workSpaceGUI.outputTextUI.ciphertextArea.insert(ciphertext, 0);
        }

    }
}
