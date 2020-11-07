package com.lsedillo.Model;

import java.util.ArrayList;

public class Binary extends Number{

    public static String name = "Binary";

    /**
     * Creates a Binary object from an input string of 1's and 0's
     *
     * @param valueString The input binary string
     */
    public Binary(String valueString) {
        super(valueString);
    }

    /**
     * Constructor given a pre-existing arraylist of booleans.
     * @param digits An ArrayList of digits to construct the Binary object with
     */
    /*
    public Binary(ArrayList<Boolean> digits) {
        this.digits = digits;
        this.getLength() = digits.size();
    }

     */

    public Binary toBinary() {return new Binary(getValueString());}
    public Hexadecimal toHexadecimal() {return toDecimal().toHexadecimal();}

    /**
     * Converts to decimal by summing up the values of all the bits times their relative positions.
     * @return  A Decimal object
     */
    public Decimal toDecimal() {
        long result = 0;
        for (int i = 0; i < getLength(); i++) {
            result += Math.pow(2, i) * Integer.parseInt(getValueString().substring(i, i+1));
        }
        return new Decimal(result);
    }

    /**
     * Populates the arraylist by filling it with T and F
     * @param digitString The 1's and 0's of the binary number, in String form
     */
    /*
    private void makeDigits(String digitString) {
        for (int i = getLength() - 1; i >= 0; i--) digits.add(digitString.charAt(i) != '0');
    }
    */
    /**
     * If a digit is true, a 1 is added to the string; if it's false, a 0 is added to the string
     * @return The binary number represented as 0's and 1's in a String
     */
    public String makeValueString(Long value) {
        char[] chars = new char[getLength()];
        for (int i = 0; i < getLength(); i++) chars[i] = (digits.get(getLength() - i - 1) ? '1' : '0');
        return new String(chars);
    }
    public String toString() {
        return getValueString();
        /*
        char[] chars = new char[getLength()];
        for (int i = 0; i < getLength(); i++) chars[i] = (digits.get(getLength() - i - 1) ? '1' : '0');
        return new String(chars);
         */
    }


//    public static void main(String[] args) {
//        Binary bin = new Binary("1101100110101010101111010101");
//        Decimal dec = new com.lsedillo.Decimal(1415);
//        System.out.println(bin);
//        System.out.println(bin.toDecimal());
//        System.out.println(dec.toBinary());
//    }
}
