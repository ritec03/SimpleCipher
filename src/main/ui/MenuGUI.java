package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
This class represents menu bar in GUI
 */
public class MenuGUI extends JMenuBar implements ActionListener {
    WorkSpaceGUI workSpaceGUI;

    // EFFECTS: produces MenuGUI object
    public MenuGUI(WorkSpaceGUI workSpaceGUI) {
        this.workSpaceGUI = workSpaceGUI;
        JMenu menu = new JMenu("Persistence");
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        menu.add(save);
        JMenuItem loadPrevious = new JMenuItem("Load Previous");
        loadPrevious.addActionListener(this);
        menu.add(loadPrevious);

        add(menu);
    }

    @Override
    // MODIFIES workSpaceGUI
    // EFFECTS responds to user selection of menus in menu bar to save or load previous data.
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        if (source.getText().equals("Save")) {
            workSpaceGUI.saveData();
        } else if (source.getText().equals("Load Previous")) {
            try {
                workSpaceGUI.loadPreviousData();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
