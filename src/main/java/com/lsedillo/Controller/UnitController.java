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

    static String convertMonthlyToBandwidth(String... tokens) {
        int i = 0;
        if(tokens.length >= 8) i+=5; //Skip past "Convert Monthly Usage to Bandwidth"
        double dataUnitsPerMonth = Double.parseDouble(tokens[i++]);
        String monthDataUnit = tokens[i++];
        String mysteryToken = tokens[i++];
        if(Character.isDigit(mysteryToken.charAt(0))) {
            double dataUnitsPerSecond = Double.parseDouble(mysteryToken);
            String secondRateUnit = tokens[i];
            Rate rate = new Rate(dataUnitsPerSecond, secondRateUnit);
            Rate perMonth = (Rate)rate.convertTo(monthDataUnit + "/month");
            return dataUnitsPerSecond + " " + secondRateUnit + " is equivalent to " + perMonth.toString();
        }
        String secondRateUnit = mysteryToken;
        Rate perMonth = new Rate(dataUnitsPerMonth, monthDataUnit + "/month");
        Rate perSecond = (Rate)perMonth.convertTo(secondRateUnit);
        return dataUnitsPerMonth + " " + monthDataUnit + " is equivalent to " + perSecond.toString();
    }
}
