package com.lsedillo.Model;

public class Data extends Unit{
    private double value;
    private String dataUnit;
    private Double atomicUnitAmount;
    private String niceName;

    public Data(Double value, String dataUnit) {
        super(value, dataUnit);
        this.atomicUnitAmount = DataUnit.valueOf(dataUnit).atomicUnitAmount;
        this.niceName = DataUnit.valueOf(dataUnit).name;
    }
    public double getAtomicUnitAmount() {
        return atomicUnitAmount;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return niceName;
    }
    public Data convertTo(String other) {
        DataUnit unit = DataUnit.valueOf(dataUnit);
        DataUnit otherUnit = DataUnit.valueOf(other);
        Double newValue = unit.atomicUnitAmount / otherUnit.atomicUnitAmount;
        return new Data(newValue, other);
    }
}
