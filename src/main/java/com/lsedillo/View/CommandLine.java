package com.lsedillo.View;

import com.lsedillo.Controller.MainController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class CommandLine {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    static void mainLoop() {

        Scanner s = new Scanner(System.in);
        System.out.print(ANSI_BLUE + "Welcome to the CS Students Handy Calculator! ");
        System.out.println("Do you have a file to read from? (y/N)" + ANSI_RESET);
        String input = s.nextLine();
        if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            System.out.println(ANSI_BLUE + "Please provide the absolute path to your file" + ANSI_RESET);
            input = s.nextLine();
            try {
                Scanner file = new Scanner(Path.of(input), StandardCharsets.UTF_8);
                System.out.println(input);
                while(file.hasNext()) {
                    String line = file.nextLine();
                    if(!line.startsWith("//"))
                        System.out.println(line + ": " + ANSI_GREEN + MainController.chooseMethod(line)+ ANSI_RESET);
                }
            } catch( IOException e) {
                System.out.println(ANSI_RED + "File does not exist: " + e + ANSI_RESET);
            }
        }
        while (true) {
            System.out.println(ANSI_BLUE + "Enter a command. Type 'help' if needed" + ANSI_RESET);
            input = s.nextLine();
            if(input.equalsIgnoreCase("help")) {
                help();
                System.out.println(ANSI_BLUE + "Enter a command. Type 'help' if needed" + ANSI_RESET);
                input = s.nextLine();
            }
            if(input.equalsIgnoreCase("quit")) break;
            System.out.println(ANSI_GREEN + MainController.chooseMethod(input.toLowerCase()) + ANSI_RESET);
        }


    }

    static void help() {
        try {
            Scanner s = new Scanner(Path.of("src/main/java/com/lsedillo/View/Help"), StandardCharsets.UTF_8);
            while(s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch( IOException e) {
            System.out.println("File does not exist: " + e);
        }
    }
}
