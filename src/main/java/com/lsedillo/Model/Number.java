package com.lsedillo.Model;

public class Number{
    public static final int BINARY = 2;
    public static final int DECIMAL = 10;
    public static final int HEXADECIMAL = 16;
    private static final String DIGITS= "0123456789ABCDEF";

    private final String valueString;
    private final long value;
    private final int length;
    private int base;

    public Number(String valueString) {
        this.valueString = valueString;
        this.length = valueString.length();
        this.value = toDecimal().getValue();
        this.base = DECIMAL;
    }

    public Number(String valueString, int base) {
        this(valueString);
        this.base = base;
    }

    public Number(String valueString, String base) {
        this(valueString);
        this.base = switch(base.toLowerCase()) {
            case "hexadecimal" -> HEXADECIMAL;
            case "binary" -> BINARY;
            default -> DECIMAL;
        };

    }

    public Number(long value) {
       this.value = value;
       this.valueString = value + "";
       this.length = valueString.length();
       this.base = DECIMAL;
    }


    public Number operation(char operation, Number other) {
        Number decimalAnswer;
        decimalAnswer = switch (operation) {
            case '+' -> new Number(this.value + other.value);
            case '-' -> new Number(this.value - other.value);
            case '*' -> new Number(this.value * other.value);
            case '/' -> new Number(this.value / other.value);
            case '%' -> new Number(this.value % other.value);
            default -> new Number(-1);
        };
        return decimalAnswer.toBase(this.base);
    }
    public Number toDecimal() {
        long result = 0;
        for (int i = 0; i < getLength(); i++) {
            result += Math.pow(base, i) * DIGITS.indexOf(valueString.charAt(getLength() - i - 1));
        }
        return new Number(result);
    }

    public Number toBase(int base) {
        Number decimal = toDecimal();
        if(base == DECIMAL) return decimal;
        long q;
        long r;
        long whatsLeft = decimal.getValue();
        StringBuilder sb = new StringBuilder();
        do {
            q = whatsLeft / base;
            r = whatsLeft % base;
            sb.insert(0, DIGITS.charAt((int)r));
            whatsLeft = q;
        } while (whatsLeft != 0);
        return new Number(sb.toString(), base);
    }

    public long getValue() {return value;}

    public int getLength() {return length;}

    public String toString() {return valueString;}

    public String getValueString() {return valueString;}
}
