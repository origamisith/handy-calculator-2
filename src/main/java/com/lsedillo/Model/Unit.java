package com.lsedillo.Model;

import java.util.Objects;

public abstract class Unit implements UnitConvertible{
    private double value;
    String unit;

    Unit(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {return value;}
    public String getUnit() {return unit;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit1 = (Unit) o;
        return Double.compare(unit1.value, value) == 0 &&
                Objects.equals(unit, unit1.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }
}
