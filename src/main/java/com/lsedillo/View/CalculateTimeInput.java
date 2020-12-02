package com.lsedillo.View;

import com.lsedillo.Controller.BandwidthController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static com.lsedillo.View.DataIOPanel.RATEUNITS;
import static com.lsedillo.View.DataIOPanel.DATAUNITS;

public class CalculateTimeInput extends JPanel implements ActionListener, DocumentListener {
    private String dataAmount;
    private String rateAmount;
    private JTextField dataAmountField;
    private JTextField rateAmountField;
    private JComboBox dataCombo;
    private JComboBox rateCombo;
    private JPanel answerPanel;
    private MyLabel answerLabel;

    public CalculateTimeInput() {
        setLayout(new GridLayout(2,1));
        Panel input = new Panel();
        input.setLayout(new FlowLayout(FlowLayout.LEFT));

        answerPanel = new JPanel();
        answerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        answerLabel = new MyLabel("", 20);

        dataAmount = "500";
        rateAmount = "367";

        dataAmountField = new JTextField(dataAmount);
        dataAmountField.setColumns(4);
        dataAmountField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        dataAmountField.getDocument().addDocumentListener(this);

        rateAmountField = new JTextField(rateAmount);
        rateAmountField.setColumns(4);
        rateAmountField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        rateAmountField.getDocument().addDocumentListener(this);

        dataCombo = new JComboBox(Arrays.copyOfRange(DATAUNITS, 5,10));
        dataCombo.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        dataCombo.addActionListener(this);

        rateCombo= new JComboBox(Arrays.copyOfRange(RATEUNITS, 0,5));
        rateCombo.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        rateCombo.addActionListener(this);

        input.add(dataAmountField);
        input.add(dataCombo);
        input.add(new MyLabel(" at ", 18));
        input.add(rateAmountField);
        input.add(rateCombo);
        MyButton clear =new MyButton("Clear");
        input.add(clear);
        clear.addActionListener((a) ->{
            rateAmountField.setText("");
            dataAmountField.setText("");
            parseInput();
        });
        add(input);

        answerPanel.add(answerLabel);
        add(answerPanel);
        parseInput();
    }

    public void parseInput() {
        String numPattern = "[0-9]+";
        String result;
        dataAmount = dataAmountField.getText();
        rateAmount = rateAmountField.getText();
        String dataUnit = ((String)dataCombo.getSelectedItem()).split(" ")[0];
        String rateUnit = (String)rateCombo.getSelectedItem();

        if(dataAmount.matches(numPattern) && rateAmount.matches(numPattern)) {
            result = "Download/Upload will take " + BandwidthController.downUpTime(dataAmount, dataUnit, rateAmount, rateUnit);
        }
        else result = "¯\\_(ツ)_/¯";
        answerLabel.setText(result);
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
