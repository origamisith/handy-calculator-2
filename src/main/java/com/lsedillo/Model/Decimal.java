package com.lsedillo.Model;

import java.util.ArrayList;

public class Decimal extends Number{
    public static String name = "Decimal";
    /**
     * Constructor that takes a string
     * @param valueString The value to pass in
     */
    public Decimal(String valueString) {
        super(valueString);
        //this.value = Long.parseLong(valueString);
    }

    /**
     * Implemented for the sake of uniformity. Returns a clone of itself
     * @return A Decimal object
     */
    public Decimal toDecimal() {return new Decimal(getValueString());}

    /**
     * Converts to hexadecimal. Uses the division and modulo method which is used for converting
     * any value from decimal to another base
     * @return A Hexadecimal object
     */
    public Hexadecimal toHexadecimal() {
        ArrayList<Byte> digits = new ArrayList<>(16);
        long q;
        byte r;
        long whatsLeft = getValue();
        do {
            q = whatsLeft / 16;
            r = (byte) (whatsLeft % 16);
            digits.add(r);
            whatsLeft = q;
        } while (whatsLeft != 0);
        return new Hexadecimal(digits);
    }
    /**
     * Converts to binary. Uses the division and modulo method which is used for converting
     * any value from decimal to another base
     * @return A binary objects
     */
    public Binary toBinary() {
        StringBuilder valueBuilder = new StringBuilder();
        //ArrayList<Boolean> digits = new ArrayList<>(16);
        long q;
        int r;
        long whatsLeft = getValue();
        do {
            q = whatsLeft / 2;
            r = (int) whatsLeft % 2;
            valueBuilder.append(r);
            whatsLeft = q;
        } while (whatsLeft != 0);
        return new Binary(valueBuilder.toString());
    }

//    public static void main(String[] args) {
//        Decimal d = new Decimal(2123923824);
//        System.out.println(d.toBinary());
//        System.out.println(Long.toBinaryString(d.value));
//    }
}
