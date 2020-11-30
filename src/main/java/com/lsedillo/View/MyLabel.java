package com.lsedillo.View;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {
    public MyLabel(String label, int fontSize) {
        super(label);
        setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize));
    }
    public MyLabel(String label) {
        this(label, 20);
    }
}
