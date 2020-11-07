package com.lsedillo.Model;

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

    public final double atomicUnitAmount;
    public final String name;

    DataUnit(double atomicUnitAmount, String name) {
        this.atomicUnitAmount = atomicUnitAmount;
        this.name = name;
    }


    /**
     * Converts between units by comparing their equivalent sizes in bits
     * @param num The number to convert from
     * @param from The unit to convert from
     * @param to The unit to convert to
     * @return The converted number.
     */
    public static double convert(double num, DataUnit from, DataUnit to) {
        return (num * from.atomicUnitAmount) / to.atomicUnitAmount;
    }
}
