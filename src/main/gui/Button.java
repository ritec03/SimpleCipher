package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JPanel implements ActionListener {
    WorkSpaceGUI workSpaceGUI;
    JButton button;

    public Button(String name, WorkSpaceGUI workSpaceGUI) {
        this.workSpaceGUI = workSpaceGUI;
        button = new JButton(name);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        button.addActionListener(this);
        add(button);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        workSpaceGUI.savedKeysGUI.saveCurrentKey();
    }
}
