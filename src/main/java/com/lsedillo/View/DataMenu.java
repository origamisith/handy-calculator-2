package com.lsedillo.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataMenu extends JPanel implements ActionListener {

    JToggleButton convertData;
    JToggleButton convertRate;
    JToggleButton calculateWeb;
    JToggleButton calculateTime;
    DataPanel parent;

    public DataMenu(DataPanel parent) {
        this.parent = parent;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setLayout(new GridLayout(6, 1));

        var btnGrp = new ButtonGroup();
        convertData = new JToggleButton("Convert Data");
        convertData.setSelected(true);
        convertRate = new JToggleButton("Convert Rate");
        calculateWeb = new JToggleButton("Required Bandwidth");
        calculateTime = new JToggleButton("Download/Upload Time");
        btnGrp.add(convertData);
        btnGrp.add(convertRate);
        btnGrp.add(calculateWeb);
        btnGrp.add(calculateTime);


        convertData.addActionListener(this);
        convertRate.addActionListener(this);
        calculateWeb.addActionListener(this);
        calculateTime.addActionListener(this);

        add(new MyLabel("Data", 30));
        add(convertData);
        add(convertRate);
        add(calculateWeb);
        add(calculateTime);
    }

    public void actionPerformed(ActionEvent event) {
        JToggleButton button = (JToggleButton) event.getSource();
        if (convertRate.equals(button)) {
            parent.setMode(DataPanel.RATE);
        } else if (convertData.equals(button)) {
            parent.setMode(DataPanel.DATA);
        } else if (calculateWeb.equals(button)) {
            parent.setMode(DataPanel.WEB);
        } else if (calculateTime.equals(button)) {
            parent.setMode(DataPanel.TIME);
        }
    }
}
