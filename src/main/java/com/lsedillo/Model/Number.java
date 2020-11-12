package com.lsedillo.Model;

public class Number{
    public static final int BINARY = 2;
    public static final int DECIMAL = 10;
    public static final int HEXADECIMAL = 16;
    private static final String DIGITS= "0123456789ABCDEF";

    private final String valueString;
    private final long decValue;
    private final int length;
    private final int base;


    public Number(String valueString, int base) {
        this.valueString = valueString.toUpperCase();
        this.length = valueString.length();
        this.base = base;
        this.decValue = toDecimalLong();
    }

    public Number(String valueString, String base) {
        this.valueString = valueString.toUpperCase();
        this.length = valueString.length();
        this.base = parseBase(base);
        this.decValue = toDecimalLong();
    }
    public Number(String valueString) {
        this(valueString, 10);
    }

    public Number(long value) {
        this(value + "", 10);
    }


    public Number operation(char operation, Number other) {
        Number decimalAnswer;
        decimalAnswer = switch (operation) {
            case '+' -> new Number(this.decValue + other.decValue);
            case '-' -> new Number(this.decValue - other.decValue);
            case '*' -> new Number(this.decValue * other.decValue);
            case '/' -> new Number(this.decValue / other.decValue);
            case '%' -> new Number(this.decValue % other.decValue);
            default -> new Number(-1);
        };
        return decimalAnswer.toBase(this.base);
    }
    public long toDecimalLong() {
        long result = 0;
        for (int i = 0; i < getLength(); i++) {
            double decimalPlace = Math.pow(base, i);
            int binDigit = DIGITS.indexOf(valueString.charAt(getLength()-i-1));
            result += Math.pow(base, i) * DIGITS.indexOf(valueString.charAt(getLength() - i - 1));
        }
        return result;
    }

    public Number toBase(int base) {
        Number decimal = new Number(decValue);
        if(base == DECIMAL) return decimal;
        long q;
        long r;
        long whatsLeft = decimal.getDecValue();
        StringBuilder sb = new StringBuilder();
        do {
            q = whatsLeft / base;
            r = whatsLeft % base;
            sb.insert(0, DIGITS.charAt((int)r));
            whatsLeft = q;
        } while (whatsLeft != 0);
        return new Number(sb.toString(), base);
    }

    public Number toBase(String base) {
       return toBase(parseBase(base));
    }

    private int parseBase(String base) {
        return switch(base.toLowerCase()) {
            case "hexadecimal" -> HEXADECIMAL;
            case "binary" -> BINARY;
            default -> DECIMAL;
        };
    }
    public long getDecValue() {return decValue;}

    public int getLength() {return length;}

    public String toString() {return valueString;}

    public String getValueString() {return valueString;}
}
