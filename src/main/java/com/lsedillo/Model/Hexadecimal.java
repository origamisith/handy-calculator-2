package com.lsedillo.Model;

import java.util.ArrayList;

public class Hexadecimal {
    private final ArrayList<Byte> digits;
    private final int length;
    private String digitsString;

    /**
     * Constructor that takes a string and converts it to an ArrayList of Bytes
     *
     * @param digitsString The string gathered from user input
     */
    public Hexadecimal(String digitsString) {
        length = digitsString.length();
        digits = new ArrayList<>(length);
        this.digitsString = digitsString.toLowerCase();
        makeDigits();
    }

    /**
     * Constructor that takes an ArrayList of Bytes
     *
     * @param digits an ArrayList of Bytes
     */
    public Hexadecimal(ArrayList<Byte> digits) {
        this.digits = digits;
        length = digits.size();
        makeDigitsString();
    }

    /**
     * Changes the arraylist of bytes (which stores each digit in base 10) to a string
     * representing the number in base 16 with 1-9 and A-F
     */

    private void makeDigitsString() {
        char[] result = new char[length];
        for (int i = length - 1; i >= 0; i--) {
            char digit = 'z';
            Byte b = digits.get(length - i - 1);
            switch (b) {
                case 10 -> digit = 'A';
                case 11 -> digit = 'B';
                case 12 -> digit = 'C';
                case 13 -> digit = 'D';
                case 14 -> digit = 'E';
                case 15 -> digit = 'F';
            }
            if (digit == 'z') digit = Byte.toString(b).charAt(0);
            result[i] = digit;
        }
        digitsString = new String(result);
    }

    /**
     * Converts a string representation of a hexadecimal number to a representation
     * where a decimal number stores each digit.
     */
    private void makeDigits() {
        for (int i = length - 1; i >= 0; i--) {
            String digitString = digitsString.substring(i, i + 1);
            byte digit = -1;
            switch (digitString) {
                case "a" -> digit = 10;
                case "b" -> digit = 11;
                case "c" -> digit = 12;
                case "d" -> digit = 13;
                case "e" -> digit = 14;
                case "f" -> digit = 15;
            }
            if (digit == -1) digit = Byte.parseByte(digitString);
            digits.add(digit);
        }
    }

    /**
     * Convert to decimal, adding up a total based on the value and place of the hex digit
     * @return A Decimal object
     */
    public Decimal toDecimal() {
        long result = 0;
        for (int i = 0; i < length; i++) {
            result += Math.pow(16, i) * digits.get(i);
        }
        return new Decimal(result);
    }

    /**
     * Simple toString, based on the traditional 1-9, A-F representation
     * @return A hex string
     */
    public String toString() {
        return digitsString.toUpperCase();
    }
//    public static void main(String[] args) {
//        Hexadecimal hex = new Hexadecimal("FFFF");
//        Decimal dec = new Decimal("1");
//        System.out.println(hex);
//        System.out.println(hex.toDecimal());
//        System.out.println(dec.toHexadecimal());
//    }
}

