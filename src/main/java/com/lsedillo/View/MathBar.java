package com.lsedillo.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MathBar extends JPanel implements ActionListener {
    JToggleButton binBtn;
    JToggleButton decBtn;
    JToggleButton hexBtn;
    MathPanel parent;

    public MathBar(MathPanel parent) {
        this.parent = parent;
//        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setLayout(new GridLayout(5, 1));
//        setAlignmentX(JPanel.LEFT_ALIGNMENT);
        var btnGrp = new ButtonGroup();
        binBtn = new MyJToggleButton("Binary");
        binBtn.setSelected(true); //default mode
        decBtn = new MyJToggleButton("Decimal");
        hexBtn = new MyJToggleButton("Hexadecimal");
        btnGrp.add(binBtn);
        btnGrp.add(decBtn);
        btnGrp.add(hexBtn);

        binBtn.addActionListener(this);
        decBtn.addActionListener(this);
        hexBtn.addActionListener(this);

        add(new MyLabel("Math", 30));
        add(binBtn);
        add(decBtn);
        add(hexBtn);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == binBtn) {
            parent.setMode(MathPanel.BINARY);
            parent.setToBase(MathPanel.BINARY);
            parent.parseInput();
        }
        if(actionEvent.getSource() == decBtn) {
            parent.setMode(MathPanel.DECIMAL);
            parent.setToBase(MathPanel.DECIMAL);
            parent.parseInput();
        }
        if(actionEvent.getSource() == hexBtn) {
            parent.setMode(MathPanel.HEXADECIMAL);
            parent.setToBase(MathPanel.HEXADECIMAL);
            parent.parseInput();
        }
    }
}
