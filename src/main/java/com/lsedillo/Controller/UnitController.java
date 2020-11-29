package com.lsedillo.Controller;

import com.lsedillo.Model.Data;
import com.lsedillo.Model.Rate;

public class UnitController {
    static String convertData(String... tokens) {
        int i = 0;
        if(tokens.length == 6) i+=4; //Skip past "Convert Data Unit To"
        String unitString = tokens[i++];
        Double dataAmount = Double.parseDouble(tokens[i]);

        Data data = new Data(dataAmount, "bits");
        Data converted = data.convertTo(unitString);
        return converted.toString();
    }

    /**
     * This method is unique because it may take three or four arguments in the input file.
     * In order to accommodate for this, I check if the third argument is a number or not. If it is,
     * this means that the method is converting from a per-second rate to a per-month rate. Otherwise,
     * the method is converting from a per-month rate to a per-second rate.
     * @param tokens
     * @return
     */
    static String convertMonthlyToBandwidth(String... tokens) {
        int i = 0;
        if(tokens.length >= 8) i+=5; //Skip past "Convert Monthly Usage to Bandwidth"
        double dataUnitsPerMonth = Double.parseDouble(tokens[i++]);
        String perMonthDataUnit = tokens[i++];
        String mysteryToken = tokens[i++]; //Either the number of per-second bandwidth units OR the per-second unit itself
        if(Character.isDigit(mysteryToken.charAt(0))) {
            double dataUnitsPerSecond = Double.parseDouble(mysteryToken);
            String perSecondRateUnit = tokens[i];
            Rate rate = new Rate(dataUnitsPerSecond, perSecondRateUnit);
            Rate perMonth = (Rate)rate.convertTo(perMonthDataUnit + "/month");
            return dataUnitsPerSecond + " " + perSecondRateUnit + " is equivalent to " + perMonth.toString();
        }
        String perSecondRateUnit = mysteryToken;
        Rate perMonth = new Rate(dataUnitsPerMonth, perMonthDataUnit + "/month");
        Rate perSecond = (Rate)perMonth.convertTo(perSecondRateUnit);
        return dataUnitsPerMonth + " " + perMonthDataUnit + " is equivalent to " + perSecond.toString();
    }
}
