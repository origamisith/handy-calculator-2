package com.lsedillo.View;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
    public DataPanel(MyFrame frame) {
        add(new DataMenu(frame), BorderLayout.WEST);

    }

    public void refresh(String mode) {

    }
}
