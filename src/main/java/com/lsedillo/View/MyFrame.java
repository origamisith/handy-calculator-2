package com.lsedillo.View;

import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Formatter;

public class MyFrame extends JFrame{
    public DataPanel dataPanel;

    public MyFrame(String title) {
        super(title);
//        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        EventQueue.invokeLater(()->{

            add(new MenuBar(this), BorderLayout.NORTH);

            var mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
            mainPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
            JPanel grid = new JPanel(new GridLayout(1,2));
            grid.add(new MathPanel());
            mainPanel.add(grid);
            mainPanel.add(new DataPanel(this));

            add(mainPanel);
            pack();
            setVisible(true);
        });
    }

    @Override
    public void setVisible(boolean b) {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        setSize(new Dimension(screenSize.width/2, screenSize.height/2));
        setLocation (screenSize.width/2-getSize().width/2,
                screenSize.height/2-getSize().height/2);
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(b);
    }
}
