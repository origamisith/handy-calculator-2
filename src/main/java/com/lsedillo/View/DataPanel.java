package com.lsedillo.View;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
    public static final String DATA = "Data";
    public static final String RATE = "Rate";
    public static final String TIME = "Time";
    public static final String WEB = "Web";

    private String mode;
    private DataIOPanel input;
    private DataMenu dataMenu;

    public DataPanel(MyFrame frame) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        input = new DataIOPanel(this);
        dataMenu = new DataMenu(this);
        add(new DataMenu(this));
        add(input);
    }

    public void setMode(String mode) {
        this.mode = mode;
        input.changeMode(mode);
    }
}
