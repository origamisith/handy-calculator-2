package com.lsedillo.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataMenu extends JPanel implements ActionListener {

    JButton convertUnitBtn;
    JButton convertDataBtn;
    JButton calculateWeb;
    JButton calculateTime;
    MyFrame frame;
    public DataMenu(MyFrame frame) {
        this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        convertUnitBtn = new JButton("Convert Data");
        convertDataBtn = new JButton("Convert Rate");
        calculateWeb = new JButton("Required Bandwidth");
        calculateTime = new JButton("Download/Upload Time");

        convertUnitBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton)(event.getSource());
        String mode = button.getText();
        frame.dataPanel.refresh(mode);
    }
}
