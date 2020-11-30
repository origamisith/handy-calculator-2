package com.lsedillo.Model;

/**
 * This class represents all numbers as a base-10 long internally, but can output
 * Strings of base 2, 10, or 16. It also handles operations and conversions.
 * The maximum decimal value allowed is Long.MAX_VALUE.
 * The maximum binary value allowed is that which, when converted to base 10, is Long.MAX_VALUE
 * The maximum hexadecimal value allowed is that which, when converted to base 10, is Long.MAX_VALUE
 * And similarly for the minimum values.
 */
public class Number{
    public static final int BINARY = 2;
    public static final int DECIMAL = 10;
    public static final int HEXADECIMAL = 16;
    private static final String DIGITS= "0123456789ABCDEF-";

    private final String valueString;
    private final long decValue;
    private final int length;
    private final int base;
    private int sign;
    /**
     * @param valueString The value, as a string
     * @param base The base, which should be specified with Number.BASE where
     *             BASE is BINARY, DECIMAL, or HEXADECIMAL
     */
    public Number(String valueString, int base) {
        this.valueString = valueString.toUpperCase();
        this.length = valueString.length();
        this.base = base;
        this.sign = 1;
        this.decValue = toDecimalLong();
    }

    /**
     * @param valueString
     * @param base
     */
    public Number(String valueString, String base) {
        this.valueString = valueString.toUpperCase();
        this.length = valueString.length();
        this.base = parseBase(base);
        this.sign = 1;
        this.decValue = toDecimalLong();
    }
    public Number(String valueString) {
        this(valueString, 10);
    }

    public Number(long value) {
        this(value + "", 10);
    }


    /**
     * Performs one of the five operations on two Numbers. The only reason for "%" is for
     * use when the remainder from division is needed since decimal places are no supported.
     * @param operation The char representing the operator
     * @param other The other number to operate on.
     * @return  The result of the operation
     * @throws IllegalArgumentException If the operator is invalid
     */
    public Number operation(char operation, Number other) throws IllegalArgumentException, ArithmeticException {
        Number decimalAnswer;

        decimalAnswer = switch (operation) {
            case '+' -> new Number(this.decValue + other.decValue);
            case '-' -> new Number(this.decValue - other.decValue);
            case '*' -> new Number(this.decValue * other.decValue);
            case '/' -> new Number(this.decValue / other.decValue);
            case '%' -> new Number(this.decValue % other.decValue);
            default -> throw new IllegalArgumentException();
        };
        if(sign != other.sign) decimalAnswer.setSign(-1);
        return decimalAnswer.toBase(this.base);
    }
    private long toDecimalLong() throws IllegalArgumentException{
        long result = 0;
        if(base == DECIMAL) return Long.parseLong(valueString);
        for (int i = 0; i < getLength(); i++) {
            double decimalPlace = Math.pow(base, i);
            int binDigit = DIGITS.indexOf(valueString.charAt(getLength()-i-1));
            if(binDigit == 16 && i == getLength()-1) sign = -1;
            else if(base == BINARY && binDigit > 1) throw new IllegalArgumentException();
            else result += decimalPlace * binDigit;
        }
        result *=sign;
        return result;
    }

    public Number toBase(int base) {
        Number decimal = new Number(decValue);
        if(base == DECIMAL) return decimal;
        long q;
        long r;
        long whatsLeft = decimal.getDecValue();
        String tempSign = "";
        if(whatsLeft < 0) {
            whatsLeft *= -1;
            tempSign = "-";
        }
        StringBuilder sb = new StringBuilder();
        do {
            q = whatsLeft / base;
            r = whatsLeft % base;
            sb.insert(0, DIGITS.charAt((int)r));
            whatsLeft = q;
        } while (whatsLeft != 0);
        return new Number(tempSign + sb.toString(), base);
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

    public void setSign(int i) throws IllegalArgumentException{
        if(i != -1 && i != 1) throw new IllegalArgumentException();
        else sign = i;
    }
    public long getDecValue() {return decValue;}

    public int getLength() {return length;}

    public String toString() {return valueString;}

    public String getValueString() {return valueString;}
}
