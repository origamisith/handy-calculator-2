package com.lsedillo.Model;

public class Data extends Unit{
    private Double bits;
    private String niceName;

    public Data(Double value, String dataUnit) {
        super(value, parseName(dataUnit));
        this.bits = DataUnit.valueOf(getUnit()).bits;
        this.niceName = DataUnit.valueOf(getUnit()).name;
    }
    public Data(String value, String dataUnit) {
        this(Double.parseDouble(value), dataUnit);
    }
    private double getBits() {
        return bits;
    }

    public Data convertTo(String other) {
        DataUnit otherUnit = DataUnit.valueOf(parseName(other));
        Double newValue = getValue() * getBits() / otherUnit.bits;
        return new Data(newValue, other);
    }

    @Override
    public String toString() {
        return getValue() + niceName;
    }

    /**
     * This accessory method is necessary to convert unabbreviated bit units to abbreviated ones,
     * for compatibility with my enum.
     * @param dataUnit
     * @return
     */
    private static String parseName(String dataUnit) {
        String abbreviated = dataUnit.toUpperCase();
        //If the unit had 'y' it would be a byte unit, and not need to be changed.
        //If the unit had 'b' as the first or second character it would already be abbreviated
        if(abbreviated.indexOf('Y') == -1 && abbreviated.indexOf('B') > 1 ) {
            abbreviated  = abbreviated.charAt(0) + abbreviated.substring(abbreviated.indexOf('B'));
        }
        //If the last character isn't an 'S', add an 'S'
        if(!abbreviated.endsWith("S")) abbreviated += "S";
        return abbreviated.toUpperCase();
    }

    public enum DataUnit {

        BITS(1, "bit"),
        KBITS(1000, "Kbit"),
        MBITS(Math.pow(10, 6), "Mbit"),
        GBITS(Math.pow(10, 9), "Gbit"),
        TBITS(Math.pow(10, 12), "Tbit"),
        BYTES(8, "Byte"),
        KILOBYTES(8*Math.pow(10,3), "KB"),
        MEGABYTES(8*Math.pow(10,6), "MB"),
        GIGABYTES(8*Math.pow(10,9), "GB"),
        TERABYTES(8*Math.pow(10,12), "TB");

        public final double bits;
        public final String name;

        DataUnit(double atomicUnitAmount, String name) {
            this.bits = atomicUnitAmount;
            this.name = name;
        }
    }
}
