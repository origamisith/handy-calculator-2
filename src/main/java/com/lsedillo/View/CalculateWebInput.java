package com.lsedillo.View;

import javax.swing.*;
import java.awt.*;

import com.lsedillo.Controller.BandwidthController;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static com.lsedillo.View.DataIOPanel.DATAUNITS;

public class CalculateWebInput extends JPanel implements ActionListener, DocumentListener{

    private String timeAmount;
    private String dataAmount;
    private String redundancy;
    private JTextField timeAmountField;
    private JTextField dataAmountField;
    private JTextField redundancyField;
    private JComboBox timeCombo;
    private JComboBox dataCombo;
    private JPanel answerPanel;
    private JTextArea answerArea;

    public CalculateWebInput() {
        timeAmount = "512";
        dataAmount = "698";
        redundancy = "2";

        answerPanel = new JPanel();

        setLayout(new GridLayout(2,1));
        Panel input = new Panel();
        input.setLayout(new FlowLayout(FlowLayout.LEFT));

        answerPanel = new JPanel();
        answerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        answerArea = new JTextArea(4, 55);
        answerArea.setEditable(false);
        answerArea.setBackground(new Color(238,238,238));
        answerArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
        answerArea.setLineWrap(true);


        timeAmountField = new JTextField(timeAmount);
        timeAmountField.setColumns(4);
        timeAmountField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        timeAmountField.getDocument().addDocumentListener(this);

        dataAmountField = new JTextField(dataAmount);
        dataAmountField.setColumns(4);
        dataAmountField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        dataAmountField.getDocument().addDocumentListener(this);

        redundancyField = new JTextField(redundancy);
        redundancyField.setColumns(4);
        redundancyField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        redundancyField.getDocument().addDocumentListener(this);

        String[] TIMEUNITS = {"Per Second", "Per Minute", "Per Hour",  "Per Day", "Per Month", "Per Year"};
        timeCombo = new JComboBox(TIMEUNITS);
        timeCombo.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        timeCombo.addActionListener(this);

        dataCombo = new JComboBox(Arrays.copyOfRange(DATAUNITS, 5,10));
        dataCombo.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        dataCombo.addActionListener(this);

        input.add(timeAmountField);
        input.add(timeCombo);
        input.add(dataAmountField);
        input.add(dataCombo);
        input.add(redundancyField);
        MyButton clear =new MyButton("Clear");
        input.add(clear);
        clear.addActionListener((a) ->{
            timeAmountField.setText("");
            dataAmountField.setText("");
            redundancyField.setText("");
            parseInput();
        });

        add(input);

        answerPanel.add(answerArea);
        add(answerPanel);
        parseInput();

    }
    public void parseInput() {
        String numPattern = "[0-9]+";
        String result;
        timeAmount = timeAmountField.getText();
        dataAmount = dataAmountField.getText();
        redundancy = redundancyField.getText();

        String timeUnit = ((String)timeCombo.getSelectedItem()).split(" ")[1];
        String dataUnit = ((String)dataCombo.getSelectedItem()).split(" ")[0];

        if(dataAmount.matches(numPattern) && timeAmount.matches(numPattern) && redundancy.matches(numPattern)) {
            System.out.println(timeAmount + " " + timeUnit + " " + dataAmount + " " + dataUnit + " " + redundancy);
            result = BandwidthController.websiteBandwidth(timeAmount, timeUnit, dataAmount, dataUnit, redundancy);
            System.out.println(result);
        }
        else result = "¯\\_(ツ)_/¯";
        answerArea.setText(result);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       parseInput();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        parseInput();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        parseInput();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        parseInput();
    }
}
