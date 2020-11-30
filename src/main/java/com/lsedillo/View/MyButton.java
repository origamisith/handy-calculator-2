package com.lsedillo.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyButton extends JButton {
    public MyButton(String label) {
        super(label);
        setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
    }
}
