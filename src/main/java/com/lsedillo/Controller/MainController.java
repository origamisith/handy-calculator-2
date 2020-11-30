package com.lsedillo.Controller;

import java.util.Arrays;

import com.lsedillo.Model.Data;
import com.lsedillo.Model.Number;
import com.lsedillo.Model.Rate;
import com.lsedillo.Model.Time;
import com.lsedillo.View.*;


public class MainController {

    /**
     * This method takes a line of input, converts it to lowercase, and sends it to either the <code>calculate</code>
     * method or the <code>convert</code> method depending on the first word of the line.
     * @param line The line of user input
     * @return The result from the <code>calculate</code> or <code>convert</code> method, which will be the result of
     * the calculation
     */
    public static String chooseMethod(String line) {
        String[] tokens = line/*.toLowerCase()*/.split(" ");
        if (tokens[0].equalsIgnoreCase("calculate")) return calculate(tokens);
        if (tokens[0].equalsIgnoreCase("convert")) return convert(tokens);
        else return CommandLine.ANSI_RED + "Invalid instruction \"" + tokens[0] + "\".";
    }

    /**
     * Passes along the tokens to the appropriate controller method and returns the result
     * @param tokens The tokenized user input
     * @return The result from whatever method is called.
     */
    private static String calculate(String[] tokens) {
        return switch (tokens[1].toLowerCase()) {
            case "binary", "decimal", "hexadecimal" -> NumberController.operation(tokens);
            case "download/upload" -> BandwidthController.downUpTime(tokens);
            case "website" -> BandwidthController.websiteBandwidth(tokens);
            default -> CommandLine.ANSI_RED + "Could not calculate \"" + tokens[1] + "\": invalid syntax.";
        };
    }

    /**
     * Passes along the tokens to the appropriate controller method and returns the result
     * @param tokens The tokens passed down from the parent method
     * @return The result from whichever method is called.
     */
    private static String convert(String[] tokens) {
        return switch(tokens[1].toLowerCase()) {
            case "binary", "decimal", "hexadecimal" -> NumberController.convertBase(tokens);
            case "data" -> UnitController.convertData(tokens);
            case "monthly" -> UnitController.convertMonthlyToBandwidth(tokens);
            default -> CommandLine.ANSI_RED + "Could not convert; unknown keyword \"" + tokens[1] + "\".";
        };
    }
}
