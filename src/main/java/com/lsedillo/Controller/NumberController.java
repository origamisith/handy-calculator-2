package com.lsedillo.Controller;

import com.lsedillo.Model.Number;

/**
 * These methods may take their arguments as either a tokenized input-file line ({Convert, Binary, to, Decimal, 1011})
 * OR as a normal set of arguments ("binary", "decimal", 1011)
 */
public class NumberController {
    static String operation(String...tokens) {
        int i = 0;
        if(tokens.length == 5) i++; //skip "Calculate"
        String base = tokens[i++];
        char op = tokens[i++].charAt(0);
        String n1 = tokens[i++];
        String n2 = tokens[i];

        Number b1 = new Number(n1, base);
        Number b2 = new Number(n2, base);
        Number result = b1.operation(op, b2);
        if (op == '/') {
            Number remainder = b1.operation('%', b2);
            return result.toString() + " Remainder: " + remainder.toString();
        }
        return result.toString();
    }

    /**
     * Because the input file syntax contains the filler word "to", I have to skip past it while
     * setting the array members to variables.
     * @param tokens Should contain the original base, [to] the new base, and the number value
     */
    static String convertBase(String...tokens) {
        int i = 0;
        if(tokens.length == 5) i++; //Skip "Convert"
        String base = tokens[i++];
        if(tokens.length == 5) i++; //Skip "to"
        String otherBase = tokens[i++];
        String value = tokens[i];
        Number n = new Number(value, base);
        Number converted = n.toBase(otherBase);
        return converted.toString();
    }
}
