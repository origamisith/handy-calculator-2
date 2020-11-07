package com.lsedillo.Model;

public abstract class Number implements NumberConvertible{
    private long value;
    private final String valueString;
    private int length;

    public Number(String valueString) {
        this.valueString = valueString;
        this.length = valueString.length();
        this.value = toDecimal().getValue();
    }

    public long getValue() {return value;}
    public String getValueString() {return valueString;}
    public int getLength() {return length;}
    public String toString() {return valueString;}
}
