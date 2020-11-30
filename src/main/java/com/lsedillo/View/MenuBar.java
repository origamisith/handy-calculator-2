package com.lsedillo.View;

import com.lsedillo.Controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;

public class MenuBar extends JPanel implements ActionListener {
    JButton parseFileBtn;
    JButton helpBtn;
    JFrame frame;
    public MenuBar(JFrame frame) {
        this.frame = frame;
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        //Choose input file to parse
        parseFileBtn = new MyButton("Parse File");
        parseFileBtn.addActionListener(this);

        //Help for the input file syntax
        helpBtn = new MyButton("?");
        helpBtn.setToolTipText("Input File Syntax");
        helpBtn.addActionListener(this);

        add(parseFileBtn);
        add(helpBtn);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == parseFileBtn) {
            var fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(this);
            StringBuilder sb = new StringBuilder();
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    Scanner fileScan = new Scanner(file);
                    while(fileScan.hasNext()) {
                        String line = fileScan.nextLine();
                        if(!line.startsWith("//"))
                            sb.append(line).append(": ").append(MainController.chooseMethod(line)).append("\n");
                    }
                } catch(Exception exception) {
                    sb.append("File not found");
                }
                JOptionPane.showMessageDialog(frame, sb.toString(), "Result", JOptionPane.PLAIN_MESSAGE);
            }
        }

        if(actionEvent.getSource() == helpBtn) {
            StringBuilder sb = new StringBuilder();
            try {
                Scanner s = new Scanner(Path.of("src/main/java/com/lsedillo/View/Help"), StandardCharsets.UTF_8);
                while(s.hasNextLine()) {
                    sb.append(s.nextLine()).append("\n");
                }
            } catch( IOException exception) {
                if(exception instanceof NoSuchFileException) sb.append("Help file not found: " + exception);
                else sb.append("Error: " + exception);
            }
            JOptionPane.showMessageDialog(frame, sb.toString(), "Input File Syntax", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
