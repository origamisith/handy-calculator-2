package com.lsedillo.Controller;

import java.util.Arrays;
import com.lsedillo.View.*;
import com.lsedillo.Model.*;


public class MainController {

    /**
     * This method takes a line of input, converts it to lowercase, and sends it to either the <code>calculate</code>
     * method or the <code>convert</code> method depending on the first word of the line. However, when it passes
     * along the line of input, it trims off the first word since it is no longer needed
     * @param line The line of user input
     * @return The result from the <code>calculate</code> or <code>convert</code> method, which will be the result of
     * the calculation
     */
    public static String chooseMethod(String line) {
        String[] tokens = line.toLowerCase().split(" ");
        if (tokens[0].equals("calculate")) return calculate(Arrays.copyOfRange(tokens, 1, tokens.length));
        if (tokens[0].equals("convert")) return convert(Arrays.copyOfRange(tokens,1,tokens.length));
        else return "Invalid instruction.";
    }

    /**
     * This method takes in the trimmed line of user input, and, depending on the second word of input, decides
     * which specific logic to invoke. For the +,-,*,/ types of methods, the binary numbers are converted to Decimal
     * objects, the operation is performed yielding a Decimal answer, and then that Decimal answer is converted back
     * to binary. For the other methods, the raw string tokens are processed to their correct values
     * to be passed into the appropriate methods. Some of the processing involves converting the standard names
     * for data / time units into the enum names.
     * @param tokens The tokenized user input, minus the first token
     * @return The result from whatever method is called.
     */
    private static String calculate(String[] tokens) {

        return switch (tokens[0]) {
            case "binary" -> {
                /*
                Binary b1 = new Binary(tokens[2]);
                Binary b2 = new Binary(tokens[3]);
                Decimal d1 = b1.toDecimal();
                Decimal d2 = b2.toDecimal();
                yield switch(tokens[1]) {
                    case "+" -> (new Decimal(d1.getValue() + d2.getValue())).toBinary().toString();
                    case "-" ->(new Decimal(d1.getValue() - d2.getValue())).toBinary().toString();
                    case "*" -> (new Decimal(d1.getValue() * d2.getValue())).toBinary().toString();
                    case "/" -> {
                        String q = (new Decimal(d1.getValue() / d2.getValue())).toBinary().toString();
                        String r = (new Decimal(d1.getValue() % d2.getValue())).toBinary().toString();
                        yield q + " Remainder: " + r;
                    }
                    default -> "Invalid operator.";
                };

                 */
                BinaryController bcon = new BinaryController(Arrays.copyOfRange(tokens, 1,  tokens.length));
                yield bcon.parse();
            }
            case "hexadecimal" -> {
                /*
                Hexadecimal h1 = new Hexadecimal(tokens[2]);
                Hexadecimal h2 = new Hexadecimal(tokens[3]);
                Decimal d1 = h1.toDecimal();
                Decimal d2 = h2.toDecimal();
                yield switch(tokens[1]) {
                    case "+" -> (new Decimal(d1.getValue() + d2.getValue())).toHexadecimal().toString();
                    case "-" ->(new Decimal(d1.getValue() - d2.getValue())).toHexadecimal().toString();
                    case "*" -> (new Decimal(d1.getValue() * d2.getValue())).toHexadecimal().toString();
                    case "/" -> {
                        String q = (new Decimal(d1.getValue() / d2.getValue())).toHexadecimal().toString();
                        String r = (new Decimal(d1.getValue() % d2.getValue())).toHexadecimal().toString();
                        yield q + " Remainder: " + r;
                    }
                    default -> "Invalid operator.";
                };
                */
                HexController hcon = new HexController(Arrays.copyOfRange(tokens, 1,  tokens.length));
                yield hcon.parse();
            }
            case "download/upload" -> {
                double time = Double.parseDouble(tokens[2]);
                DataUnits dataUnit1 = DataUnits.valueOf(tokens[3].toUpperCase());
                double bandwidth = Double.parseDouble(tokens[4]);
                //Trimming off the "/s" from the bandwidth unit
                String dataUnitString = tokens[5].substring(0, tokens[5].indexOf('/'))+ "s";
                DataUnits dataUnit2 = DataUnits.valueOf(dataUnitString.toUpperCase());
                yield Bandwidth.downUpTime(time, dataUnit1, bandwidth, dataUnit2);
            }
            case "website" -> {
                try {
                    double views = Double.parseDouble(tokens[2]);
                    TimeUnits time = TimeUnits.valueOf(tokens[4].toUpperCase());
                    double pageSize = Double.parseDouble(tokens[5]);
                    DataUnits sizeUnit = DataUnits.valueOf(tokens[6].toUpperCase());
                    double redundancy = Double.parseDouble(tokens[7]);
                    yield Bandwidth.webBandwidth(views, time, pageSize, sizeUnit, redundancy);
                } catch(ArrayIndexOutOfBoundsException e) {
                    yield Calculator.ANSI_RED + "Error: Wrong number of arguments";
                }
            }
            default -> ("Could not calculate; invalid syntax.");
        };
    }

    /**
     * Handles conversion for binary, hexadecimal, and decimal, data, monthly usage, and bandwidth.
     * @param tokens The rest of the tokens passed down from the parent method
     * @return The result from whichever method is called.
     */
    private static String convert(String[] tokens) {
        return switch(tokens[0]) {
            case  "binary" -> {
                //(new Binary(tokens[3])).toDecimal().toString();

                yield BinaryController.convert("Bi)
            }
            case "hexadecimal" -> (new Hexadecimal(tokens[3])).toDecimal().toString();
            case "decimal" -> {
                if(tokens[2].equals("hexadecimal")) yield (new Decimal(tokens[3])).toHexadecimal().toString();
                if(tokens[2].equals("binary")) yield (new Decimal(tokens[3])).toBinary().toString();
                else yield "Cannot convert decimal to that type";
            }
            case "data" -> {
                Double dataAmount = Double.parseDouble(tokens[4]);
                String unitsString;
                //If the data type doesn't include bytes (doesn't have a 'y' in it), then it must include bits.
                //As my enum is stored in the abbreviated form for the "bit" data units (kbits, gbits, tbits),
                //I use this to trim the input such that kilobit -> kbits, gigabits -> gbits, and so on.
                if(tokens[3].indexOf('y') < 0) {
                    unitsString = tokens[3].charAt(0) + tokens[3].substring(tokens[3].indexOf('b'), tokens[3].indexOf('s')) + "s";
                }
                else unitsString = tokens[3];
                DataUnits dataUnit = DataUnits.valueOf(unitsString.toUpperCase());
                double result  = DataUnits.convert(dataAmount, DataUnits.BITS, dataUnit);
                yield result + " " + dataUnit.name;
            }
            case "monthly" -> {
                double dataSize = Double.parseDouble(tokens[4]);
                DataUnits dataUnit = DataUnits.valueOf(tokens[5].toUpperCase());
                double bandwidthSize = Double.parseDouble(tokens[6]);
                //Removing the "/s" and replacing it with "S" for my bandwidth unit
                DataUnits bandwidthUnit = DataUnits.valueOf(tokens[7].substring(0, tokens[7].indexOf('/')).toUpperCase()+ "S");
                yield Bandwidth.hostBandwidth(dataSize,dataUnit,bandwidthSize,bandwidthUnit);
            }
            default -> HandyCalculatorCLI.ANSI_RED + "Could not convert; unknown keyword " + tokens[0];
        };
    }
}
