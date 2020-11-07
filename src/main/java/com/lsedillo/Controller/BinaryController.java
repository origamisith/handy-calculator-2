package com.lsedillo.Controller;
import com.lsedillo.Model.*;
public class BinaryController {
    String[] tokens;
    BinaryController(String[] tokens) {
       this.tokens = tokens;
    }
    public String parse() {
        /*
        Binary b1 = new Binary(tokens[2]);
        Binary b2 = new Binary(tokens[3]);
        Decimal d1 = b1.toDecimal();
        Decimal d2 = b2.toDecimal();
        yield switch(tokens[1]) {
            case "+" -> (new Decimal(d1.getValue() + d2.getValue())).toBinary().toString();
            case "-" ->(new Decimal(d1.getValue() - d2.getValue())).toBinary().toString();
            case "*" -> (new Decimal(d1.getValue() * d2.getValue())).toBinary().toString();
            case "/" -> {
                String q = (new Decimal(d1.getValue() / d2.getValue())).toBinary().toString();
                String r = (new Decimal(d1.getValue() % d2.getValue())).toBinary().toString();
                yield q + " Remainder: " + r;
            }
            default -> "Invalid operator.";
        };

         */
        return "";
    }


}
