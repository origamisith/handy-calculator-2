package com.lsedillo.Model;

import java.util.ArrayList;

public class Hexadecimal extends Number{
     public static String name = "Hexadeicmal";
    /**
     * Constructor that takes a string and converts it to an ArrayList of Bytes
     *
     * @param digitsString The string gathered from user input
     */
    public Hexadecimal(String digitsString) {
        super(digitsString);
    }

    /**
     * Converts a string representation of a hexadecimal number to a representation
     * where a decimal number stores each digit.
     */
    private long charToLong(char c) {
        long digit = -1;
        switch (c) {
            case 'a' -> digit = 10;
            case 'b' -> digit = 11;
            case 'c' -> digit = 12;
            case 'd' -> digit = 13;
            case 'e' -> digit = 14;
            case 'f' -> digit = 15;
        }
        if (Character.isDigit(c)) digit = Long.parseLong(c+"");
        return digit;
    }

    /**
     * Convert to decimal, adding up a total based on the value and place of the hex digit
     * @return A Decimal object
     */
    public Decimal toDecimal() {
        long result = 0;
        for (int i = 0; i < getLength(); i++) {
            result += Math.pow(16, i) * digits.get(i);
        }
        return new Decimal(result);
    }

    public Hexadecimal toHexadecimal() {
        return new Hexadecimal(getValueString());
    }

    public Binary toBinary() {
        return toDecimal().toBinary();
    }
//    public static void main(String[] args) {
//        Hexadecimal hex = new Hexadecimal("FFFF");
//        Decimal dec = new Decimal("1");
//        System.out.println(hex);
//        System.out.println(hex.toDecimal());
//        System.out.println(dec.toHexadecimal());
//    }
}

