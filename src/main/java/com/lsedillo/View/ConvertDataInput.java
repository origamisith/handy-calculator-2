package com.lsedillo.View;

import com.lsedillo.Controller.UnitController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.lsedillo.View.DataIOPanel.DATAUNITS;

public class ConvertDataInput extends JPanel implements ActionListener, DocumentListener {
    private String amount;
    private String currentUnit;
    private JPanel answer;
    private JTextField amountField;

    public ConvertDataInput(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        amount = "350";
        currentUnit = "Megabytes (MB)";

        this.answer = new JPanel();
        answer.setLayout(new GridLayout(2,5));
        answer.setPreferredSize(new Dimension(700,100));
        for(int i = 0; i < 10; i++) {
            answer.add(new MyLabel("", 18));
        }


        Panel input = new Panel();
        input.setLayout(new FlowLayout(FlowLayout.LEFT));

        amountField = new JTextField(amount, 10);
        amountField.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        amountField.getDocument().addDocumentListener(this);

        JComboBox unitChoose = new JComboBox(DATAUNITS);
        unitChoose.setFont(new Font(Font.DIALOG, Font.PLAIN,25));
        unitChoose.setSelectedItem("Megabytes (MB)");
        unitChoose.addActionListener(this);

        parseInput();
        input.add(amountField);
        input.add(unitChoose);
        input.add(new MyLabel("is equivalent to"));

        MyButton clear =new MyButton("Clear");
        input.add(clear);
        clear.addActionListener((a) ->{
            amountField.setText("");
            parseInput();
        });
        add(input);
        add(answer);
    }

    public void parseInput() {
        String numPattern = "[0-9]+";
        amount = amountField.getText();

        if(amount.matches(numPattern)) {

            String converted;
            for(int i = 0; i < 10; i++) {
                converted = UnitController
                        .convertData2(currentUnit.split(" ")[0], amount,DATAUNITS[i].split(" ")[0]);
                MyLabel answerLabel = (MyLabel)answer.getComponent(i);
                answerLabel.setText(converted);
            }
        }
        else {
            for(int i = 0; i < 10; i++) {
                MyLabel answerLabel = (MyLabel)answer.getComponent(i);
                answerLabel.setText("¯\\_(ツ)_/¯");
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JComboBox) {
            JComboBox cb = (JComboBox) e.getSource();
            currentUnit = (String)cb.getSelectedItem();
        }

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
        System.out.println("Changed");
    }

}
