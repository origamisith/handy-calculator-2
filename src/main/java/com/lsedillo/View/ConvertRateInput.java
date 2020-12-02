package com.lsedillo.View;

import com.lsedillo.Controller.UnitController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static com.lsedillo.View.DataIOPanel.DATAUNITS;
import static com.lsedillo.View.DataIOPanel.RATEUNITS;

public class ConvertRateInput extends JPanel implements ActionListener, DocumentListener {

    private String amount;
    private JTextField amountField;
    private JComboBox[] rateCombo = new JComboBox[2];
    private JPanel answerPanel;
    private MyButton swap;
    private MyLabel answerLabel;
    private boolean mode;
    private final boolean TOBANDWIDTH= true;
    private final boolean TOMONTHLY = false;


    public ConvertRateInput() {
//        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setLayout(new GridLayout(2,1));
        Panel input = new Panel();
        input.setLayout(new FlowLayout(FlowLayout.LEFT));
        mode = TOBANDWIDTH;
        amount = "250";
        String[] perSecond = Arrays.copyOfRange(RATEUNITS, 0, 5);
        String[] perMonth = Arrays.copyOfRange(RATEUNITS, 5, 10);

        answerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        answerLabel = new MyLabel("", 20);

        amountField = new JTextField(amount);
        amountField.setColumns(4);
        amountField.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        amountField.getDocument().addDocumentListener(this);


        JComboBox unitChoose1 = new JComboBox(perMonth);
        unitChoose1.setFont(new Font(Font.DIALOG, Font.PLAIN,18));
        unitChoose1.addActionListener(this);
        rateCombo[0] = unitChoose1;

        JComboBox unitChoose2 = new JComboBox(perSecond);
        unitChoose2.setFont(new Font(Font.DIALOG, Font.PLAIN,18));
        unitChoose2.addActionListener(this);
        rateCombo[1] = unitChoose2;

        swap = new MyButton("<->");
        swap.addActionListener((e)->{
            mode = !mode;
            JComboBox temp = rateCombo[0];
            rateCombo[0] = rateCombo[1];
            rateCombo[1] = temp;
            String oldAmount = amountField.getText();
//            input.removeAll();
            removeAll();
            input.add(amountField);
            amountField.setText(oldAmount);
            input.add(rateCombo[0]);
            input.add(swap);
            input.add(rateCombo[1]);
            parseInput();
            add(input, BorderLayout.NORTH);
            add(answerPanel);
            invalidate();
            validate();
        });


        input.add(amountField);
        input.add(unitChoose1);
        input.add(swap);
        input.add(unitChoose2);
        MyButton clear =new MyButton("Clear");
        input.add(clear);
        clear.addActionListener((a) ->{
            amountField.setText("");
            parseInput();
        });

        answerPanel.add(answerLabel);
        add(input);
        add(answerPanel);
        parseInput();
        amountField.setText(amount);
    }

    public void parseInput() {

        String numPattern = "[0-9]+";
        amount = amountField.getText();
        String result;
//        String firstSelected = ((String)(rateCombo[0].getSelectedItem())).split(" ")[0];
        int firstIndex = mode ? 0 : 1;
        int secondIndex = mode ? 1 : 0;
        String firstSelected = ((String)rateCombo[firstIndex].getSelectedItem());
        String secondSelected = ((String)rateCombo[secondIndex].getSelectedItem());

        if(amount.matches(numPattern)) {
            if(mode == TOBANDWIDTH) {
                 result = UnitController.convertMonthlyToBandwidth(amount, firstSelected.split("/")[0], secondSelected);
            }
            else {
                result = UnitController.convertMonthlyToBandwidth("0", firstSelected.split("/")[0], amount, secondSelected);
            }
        }
        else {
           result =  "¯\\_(ツ)_/¯";
        }
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
