package com.lsedillo.Model;

/**
 *Responsible for calculations that involve bandwidth. Makes heavy use of my unit and number base classes.
 */
//public class Bandwidth {
//
//    /**
//     * Calculates the download / upload time for a file given the following parameters:
//     * @param file The File object on which to operate on
//     * @param rate The Rate object on which to operate on
//     * @return The time required to download or upload the given file for the given bandwidth
//     */
//    public static Time downUpTime(Data file, Rate rate) {
//
//    }
//
//    /**
//     * Calculates the bandwidth required for a website given the following parameters:
//     * @param views The average number of views for the specified time period
//     * @param time The time period over which the views are specified.
//     * @param pageSize The average page size, measured in a specified unit of measure
//     * @param sizeUnit The unit with which the page size is measured
//     * @param redundancy A multiplier. If a redundancy of 2 is specified, the required bandwidth
//     *                   will be doubled.
//     * @return The bandwidth required, measured in Mbit/s and GB/mo
//     */
//    public static String webBandwidth(double views, TimeUnits time, double pageSize, DataUnits sizeUnit, double redundancy) {
//        double seconds = TimeUnits.convert(time, TimeUnits.SECOND);
//        double months = TimeUnits.convert(time, TimeUnits.MONTH);
//        double megabits = DataUnits.convert(pageSize, sizeUnit, DataUnits.MBITS);
//        double gigabytes = DataUnits.convert(pageSize, sizeUnit, DataUnits.GIGABYTES);
//        String result = "Actual bandwidth need is " + (views * megabits / seconds) + " Mbits/s or "
//                + (views * gigabytes / months) + "GB per month";
//        result += "\nWith redundancy factor of "+ redundancy + ", the bandwidth need is " + (views * megabits * redundancy) / seconds + "Mbits/s or" +
//                (views * gigabytes * redundancy) / months + " GB per month";
//        return result;
//    }
//
//    /**
//     * Converts between monthly usage and bandwidth. To calculate bandwidth, specify the amount of
//     * bandwidth as -1. To calculate monthly usage, specify the amount of monthly usage as -1.
//     * Otherwise, the program will throw an error.
//     * @param dataSize The amount of monthly data usage. Must specify as -1 if trying to calculate
//     *                 monthly data usage from bandwidth.
//     * @param dataUnit The unit with which the data is measured. Always specify this
//     * @param bandSize The amount of bandwidth. Must specify as -1 if trying to calculate bandwidth
//     *                 from monthly data usage
//     * @param bandwidthUnit The unit with which bandwidth is measured. Always specify this.
//     * @return Either the amount of monthly data usage given a bandwidth, or the amount of bandwidth
//     * given a monthly usage.
//     */
//    public static String hostBandwidth(double dataSize, DataUnits dataUnit, double bandSize, DataUnits bandwidthUnit) {
//        if (bandSize == -1) {
//            double newData = DataUnits.convert(dataSize, dataUnit, bandwidthUnit);
//            double newTime = TimeUnits.convert(TimeUnits.MONTH, TimeUnits.SECOND);
//            double result = newData / newTime;
//            return dataSize + " " + dataUnit.name + " per month is equivalent to "
//                    + result + " " + bandwidthUnit.name + "/s.";
//        } else if (dataSize == -1) {
//            double newData = DataUnits.convert(bandSize, bandwidthUnit, dataUnit);
//            double newTime = TimeUnits.convert(TimeUnits.SECOND, TimeUnits.MONTH);
//            double result = newData / newTime;
//            return bandSize + " " + bandwidthUnit.name + "/s" + " is equivalent to " +
//                    result + " " + dataUnit.name + " per month.";
//        }
//        return "Please specify either the monthly usage or the bandwidth as -1";
//        }


//    public static void main(String[] args) {
//        System.out.println(downUpTime(500, DataUnits.MEGABYTES, 5, DataUnits.MBITS));
//        System.out.println(webBandwidth(455, TimeUnits.HOUR, 10, DataUnits.MEGABYTES, 3));
//        System.out.println(hostBandwidth(-1, DataUnits.MEGABYTES, 8, DataUnits.KBITS));
//
//    }
//}
