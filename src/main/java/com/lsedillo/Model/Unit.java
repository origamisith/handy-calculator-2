package com.lsedillo.Model;

public abstract class Unit implements UnitConvertible{
    double value;
    double atomicUnitAmount;
    String unit;

    Unit(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {return value;}
    public String getUnit() {return unit;}

    public double getAtomicUnitAmount() { return atomicUnitAmount; }

}
