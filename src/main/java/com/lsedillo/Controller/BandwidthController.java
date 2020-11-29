package com.lsedillo.Controller;

import com.lsedillo.Model.Data;
import com.lsedillo.Model.Rate;
import com.lsedillo.Model.Time;

public class BandwidthController {
    /**
     * In order to ignore the words "download/upload time" from the input file syntax, if there are
     * six elements of <code>tokens</code>, the first variable stored will be the third element.
     * @param tokens
     * @return
     */
    static String downUpTime(String...tokens) {
        int i = 0;
        if(tokens.length == 7) i+=3; //skip "Calculate download/upload time"
        String fileSize = tokens[i++];
        String fileUnit = tokens[i++];
        String bandSize = tokens[i++];
        String bandUnit = tokens[i];
        Data data = new Data(fileSize, fileUnit);
        Rate rate = new Rate(bandSize, bandUnit);
        Time time = new Time(data, rate);
        return time.toString();
    }

    static String websiteBandwidth(String...tokens) {
        int i = 0;
        if(tokens.length == 9) i+=3; //skip "Calculate website calculator"
        double views = Double.parseDouble(tokens[i++]);
        if(tokens.length == 9) i++; //skip "per"
        String timeUnit = tokens[i++];
        double numberOfDataUnits = Double.parseDouble(tokens[i++]);
        String dataUnit = tokens[i++];
        double redundancy = Double.parseDouble(tokens[i]);

        Time time = new Time(1.0, timeUnit);
        Data data = new Data(views * numberOfDataUnits, dataUnit);
        Rate rate = new Rate(data, time);
        Rate perSecond= (Rate)rate.convertTo("Mbit/s");
        Rate perMonth = (Rate)rate.convertTo("gigabyte/month");

        String result = "Actual bandwidth need is " + perSecond.getValue() + " Mbits/s or "
                + perMonth.getValue() + "GB per month";
        result += "\nWith redundancy factor of "+ redundancy+ ", the bandwidth need is " + perSecond.getValue() * redundancy + " Mbits/s or " +
                perMonth.getValue() * redundancy +  " GB per month";
        return result;
    }

}
