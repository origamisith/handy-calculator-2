package com.lsedillo.View;

import com.lsedillo.Controller.MainController;
import com.lsedillo.Controller.NumberController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.swing.text.StyleConstants.Size;

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
        c.gridy = 0;
        c.gridheight = 5;
        c.ipady=50;
        c.insets = new Insets(0,5,0,45);
        add(new MathBar(this), c);
        c.weightx = 0;

        c.ipady  = 0;
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.insets = new Insets(50, 0, 0, 0);
        textArea = new JTextArea(input, 1, 20);
        textArea.setMaximumSize(new Dimension(50, 40));
        textArea.setLineWrap(true);
        textArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 30));
        textArea.getDocument().addDocumentListener(this);
        add(textArea, c);

//        c.insets = new Insets(0,0,0,0);
        c.fill=GridBagConstraints.NONE;
        c.gridy=0;
        c.weightx = 0.0;
        c.gridx=4;
        MyButton clear = new MyButton("Clear");
        clear.setPreferredSize(new Dimension(100, textArea.getPreferredSize().height));
        clear.addActionListener(e->clear());
        add(clear, c);

        c.gridx = 7;
        c.insets = new Insets(50,0,0,25);
        add(new MyLabel("to", 30),c);

        c.gridx=10;
        String[] bases = {BINARY, DECIMAL, HEXADECIMAL};
        chooseToBase = new JComboBox<String>(bases);
        chooseToBase.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        chooseToBase.setSelectedItem(toBase);
        chooseToBase.addActionListener(this);
        add(chooseToBase, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        answer = new MyLabel("", 30);
        System.out.println(textArea.getPreferredSize().width);
        answer.setPreferredSize(new Dimension(new Dimension(textArea.getPreferredSize().width, 40)));
//        answer.set
        add(answer, c);

        c.gridx = 3;
        var copyBtn = new MyButton("Copy");
        copyBtn.addActionListener(a->{
            Toolkit.getDefaultToolkit().getSystemClipboard()
                    .setContents(new StringSelection(answer.getText().split(" ")[1]), null);
        });
        add(copyBtn,c);

        parseInput();
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
        parseInput();
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
            String result = "¯\\_(ツ)_/¯";
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
//                int x = 5;
//                for(int i = 0; i < 100000; i++) {
//                   x=i;
//                }
//                System.out.println("I don't know why i need this but the program breaks without it. No, seriously, I'm talking about this actual println line");
            }

            answer.setText("Result: " + result);
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
