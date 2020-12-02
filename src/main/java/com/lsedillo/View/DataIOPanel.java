package com.lsedillo.View;

import javax.swing.*;
import java.awt.*;

public class DataIOPanel extends JPanel {
    JPanel data;
    JPanel rate;
    JPanel time;
    JPanel web;
    JPanel currentInput;
    private String mode;
    JPanel answerPanel;
    MyLabel answer;
    DataPanel parent;
    public static final String[] DATAUNITS = {"bits (b)", "kilobits (kb)", "megabit (mb)","gigabit (gb)","terabit (tb)",
            "Bytes (B)", "Kilobytes (KB)", "Megabytes (MB)", "Gigabytes (GB)", "Terabytes (TB)"};
    public static final String[] RATEUNITS = new String[10];

    public DataIOPanel(DataPanel parent) {
        this.parent = parent;
        for(int i = 0; i < 10;i++) {
            if (i < 5) RATEUNITS[i] = DATAUNITS[i].split(" ")[0] + "/s";
            else RATEUNITS[i] = DATAUNITS[i].split(" ")[0] + "/month";
        }
        answerPanel = new JPanel();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//        setLayout(new FlowLayout(FlowLayout.CENTER));
        data = new ConvertDataInput();
        rate = new ConvertRateInput();
        time = new CalculateTimeInput();
        web = new CalculateWebInput();

        currentInput = data;
        mode = DataPanel.DATA;
        add(currentInput, BorderLayout.NORTH);
    }


    public void changeMode(String mode) {
        if(this.mode.equals(mode)) return;
        this.mode = mode;

        removeAll();
        currentInput = switch(mode) {
            case DataPanel.RATE ->rate;
            case DataPanel.TIME-> time;
            case DataPanel.WEB -> web;
            default -> data;
        };

        add(currentInput, BorderLayout.NORTH);
        add(answerPanel);
        invalidate();
        validate();
    }

}
