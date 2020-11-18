package gui;

import javax.swing.*;

public class Button extends JPanel {
    JButton button;

    public Button(String name) {
        button = new JButton(name);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales

        add(button);
    }



}
