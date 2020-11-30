package com.lsedillo.Model;

public class Rate extends Unit{
    private Data data;
    private Time time;

    public Rate(double value, String unit) {
        super(value, unit);
        String dataUnit = unit.split("/")[0]/*.toUpperCase()*/;
        data = new Data(value, dataUnit);
        String timeUnit = unit.split("/")[1]/*.toUpperCase()*/;
        if(timeUnit.equals("s")) time = new Time(1.0, "second");
        else time = new Time(1.0, timeUnit);
    }

    public Rate(String value, String unit) {
        this(Double.parseDouble(value), unit);
    }

    public Rate(Data data, Time time) {
       this(data.getValue() / time.getValue(),data.getUnit() + "/" + time.getUnit());
    }

    @Override
    public String toString() {
        return getValue() + " " + getUnit();
    }

    public Data getData() {
        return data;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public Unit convertTo(String unit) {
        String newDataUnit = unit.substring(0, unit.indexOf("/"))/*.toUpperCase()*/;
        String newTimeUnit = unit.substring(unit.indexOf("/") + 1)/*.toUpperCase()*/;
        if(newTimeUnit.equalsIgnoreCase("s")) newTimeUnit = "second";
        Time newTime = time.convertTo(newTimeUnit);
        Data newData = data.convertTo(newDataUnit);
        return new Rate(newData, newTime);
    }

    public static void main(String[] args) {

    }
}
