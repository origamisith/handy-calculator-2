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
    String mode;
    String toBase;
    public static final String BINARY = "Binary";
    public static final String DECIMAL = "Decimal";
    public static final String HEXADECIMAL = "Hexadecimal";

    private String input;
    public MathPanel() {
        input = "1100+1010";

        setMode(BINARY);

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
        clear.addActionListener(e->{
            textArea.setText("");
            input = "";
        });
        c.gridx = 2;
        add(clear, c);

        c.gridx=3;
        String[] bases = {"To Binary", "To Decimal", "To Hexadecimal"};
        var chooseBase = new JComboBox<String>(bases);
        chooseBase.setSelectedIndex(0);
        chooseBase.addActionListener(this);
        add(chooseBase, c);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        answer = new MyLabel("");
//        answer = new MyLabel("Answer: +" + parseInput());
        add(answer, c);
        parseInput();

//        add(new JLabel("Answer"), c);
    }

    public void setMode(String mode) {
        this.mode = mode;
        this.toBase = "To " + mode;
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        Runnable parse = new Runnable() {
            @Override
            public void run() {
                parseInput();
            }
        };
        SwingUtilities.invokeLater(parse);
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        Runnable parse = new Runnable() {
            @Override
            public void run() {
                parseInput();
            }
        };
        SwingUtilities.invokeLater(parse);
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        Runnable parse = new Runnable() {
            @Override
            public void run() {
                parseInput();
            }
        };
        SwingUtilities.invokeLater(parse);
    }
    private String parseInput() {
        input = textArea.getText();
        String result = "Please Enter Command";
        String decOperationPattern = "[0-9]+[*\\/\\-+][0-9]+";
        String hexOperationPattern = "[0-9,a-f,A-F]+[*\\/\\-+][0-9,a-f, A-F]+";
        String binOperationPattern = "[0,1]+[*\\/\\-+][0,1]+";
        String decConversionPattern = "\\[0-9]+";
        String hexConversionPattern = "\\[0-9,a-f,A-F]+";
        String binConversionPattern = "\\[0,1]+";

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
            String converted;
            if(toBase.equalsIgnoreCase("To " + mode)) converted = unconverted;
            else converted = MainController.chooseMethod("Convert Decimal " + toBase + " " + unconverted);
            result= converted;
        }


        if(mode == DECIMAL && input.matches(decConversionPattern)
                || mode == HEXADECIMAL && input.matches(hexConversionPattern)
                || mode == BINARY && input.matches(binConversionPattern)) {
            result = NumberController.convertBase(mode, toBase.split(" ")[1], input);
        }

            answer.setText(result);
        return result;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JComboBox cb = (JComboBox)actionEvent.getSource();
        String base = (String)cb.getSelectedItem();
        toBase = base;
        Runnable parse = new Runnable() {
            @Override
            public void run() {
                parseInput();
            }
        };
        SwingUtilities.invokeLater(parse);
    }
}
