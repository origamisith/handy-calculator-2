package com.lsedillo.Model;

import java.util.ArrayList;

public class Decimal extends Number{
    private final long value;

    /**
     * Constructor that takes a long
     * @param value The value to pass in
     */
    public Decimal(long value) {
        this.value = value;
    }

    /**
     * Constructor that takes a string
     * @param valueString The value to pass in
     */
    public Decimal(String valueString) {
        this.value = Long.parseLong(valueString);
    }

    /**
     * Returns the value that a <code>Deicmal</code> holds as a long
     * @return long value
     */
    public long getValue() {
        return value;
    }

    public long toDecimal() {return value; }
    /**
     * Converts to hexadecimal. Uses the division and modulo method which is used for converting
     * any value from decimal to another base
     * @return A Hexadecimal object
     */
    public Hexadecimal toHexadecimal() {
        ArrayList<Byte> digits = new ArrayList<>(16);
        long q;
        byte r;
        long whatsLeft = value;
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
        ArrayList<Boolean> digits = new ArrayList<>(16);
        long q;
        int r;
        long whatsLeft = value;
        do {
            q = whatsLeft / 2;
            r = (int) whatsLeft % 2;
            digits.add(r == 1);
            whatsLeft = q;
        } while (whatsLeft != 0);
        return new Binary(digits);
    }

    /**
     * Easy implementation based on the long value
     * @return String representation of the stored long
     */
    public String toString() {
        return "" + value;
    }

//    public static void main(String[] args) {
//        Decimal d = new Decimal(2123923824);
//        System.out.println(d.toBinary());
//        System.out.println(Long.toBinaryString(d.value));
//    }
}
