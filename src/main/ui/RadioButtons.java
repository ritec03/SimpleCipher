package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtons extends JPanel implements ActionListener {
    WorkSpaceGUI workSpaceGUI;

    public RadioButtons(WorkSpaceGUI workSpaceGUI) {
        this.workSpaceGUI = workSpaceGUI;
        JRadioButton encode = new JRadioButton("Encode");
        encode.setActionCommand("encode");
        encode.setSelected(true);

        JRadioButton decode = new JRadioButton("Decode");
        decode.setActionCommand("decode");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(encode);
        buttonGroup.add(decode);
        add(encode);
        add(decode);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("encode")) {
            workSpaceGUI.setEncodeMode();
        } else if (e.getActionCommand().equals("decode")) {
            workSpaceGUI.setDecodeMode();
        }
    }
}
