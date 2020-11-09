package com.lsedillo.Model;

public class Time extends Unit{
    private Double seconds;
    private String niceName;

    public static final double SECOND = 1;
    public static final double MINUTE = 60;
    public static final double HOUR = 3600;
    public static final double DAY = 3600 * 24;
    public static final double WEEK = 3600*24*7;
    public static final double MONTH = 3600 * 24 * 30.436875;
    public static final double YEAR = 3600 * 24 * 365;

    public Time(Double value, String timeUnit) {
        super(value, timeUnit.toUpperCase());
        this.seconds = TimeUnit.valueOf(this.getUnit()).seconds;
        this.niceName = TimeUnit.valueOf(this.getUnit()).name;
    }

    private double getSeconds() {
        return seconds;
    }

    public Time convertTo(String other) {
        TimeUnit unit = TimeUnit.valueOf(getUnit());
        TimeUnit otherUnit = TimeUnit.valueOf(other);
        Double newValue = unit.seconds / otherUnit.seconds;
        return new Time(newValue, other);
    }

    /**
     * Converts a raw number of seconds into all the available time units
     * @param seconds The number of seconds
     * @return The human-readable time
     */
    public static String readableTime(double seconds) {
        StringBuilder result = new StringBuilder();
        int monthsLeft = readableAux((int)seconds, result, TimeUnit.YEAR);
        int weeksLeft = readableAux(monthsLeft, result, TimeUnit.MONTH);
        int daysLeft = readableAux(weeksLeft, result, TimeUnit.WEEK);
        int hoursLeft = readableAux(daysLeft, result, TimeUnit.DAY);
        int minutesLeft = readableAux(hoursLeft, result, TimeUnit.HOUR);
        int secondsLeft = readableAux(minutesLeft, result, TimeUnit.MINUTE);
        result.append((secondsLeft < 1) ? "" : secondsLeft + " " + TimeUnit.SECOND.name);
        return result.toString();
    }

    /**
     * An auxiliary method for the <code>readableTime</code> method for use on each time unit
     * @param seconds The raw number of seconds remaining
     * @param result The string of results that is growing by one time unit each time
     * @param unit The current time unit that is being processed
     * @return An integer representing the number of seconds left after some have been
     * reincarnated inside the result string
     */
    private static int readableAux(int seconds, StringBuilder result, TimeUnit unit) {
        int q = seconds / (int)unit.seconds;
        int r = seconds % (int)unit.seconds;
        result.append((q < 1) ? "" : q + " " + unit.name + " ");
        return r;
    }

    public enum TimeUnit {
        SECOND(1, "seconds"),
        MINUTE(60, "minutes"),
        HOUR(3600, "hours"),
        DAY(3600 * 24, "days"),
        WEEK(3600*24*7, "weeks"),
        MONTH(3600 * 24 * 30.436875, "months"),
        YEAR(3600 * 24 * 365, "years");

        public double seconds;
        public String name;

        /**
         * Constructor that takes a value in seconds and a string name
         * @param seconds The number of seconds
         * @param name The name
         */
        TimeUnit(double seconds, String name) {
            this.seconds = seconds;
            this.name = name;
        }
    }
}

