package gui;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.lang.reflect.Array;
import java.util.Vector;

public class KeyTable extends JPanel implements TableModelListener {
    protected WorkSpaceGUI workSpaceGUI;
    JTable table;
    Vector<String> columnNameVector;

    public KeyTable(WorkSpaceGUI workSpaceGUI) {
        this.workSpaceGUI = workSpaceGUI;

        columnNameVector = new Vector<>();
        columnNameVector.add("Key");
        columnNameVector.add("Value");

        Vector<Vector> vector = workSpaceGUI.produceKeyVector();

        table = new JTable(vector,columnNameVector);

        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane);
        table.setFillsViewportHeight(false);
        table.getModel().addTableModelListener(this);

    }

    public void updateKeyTableUI(Vector<Vector> vector) {
        JTable newTable = new JTable(vector, columnNameVector);
        TableModel model = newTable.getModel();
        table.setModel(model);
        table.getModel().addTableModelListener(this);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        if (columnName.equals("Value")) {
            String dataString = (String) data;
            char[] dataStringArray = dataString.toCharArray();
            Character value = (Character) Array.get(dataStringArray, 0);

            Object keyData = model.getValueAt(row, 0);
            Character key = (Character) keyData;
            workSpaceGUI.workSpace.getText().getKey().replaceValue(key,value);

            // TODO clean up the mess
            workSpaceGUI.workSpace.getText().encryptText();

            String ciphertext = workSpaceGUI.workSpace.getText().printCiphertext();
            workSpaceGUI.cipherTextUI.ciphertextArea.setText(null);
            workSpaceGUI.cipherTextUI.ciphertextArea.insert(ciphertext, 0);
        }

    }
}
