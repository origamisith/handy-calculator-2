package com.lsedillo.View;

import com.lsedillo.Controller.MainController;
import com.lsedillo.Controller.NumberController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathPanel extends JPanel implements DocumentListener, ActionListener
{
    JLabel answer;
    JTextArea textArea;
    JComboBox<String> chooseToBase;
    String mode;
    String toBase;
    public static final String BINARY = "Binary";
    public static final String DECIMAL = "Decimal";
    public static final String HEXADECIMAL = "Hexadecimal";

    private String input;
    public MathPanel() {
        input = "1100+1010";

        mode = BINARY;
        toBase = BINARY;
        setAlignmentX(JPanel.LEFT_ALIGNMENT);
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridy = 1;
        c.gridheight = 2;
        add(new MathBar(this), c);


        c.weightx = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.insets = new Insets(100, 0, 0, 0);
        textArea = new JTextArea(input, 2, 20);
        textArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
        textArea.getDocument().addDocumentListener(this);
        add(textArea, c);

        MyButton clear = new MyButton("clear");
        clear.addActionListener(e->clear());
        c.gridx = 2;
        add(clear, c);

        c.gridx=3;
        String[] bases = {BINARY, DECIMAL, HEXADECIMAL};
        chooseToBase = new JComboBox<String>(bases);
        chooseToBase.setSelectedItem(toBase);
        chooseToBase.addActionListener(this);
        add(chooseToBase, c);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        answer = new MyLabel("");
        add(answer, c);
    }

    private void clear() {
        textArea.setText("");
        input = "";
        chooseToBase.setSelectedItem(mode);
    }
    public void setMode(String mode) {
        this.mode = mode;
        setToBase(mode);
        chooseToBase.setSelectedItem(toBase);
    }

    public void setToBase(String toBase) {
        this.toBase = toBase;
//        parseInput();
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        parseInput();
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        parseInput();
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        parseInput();
    }
     public void parseInput() {
//        SwingUtilities.invokeLater(()-> {
            String result = "Please Enter Command";
            input = textArea.getText();
            String decOperationPattern = "[0-9]+[*\\/\\-+][0-9]+";
            String hexOperationPattern = "[0-9,a-f,A-F]+[*\\/\\-+][0-9,a-f, A-F]+";
            String binOperationPattern = "[0,1]+[*\\/\\-+][0,1]+";
            String decConversionPattern = "[0-9]+";
            String hexConversionPattern = "[0-9,a-f,A-F]+";
            String binConversionPattern = "[0,1]+";

            if(mode == DECIMAL &&input.matches(decOperationPattern )
                    || mode == HEXADECIMAL && input.matches(hexOperationPattern)
                    || mode == BINARY && input.matches(binOperationPattern)) {
                String charPattern = "[*\\/\\-+]";
                String first = input.split(charPattern)[0];
                String second = input.split(charPattern)[1];
                Matcher m = Pattern.compile(charPattern).matcher(input);
                int index = 0;
                while(m.find()) {
                    index = m.start();
                }
                String operator = "" + input.charAt(index);
                String unconverted = NumberController.operation(mode, operator, first, second);
                if(toBase.equalsIgnoreCase(mode)) result = unconverted;
                else result = NumberController.convertBase(mode, toBase, unconverted);
            }


            else if(mode == DECIMAL && input.matches(decConversionPattern)
                    || mode == HEXADECIMAL && input.matches(hexConversionPattern)
                    || mode == BINARY && input.matches(binConversionPattern)) {
                result = NumberController.convertBase(mode, toBase, input);
                int x = 5;
                for(int i = 0; i < 100000; i++) {
                   x=i;
                }
//                System.out.println("I don't know why i need this but the program breaks without it. No, seriously, I'm talking about this actual println line");
            }

            answer.setText(result);
//        });
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JComboBox cb = (JComboBox)actionEvent.getSource();
        toBase = (String)cb.getSelectedItem();
//        SwingUtilities.invokeLater(this::parseInput);
        parseInput();
    }
}
