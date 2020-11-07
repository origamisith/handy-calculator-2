package com.lsedillo.Model;

public abstract class Number implements NumberConvertible{
    private long value;
    private final String valueString;
    private int length;
    public static String name = "";

    public Number(String valueString) {
        this.valueString = valueString;
        this.length = valueString.length();
        this.value = toDecimal().getValue();
    }
    public Number(long value) {
        this.value = value;
        this.valueString = value + "";

        switch(name) {
            case "Binary": return
            case "Hexadecimal": return toHexadecimal();
            default: return toDecimal();
        }
    }

    public Number toSelf() {
        switch(name) {
            case "Binary": return toBinary();
            case "Hexadecimal": return toHexadecimal();
            default: return toDecimal();
        }
    }


    public long getValue() {return value;}
    public String getValueString() {return valueString;}
    public int getLength() {return length;}
    public String toString() {return valueString;}
}
