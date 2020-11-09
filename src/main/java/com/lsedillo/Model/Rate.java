package com.lsedillo.Model;

public class Rate extends Unit{
    Data data;
    Time time;

    public Rate(double value, String unit) {
        super(value, unit);
        String dataUnit = unit.split("/")[0].toUpperCase() + "S";
        data = new Data(value, dataUnit);
        String timeUnit = unit.split("/")[1].toUpperCase();
        if(timeUnit.equals("S")) time = new Time(1.0, "second");
        else time = new Time(1.0, timeUnit);
    }

    public Rate(Data data, Time time) {
       this(data.getValue() / time.getValue(),data.getUnit() + "/" + time.getUnit());
    }

    @Override
    public Unit convertTo(String unit) {
        String newDataUnit = unit.substring(0, unit.indexOf("/")).toUpperCase() + "s";
        String newTimeUnit = unit.substring(unit.indexOf("/") + 1).toUpperCase();
        Time newTime = time.convertTo(newTimeUnit);
        Data newData = data.convertTo(newDataUnit);
        return new Rate(newData, newTime);
    }
}
